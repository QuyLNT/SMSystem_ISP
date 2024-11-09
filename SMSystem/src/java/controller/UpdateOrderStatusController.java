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
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Asus
 */
public class UpdateOrderStatusController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "orderList.jsp";
    private static final String SUCCESS = "orderList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            String status = request.getParameter("status");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            OrderDAO d = new OrderDAO();
            boolean check = d.updateOrderStatus(status, orderId);
            if (check && loginUser.getRoleId().equals("MN")) {
                if ("Not Completed".equals(status)) {
                    d.returnItemsToStock(orderId);
                }
                List<OrderDTO> orderList = (List<OrderDTO>) session.getAttribute("ORDER_LIST");
                for (OrderDTO o : orderList) {
                    if (o.getOrderId() == orderId) {
                        o.setOrderStatus(status);
                    }
                }

                request.setAttribute("ORDER_LIST", orderList);
                request.setAttribute("ms", "Update Successfully");
                request.setAttribute("orderId", orderId);

                url = SUCCESS;

            } else if (check && loginUser.getRoleId().equals("SP")) {
                request.setAttribute("ms", "Update Order "+ orderId +" Successfully");
                url = "shipList.jsp";
            } else {
                request.setAttribute("ms", "Something wrong at sever");
            }
        } catch (SQLException e) {
            log("Error at Update Order Controller:" + e.toString());
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateOrderStatusController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateOrderStatusController.class.getName()).log(Level.SEVERE, null, ex);
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
