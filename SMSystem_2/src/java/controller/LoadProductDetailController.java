/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.category.BrandDAO;
import model.category.UserObjectDAO;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductImageDAO;
import model.product.ProductImageDTO;
import model.product.ProductVariantDAO;

/**
 *
 * @author LENOVO
 */
public class LoadProductDetailController extends HttpServlet {

    private static final String ERROR = "product.jsp";
    private static final String SUCCESS = "product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productId");
            ProductDAO productDao = new ProductDAO();
            BrandDAO brandDao = new BrandDAO();
            UserObjectDAO userObjectDao = new UserObjectDAO();
            ProductVariantDAO variantDao = new ProductVariantDAO();
            ProductImageDAO imageDao = new ProductImageDAO();

            List<Float> availableleSize;
            List<Float> allSize;
            List<ProductDTO> relatedList;
            List<ProductImageDTO> imageList;
            ProductDTO product;

            if (productID != null) {
                int id = Integer.parseInt(productID);
                product = productDao.getProductById(id);
                product.setListVariants(variantDao.getVariantByProduct(product.getProductId()));
                relatedList = productDao.getRelatedList(id);
                imageList = imageDao.getImageByProduct(id);
                product.setListImages(imageList);
                for(ProductDTO p: relatedList){
                    p.setListImages(imageDao.getImageByProduct(p.getProductId()));
                }

                HttpSession session = request.getSession();
                session.setAttribute("PRODUCT", product);
                session.setAttribute("IMAGE", product.getListImages());
                session.setAttribute("RELATED_LIST", relatedList);
                url = SUCCESS;

            }

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            log("Error at LoadProductDetailController: " + e.toString());
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
