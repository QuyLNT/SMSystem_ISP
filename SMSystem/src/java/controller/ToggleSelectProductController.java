/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author LENOVO
 */
public class ToggleSelectProductController extends HttpServlet {

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
    private static final String SUCCES = "shopping-cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String cartItemIdStr = request.getParameter("cartItemId");
            int isSelectedInt = Integer.parseInt(request.getParameter("isSelected"));
            boolean isSelected = false;
            if (cartItemIdStr != null && !cartItemIdStr.isEmpty()) {
                int cartItemId = Integer.parseInt(cartItemIdStr);
                if (isSelectedInt == 1) {
                    isSelected = true;
                }
                CartDAO cartDao = new CartDAO();
                boolean checkUpdate = cartDao.updateSelectedItem(cartItemId, isSelected);

                HttpSession session = request.getSession();
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if (checkUpdate) {
                    for (CartItems item : cart.getCartItemsList()) {
                        if (item.getCartItemId() == cartItemId) {
                            item.setIsSelected(isSelected);
                        }
                    }
                    session.setAttribute("CART", cart);
                    url = SUCCES;
                } 
            }
        } catch (NumberFormatException|ClassNotFoundException|SQLException e) {
            log("Error at ToggleFlashSaleController: " + e.toString());
        }finally {
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
