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
import model.user.UserDAO;
import model.user.UserDTO;
import model.user.UserError;

/**
 *
 * @author Asus
 */
public class CreateUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "register.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserDAO userDAO = new UserDAO();
        boolean checkValidation = true;
        UserError userError = new UserError();
        try {
            // Fetching form parameters
            String fullName = request.getParameter("fullName");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String confirmPass = request.getParameter("confirmPass");
            String emailRegex = "^(?=.*[a-zA-Z])[a-zA-Z0-9._%+-]{6,}@gmail\\.com$";
            boolean isEmailValid = email.matches(emailRegex);

            // Validate form parameters
            if (password == null || password.isEmpty() || !password.equals(confirmPass)) {
                request.setAttribute("err", "Password and confirm password do not match.");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            if (!isEmailValid) {
                request.setAttribute("err", "Invalid email format.");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            // Validate phone number: not empty, 10 digits, not duplicate
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                request.setAttribute("err", "Phone number cannot be empty.");
                checkValidation = false;
            } else {
                String phoneRegex = "^(09|08|07|05|03)\\d{8}$"; // Only allow exactly 10 digits
                boolean isPhoneValid = phoneNumber.matches(phoneRegex);
                if (!isPhoneValid) {
                    request.setAttribute("err", "Phone number must be 10 digits and start with 09, 08, 07, 05, or 03.");
                    checkValidation = false;

                }
            }
            if (checkValidation) {
                // Tạo đối tượng UserDTO
                UserDTO user = new UserDTO(0, fullName, userName, password, phoneNumber, "", email, true, "CUS", null);

                // Gọi phương thức createUser trong UserDAO để tạo mới user
                boolean result = userDAO.createUser(user);
                if (result) {
                    request.setAttribute("ms", "Your account is ready. Enjoy your experience!.");
                    url = SUCCESS;
                } else {
                    request.setAttribute("USER_ERROR", userError);
                }
            }
        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
            // Xử lý ngoại lệ
            if (e.toString().contains("duplicate")) {
                request.setAttribute("err", "Email or phone number is exits");
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
