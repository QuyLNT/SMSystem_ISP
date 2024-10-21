/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.discount.DiscountDAO;
import model.discount.DiscountDTO;

/**
 *
 * @author dell
 */
public class ApplyDiscountController extends HttpServlet {

    private static final String ERROR = "shopping-cart.jsp";
    private static final String SUCCESS = "shopping-cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String discountCode = request.getParameter("code");
            DiscountDAO dao = new DiscountDAO();
            DiscountDTO discount = dao.ApplyCode(discountCode);
            String msg = "";

            HttpSession session = request.getSession();

            if (discount != null) {
                if ("Expired".equals(discount.getStatus())) {
                    msg = "This discount code has expired."; 
                } else if (discount.getUsed() >= discount.getUsageLimit()) {
                    msg = "This discount code has reached its usage limit.";
                } else {
                    msg = "Code valid, your bill will be reduced by $" + discount.getDiscountAmount() + ".";
                    session.setAttribute("code", discount);
                    dao.updateUsage(discountCode);
                }
            } else {
                msg = "Invalid code."; 
            }

            request.setAttribute("msg", msg);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ApplyDiscountController: " + e.toString());
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
