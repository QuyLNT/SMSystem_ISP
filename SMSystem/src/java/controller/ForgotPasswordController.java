/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.user.UserDAO;

/**
 *
 * @author dell
 */
public class ForgotPasswordController extends HttpServlet {

    private static final String ERROR = "forgotPassword.jsp";
    private static final String SUCCESS = "forgotPassword.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            String email = request.getParameter("email");

            if (email != null && !email.isEmpty()) {
                UserDAO userDAO = new UserDAO();

                if (userDAO.isEmailRegistered(email)) {
                    String fullName = userDAO.getUserNameByEmail(email);
                    String newPassword = generateRandomPassword(8);
                    boolean updateStatus = userDAO.updatePasswordByEmail(email, newPassword);

                    if (updateStatus) {
                        sendEmail(email, newPassword, fullName);
                        request.setAttribute("ms", "A new password has been sent to your email.");
                    } else {
                        request.setAttribute("err", "Failed to update password in the database.");
                    }
                } else {
                    request.setAttribute("err", "The email you entered is not registered.");
                }
            }
        } catch (Exception e) {
            log("Error at ForgotPasswordController: " + e.toString());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return password.toString();
    }

    private void sendEmail(String email, String newPassword, String fullName) {
        final String username = "smsystem8386@gmail.com";
        final String password = "bjjv dgxp ymfq dzxf";

        // Cấu hình các thuộc tính để gửi email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo phiên gửi email
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Password Reset");
            String emailContent = "<html>"
                    + "<body style='font-family: Arial, sans-serif; color: #000000;'>"
                    + "<h2 style='color: #4CAF50;'>Password Reset Request</h2>"
                    + "<p>Dear " + fullName + ",</p>"
                    + "<p>We have received a request to reset your password.</p>"
                    + "<p><strong>Your new password is: " + newPassword + "</strong></p>"
                    + "<p>Please change your password after logging in.</p>"
                    + "<br>"
                    + "<p>Best regards,<br>SMSystem</p>"
                    + "</body>"
                    + "</html>";

            message.setContent(emailContent, "text/html; charset=utf-8");

            // Gửi email
            Transport.send(message);
            System.out.println("Email sent successfully to " + email);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
