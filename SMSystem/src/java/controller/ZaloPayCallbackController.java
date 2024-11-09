/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import model.payment.OnlinePaymentDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author LENOVO
 */
public class ZaloPayCallbackController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ZaloPayCallbackController.class.getName());
    private Mac HmacSHA256;
    private static final Map<String, String> CONFIG = new HashMap<String, String>() {
        {
            put("app_id", "2554");
            put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
            put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
            put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
        }
    };

    @Override
    public void init() throws ServletException {
        try {
            HmacSHA256 = Mac.getInstance("HmacSHA256");
            HmacSHA256.init(new SecretKeySpec(CONFIG.get("key2").getBytes(), "HmacSHA256"));
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new ServletException("Error initializing HmacSHA256", e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject result = new JSONObject();
        response.setContentType("application/json");

        // Đọc dữ liệu JSON từ yêu cầu callback
        StringBuilder jsonBuffer = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        try {
            JSONObject cbdata = new JSONObject(jsonBuffer.toString());
            String dataStr = cbdata.getString("data");
            String reqMac = cbdata.getString("mac");

            // Tạo mã MAC từ dataStr và so sánh với reqMac
            byte[] hashBytes = HmacSHA256.doFinal(dataStr.getBytes());
            String mac = DatatypeConverter.printHexBinary(hashBytes).toLowerCase();

            // Xác minh callback từ ZaloPay
            if (!reqMac.equals(mac)) {
                result.put("returncode", -1);
                result.put("returnmessage", "mac not equal");
            } else {
                // Callback hợp lệ: xử lý logic cập nhật trạng thái đơn hàng
                JSONObject data = new JSONObject(dataStr);
                String appTransId = data.getString("app_trans_id");

                OnlinePaymentDAO payDao = new OnlinePaymentDAO();
                String status = "Success";
                boolean updateStatus = false;
                updateStatus = payDao.updatePayStatus(appTransId, status);

                result.put("returncode", 1);
                result.put("returnmessage", "success");
            }
        } catch (ClassNotFoundException | SQLException | IllegalStateException | JSONException ex) {
            result.put("returncode", 0); // ZaloPay server sẽ callback lại nếu trả về mã này
            result.put("returnmessage", ex.getMessage());
        }

        response.getWriter().write(result.toString());

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
