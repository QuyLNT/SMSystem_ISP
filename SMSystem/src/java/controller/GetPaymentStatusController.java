/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.payment.OnlinePaymentDAO;
import model.payment.OnlinePaymentDTO;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import utils.HMACUtil;

/**
 *
 * @author LENOVO
 */
public class GetPaymentStatusController extends HttpServlet {

    private static final Map<String, String> config = new HashMap<String, String>() {
        {
            put("app_id", "2554");
            put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
            put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
            put("endpoint", "https://sb-openapi.zalopay.vn/v2/query");
        }
    };

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String app_trans_id = request.getParameter("transId");
        String data = config.get("app_id") + "|" + app_trans_id + "|" + config.get("key1"); // appid|app_trans_id|key1
        String mac;
        
        try {
            mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data);
        } catch (Exception e) {
            throw new ServletException("Error while generating HMAC", e);
        }

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("app_id", config.get("app_id")));
        params.add(new BasicNameValuePair("app_trans_id", app_trans_id));
        params.add(new BasicNameValuePair("mac", mac));

        URIBuilder uri;
        try {
            uri = new URIBuilder(config.get("endpoint"));
            uri.addParameters(params);
        } catch (URISyntaxException e) {
            throw new ServletException("Error while building URI", e);
        }

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(uri.build());
            post.setEntity(new UrlEncodedFormEntity(params));

            try (CloseableHttpResponse res = client.execute(post)) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
                StringBuilder resultJsonStr = new StringBuilder();
                String line;

                while ((line = rd.readLine()) != null) {
                    resultJsonStr.append(line);
                }

                JSONObject result = new JSONObject(resultJsonStr.toString());
                String status;
                int statusCode = result.getInt("return_code");
                switch (statusCode) {
                    case 1:
                        status = "Success";
                        break;
                    case 2:
                        status = "Failed";
                        break;
                    case 3:
                        status = "Unpaid/In processing";
                        break;
                    default:
                        status = "Pending";
                        break;
                }
                OnlinePaymentDAO payDao = new OnlinePaymentDAO();
                boolean checkUpdate = payDao.updatePayStatus(app_trans_id, status);
                if(checkUpdate){
                    List<OnlinePaymentDTO> payList = payDao.getAll();
                    HttpSession session = request.getSession();
                    session.setAttribute("PAY_LIST", payList);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Error while making HTTP request", e);
        }finally{
            request.getRequestDispatcher("paymentList.jsp").forward(request, response);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
