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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductImageDAO;
import model.product.ProductImageDTO;

/**
 *
 * @author LENOVO
 */
public class UpdateProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "productList.jsp";
    private static final String SUCCESS = "productList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            ProductDAO productDao = new ProductDAO();
            ProductImageDAO imageDAO = new ProductImageDAO();
            ProductDTO currentProduct = productDao.getProductById(productId);
            StringBuilder errorMessages = new StringBuilder();

            String name = request.getParameter("name");
            if (name == null || name.isEmpty()) {
                name = currentProduct.getName();
            }

            int userObjectID;
            String userObjectIDStr = request.getParameter("userObjectID");
            if (userObjectIDStr == null || userObjectIDStr.isEmpty()) {
                userObjectID = currentProduct.getUserOjectId();
            } else {
                userObjectID = Integer.parseInt(userObjectIDStr);
            }

            int brandID;
            String brandIDStr = request.getParameter("brandID");
            if (brandIDStr == null || brandIDStr.isEmpty()) {
                brandID = currentProduct.getBrandId();
            } else {
                brandID = Integer.parseInt(brandIDStr);
            }

            float price;
            String priceStr = request.getParameter("price");
            if (priceStr == null || priceStr.isEmpty()) {
                price = currentProduct.getPrice();
            } else {
                price = Float.parseFloat(priceStr);
            }

            float sale;
            String saleStr = request.getParameter("sale");
            if (saleStr == null || saleStr.isEmpty()) {
                sale = currentProduct.getSale();
            } else {
                sale = Float.parseFloat(saleStr);
            }

            String avatar = request.getParameter("avatar");
            if (avatar == null || avatar.isEmpty()) {
                avatar = currentProduct.getAvatarPath();
            }

            String color = request.getParameter("color");
            if (color == null || color.isEmpty()) {
                color = currentProduct.getColor();
            }

            String detail = request.getParameter("detail");
            if (detail == null || detail.isEmpty()) {
                detail = currentProduct.getDetail();
            }

            int warrantyPeriod;
            String warrantyPeriodStr = request.getParameter("warranty");
            if (warrantyPeriodStr == null || warrantyPeriodStr.isEmpty()) {
                warrantyPeriod = currentProduct.getWarrantyPeriod();
            } else {
                warrantyPeriod = Integer.parseInt(warrantyPeriodStr);
            }

            boolean hot = currentProduct.isHot();
            boolean status = currentProduct.isProductStatus();

            String[] imageIds = request.getParameterValues("imageId");
            String[] imageUrls = new String[5];
            imageUrls[0] = request.getParameter("productImage1");
            imageUrls[1] = request.getParameter("productImage2");
            imageUrls[2] = request.getParameter("productImage3");
            imageUrls[3] = request.getParameter("productImage4");
            imageUrls[4] = request.getParameter("productImage5");
            List<ProductImageDTO> images = new ArrayList<>();
            boolean hasInvalidImages = false;
            int imageIdIndex = 0;

            for (int i = 0; i < imageUrls.length; i++) {
                String imageUrl = imageUrls[i];
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    if (!imageUrl.matches("^https?://.*(jpg|jpeg|png|webp|image).*")) {
                        hasInvalidImages = true;
                            errorMessages.append("Image ").append(i + 1).append(" is wrong with format! "); 
                    } else {
                        while (imageIdIndex < imageIds.length) {
                            int imageId = Integer.parseInt(imageIds[imageIdIndex]);
                            imageIdIndex++;

                            if (!imageDAO.isAvatar(imageId)) {
                                ProductImageDTO detailImage = new ProductImageDTO(productId, imageUrl, false);
                                detailImage.setImageId(imageId);
                                detailImage.setImagePath(imageUrl);
                                images.add(detailImage);
                                break; 
                            }
                        }
                    }
                } else {
                    errorMessages.append("Image ").append(i + 1).append(" cannot be empty! "); 
                }

            }

            ProductDTO updatedProduct = new ProductDTO(productId, brandID, userObjectID, detail, hot, name, color, price, sale, warrantyPeriod, status);
            ProductImageDTO avatarImage = new ProductImageDTO(productId, avatar, true);

            HttpSession session = request.getSession();
            List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");

            boolean checkProduct = productDao.updateProduct(updatedProduct);
            boolean checkAvatar = imageDAO.updateAvatar(avatarImage);
            boolean check = imageDAO.updateDetailImages(images);

            if (errorMessages.length() > 0) {
                request.setAttribute("err", errorMessages.toString()); // Gửi thông báo lỗi
            } else if (checkProduct && checkAvatar && check) {
                for (ProductDTO p : productList) {
                    if (p.getProductId() == updatedProduct.getProductId()) {
                        p.setName(updatedProduct.getName());
                        p.setColor(updatedProduct.getColor());
                        p.setHot(updatedProduct.isHot());
                        p.setUserOjectId(updatedProduct.getUserOjectId());
                        p.setBrandId(updatedProduct.getBrandId());
                        p.setDetail(updatedProduct.getDetail());
                        p.setPrice(updatedProduct.getPrice());
                        p.setProductStatus(updatedProduct.isProductStatus());
                        p.setSale(updatedProduct.getSale());
                        p.setWarrantyPeriod(updatedProduct.getWarrantyPeriod());
                        p.setListImages(imageDAO.getImageByProduct(p.getProductId()));
                    }
                }
                request.setAttribute("LIST_PRODUCT", productList);
                request.setAttribute("ms", "Update successfully");
                url = SUCCESS;

            } else {
                request.setAttribute("err", "Update failed");
            }
        } catch (SQLException e) {
            log("Error at UpdateProductController: " + e.toString());
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
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProductController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProductController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
