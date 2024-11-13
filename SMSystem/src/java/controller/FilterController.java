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
import java.util.Arrays;
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
 * @author LENOVO
 */
public class FilterController extends HttpServlet {

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
            String[] colors = request.getParameterValues("color");
            String[] brands = request.getParameterValues("brand");
            String minPrice_raw = request.getParameter("minPrice");
            String maxPrice_raw = request.getParameter("maxPrice");
            String uOb = request.getParameter("uOb");
            int[] intBrands;
            int[] intUOb;
            List<Integer> brandList = new ArrayList<>();
            if (brands != null) {
                intBrands = new int[brands.length];
                for (int i = 0; i < brands.length; i++) {
                    try {
                        intBrands[i] = Integer.parseInt(brands[i]);
                        brandList.add(Integer.parseInt(brands[i]));
                    } catch (NumberFormatException e) {

                    }
                }
            } else {
                intBrands = new int[0];
            }
            if (colors == null) {
                colors = new String[0];
            }
            if (uOb != null) {
                intUOb = new int[1];
                intUOb[0] = Integer.parseInt(uOb);
            } else {
                intUOb = new int[0];
            }
            ProductDAO dao = new ProductDAO();
            ProductImageDAO imageDao = new ProductImageDAO();
            List<ProductDTO> list = dao.filter(intBrands, minPrice_raw, maxPrice_raw, colors, intUOb);
            for (ProductDTO p : list) {
                p.setListImages(imageDao.getImageByProduct(p.getProductId()));
            }
            HttpSession session = request.getSession();
            session.setAttribute("PRODUCT_LIST", list);

            request.setAttribute("SELECTED_BRANDS", brandList);
            request.setAttribute("SELECTED_COLORS", Arrays.asList(colors));
            if (minPrice_raw != null) {
                request.setAttribute("MIN_PRICE", minPrice_raw);
            }
            if (maxPrice_raw != null) {
                request.setAttribute("MAX_PRICE", maxPrice_raw);
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
        } finally {
            request.getRequestDispatcher("shop.jsp").forward(request, response);
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
