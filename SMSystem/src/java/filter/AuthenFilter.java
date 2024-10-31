/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user.UserDTO;

/**
 *
 * @author LENOVO
 */
public class AuthenFilter implements Filter {

    private static List<String> USER_RESOURCE;
    private static List<String> ADMIN_RESOURCE;
    private static List<String> MANAGER_RESOURCE;
    private static List<String> SHIPPER_RESOURCE;
    private static List<String> NON_AUTHEN_RESOURCE;

    private static final String CUS = "CUS";
    private static final String AD = "AD";
    private static final String MN = "MN";
    private static final String SP = "SP";

    private static final String HOME_PAGE = "LoadTopListByCateController";
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthenFilter() {
        USER_RESOURCE = new ArrayList<>();
        USER_RESOURCE.add("shopping-cart.jsp");
        USER_RESOURCE.add("check-out.jsp");
        USER_RESOURCE.add("myOrder.jsp");
        USER_RESOURCE.add("product.jsp");

        ADMIN_RESOURCE = new ArrayList<>();
        ADMIN_RESOURCE.add("adminHome.jsp");
        ADMIN_RESOURCE.add("userList.jsp");
        ADMIN_RESOURCE.add("LoadAdminHomeDataController");
        ADMIN_RESOURCE.add("DeleteUserController");
        ADMIN_RESOURCE.add("LoadUserListController");
        ADMIN_RESOURCE.add("SearchUserByUserNameController");
        ADMIN_RESOURCE.add("ToggleUserRoleController");

        MANAGER_RESOURCE = new ArrayList<>();
        MANAGER_RESOURCE.add("managerHome.jsp");
        MANAGER_RESOURCE.add("brandList.jsp");
        MANAGER_RESOURCE.add("discountList.jsp");
        MANAGER_RESOURCE.add("orderList.jsp");
        MANAGER_RESOURCE.add("categoriesList.jsp");
        MANAGER_RESOURCE.add("orderDetail.jsp");
        MANAGER_RESOURCE.add("productList.jsp");
        MANAGER_RESOURCE.add("LoadManagerHomeDataController");
        MANAGER_RESOURCE.add("CreateBrandController");
        MANAGER_RESOURCE.add("CreateProductController");
        MANAGER_RESOURCE.add("DeleteProductController");
        MANAGER_RESOURCE.add("LoadBrandListController");
        MANAGER_RESOURCE.add("LoadDiscountListController");
        MANAGER_RESOURCE.add("LoadProductController");
        MANAGER_RESOURCE.add("LoadProductListController");
        MANAGER_RESOURCE.add("SearchBrandController");
        MANAGER_RESOURCE.add("SearchProductByNameController");
        MANAGER_RESOURCE.add("ToggleDiscountStatusController");
        MANAGER_RESOURCE.add("ToggleFlashSaleController");
        MANAGER_RESOURCE.add("ToggleProductStatusController");
        MANAGER_RESOURCE.add("UpdateProductController");


        SHIPPER_RESOURCE = new ArrayList<>();
        SHIPPER_RESOURCE.add("shipperPage.jsp");
        SHIPPER_RESOURCE.add("shipList.jsp");
        SHIPPER_RESOURCE.add("shipList.jsp");

        NON_AUTHEN_RESOURCE = new ArrayList<>();
        NON_AUTHEN_RESOURCE.add("login.jsp");
        NON_AUTHEN_RESOURCE.add("register.jsp");
        NON_AUTHEN_RESOURCE.add("homePage.jsp");
        NON_AUTHEN_RESOURCE.add("contact.jsp");
        NON_AUTHEN_RESOURCE.add("myAccount.jsp");
        NON_AUTHEN_RESOURCE.add("product.jsp");
        NON_AUTHEN_RESOURCE.add("shop.jsp");
        NON_AUTHEN_RESOURCE.add("success.jsp");
        NON_AUTHEN_RESOURCE.add("MainController");
        NON_AUTHEN_RESOURCE.add("LoadTopListByCateController");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String url = req.getRequestURI();
            int index = url.lastIndexOf("/");
            String resource = url.substring(index + 1);
            boolean checkContain = false;
            for (String value : NON_AUTHEN_RESOURCE) {
                if (url.contains(value)) {
                    checkContain = true;
                    break;
                }
            }
            if (checkContain) {
                chain.doFilter(request, response);
            } else {
                HttpSession session = req.getSession();
                if (session.getAttribute("LOGIN_USER") == null) {
                    res.sendRedirect(HOME_PAGE);
                } else {
                    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                    String roleID = user.getRoleId();
                    if (AD.equals(roleID) && ADMIN_RESOURCE.contains(resource)) {
                        chain.doFilter(request, response);
                    } else if (CUS.equals(roleID) && USER_RESOURCE.contains(resource)) {
                        chain.doFilter(request, response);
                    } else if (MN.equals(roleID) && MANAGER_RESOURCE.contains(resource)) {
                        chain.doFilter(request, response);
                    } else if (SP.equals(roleID) && SHIPPER_RESOURCE.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(HOME_PAGE);
                    }
                }
            }
        } catch (IOException | ServletException e) {
        }
    }

    /**
     * Return the filter configuration object for this filter.
     * @return 
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthenFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (IOException ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
