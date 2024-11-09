/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.order.OrderDAO;
import model.order.OrderDTO;
import model.user.UserDTO;

/**
 *
 * @author dell
 */
public class LoadMyOrderController extends HttpServlet {

    private static final String ERROR = "myOrder.jsp";
    private static final String SUCCESS = "myOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {
                OrderDAO orderDAO = new OrderDAO();
                List<OrderDTO> orderList = orderDAO.getAllOrder(user);
                Map<String, Integer> orderStatusCount = orderDAO.getOrderStatusCount(user.getUserId());
                if (orderList != null && !orderList.isEmpty() && orderStatusCount != null) {
                    for (OrderDTO d : orderList) {
                        d.setTotalPrice(orderDAO.getOrderTotal(d.getOrderId()));
                    }
                    session.setAttribute("STATUS_COUNT", orderStatusCount);
                    session.setAttribute("MY_ORDERS", orderList);
                    url = SUCCESS;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at LoadMyOrderController: " + e.toString());
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
