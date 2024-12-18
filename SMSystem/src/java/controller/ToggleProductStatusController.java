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

/**
 *
 * @author LENOVO
 */
public class ToggleProductStatusController extends HttpServlet {

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
    private static final String SUCCES = "productList.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productIdStr = request.getParameter("productId");
            int productStatusInt = Integer.parseInt(request.getParameter("Product_Status"));
            boolean productStatus = false;
            if(productIdStr!=null && !productIdStr.isEmpty()){
                int productId = Integer.parseInt(productIdStr);
                if(productStatusInt == 1){
                    productStatus=true;
                }
                ProductDAO productDAO = new ProductDAO();
                boolean checkUpdate = productDAO.updateProductStatus(productId,productStatus);
                
                HttpSession session = request.getSession();
                List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");
                if(checkUpdate){
                    for(ProductDTO p: productList){
                        if(p.getProductId()==productId){
                            p.setProductStatus(productStatus);
                        }
                    }
                    session.setAttribute("PRODUCT_LIST", productList);
                    url=SUCCES;
                    request.setAttribute("ms", "Set product status successfully");

                }else{
                    request.setAttribute("err", "Set product status failed");
                }
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            log("Error at ToggleFlashSaleController: "+e.toString());
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
