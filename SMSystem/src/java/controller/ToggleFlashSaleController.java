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

/**
 *
 * @author LENOVO
 */
public class ToggleFlashSaleController extends HttpServlet {

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
            int isHotInt = Integer.parseInt(request.getParameter("Hot"));
            boolean isHot = false;
            if(productIdStr!=null && !productIdStr.isEmpty()){
                int productId = Integer.parseInt(productIdStr);
                if(isHotInt == 1){
                    isHot=true;
                }
                ProductDAO productDAO = new ProductDAO();
                boolean checkUpdate = productDAO.updateHot(productId,isHot);
                
                
                HttpSession session = request.getSession();
                List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");
                if(checkUpdate){
                    for(ProductDTO p: productList){
                        if(p.getProductId()==productId){
                            p.setHot(isHot);
                        }
                    }
                    session.setAttribute("PRODUCT_LIST", productList);
                    request.setAttribute("ms", "Set hot successfully");
                    url=SUCCES;
                }else{
                    request.setAttribute("err", "Set hot failed");
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
