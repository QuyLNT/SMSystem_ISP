/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.cart.CartDAO;
import model.cart.CartDTO;
import model.cart.CartItems;
import model.product.ProductDTO;
import model.product.ProductVariantDAO;

/**
 *
 * @author Asus
 */
public class EditQuantityController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "shopping-cart.jsp";
    private static final String SUCCESS = "shopping-cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            // Lấy cartItemId và số lượng từ request
            int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
            int num = Integer.parseInt(request.getParameter("num"));

            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");

            if (cart != null) {
                CartDAO dao = new CartDAO();
                ProductVariantDAO variantDao = new ProductVariantDAO();
                // Duyệt qua các items trong giỏ hàng
                for (CartItems item : cart.getCartItemsList()) {
                    if (item.getCartItemId() == cartItemId) { // So sánh cartItemId
                        int newQuantity = item.getQuantity() + num; // Tăng hoặc giảm số lượng
                        // Đảm bảo số lượng mới không âm
                        if (newQuantity > 0) {
                            // Cập nhật số lượng mới vào database bằng CartDAO
                            boolean checkValidate = variantDao.checkValidateStock(item.getProduct().getProductId(),
                                    item.getSize(), newQuantity);
                            if (checkValidate) {
                                boolean updated = dao.editQuantity(item.getCartItemId(), newQuantity);
                                if (updated) {
                                    item.setQuantity(newQuantity);
                                    session.setAttribute("CART", cart); // Cập nhật lại giỏ hàng trong session
                                    url = SUCCESS;
                                }
                            } else {
                                request.setAttribute("err", "Sorry, our stock quantity is insufficient to add the requested amount.");
                            }
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            log("Error at EditQuantityController: " + e.getMessage());
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
