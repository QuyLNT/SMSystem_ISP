/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import model.order.OrderDTO;
import model.payment.OnlinePaymentDAO;
import model.payment.OnlinePaymentDTO;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.HMACUtil;

public class PaymentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    private static final Map<String, String> CONFIG = new HashMap<String, String>() {
        {
            put("app_id", "2554");
            put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
            put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
            put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
        }
    };

    public static String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Random rand = new Random();
            int random_id = rand.nextInt(1000000);
            OrderDTO userOrder = (OrderDTO) request.getAttribute("ORDER");

            String app_trans_id = getCurrentTimeString("yyMMdd") + "_" + random_id;
            String description = "SMSystem - Payment for the order #" + userOrder.getOrderId();
            float amount = userOrder.getTotalPrice() * 24000;
            OnlinePaymentDTO payment = new OnlinePaymentDTO();
            payment.setAmount(amount);
            payment.setPaymentId(app_trans_id);
            payment.setOrderId(userOrder.getOrderId());
            payment.setDescription(description);
            OnlinePaymentDAO payDao = new OnlinePaymentDAO();
            boolean checkInsert = payDao.createPayment(payment);

            final Map embed_data = new HashMap() {
                {
                    put("redirecturl", "https://a104-123-21-145-218.ngrok-free.app/SMSystem/success.jsp");
                }
            };

            final Map<String, Object>[] item = new Map[]{
                new HashMap<String, Object>() {
                    {
                        put("itemid", userOrder.getOrderId());
                    }
                }
            };
            Map<String, Object> order = new HashMap<String, Object>() {
                {
                    put("app_id", CONFIG.get("app_id"));
                    put("app_trans_id", app_trans_id);
                    put("app_time", System.currentTimeMillis());
                    put("app_user", "user123");
                    put("amount", (long) amount);
                    put("description", description);
                    put("bank_code", "");
                    put("callback_url", "https://a104-123-21-145-218.ngrok-free.app/SMSystem/ZaloPayCallbackController");
                    put("item", new JSONArray(item).toString());
                    put("embed_data", new JSONObject(embed_data).toString());
                }
            };

            // app_id +”|”+ app_trans_id +”|”+ appuser +”|”+ amount +"|" + app_time +”|”+ embed_data +"|" +item
            String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" + order.get("app_user") + "|" + order.get("amount")
                    + "|" + order.get("app_time") + "|" + order.get("embed_data") + "|" + order.get("item");
            String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, CONFIG.get("key1"), data);
            order.put("mac", mac);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(CONFIG.get("endpoint"));

            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, Object> e : order.entrySet()) {
                params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
            }

            // Content-Type: application/x-www-form-urlencoded
            post.setEntity(new UrlEncodedFormEntity(params));

            CloseableHttpResponse res = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
            StringBuilder resultJsonStr = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                resultJsonStr.append(line);
            }

            JSONObject result = new JSONObject(resultJsonStr.toString());
            if (result.has("order_url")) {
                String orderUrl = result.getString("order_url");

                response.sendRedirect(orderUrl);
            } else {
                System.out.println("Không tìm thấy order_url trong phản hồi từ API.");
                response.getWriter().write(result.toString());
            }
        } catch (ClassNotFoundException | SQLException | IOException | JSONException e) {
            log("Error at PaymentController: " + e.toString());
        }
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
