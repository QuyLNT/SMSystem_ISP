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
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductImageDAO;
import model.product.ProductVariantDAO;

/**
 *
 * @author LENOVO
 */
public class FilterProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String cateStr = request.getParameter("CateFilter");
            String brandStr = request.getParameter("BrandFilter");
            int cate;
            int brand;
            if (!"".equals(cateStr)) {
                cate = Integer.parseInt(cateStr);
            } else {
                cate = 0;
            }
            if (!"".equals(brandStr)) {
                brand = Integer.parseInt(brandStr);
            } else {
                brand = 0;
            }
            ProductDAO productdao = new ProductDAO();
            ProductImageDAO imageDao = new ProductImageDAO();
            ProductVariantDAO variantDao = new ProductVariantDAO();
            List<ProductDTO> list = productdao.getFilterList(cate, brand);
            if(list!=null){
                for(ProductDTO p : list){
                    p.setListImages(imageDao.getImageByProduct(p.getProductId()));
                    p.setListVariants(variantDao.getVariantByProduct(p.getProductId()));
                }
                HttpSession session = request.getSession();
                session.setAttribute("PRODUCT_LIST", list);          
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            log(e.toString());
        } finally {
            request.getRequestDispatcher("productList.jsp").forward(request, response);
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
