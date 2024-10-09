/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductError;
import model.product.ProductImageDAO;

/**
 *
 * @author dell
 */
@WebServlet(name = "CreateProductController", urlPatterns = {"/CreateProductController"})
public class CreateProductController extends HttpServlet {

    private static final String ERROR = "productList.jsp";
    private static final String SUCCESS = "productList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        boolean checkValidation = true;
        ProductDAO productDAO = new ProductDAO();
        try {
            boolean checkInsert = false;
            int brandId = Integer.parseInt(request.getParameter("brandID"));
            int userObjectId = Integer.parseInt(request.getParameter("userObjectID"));
            String detail = request.getParameter("detail");
            String name = request.getParameter("Name");
            String color = request.getParameter("color");
            float price = Float.parseFloat(request.getParameter("price"));
            float sale = Float.parseFloat(request.getParameter("sale"));
            int warrantyPeriod = Integer.parseInt(request.getParameter("warrantyPeriod"));

            // Validation checks
            if (name.length() < 3 || name.length() > 100) {
                productError.setNameError("Product name must be between 3 and 100 characters");
                checkValidation = false;
            }
            if (detail.length() < 5 || detail.length() > 500) {
                productError.setDetailError("Product detail must be between 5 and 500 characters");
                checkValidation = false;
            }
            if (price < 0) {
                productError.setPriceError("Price must be a positive number");
                checkValidation = false;
            }


            if (checkValidation) {
                // Create ProductDTO object (default hot and productStatus as false)
                ProductDTO product = new ProductDTO(0, brandId, userObjectId, detail, false, name, color, price, sale, warrantyPeriod, false);

                // Insert product
                int createdProductId = productDAO.addProduct(product);

                if (createdProductId > 0) {
                    // Success: Insert images if present
                    ProductImageDAO imageDAO = new ProductImageDAO();
                    String avatar = request.getParameter("avatar");
                    
                    String[] imageUrls = new String[5];
                    imageUrls[0] = request.getParameter("productImage1");
                    imageUrls[1] = request.getParameter("productImage2");
                    imageUrls[2] = request.getParameter("productImage3");
                    imageUrls[3] = request.getParameter("productImage4");
                    imageUrls[4] = request.getParameter("productImage5");

                    for (String imageUrl : imageUrls) {
                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            checkInsert = imageDAO.addProductImage(userObjectId, imageUrl, false);
                        }
                    }
                    request.setAttribute("ms", "New product created successfully!");
                    url = SUCCESS;  
                } else {
                    productError.setErrorMessage("Failed to create product due to an unknown error.");
                }
            } else {
                // Validation failed, set error messages to be displayed on the form
                request.setAttribute("PRODUCT_ERROR", productError);
            }

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            log("Error at CreateProductController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                productError.setNameError("Product name already exists.");
                request.setAttribute("PRODUCT_ERROR", productError);
            }
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
