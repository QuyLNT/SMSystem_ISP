/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.shipment.ShipmentDAO;
import model.shipment.ShipmentDTO;
import model.user.UserDTO;

/**
 *
 * @author LENOVO
 */
public class LoadShipDataController extends HttpServlet {

    private static final String ERROR = "shipperPage.jsp";
    private static final String SUCCESS = "shipperPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            ShipmentDAO dao = new ShipmentDAO();
            List<ShipmentDTO> shipList;
            shipList = dao.getToDayShip(loginUser.getUserId());
            if (shipList != null) {
                int today = shipList.size();
                int delivering = 0;
                int completed = 0;
                for (ShipmentDTO s : shipList) {
                    if (s.getShipmentStatus().contains("completed")) {
                        delivering++;
                    } else {
                        completed++;
                    }
                }
                session.setAttribute("shipments", shipList);
                request.setAttribute("TOTAL", today);
                request.setAttribute("completed", completed);
                request.setAttribute("delivering", delivering);
                url = SUCCESS;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at LoadShipDataController: " + e.toString());
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
