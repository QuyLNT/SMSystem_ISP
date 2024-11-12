/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.category.UserObjectDAO;
import model.category.UserObjectDTO;
import model.discount.DiscountDAO;
import model.discount.DiscountDTO;
import model.order.OrderDAO;
import model.order.OrderDTO;
import model.order.OrderDetailDAO;
import model.order.OrderDetailDTO;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductVariantDAO;

/**
 *
 * @author LENOVO
 */
public class LoadManagerHomeDataController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "managerHome.jsp";
    private static final String SUCCESS = "managerHome.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAO productDao = new ProductDAO();
            ProductVariantDAO variantDao = new ProductVariantDAO();
            UserObjectDAO uObDao = new UserObjectDAO();
            OrderDAO ordDAO = new OrderDAO();
            OrderDetailDAO detailDao = new OrderDetailDAO();
            DiscountDAO discountDao = new DiscountDAO();
            List<ProductDTO> stockOfProduct;
            List<UserObjectDTO> uObList;
            List<OrderDTO> ordList;

            stockOfProduct = variantDao.getStockByProduct();
            uObList = uObDao.getAllUserObject();

            ordList = ordDAO.getTodayOrder();
            int allOrder = ordList.size();
            int compelteCount = 0;
            int notCompleteCount = 0;
            float total = 0;
            float revenue = 0;
            for (OrderDTO o : ordList) {
                if (o.getOrderStatus().equalsIgnoreCase("Completed")) {
                    compelteCount++;
                }
                if (o.getOrderStatus().equalsIgnoreCase("Not Completed")) {
                    notCompleteCount++;
                }
                List<OrderDetailDTO> orderDetails = detailDao.getOrderDetailListByOrderID(o.getOrderId());
                for (OrderDetailDTO detail : orderDetails) {
                    if (detail.getProduct() != null) {
                        float salePrice = detail.getQuantity() * (detail.getProduct().getPrice() * (1 - detail.getProduct().getSale()));
                        total += salePrice;
                    }
                }
                if (o.getDiscountCode() != null) {
                    DiscountDTO dc = discountDao.getDiscountByCode(o.getDiscountCode());
                    if (dc != null) {
                        total = total - dc.getDiscountAmount();
                    }
                }
                revenue += total;
            }

            int allStock = 0;
            for (ProductDTO p : stockOfProduct) {
                allStock += p.getTotalStock();
            }
            HttpSession session = request.getSession();
            session.setAttribute("TOTAL_ORDER", allOrder);
            session.setAttribute("REVENUE", revenue);
            session.setAttribute("COMP_ORDER", compelteCount);
            session.setAttribute("NOT_COMP_ORDER", notCompleteCount);
            session.setAttribute("ALL_QUANTITY", allStock);
            session.setAttribute("STOCK_OF_PRODUCT", stockOfProduct);
            session.setAttribute("USER_OBJECT_LIST", uObList);

            url = SUCCESS;

        } catch (ClassNotFoundException | SQLException e) {
            log("Error at LoadProductController: " + e.toString());
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
