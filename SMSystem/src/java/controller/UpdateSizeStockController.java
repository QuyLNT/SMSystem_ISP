/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
public class UpdateSizeStockController extends HttpServlet {

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
            String[] variantIds = request.getParameterValues("variantId");
            String[] stocks = request.getParameterValues("stock");

            ProductVariantDAO variantDAO = new ProductVariantDAO();
            List<ProductVariantDTO> variants = new ArrayList<>();
            for (int i = 0; i < variantIds.length; i++) {
                int variantId = Integer.parseInt(variantIds[i]);
                int stock = Integer.parseInt(stocks[i]);

                ProductVariantDTO variant = new ProductVariantDTO();
                variant.setVariantId(variantId);
                variant.setStock(stock);
                variants.add(variant);
            }

            for (ProductVariantDTO variant : variants) {
                variantDAO.updateStock(variants);
                request.setAttribute("ms", "Update stock successfully!");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateSizeStockController:" + e.toString());
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
