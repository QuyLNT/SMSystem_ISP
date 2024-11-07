/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class SendMailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    public void sendEmail(List<String> emails, String newPassword, String fullName) {
        final String username = "smsystem8386@gmail.com";
        final String password = "bjjv dgxp ymfq dzxf";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo danh sách địa chỉ hợp lệ
            List<InternetAddress> validAddresses = new ArrayList<>();

            for (String email : emails) {
                try {
                    InternetAddress emailAddress = new InternetAddress(email.trim());
                    emailAddress.validate();  // Kiểm tra định dạng email hợp lệ
                    validAddresses.add(emailAddress);
                } catch (AddressException e) {
                    System.out.println("Invalid email address skipped: " + email);
                }
            }

            // Chỉ gửi nếu có ít nhất một địa chỉ hợp lệ
            if (!validAddresses.isEmpty()) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));

                // Chuyển danh sách địa chỉ hợp lệ thành mảng
                message.setRecipients(Message.RecipientType.TO,
                        validAddresses.toArray(new InternetAddress[0]));

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
                System.out.println("Email sent successfully to: " + validAddresses);
            } else {
                System.out.println("No valid email addresses to send.");
            }

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
