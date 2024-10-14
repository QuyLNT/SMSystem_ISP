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
    private static final String SUCCESS = "myAccount.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "ERROR";
        boolean hasError = false;
        try {

            String fullName = request.getParameter("fullName");
            String pass = request.getParameter("pass");
            String phone = request.getParameter("phone");
            String sex = request.getParameter("sex");
            String email = request.getParameter("email");
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String userName = user.getUserName();
            UserDAO dao = new UserDAO();
            UserDTO userInput = new UserDTO(userName, fullName, pass, phone, sex, email);
            if (fullName == null || fullName.isEmpty()) {
                request.setAttribute("FULLNAME_ERROR", "Can't be blank!");
                hasError = true;
            } else if (fullName.length() > 50) {
                request.setAttribute("FULLNAME_ERROR", "Must not exceed 50 characters!");
                hasError = true;
            }

            if (pass == null || pass.isEmpty()) {
                request.setAttribute("PASS_ERROR", "Can't be blank ");
                hasError = true;
            } else if (pass.length() < 3) {
                request.setAttribute("PASS_ERROR", "Must be at least 3 characters long!");
                hasError = true;
            }

            if (phone == null || phone.isEmpty() || !phone.matches("^\\d{10,15}$")) {
                request.setAttribute("PHONE_ERROR", "Can't be blank!");
                hasError = true;
            } else if (!phone.matches("^\\d{10,15}$")) {
                request.setAttribute("PHONE_ERROR", "Invalid phone number! Must contain only digits and be between 10 to 15 characters.");
                hasError = true;
            } else if (dao.isPhoneExists(phone) && !phone.equals(user.getPhoneNumber())) {
                request.setAttribute("PHONE_ERROR", "Phone number already exists!");
                hasError = true;
            }

            if (sex == null || sex.isEmpty()) {
                request.setAttribute("SEX_ERROR", "Can't be blank!");
                hasError = true;
            }

            if (email == null || email.isEmpty()) {
                request.setAttribute("EMAIL_ERROR", "Can't be blank!");
                hasError = true;
            } else if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                request.setAttribute("EMAIL_ERROR", "Invalid email format!");
                hasError = true;
            } else if (dao.isEmailExists(email) && !email.equals(user.getEmail())) {
                request.setAttribute("EMAIL_ERROR", "Email already exists!");
                hasError = true;
            }

            if (!hasError) {
                boolean checkUserAfter = dao.userAfterUpdate(userInput);
                if (checkUserAfter) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Update Successfully");
                    user.setFullName(fullName);
                    user.setPassword(pass);
                    user.setPhoneNumber(phone);
                    user.setSex(sex);
                    user.setEmail(email);
                } else {
                    url = ERROR;
                    request.setAttribute("MESSAGE", "Update Fail");
                }
            } else {
                url = ERROR;
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
