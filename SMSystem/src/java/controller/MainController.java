/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String WELLCOME_PAGE="login.jsp";
    private final static String LOGIN="Sign In" ;
    private final static String LOGIN_CONTROLLER="LoginController" ;
    private final static String UPDATE_PRODUCT="Update Product" ;
    private final static String UPDATE_PRODUCT_CONTROLLER="UpdateProductController";
    private final static String LOAD_DATA="LoadProductData" ;
    private final static String LOAD_PRODUCT_DATA_CONTROLLER="LoadProductController";
    private final static String UPDATE_USER="Update" ;
    private final static String UPDATE_USER_CONTROLLER="UpdateUserController";
    
    private final static String CREATE_PRODUCT = "Create Product" ;
    private final static String CREATE_PRODUCT_CONTROLLER = "CreateProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=WELLCOME_PAGE;
        try{
            String action = request.getParameter("action");
            if(LOGIN.equals(action)){
                url=LOGIN_CONTROLLER;
            }else if(UPDATE_PRODUCT.equals(action)){
                url=UPDATE_PRODUCT_CONTROLLER;
            }else if(LOAD_DATA.equals(action)){
                url=LOAD_PRODUCT_DATA_CONTROLLER;
            }else if(UPDATE_USER.equals(action)){
                url=UPDATE_USER_CONTROLLER;
            }else if(CREATE_PRODUCT.equals(action)){
                url=CREATE_PRODUCT_CONTROLLER;
            }
            
            
            
            
            
            
            
            
            
        }catch(Exception e){
           log("Error at MainController" + e.toString());
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
