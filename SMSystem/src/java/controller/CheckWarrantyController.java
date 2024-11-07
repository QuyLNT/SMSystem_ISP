/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.order.OrderDetailDAO;
import model.user.UserDAO;

/**
 *
 * @author LENOVO
 */
public class CheckWarrantyController extends HttpServlet {

    private static final String ERROR = "warrantyPage.jsp";
    private static final String SUCCESS = "warrantyPage.jsp";
    private static final String PHONE_REGEX = "^(?:\\+84|0)(3[2-9]|5[6|8|9]|7[0|6|7|8|9]|8[1-9]|9[0-9])[0-9]{7}$";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String phoneNumber = request.getParameter("phone");
            if (phoneNumber != "") {
                UserDAO userDao = new UserDAO();
                boolean checkValidation = true;
                if (!phoneNumber.matches(PHONE_REGEX)) {
                    request.setAttribute("err", "This phone number is invalid");
                    checkValidation = false;
                }
                if (checkValidation) {
                    boolean checkExistPhone = userDao.checkPhoneExists(phoneNumber);
                    if (checkExistPhone) {
                        OrderDetailDAO orderDao = new OrderDetailDAO();
                        List<Map<String, Object>> warrantyList = orderDao.checkWarrant(phoneNumber);
                        if (warrantyList == null) {
                            request.setAttribute("err", "No products found in warranty list.");
                        } else {
                            request.setAttribute("WARRANTY_LIST", warrantyList);
                            url = SUCCESS;
                        }
                    } else {
                        request.setAttribute("err", "This phone number is not in the warranty list.");
                    }
                }
            } else {
                request.setAttribute("err", "Enter your phone number to check product warranty.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at CheckWarrantyController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
