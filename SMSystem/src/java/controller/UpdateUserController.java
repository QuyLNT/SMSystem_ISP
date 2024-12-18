/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
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
        String url = ERROR;
        boolean hasError = false;
        try {

            String fullName = request.getParameter("fullName");
            String pass = request.getParameter("pass");
            String phone = request.getParameter("phone");
            String sex = request.getParameter("sex");
            String email = request.getParameter("email");

            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmNewPassword = request.getParameter("confirmNewPassword");

            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String userName = user.getUserName();
            UserDAO dao = new UserDAO();
            UserDTO userInput = new UserDTO(userName, fullName, pass, phone, sex, email);

            if (fullName == null || fullName.isEmpty()) {
                request.setAttribute("FULLNAME_ERROR", "Can't be blank!");
                hasError = true;
            }
            if (!fullName.isEmpty() && fullName.length() > 50) {
                request.setAttribute("FULLNAME_ERROR", "Must not exceed 50 characters!");
                hasError = true;
            }

            if (newPassword != null && !newPassword.isEmpty()) {
                // Kiểm tra mật khẩu hiện tại
                if (currentPassword == null || !currentPassword.equals(user.getPassword())) {
                    request.setAttribute("CURRENT_PASS_ERROR", "Current password is incorrect.");
                    hasError = true;
                }

                // Kiểm tra xác nhận mật khẩu mới
                if (!newPassword.equals(confirmNewPassword)) {
                    request.setAttribute("PASS_ERROR", "New password does not match confirmation.");
                    hasError = true;
                } else if (newPassword.length() < 3) {
                    request.setAttribute("PASS_ERROR", "New password must be at least 3 characters long.");
                    hasError = true;
                }
            }

            if (phone == null || phone.isEmpty()) {
                request.setAttribute("PHONE_ERROR", "Can't be blank!");
                hasError = true;
            }
            if (!phone.isEmpty() && !phone.matches("^(09|08|07|05|03)\\d{8}$")) {
                request.setAttribute("PHONE_ERROR", "Phone number must be 10 digits and start with 09, 08, 07, 05, or 03.");
                hasError = true;
            }

            if (sex == null || sex.isEmpty()) {
                request.setAttribute("SEX_ERROR", "Please select your gender!");
                hasError = true;
            } else if (sex.equals("")) {
                request.setAttribute("SEX_ERROR", "Please select your gender!");
                hasError = true;
            }

            if (email == null || email.isEmpty()) {
                request.setAttribute("EMAIL_ERROR", "Can't be blank!");
                hasError = true;
            } else if (!email.isEmpty() && !email.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9._%+-]{6,}@gmail\\.com$")) {
                request.setAttribute("EMAIL_ERROR", "Email must be at least 6 characters before @gmail.com and contain letters (not just numbers).");
                hasError = true;
            }

            if (!hasError) {
                UserDTO updatedUser = new UserDTO(userName, fullName, user.getPassword(), phone, sex, email);
                boolean isUpdated = dao.userAfterUpdate(updatedUser, newPassword);

                if (isUpdated) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Update Successfully");
                    user.setFullName(fullName);
                    user.setPhoneNumber(phone);
                    user.setSex(sex);
                    user.setEmail(email);
                    if (newPassword != null && !newPassword.isEmpty()) {
                        user.setPassword(newPassword);
                    }
                }
            } else {
                url = ERROR;
                request.setAttribute("MESSAGE", "Update Fail");
            }
        } catch (ClassNotFoundException | SQLException e) {
            if (e.toString().contains("duplicate")) {
                request.setAttribute("MESSAGE", "Email or phone number is exits");
            }
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
