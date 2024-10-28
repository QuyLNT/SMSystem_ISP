/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class SearchOrderController extends HttpServlet {

    private static final String ERROR = "myOrder.jsp";
    private static final String SUCCESS = "myOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        OrderDAO orderDao = new OrderDAO();

        try {
            String orderIdStr = request.getParameter("OrderID");
            List<OrderDTO> orderList;

            if (orderIdStr == null || orderIdStr.isEmpty()) {
                // Nếu không có OrderID, lấy tất cả đơn hàng
                orderList = orderDao.getAllOrdersByCustomerId(user.getUserId());
            } else {
                // Nếu có OrderID, tìm kiếm theo OrderID
                int orderId = Integer.parseInt(orderIdStr);
                OrderDTO order = orderDao.getOrderById(user, orderId);

            if (order != null) {
                    orderList = new ArrayList<>();
                orderList.add(order);
            } else {
                    orderList = new ArrayList<>();
                session.setAttribute("ms", "You do not have an order with Order ID: " + orderIdStr);
            }
            }

            // Lưu danh sách đơn hàng vào session để hiển thị trong JSP
            session.setAttribute("ORDER_LIST", orderList);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchOrderController: " + e.toString());
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
