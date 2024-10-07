/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.ProductDAO;
import model.product.ProductDTO;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR="productList.jsp";
    private static final String SUCCESS="productList.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
         try{
            int productId = Integer.parseInt(request.getParameter("productId"));
            ProductDAO productDao = new ProductDAO();
            ProductDTO currentProduct = productDao.getProductById(productId);

            String name = request.getParameter("name");
            if (name == null || name.isEmpty()) {
                name = currentProduct.getName();
            }

            int userObjectID;
            String userObjectIDStr = request.getParameter("userObjectID");
            if (userObjectIDStr == null || userObjectIDStr.isEmpty()) {
                userObjectID = currentProduct.getUserOjectId();
            } else {
                userObjectID = Integer.parseInt(userObjectIDStr);
            }

            int brandID;
            String brandIDStr = request.getParameter("brandID");
            if (brandIDStr == null || brandIDStr.isEmpty()) {
                brandID = currentProduct.getBrandId();
            } else {
                brandID = Integer.parseInt(brandIDStr);
            }

            double price;
            String priceStr = request.getParameter("price");
            if (priceStr == null || priceStr.isEmpty()) {
                price = currentProduct.getPrice();
            } else {
                price = Double.parseDouble(priceStr);
            }

            float sale;
            String saleStr = request.getParameter("sale");
            if (saleStr == null || saleStr.isEmpty()) {
                sale = currentProduct.getSale();
            } else {
                sale = Float.parseFloat(saleStr);
            }

            String avatar = request.getParameter("avatar");
            if (avatar == null || avatar.isEmpty()) {
                avatar = currentProduct.getAvatarPath();
            }

            String color = request.getParameter("color");
            if (color == null || color.isEmpty()) {
                color = currentProduct.getColor();
            }

            int stock;
            String stockStr = request.getParameter("stock");
            if (stockStr == null || stockStr.isEmpty()) {
                stock = currentProduct.getStock();
            } else {
                stock = Integer.parseInt(stockStr);
            }

            String detail = request.getParameter("detail");
            if (detail == null || detail.isEmpty()) {
                detail = currentProduct.getDetail();
            }

            ProductDTO updatedProduct = new ProductDTO();
            
            
        }catch(ClassNotFoundException | SQLException | NamingException e){
           log("Error at CreateProductController: " +e.toString());
        }finally{           
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
