/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.discount.DiscountDAO;
import model.discount.DiscountDTO;

/**
 *
 * @author CHAU DUYEN
 */
public class CreateDiscountController extends HttpServlet {

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
    private static final String SUCCESS = "discountList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            String code = request.getParameter("code");
            String detail = request.getParameter("detail");
            String amountStr = request.getParameter("amount");
            String limitStr = request.getParameter("limit");
            float amount = 0;
            int limit = 0;
            String startDayStr = request.getParameter("startDay");
            String endDayStr = request.getParameter("endDay");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDay = dateFormat.parse(startDayStr);
            java.util.Date parsedEndDay = dateFormat.parse(endDayStr);
            Date startDay = new Date(parsedStartDay.getTime());
            Date endDay = new Date(parsedEndDay.getTime());

            DiscountDAO discountDAO = new DiscountDAO();
            boolean checkValidation = false;

            if (code == null || code.isEmpty()) {
                request.setAttribute("err", "Code can't be blank!");
                checkValidation = true;
            } else if (code.length() < 10 || code.length() > 50) {
                request.setAttribute("err", "Code name must be between 10 and 50 characters.");
                checkValidation = true;
            }

            if (detail == null || detail.isEmpty()) {
                request.setAttribute("err", "Deatil can't be blank!");
                checkValidation = true;
            } else if (detail.length() < 10 || detail.length() > 100) {
                request.setAttribute("err", "Deatil must be between 10 and 100 characters.");
                checkValidation = true;
            }

            if (amountStr == null || amountStr.trim().isEmpty()) {
                request.setAttribute("err", "Amount can't be blank!");
                checkValidation = true;
            } else {
                amount = Float.parseFloat(amountStr);
                if (amount < 0.1 || amount > 1.0) {
                    request.setAttribute("err", "Amount must be between 0.1 and 1.0");
                    checkValidation = true;
                }
            }

            if (endDay.before(startDay)) {
                request.setAttribute("err", "End date cannot be before start date.");
                checkValidation = true;
            }

            if (limitStr == null || limitStr.trim().isEmpty()) {
                request.setAttribute("err", "Limit can't be blank!");
                checkValidation = true;
            } else {
                limit = Integer.parseInt(limitStr);
                if (limit < 1) {
                    request.setAttribute("err", "Limit must be greater than 0.");
                    checkValidation = true;
                }
            }

            if (!checkValidation) {
                DiscountDTO discount = new DiscountDTO(1, code, detail, amount, startDay, endDay, limit, 0, "Active");
                discount = discountDAO.addDiscount(discount);
                if (discount != null) {
                    HttpSession session = request.getSession();
                    List<DiscountDTO> discountList = (List<DiscountDTO>) session.getAttribute("DISCOUNT_LIST");
                    discountList.add(discount);
                    session.setAttribute("DISCOUNT_LIST", discountList);
                    request.setAttribute("ms", "New discount created successfully!");
                    url = SUCCESS;

                } else {
                    request.setAttribute("err", "Create failed");
                }
            }

        } catch (ClassNotFoundException| ParseException|SQLException e) {
            log("Error at CreateDiscountController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                request.setAttribute("err", "Discount code already exits");
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
