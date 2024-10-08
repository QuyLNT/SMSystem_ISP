/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user.UserDAO;
import model.user.UserDTO;

/**
 *
 * @author CHAU DUYEN
 */
public class UpdateUserController extends HttpServlet {

    private static final String ERROR = "myAccount.jsp";
    private static final String SUCCESS = "myAcccount.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "ERROR";
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String fullName = request.getParameter("fullName");
            String userName = request.getParameter("userName");
            String pass = request.getParameter("userPass");
            String phone = request.getParameter("phone");
            String Sex = request.getParameter("Sex");
            String email = request.getParameter("email");
            UserDTO userInput = new UserDTO(user.getUserId(), fullName, userName, pass, phone, Sex, email);

            UserDAO dao = new UserDAO();
            boolean check = dao.userAfterUpdate(user);
            if (check) {
                request.setAttribute("MESSAGE", "Update Fail");
            } else {
                boolean checkUserAfter = dao.userAfterUpdate(user);
                if (checkUserAfter) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Update Successfully");

                } else {

                    request.setAttribute("MESSAGE", "Update Fail");
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
