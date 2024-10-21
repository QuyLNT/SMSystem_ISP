/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductImageDAO;

/**
 *
 * @author dell
 */
public class DeleteProductController extends HttpServlet {

    private static final String ERROR = "productList.jsp";
    private static final String SUCCESS = "productList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductDAO productDAO = new ProductDAO();
        ProductImageDAO imageDAO = new ProductImageDAO();

        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            boolean imageDeleted = imageDAO.deleteProductImages(productId);
            boolean productDeleted = productDAO.deleteProduct(productId);

            if (imageDeleted && productDeleted) {
                HttpSession session = request.getSession();
            List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");
            
            // Kiểm tra và xóa sản phẩm khỏi danh sách
            if (productList != null) {
                productList.removeIf(product -> product.getProductId() == productId);
                session.setAttribute("PRODUCT_LIST", productList); // Cập nhật lại danh sách trong session
            }
                request.setAttribute("ms", "Product and its images deleted successfully!");
            } else {
                request.setAttribute("err", "Failed to delete the product or its images.");
            }

            url = SUCCESS;
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            log("Error at DeleteProductController: " + e.toString());
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
