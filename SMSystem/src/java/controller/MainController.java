/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String WELLCOME_PAGE = "LoadTopListByCateController";
    private final static String LOGIN = "Sign In";
    private final static String LOGIN_CONTROLLER = "LoginController";
    private final static String UPDATE_PRODUCT = "Update Product";
    private final static String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";
    private final static String LOAD_DATA = "LoadProductData";
    private final static String LOAD_PRODUCT_DATA_CONTROLLER = "LoadProductController";
    private final static String UPDATE_USER = "Update";
    private final static String UPDATE_USER_CONTROLLER = "UpdateUserController";
    private final static String LOAD_MANAGER_HOME_DATA = "LoadManagerHomeData";
    private final static String LOAD_MANAGER_HOME_CONTROLLER = "LoadManagerHomeDataController";
    private final static String LOAD_PRODUCT_LIST = "LoadProductList";
    private final static String LOAD_PRODUCT_LIST_CONTROLLER = "LoadProductListController";
    private final static String LOAD_DISCOUNT_LIST = "LoadDiscountList";
    private final static String LOAD_DISCOUNT_LIST_CONTROLLER = "LoadDiscountListController";
    private final static String CREATE_PRODUCT = "Create Product";
    private final static String CREATE_PRODUCT_CONTROLLER = "CreateProductController";
    private final static String SEARCH_PRODUCT = "SearchProductName";
    private final static String SEARCH_PRODUCT_CONTROLLER = "SearchProductByNameController";
    private final static String TOGGLE_FLASH_SALE = "toggleFlashSale";
    private final static String TOGGLE_FLASH_SALE_CONTROLLER = "ToggleFlashSaleController";
    private final static String TOGGLE_PRODUCT_STATUS = "toggleProductStatus";
    private final static String TOGGLE_PRODUCT_STATUS_CONTROLLER = "ToggleProductStatusController";

    private final static String CREATE_USER = "Sign Up";
    private final static String CREATE_USER_CONTROLLER = "CreateUserController";
    private final static String SEARCH_USER = "SearchUserName";
    private final static String SEARCH_USER_CONTROLLER = "SearchUserByUserNameController";
    private final static String TOGGLE_DISCOUNT_STATUS = "toggleDiscountStatus";
    private final static String TOGGLE_DISCOUNT_STATUS_CONTROLLER = "ToggleDiscountStatusController";
    private final static String SEARCH_BRANCH = "SearchBrandName";
    private final static String SEARCH_BRANCH_CONTROLLER = "SearchBrandController";
    private final static String LOAD_BRAND_LIST = "LoadBrandList";
    private final static String LOAD_BRAND_LIST_CONTROLLER = "LoadBrandListController";
    private final static String DELETE_PRODUCT = "Delete Product";
    private final static String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    private final static String LOAD_ADMIN_HOME = "LoadAdminHome";
    private final static String LOAD_ADMIN_HOME_CONTROLLER = "LoadAdminHomeDataController";
    private final static String LOAD_USER_LIST = "LoadUserList";
    private final static String LOAD_USER_LIST_CONTROLLER = "LoadUserListController";
    private final static String TOGGLE_USER_ROLE = "toggleUserRole";
    private final static String TOGGLE_USER_ROLE_CONTROLLER = "ToggleUserRoleController";
    private final static String DELETE_USER = "Delete User";
    private final static String DELETE_USER_CONTROLLER = "DeleteUserController";
    private final static String REMOVE_CART = "doDelete";
    private final static String REMOVE_CART_CONTROLLER = "RemoveCartController";
    private final static String CREATE_BRAND = "Create Brand";
    private final static String CREATE_BRAND_CONTROLLER = "CreateBrandController";
    private final static String LOAD_SHOP_PAGE = "ShopPage";
    private final static String LOAD_SHOP_PAGE_CONTROLLER = "LoadShopPageController";
    private final static String HOME_PAGE = "HomePage";
    private final static String HOME_PAGE_CONTROLLER = "LoadTopListByCateController";
    private final static String SORT_SHOP_PAGE = "SortShopPage";
    private final static String SORT_SHOP_PAGE_CONTROLLER = "SortProductListController";
    private final static String VIEW = "View";
    private final static String LOAD_PRODUCT_DETAIL = "LoadProductDetailController";

    private final static String ADD_TO_CART = "Add To Cart";
    private final static String ADD_TO_CART_CONTROLLER = "AddToCartController";
    private static final String LOGIN_GOOGLE = "LoginGoogle";
    private static final String LOGIN_GOOGLE_CONTROLLER = "LoginGoogleController";
    private final static String EDIT_QUANTITY = "Edit quantity";
    private final static String EDIT_QUANTITY_CONTROLLER = "EditQuantityController";
    private final static String SEARCH_PRODUCT_CATEGORIES = "SearchCategories";
    private final static String SEARCH_PRODUCT_CATEGORIES_CONTROLLER = "SearchCategoriesController";
    private final static String EDIT_SIZE = "Edit Size";
    private final static String EDIT_SIZE_CONTROLLER = "EditSizeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELLCOME_PAGE;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (UPDATE_PRODUCT.equals(action)) {
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if (LOAD_DATA.equals(action)) {
                url = LOAD_PRODUCT_DATA_CONTROLLER;
            } else if (UPDATE_USER.equals(action)) {
                url = UPDATE_USER_CONTROLLER;
            } else if (CREATE_PRODUCT.equals(action)) {
                url = CREATE_PRODUCT_CONTROLLER;
            } else if (TOGGLE_FLASH_SALE.equals(action)) {
                url = TOGGLE_FLASH_SALE_CONTROLLER;
            } else if (TOGGLE_PRODUCT_STATUS.equals(action)) {
                url = TOGGLE_PRODUCT_STATUS_CONTROLLER;
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (SEARCH_USER.equals(action)) {
                url = SEARCH_USER_CONTROLLER;
            } else if (EDIT_SIZE.equals(action)) {
                url = EDIT_SIZE_CONTROLLER;
            }else if(SEARCH_PRODUCT_CATEGORIES.equals(action)){
                url=SEARCH_PRODUCT_CATEGORIES_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
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
