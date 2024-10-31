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
    private final static String APPLY_DISCOUNT = "Apply";
    private final static String APPLY_DISCOUNT_CONTROLLER = "ApplyDiscountController";
    private final static String CREATE_DISCOUNT = "Add Discount";
    private final static String CREATE_DISCOUNT_CONTROLLER = "CreateDiscountController";
    private final static String SEARCH_PRODUCT_CUS = "Search";
    private final static String SEARCH_PRODUCT_CUS_CONTROLLER = "SearchProductController";
    private final static String REMOVE_DISCOUNT = "Remove";
    private final static String REMOVE_DISCOUNT_CONTROLLER = "RemoveDiscountController";
    private final static String TOGGLE_SELECT_PRODUCT = "toggleSelectProduct";
    private final static String TOGGLE_SELECT_PRODUCT_CONTROLLER = "ToggleSelectProductController";
    private final static String PROCEED_CHECK_OUT = "ProceedCheckOut";
    private final static String PROCEED_CHECK_OUT_CONTROLLER = "ProceedCheckOutController"; 
    private final static String LOAD_MY_ORDER = "LoadMyOrder";
    private final static String LOAD_MY_ORDER_CONTROLLER = "LoadMyOrderController";
    private final static String SEARCH_ORDER = "Search";
    private final static String SEARCH_ORDER_CONTROLLER = "SearchOrderController"; 
    
    private final static String ORDER_DETAIL = "View-Detail";
    private final static String ORDER_DETAIL_CONTROLLER = "LoadOrderDetailController";
    private final static String ORDER = "LoadOrderList";
    private final static String ORDER_CONTROLLER = "LoadOrderListController";
    
    private final static String LOAD_ORDER_LIST = "LoadOrderList";
    private final static String LOAD_ORDER_LIST_CONTROLLER = "LoadOrderListController";
    private final static String UPDATE_ORDER_STATUS = "UpdateStatus";
    private final static String UPDATE_ORDER_STATUS_CONTROLLER = "UpdateOrderStatusController";
    private final static String FORGOT_PASSWORD = "ForgotPassword";
    private final static String FORGOT_PASSWORD_CONTROLLER = "ForgotPasswordController";
    private final static String CREATE_SIZE= "Add Size and Stock";
    private final static String CREATE_SIZE_CONTROLLER = "CreateSizeController";
    private final static String UPDATE_STOCK = "Update Stock";
    private final static String UPDATE_STOCK_CONTROLLER = "UpdateSizeStockController";

    private final static String PLACE_ORDER = "PlaceOrder";
    private final static String CHECK_OUT_CONTROLLER = "CheckoutController";
    private final static String VIEW_CART = "ViewCart";
    private final static String VIEW_CART_CONTROLLER = "CheckCartItemController";

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
            } else if (LOAD_MANAGER_HOME_DATA.equals(action)) {
                url = LOAD_MANAGER_HOME_CONTROLLER;
            } else if (LOAD_PRODUCT_LIST.equals(action)) {
                url = LOAD_PRODUCT_LIST_CONTROLLER;
            } else if (LOAD_DISCOUNT_LIST.equals(action)) {
                url = LOAD_DISCOUNT_LIST_CONTROLLER;
            } else if (TOGGLE_DISCOUNT_STATUS.equals(action)) {
                url = TOGGLE_DISCOUNT_STATUS_CONTROLLER;
            } else if (SEARCH_BRANCH.equals(action)) {
                url = SEARCH_BRANCH_CONTROLLER;
            } else if (LOAD_BRAND_LIST.equals(action)) {
                url = LOAD_BRAND_LIST_CONTROLLER;
            } else if (DELETE_PRODUCT.equals(action)) {
                url = DELETE_PRODUCT_CONTROLLER;
            } else if (LOAD_ADMIN_HOME.equals(action)) {
                url = LOAD_ADMIN_HOME_CONTROLLER;
            } else if (LOAD_USER_LIST.equals(action)) {
                url = LOAD_USER_LIST_CONTROLLER;
            } else if (TOGGLE_USER_ROLE.equals(action)) {
                url = TOGGLE_USER_ROLE_CONTROLLER;
            } else if (CREATE_USER.equals(action)) {
                url = CREATE_USER_CONTROLLER;
            } else if (DELETE_USER.equals(action)) {
                url = DELETE_USER_CONTROLLER;
            } else if (REMOVE_CART.equals(action)) {
                url = REMOVE_CART_CONTROLLER;
            } else if (HOME_PAGE.equals(action)) {
                url = HOME_PAGE_CONTROLLER;
            } else if (CREATE_BRAND.equals(action)) {
                url = CREATE_BRAND_CONTROLLER;
            } else if (LOAD_SHOP_PAGE.equals(action)) {
                url = LOAD_SHOP_PAGE_CONTROLLER;
            } else if (SORT_SHOP_PAGE.equals(action)) {
                url = SORT_SHOP_PAGE_CONTROLLER;
            } else if (VIEW.equals(action)) {
                url = LOAD_PRODUCT_DETAIL;
            } else if (ADD_TO_CART.equals(action)) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (LOGIN_GOOGLE.equals(action)) {
                url = LOGIN_GOOGLE_CONTROLLER;
            } else if (EDIT_QUANTITY.equals(action)) {
                url = EDIT_QUANTITY_CONTROLLER;
            } else if (SEARCH_PRODUCT_CATEGORIES.equals(action)) {
                url = SEARCH_PRODUCT_CATEGORIES_CONTROLLER;
            } else if (EDIT_SIZE.equals(action)) {
                url = EDIT_SIZE_CONTROLLER;
            } else if (APPLY_DISCOUNT.equals(action)) {
                url = APPLY_DISCOUNT_CONTROLLER;
            } else if (CREATE_DISCOUNT.equals(action)) {
                url = CREATE_DISCOUNT_CONTROLLER;
            } else if (REMOVE_DISCOUNT.equals(action)) {
                url = REMOVE_DISCOUNT_CONTROLLER;
            } else if (SEARCH_PRODUCT_CUS.equals(action)) {
                url = SEARCH_PRODUCT_CUS_CONTROLLER;
            } else if (TOGGLE_SELECT_PRODUCT.equals(action)) {
                url = TOGGLE_SELECT_PRODUCT_CONTROLLER;
            } else if (PROCEED_CHECK_OUT.equals(action)) {
                url = PROCEED_CHECK_OUT_CONTROLLER;
            }else if (LOAD_MY_ORDER.equals(action)) {
                url = LOAD_MY_ORDER_CONTROLLER;
            }else if (SEARCH_ORDER.equals(action)) {
                url = SEARCH_ORDER_CONTROLLER;
            }else if (ORDER_DETAIL.equals(action)) {
                url = ORDER_DETAIL_CONTROLLER;
            }else if (ORDER.equals(action)) {
                url = ORDER_CONTROLLER;
            } else if (LOAD_ORDER_LIST.equals(action)) {
                url = LOAD_ORDER_LIST_CONTROLLER;
            }else if (UPDATE_ORDER_STATUS.equals(action)) {
                url = UPDATE_ORDER_STATUS_CONTROLLER;
            }else if (FORGOT_PASSWORD.equals(action)) {
                url = FORGOT_PASSWORD_CONTROLLER;
            } else if (PLACE_ORDER.equals(action)) {
                url = CHECK_OUT_CONTROLLER;
            }else if (VIEW_CART.equals(action)) {
                url = VIEW_CART_CONTROLLER;
            }else if (CREATE_SIZE.equals(action)) {
                url = CREATE_SIZE_CONTROLLER;
            }else if (UPDATE_STOCK.equals(action)) {
                url = UPDATE_STOCK_CONTROLLER;
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
