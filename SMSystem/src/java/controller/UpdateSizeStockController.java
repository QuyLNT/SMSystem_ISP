/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.category.UserObjectDTO;
import model.product.ProductDTO;
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
    private static final String ERROR = "productList.jsp";
    private static final String SUCCESS = "productList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String[] variantIds = request.getParameterValues("variantId");
            String[] stocks = request.getParameterValues("stock");
            String[] sizes = request.getParameterValues("sizes");
            String productId = request.getParameter("productId");
            int pId = 0;
            if (productId != null) {
                pId = Integer.parseInt(productId);
            }
            ProductVariantDAO variantDAO = new ProductVariantDAO();
            List<ProductVariantDTO> variants = new ArrayList<>();
            for (int i = 0; i < variantIds.length; i++) {
                int variantId = Integer.parseInt(variantIds[i]);
                int stock = Integer.parseInt(stocks[i]);
                float size = Float.parseFloat(sizes[i]);
                
                ProductVariantDTO variant = new ProductVariantDTO();
                variant.setVariantId(variantId);
                variant.setSize(size);
                variant.setStock(stock);
                variants.add(variant);
            }

            HttpSession session = request.getSession();
            List<ProductDTO> stockOfProduct = (List<ProductDTO>) session.getAttribute("STOCK_OF_PRODUCT");
            List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");

            for (ProductVariantDTO variant : variants) {
                variantDAO.updateStock(variants);
                for (ProductDTO p : stockOfProduct) {
                    if (p.getProductId() == pId) {
                        p.setTotalStock(variantDAO.getStockByProduct(pId));
                    }
                }
                request.setAttribute("ms", "Update stock successfully!");
                url = SUCCESS;
            }
            for (ProductDTO p : productList) {
                if (p.getProductId() == pId) {
                    p.setListVariants(variants);
                    break;
                }
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
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
