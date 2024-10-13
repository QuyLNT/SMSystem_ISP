/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
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
 * @author Asus
 */
public class DeleteUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String ERROR = "LoadUserListController";
    private static final String SUCCESS = "LoadUserListController";
    private static final String ERROR = "userList.jsp";
    private static final String SUCCESS = "userList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String url = ERROR;
        try{
            String userID= request.getParameter("userId");
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if(userID.equals(loginUser.getUserId())){
                request.setAttribute("err", "Can not delete");     
            }else{
                UserDAO dao = new UserDAO();
                boolean checkDelete = dao.delete(userID);
                if(checkDelete){
                    request.setAttribute("ms", "Delete user successfully");
                    url=SUCCESS;
                }
        UserDAO userDAO = new UserDAO();

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            boolean userDelete= userDAO.delete(userId);

            if (userDelete) {
                HttpSession session = request.getSession();
            List<UserDTO> userList = (List<UserDTO>) session.getAttribute("USER_LIST");
            
            // Kiểm tra và xóa sản phẩm khỏi danh sách
            if (userList != null) {
                userList.removeIf(user -> user.getUserId() == userId);
                session.setAttribute("USER_LIST", userList); // Cập nhật lại danh sách trong session
            }
                request.setAttribute("ms", "User deleted successfully!");
            } else {
                request.setAttribute("err", "Failed to delete the user ");
            }

            url = SUCCESS;
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
