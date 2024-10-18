/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.cart.CartDAO;
import model.cart.CartDTO;
import model.cart.CartItems;
import model.category.BrandDAO;
import model.category.UserObjectDAO;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductImageDAO;
import model.product.ProductVariantDAO;
import model.user.UserDTO;

/**
 *
 * @author ADMIN
 */
public class LoadTopListByCateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "homePage.jsp";
    private static final String SUCCESS = "homePage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ProductDAO productDao = new ProductDAO();
            BrandDAO brandDao = new BrandDAO();
            UserObjectDAO uObDao = new UserObjectDAO();
            ProductVariantDAO variantDao = new ProductVariantDAO();
            ProductImageDAO imageDao = new ProductImageDAO();
            List<ProductDTO> menList;
            List<ProductDTO> womenList;
            List<ProductDTO> kidList;
            menList = productDao.getTopMenList();
            womenList = productDao.getTopWomenList();
            kidList = productDao.getTopKidList();
            for (ProductDTO p : menList) {
                p.setListImages(imageDao.getImageByProduct(p.getProductId()));
            }
            for (ProductDTO pr : womenList) {
                pr.setListImages(imageDao.getImageByProduct(pr.getProductId()));
            }
            for (ProductDTO pro : kidList) {
                pro.setListImages(imageDao.getImageByProduct(pro.getProductId()));
            }
                        List<Float> availableleSize=null;
                        List<Float> allSize=null;
            CartDAO cartDao = new CartDAO();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser != null) {
                CartDTO cart = cartDao.getCartByUserId(loginUser.getUserId());
                if (cart != null) {
                    for (CartItems c : cart.getCartItemsList()) {
                        c.getProduct().setListImages(imageDao.getImageByProduct(c.getProduct().getProductId()));

                        availableleSize = variantDao.getAvailableSize(c.getProduct().getProductId());
                        allSize = variantDao.getAllSize(c.getProduct().getProductId());
                    }
                    session.setAttribute("CART", cart); // Load cart có sẵn
                } else {
//                    // Nếu user chưa có giỏ hàng, tạo giỏ hàng mới
//                    cart = new CartDTO();
//                    cart.setCustomerId(loginUser.getUserId());
//                    cartDao.createCart(cart); // Gọi createCart() để tạo giỏ hàng mới trong DB
//                    session.setAttribute("CART", cart); // Set giỏ hàng mới vào session
                }
            }

            if (menList != null && womenList != null && kidList != null) {
                session.setAttribute("MEN_LIST", menList);
                session.setAttribute("WOMEN_LIST", womenList);
                session.setAttribute("KID_LIST", kidList);
                session.setAttribute("ALL_SIZE", allSize);
                session.setAttribute("AVAILABLE_SIZE", availableleSize);
                url = SUCCESS;

            }

        } catch (ClassNotFoundException | SQLException e) {
            log("Error at LoadTopListByCateController: " + e.toString());
        } catch (NamingException ex) {
            Logger.getLogger(LoadTopListByCateController.class.getName()).log(Level.SEVERE, null, ex);
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
