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
import javax.servlet.http.HttpSession;
import model.order.OrderDAO;
import model.order.OrderDTO;
import model.user.UserDAO;
import model.user.UserDTO;

/**
 *
 * @author dell
 */
public class AssignShipperController extends HttpServlet {

    private static final String ERROR = "orderList.jsp";
    private static final String SUCCESS = "orderList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int shipperId = Integer.parseInt(request.getParameter("shipperId"));
            String ship = request.getParameter("ship");

            OrderDAO orderDAO = new OrderDAO();
            boolean isAssigned = orderDAO.assignShipperToOrder(orderId, shipperId, ship);

            UserDAO userDAO = new UserDAO();
            UserDTO shipper = userDAO.getUserById(shipperId);
            String shipperName = (shipper != null) ? shipper.getFullName() : "Unknown Shipper";

            if (isAssigned) {
                request.setAttribute("message", "Shipper " + shipperName + " has been successfully assigned to Order ID " + orderId + ".");

                HttpSession session = request.getSession();
                List<OrderDTO> orderList = (List<OrderDTO>) session.getAttribute("ORDER_LIST");
                Map<Integer, Integer> shipperMap = orderDAO.getShipperMap();

                List<UserDTO> shippers = userDAO.getAllShippers();


                request.setAttribute("ORDER_LIST", orderList);
                request.setAttribute("SHIPPER_LIST", shippers);
                request.setAttribute("SHIPPER_MAP", shipperMap);

                url = SUCCESS;
            } else {
                request.setAttribute("message", "Assign failed shipper to Order ID " + orderId + ".");
            }
        } catch (Exception e) {
            log("Error at AssignShipperController: " + e.toString());
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
