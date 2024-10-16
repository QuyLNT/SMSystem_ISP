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
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.user.UserDTO;

/**
 *
 * @author dell
 */
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "shopping-cart.jsp";
    private static final String SUCCESS = "shopping-cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String url = ERROR;

    try {
        int productId = Integer.parseInt(request.getParameter("pId"));
        int quantity = Integer.parseInt(request.getParameter("qnt"));
        String size_raw = request.getParameter("size");
        Float size = Float.parseFloat(size_raw);

        ProductDAO productDao = new ProductDAO();
        ProductDTO product = productDao.getProductById(productId);

        HttpSession session = request.getSession();
        CartDTO cart = (CartDTO) session.getAttribute("CART");

        // Nếu giỏ hàng chưa có, tạo mới
        if (cart == null) {
            cart = new CartDTO();
        }

        // Kiểm tra sản phẩm còn hàng không
        if (quantity > product.getTotalStock()) {
            request.setAttribute("err", "Out of stock. Only " + product.getTotalStock() + " product .");
        } else {
            CartDAO cartDao = new CartDAO();
            int customerId = ((UserDTO) session.getAttribute("LIST_USER")).getUserId();

            // Tạo giỏ hàng nếu chưa có và lấy cartId
            int cartId = cartDao.createCart(customerId);
            cart.setCartId(cartId);

            // Thêm sản phẩm vào giỏ hàng
            if (cart.existedProduct(productId, size)) {
                CartItems existingItem = new CartItems(product, 0, cartId, productId, quantity, size);
                cart.addItemExist(existingItem);
            } else {
                CartItems newItem = new CartItems(product, 0, cartId, productId, quantity, size);
                cartDao.addCartItem(cartId, productId, quantity, size);
                cart.addItem(newItem);
            }

            session.setAttribute("CART", cart);
            session.setAttribute("size", String.valueOf(cart.getSize()));
            request.setAttribute("ms", "Purchase successful! Check cart and checkout.");
            url = SUCCESS;
        }
    } catch (Exception e) {
        log("Error at AddToCartController: " + e.toString());
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
