/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductImageDAO;

/**
 *
 * @author LENOVO
 */
public class SortProductListController extends HttpServlet {

    private static final String ERROR = "shop.jsp";
    private static final String SUCCESS = "shop.jsp";
    private static final Comparator<ProductDTO> PRICE_ASCENDING = Comparator.comparing(ProductDTO::getPrice);
    private static final Comparator<ProductDTO> PRICE_DESCENDING = Comparator.comparing(ProductDTO::getPrice).reversed();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            ProductDAO productDao = new ProductDAO();
            ProductImageDAO imageDao = new ProductImageDAO();
            HttpSession session = request.getSession();

            List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");
            if (productList != null) {
                switch (id) {
                    case "1":
                        List<ProductDTO> defaultList = productDao.search("");
                        for (ProductDTO p : defaultList) {
                            p.setListImages(imageDao.getImageByProduct(p.getProductId()));
                        }
                        session.setAttribute("PRODUCT_LIST", defaultList);
                        break;
                    case "2":
                        List<ProductDTO> filteredProducts = filterProductsWithSaleGreaterThanZero(productList);
                        session.setAttribute("PRODUCT_LIST", filteredProducts);
                        break;
                    case "3":
                        productList.sort(PRICE_DESCENDING);
                        session.setAttribute("PRODUCT_LIST", productList);
                        break;
                    case "4":
                        productList.sort(PRICE_ASCENDING);
                        session.setAttribute("PRODUCT_LIST", productList);
                        break;
                    default:
                        break;
                }
                session.setAttribute("ID", id);

            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at SortProductListController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public static List<ProductDTO> filterProductsWithSaleGreaterThanZero(List<ProductDTO> productList) {
        return productList.stream()
                .filter(product -> product.getSale() > 0)
                .collect(Collectors.toList());
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
