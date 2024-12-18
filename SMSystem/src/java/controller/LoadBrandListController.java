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
import model.category.BrandDAO;
import model.category.BrandDTO;
import model.product.ProductDAO;

/**
 *
 * @author LENOVO
 */
public class LoadBrandListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR="brandList.jsp";
    private static final String SUCCESS="brandList.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            BrandDAO brandDao = new BrandDAO();
            ProductDAO productDao = new ProductDAO();

            List<BrandDTO> brandList = brandDao.getAllBrand();
            for(BrandDTO brand : brandList){
                brand.setProductCount(brandDao.getProductCountByBrand(brand.getBrandId()));
            }
            if(brandList!=null){
                HttpSession session = request.getSession();
                session.setAttribute("BRAND_LIST", brandList);               
                url = SUCCESS;

            }else{
                request.setAttribute("err", "Load Data Failed");
            }
        }catch(ClassNotFoundException | SQLException e){
           log("Error at LoadBrandListController: " +e.toString());
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
