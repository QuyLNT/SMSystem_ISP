/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.discount.DiscountDAO;
import model.discount.DiscountDTO;
import model.order.OrderDAO;
import model.order.OrderDTO;
import model.order.OrderDetailDAO;
import model.order.OrderDetailDTO;
import model.product.ProductImageDAO;

/**
 *
 * @author CHAU DUYEN
 */
public class LoadOrderDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "orderDetail.jsp";
    private static final String SUCCESS = "orderDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            int orderId = Integer.parseInt(request.getParameter("orderId"));
            OrderDAO orderDao = new OrderDAO();
            DiscountDAO discountDAO = new DiscountDAO();
            OrderDetailDAO orderDetailDao = new OrderDetailDAO();
            ProductImageDAO imageDao = new ProductImageDAO();

            OrderDTO order = orderDao.getOrderById(orderId);
            List<OrderDetailDTO> orderDetails = orderDetailDao.getOrderDetailListByOrderID(orderId);
            if (order != null && orderDetails != null) {
                for (OrderDetailDTO detail : orderDetails) {
                    detail.getProduct().setListImages(imageDao.getImageByProduct(detail.getProduct().getProductId()));
                }
                float total = 0;
                for (OrderDetailDTO detail : orderDetails) {
                    if (detail.getProduct() != null) {
                        float salePrice = detail.getQuantity() * (detail.getProduct().getPrice() * (1 - detail.getProduct().getSale()));
                        total += salePrice;
                    }
                }
                if (order.getDiscountCode() != null) {
                    DiscountDTO dc = discountDAO.getDiscountByCode(order.getDiscountCode());
                    if (dc != null) {
                        total = total * (1 - dc.getDiscountAmount());
                    }
                }
                order.setTotalPrice(total);
                request.setAttribute("ORDER", order);
                request.setAttribute("ORDER_DETAILS", orderDetails);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at LoadOrderDetailController: " + e.toString());
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