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
import model.category.BrandDAO;
import model.category.BrandDTO;
import model.category.UserObjectDAO;
import model.category.UserObjectDTO;
import model.discount.DiscountDAO;
import model.discount.DiscountDTO;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductImageDAO;
import model.product.ProductVariantDAO;
import model.product.ProductVariantDTO;

/**
 *
 * @author LENOVO
 */
public class LoadProductListController extends HttpServlet {

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
            ProductDAO productDao = new ProductDAO();
            BrandDAO brandDao = new BrandDAO();
            UserObjectDAO uObDao= new UserObjectDAO();
            ProductVariantDAO variantDao = new ProductVariantDAO();
            ProductImageDAO imageDao = new ProductImageDAO();
            List<ProductDTO> productList;
            List<BrandDTO> brandList;
            
            productList = productDao.getAllProduct();
            brandList = brandDao.getAllBrand();
            for(ProductDTO p: productList){
                p.setListImages(imageDao.getImageByProduct(p.getProductId()));
            }
                        
            if(productList !=null && brandList!=null){
                HttpSession session = request.getSession();
                session.setAttribute("PRODUCT_LIST", productList);
                session.setAttribute("BRAND_LIST", brandList);
                
                url = SUCCESS;

            }
            
            
        }catch(ClassNotFoundException | SQLException e){
           log("Error at LoadProductController: " +e.toString());
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
