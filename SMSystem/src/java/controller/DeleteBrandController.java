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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.category.BrandDAO;
import model.category.BrandDTO;

/**
 *
 * @author LENOVO
 */
public class DeleteBrandController extends HttpServlet {

    private static final String ERROR = "brandList.jsp";
    private static final String SUCCESS = "brandList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        BrandDAO brandDao = new BrandDAO();
        boolean checkDelete = false;
        boolean flag = false;
        try {
            HttpSession session = request.getSession();
            List<BrandDTO> list = (List<BrandDTO>) session.getAttribute("BRAND_LIST");
            int brandId = Integer.parseInt(request.getParameter("brandId"));
            for (BrandDTO b : list) {
                if (b.getBrandId() == brandId) {
                    flag = b.getProductCount() == 0;
                    break;
                }
            }
            if (flag) {
                checkDelete = brandDao.deleteBrand(brandId);

                if (checkDelete) {
                    for (BrandDTO b : list) {
                        if (b.getBrandId() == brandId) {
                            list.remove(b);
                            break;
                        }
                    }
                    session.setAttribute("BRAND_LIST", list);
                    request.setAttribute("ms", "Brand deleted successfully!");
                } else {
                    request.setAttribute("err", "Failed to delete brand.");
                }
                url = SUCCESS;
            } else {
                request.setAttribute("err", "Cannot delete brand when product exists.");
            }

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
