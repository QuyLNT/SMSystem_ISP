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
import model.discount.DiscountDAO;
import model.discount.DiscountDTO;
import model.product.ProductDAO;

/**
 *
 * @author LENOVO
 */
public class ToggleDiscountStatusController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "discountList.jsp";
    private static final String SUCCES = "discountList.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String url = ERROR;
        try {
            String discountIdStr = request.getParameter("discountId");
            String status = request.getParameter("status");
            if(discountIdStr!=null && !discountIdStr.isEmpty()){
                int discountId = Integer.parseInt(discountIdStr);
                DiscountDAO discountDao = new DiscountDAO();
                boolean checkUpdate = discountDao.updateStatus(discountId,status);
                if(checkUpdate){
                    HttpSession session = request.getSession();
                    List<DiscountDTO> discountList = (List<DiscountDTO>) session.getAttribute("DISCOUNT_LIST");
                    for(DiscountDTO d: discountList){
                        if(d.getDiscountId()==discountId){
                            d.setStatus(status);
                        }
                    }
                    session.setAttribute("DISCOUNT_LIST", discountList);
                    request.setAttribute("ms", "Set status successfully");                    
                    url=SUCCES;

                }else{
                    request.setAttribute("err", "Set status failed");
                }
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            log("Error at ToggleDiscountStatusController: "+e.toString());
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
