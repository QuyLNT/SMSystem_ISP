/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.cart.CartDAO;
import model.cart.CartDTO;
import model.cart.CartItems;

/**
 *
 * @author Asus
 */
public class EditSizeController extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String cartItemIdRaw = request.getParameter("cartItemId");
            String sizeRaw = request.getParameter("size");

            // Kiểm tra tính hợp lệ của tham số
            if (cartItemIdRaw == null || sizeRaw == null) {
                request.setAttribute("ERROR_MESSAGE", "Missing cart item ID or size.");
            } else {
                int cartItemId = Integer.parseInt(cartItemIdRaw);
                float size = Float.parseFloat(sizeRaw);

                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if (cart != null) {
                    List<CartItems> cartItemsList = cart.getCartItemsList();
                    CartDAO cartDAO = new CartDAO();
//                    boolean itemFound = false;

                    for (CartItems item : cartItemsList) {
                        if (item.getCartItemId() == cartItemId) {
                            item.setSize(size);
                            boolean isUpdated = cartDAO.updateSize(cartItemId, sizeRaw);
                            if (isUpdated) {
                                session.setAttribute("CART", cart);
                                url = SUCCESS;
                            } else {
                                request.setAttribute("ERROR_MESSAGE", "Failed to update size in database.");
                            }
//                            itemFound = true;
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            request.setAttribute("ERROR_MESSAGE", "Invalid input format.");
            e.printStackTrace();

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
        } catch (SQLException ex) {
            Logger.getLogger(EditSizeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditSizeController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(EditSizeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditSizeController.class.getName()).log(Level.SEVERE, null, ex);
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
