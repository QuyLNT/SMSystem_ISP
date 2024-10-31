/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.cart.CartDAO;
import model.cart.CartDTO;
import model.cart.CartItems;
import model.discount.DiscountDTO;
import model.order.OrderDAO;
import model.order.OrderDTO;
import model.order.OrderDetailDAO;
import model.product.ProductVariantDAO;
import model.user.UserDTO;

/**
 *
 * @author LENOVO
 */
public class CheckoutController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            DiscountDTO code = (DiscountDTO) session.getAttribute("code");
            String discountStr;
            Integer discount = null;
            boolean checkInsert = false;
            String street = request.getParameter("street");
            String district = request.getParameter("districtName");
            String city = request.getParameter("provinceName");
            if (code != null) {
                discountStr = request.getParameter("discount");
                discount = Integer.parseInt(discountStr);
            }
            int payMethod = Integer.parseInt(request.getParameter("pay"));
            int shipMethod = Integer.parseInt(request.getParameter("ship"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            String totalStr = request.getParameter("total");
            Float total = Float.parseFloat(totalStr);
            String formattedNumber = String.format("%.1f", total);
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            CartDAO cartDao = new CartDAO();
            OrderDAO orderDao = new OrderDAO();
            ProductVariantDAO variantDao = new ProductVariantDAO();
            OrderDetailDAO orderDetailDao = new OrderDetailDAO();
            CartDTO cart = (CartDTO) session.getAttribute("SELECTED_CART");
            if (cart != null) {
                int insertedOrderId = orderDao.createOrder(userId, street, district, city, discount, payMethod, shipMethod);
                if (insertedOrderId != 0) {
                    for (CartItems item : cart.getCartItemsList()) {
                        int pId = item.getProduct().getProductId();
                        int quantity = item.getQuantity();
                        float size = item.getSize();
                        checkInsert = orderDetailDao.insertOrderDetail(pId, insertedOrderId, quantity, size);
                        variantDao.updateStock(pId, size, quantity);
                    }
                    OrderDTO order = new OrderDTO();
                    order.setOrderId(insertedOrderId);
                    order.setCustomer(loginUser);
                    order.setStreet(street);
                    order.setDistrict(district);
                    order.setCity(city);
                    if (code != null) {
                        order.setDiscountCode(code.getDiscountCode());
                    }
                    order.setTotalPrice(Float.parseFloat(formattedNumber));
                    request.setAttribute("ORDER", order);
                }
                boolean checkDelete = cartDao.removeSelectedItem(cart.getCartId());
            }

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            log("Error at CheckoutController: " + e.toString());
        } finally {
            if (request.getParameter("pay").equals("1")) {
                request.getRequestDispatcher("/PaymentController").forward(request, response);
            } else {
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }
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
