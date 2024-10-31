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
import model.product.ProductVariantDAO;
import model.product.ProductVariantDTO;

/**
 *
 * @author CHAU DUYEN
 */
public class CreateSizeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "LoadProductListController";
    private static final String SUCCESS = "LoadProductListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductVariantDAO variantDAO = new ProductVariantDAO();
            int productId = Integer.parseInt(request.getParameter("productId"));
            float size = Float.parseFloat(request.getParameter("size"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            boolean checkValidation = true;
            if (stock < 0 || stock == 0) {
                request.setAttribute("err", "Stock must be greater than 0!");
                checkValidation = false;
            }

            if (variantDAO.isSizeExists(productId, size)) {
                request.setAttribute("err", "This size already exists for the selected product.");
                checkValidation = false;
            }

            if (checkValidation) {
                ProductVariantDTO variant = new ProductVariantDTO(productId, size, stock);
                variant = variantDAO.createNewSize(variant);
                if (variant != null) {
                    request.setAttribute("ms", "Size added successfully!");
                    url = SUCCESS; // Update URL to success page
                } else {
                    request.setAttribute("err", "Failed to add size. Please try again.");
                }
            } else {
                url = ERROR;
            }

        } catch (Exception e) {
            log("Error at CreateSizeController:" + e.toString());
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
