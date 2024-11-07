/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
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
import model.product.ProductVariantDAO;
import model.product.ProductVariantDTO;
import model.user.UserDTO;
import model.product.ProductImageDAO;

/**
 *
 * @author dell
 */
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "product.jsp";
    private static final String SUCCESS = "product.jsp";

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
            ProductImageDAO imageDao = new ProductImageDAO();
            product.setListImages(imageDao.getImageByProduct(productId));
            ProductVariantDAO variantDao = new ProductVariantDAO();
            List<ProductVariantDTO> availableVariants = variantDao.getVariantByProduct(productId);

            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {
                int customerId = user.getUserId();

                if (cart == null) {
                    cart = new CartDTO();
                }

                ProductVariantDTO selectedVariant = null;
                for (ProductVariantDTO variant : availableVariants) {
                    if (variant.getProductId() == productId && variant.getSize() == size) {
                        selectedVariant = variant;
                        break;
                    }
                }

                if (selectedVariant == null) {
                    request.setAttribute("err", "This size is not available for the selected product.");
                } else if (quantity > selectedVariant.getStock()) {
                    request.setAttribute("err", "Out of stock for this size. Only " + selectedVariant.getStock() + " items available.");
                } else {
                    CartItems existingItem = cart.getItemByProductIdAndSize(productId, size);
                    if (existingItem != null) {
                        int newQuantity = existingItem.getQuantity() + quantity;
                        if (newQuantity > selectedVariant.getStock()) {
                            request.setAttribute("err", "Out of stock for this size. Only " + selectedVariant.getStock() + " items available.");
                        } else {
                            existingItem.setQuantity(newQuantity);
                            request.setAttribute("ms", "Quantity updated successfully!");
                        }
                    } else {
                        CartDAO cartDao = new CartDAO();
                        int cartId = cartDao.createCartIfNotExists(customerId);
                        cartDao.addCartItem(cartId, productId, quantity, size);
                        cart = cartDao.getCartByUserId(customerId);
                        for (CartItems c : cart.getCartItemsList()) {
                            c.getProduct().setListImages(imageDao.getImageByProduct(c.getProduct().getProductId()));
                            c.getProduct().setListVariants(variantDao.getVariantByProduct(c.getProduct().getProductId()));
                        }
                        session.setAttribute("CART", cart);
                        session.setAttribute("size", String.valueOf(cart.getSize()));
                        request.setAttribute("ms", "Product added to cart successfully!");
                    }
                    url = SUCCESS;
                }
            } else {
                url = "login.jsp";
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
