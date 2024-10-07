package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.category.UserObjectDAO;
import model.category.UserObjectDTO;
import model.category.BrandDAO;
import model.category.BrandDTO;
import java.sql.SQLException;
import java.util.List;

public final class productList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Products</title>\r\n");
      out.write("        <title>Kẻ kiểm soát thông tin</title>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/user1.css\" />\r\n");
      out.write("        <link\r\n");
      out.write("            rel=\"stylesheet\"\r\n");
      out.write("            href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css\"\r\n");
      out.write("            />\r\n");
      out.write("        <link rel=\"icon\" href=\"favicon_io/favicon.ico\" type=\"img/x-icon\" />\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <main class=\"main-wrap\">\r\n");
      out.write("            <header class=\"main-head\">\r\n");
      out.write("                <div class=\"main-nav\">\r\n");
      out.write("                    <nav class=\"navbar\">\r\n");
      out.write("                        <div class=\"navbar-nav\">\r\n");
      out.write("                            <div class=\"title\">\r\n");
      out.write("                                <h3>\r\n");
      out.write("                                    <img src=\"favicon_io/favicon-32x32.png\" alt=\"anh chu cho\" />\r\n");
      out.write("                                    <span class=\"title-text\">Nice</span>\r\n");
      out.write("                                </h3>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <ul class=\"nav-list\">\r\n");
      out.write("                                <li class=\"nav-list-item\">\r\n");
      out.write("                                    <a href=\"adminHome.jsp\" class=\"nav-link\">\r\n");
      out.write("                                        <i class=\"fa-solid fa-house\"></i>\r\n");
      out.write("                                        <span class=\"link-text\">Home</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <li class=\"nav-list-item\">\r\n");
      out.write("                                    <a href=\"userList.jsp\" class=\"nav-link\">\r\n");
      out.write("                                        <i class=\"fa-solid fa-user\"></i>\r\n");
      out.write("                                        <span class=\"link-text\">Accounts</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <li class=\"nav-list-item\">\r\n");
      out.write("                                    <a href=\"categoriesList.jsp\" class=\"nav-link\">\r\n");
      out.write("                                        <i class=\"fa-solid fa-list\"></i>\r\n");
      out.write("                                        <span class=\"link-text\">Categories</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li class=\"nav-list-item\">\r\n");
      out.write("                                    <a href=\"productList.jsp\" class=\"nav-link\">\r\n");
      out.write("                                        <i class=\"fa-solid fa-capsules\"></i>\r\n");
      out.write("                                        <span class=\"link-text\">Products</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <li class=\"nav-list-item\">\r\n");
      out.write("                                    <a href=\"discountList.jsp\" class=\"nav-link\">\r\n");
      out.write("                                        <i class=\"fa-solid fa-percent\"></i>\r\n");
      out.write("                                        <span class=\"link-text\">Discount</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <li class=\"nav-list-item\">\r\n");
      out.write("                                    <a href=\"orderList.jsp\" class=\"nav-link\">\r\n");
      out.write("                                        <i class=\"fa-solid fa-file-invoice\"></i>\r\n");
      out.write("                                        <span class=\"link-text\">Order</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <li class=\"nav-list-item\">\r\n");
      out.write("                                    <a href=\"LogoutController\" class=\"nav-link\">\r\n");
      out.write("                                        <i class=\"fa-solid fa-right-from-bracket\"></i>\r\n");
      out.write("                                        <span class=\"link-text\">Log out</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </nav>\r\n");
      out.write("                </div>\r\n");
      out.write("            </header>\r\n");
      out.write("            <section class=\"showcase\">\r\n");
      out.write("                <div class=\"overlay\">\r\n");
      out.write("                    <div class=\"head\">\r\n");
      out.write("                        <button class=\"toggler\">\r\n");
      out.write("                            <i class=\"fa-solid fa-bars\"></i>\r\n");
      out.write("                        </button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"content\">\r\n");
      out.write("                        <div class=\"welcome\">\r\n");
      out.write("\r\n");
      out.write("                            <!-- Button trigger modal -->\r\n");
      out.write("                            <button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#addModal\">\r\n");
      out.write("                                <i class=\"fa-solid fa-plus\"></i> Add new Product\r\n");
      out.write("                            </button>\r\n");
      out.write("                            ");


                                ProductDAO productDao = new ProductDAO();
                                List<ProductDTO> productList = null;
                                String noResults = (String) request.getAttribute("NO_RESULTS");

                                productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
                                if (productList == null) {
                                    productList = productDao.getAllProduct();
                                }

                                String ms = "";
                                String err = "";
                                if (request.getAttribute("ms") != null) {
                                    ms = (String) request.getAttribute("ms");
                                }
                                if (request.getAttribute("err") != null) {
                                    err = (String) request.getAttribute("err");
                                }
                                if (ms != null || err != null) {

                            
      out.write("\r\n");
      out.write("                            <div class=\"mes-suc\">\r\n");
      out.write("                                ");
      out.print(ms);
      out.write(' ');
      out.print(err);
      out.write("\r\n");
      out.write("                            </div>                          \r\n");
      out.write("                            ");
}
      out.write("\r\n");
      out.write("                            <!-- Modal Add -->\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"modal fade\" id=\"addModal\" tabindex=\"-1\" aria-labelledby=\"addModalLabel\" aria-hidden=\"true\">\r\n");
      out.write("                                <div class=\"modal-dialog\">\r\n");
      out.write("                                    <div class=\"modal-content\">\r\n");
      out.write("                                        <div class=\"modal-header\">\r\n");
      out.write("                                            <h1 class=\"modal-title fs-5\" id=\"addModalLabel\">Create new product </h1>\r\n");
      out.write("                                            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                        <form action=\"CreateProductController\" method=\"POST\">\r\n");
      out.write("                                            <div class=\"modal-body\">\r\n");
      out.write("\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Name</span>\r\n");
      out.write("                                                    <input name=\"Name\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" required=\"\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Category</span>\r\n");
      out.write("                                                    <select name=\"userObjectID\" class=\"form-control\" required>\r\n");
      out.write("                                                        ");

                                                            UserObjectDAO categoriesDao = new UserObjectDAO();
                                                            List<UserObjectDTO> userObjectList = categoriesDao.getAllUserObject();
                                                            for (UserObjectDTO uo : userObjectList) {
                                                        
      out.write("\r\n");
      out.write("                                                        <option value=\"");
      out.print( uo.getUserObjectId());
      out.write('"');
      out.write('>');
      out.print( uo.getUserObjectName());
      out.write("</option>\r\n");
      out.write("                                                        ");
 } 
      out.write("\r\n");
      out.write("                                                    </select>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Brand</span>\r\n");
      out.write("                                                    <select name=\"brandID\" class=\"form-control\" required>\r\n");
      out.write("                                                        ");

                                                            BrandDAO brandDao = new BrandDAO();
                                                            List<BrandDTO> brandList = brandDao.getAllBrand();
                                                            for (BrandDTO brand : brandList) {
                                                        
      out.write("\r\n");
      out.write("                                                        <option value=\"");
      out.print( brand.getBrandId());
      out.write('"');
      out.write('>');
      out.print( brand.getBrandName());
      out.write("</option>\r\n");
      out.write("                                                        ");
 } 
      out.write("\r\n");
      out.write("                                                    </select>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Price</span>\r\n");
      out.write("                                                    <input name=\"price\" type=\"number\" step=\"0.01\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" required=\"\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Sale</span>\r\n");
      out.write("                                                    <input name=\"sale\" type=\"number\" step=\"0.01\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Avatar(URL)</span>\r\n");
      out.write("                                                    <input name=\"avatar\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Color</span>\r\n");
      out.write("                                                    <input name=\"color\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" required=\"\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Size</span>\r\n");
      out.write("                                                    <input name=\"size\" type=\"number\" step=\"0.01\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" required=\"\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Stock</span>\r\n");
      out.write("                                                    <input name=\"stock\" type=\"number\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" required=\"\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Detail</span>\r\n");
      out.write("                                                    <textarea name=\"detail\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" ></textarea>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Product Image 1 (URL)</span>\r\n");
      out.write("                                                    <input name=\"productImage1\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" >\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Product Image 2 (URL)</span>\r\n");
      out.write("                                                    <input name=\"productImage2\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" >\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Product Image 3 (URL)</span>\r\n");
      out.write("                                                    <input name=\"productImage3\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" >\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Product Image 4 (URL)</span>\r\n");
      out.write("                                                    <input name=\"productImage4\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" >\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div class=\"modal-footer\">\r\n");
      out.write("                                                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\r\n");
      out.write("                                                <input type=\"submit\" name=\"action\" value=\"Create Product\" class=\"btn btn-primary\"/>\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            ");

                                String searchProductName = request.getParameter("searchProductName");
                                if (searchProductName == null) {
                                    searchProductName = "";
                                }
                            
      out.write("\r\n");
      out.write("                            <div class=\"search-form\">\r\n");
      out.write("                                <form action=\"SearchProductByNameController\" method=\"POST\">\r\n");
      out.write("                                    Search Product: <input type=\"text\" name=\"searchProductName\" placeholder=\"Enter product name\" value=\"");
      out.print( searchProductName);
      out.write("\"/>\r\n");
      out.write("                                    <button type=\"submit\" value=\"SearchProductName\">Search</button>\r\n");
      out.write("                                </form>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"welcome\">\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"table-tilte\">Product Table</div>\r\n");
      out.write("\r\n");
      out.write("                            ");
 if (noResults != null) {
      out.write("\r\n");
      out.write("                            <p>");
      out.print( noResults);
      out.write("</p>\r\n");
      out.write("                            ");
 } else if (productList != null && !productList.isEmpty()) { 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                            <table class=\"table table-hover\">\r\n");
      out.write("                                <thead>\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <th>Avatar</th>\r\n");
      out.write("                                        <th>Name</th>\r\n");
      out.write("                                        <th>Category</th>\r\n");
      out.write("                                        <th>Brand</th>\r\n");
      out.write("                                        <th>Price</th>\r\n");
      out.write("                                        <th>Sale</th>\r\n");
      out.write("                                        <th>Sale Price</th>\r\n");
      out.write("                                        <th>Flash Sale</th>\r\n");
      out.write("                                        <th>Stock</th>\r\n");
      out.write("                                        <th>Status</th>\r\n");
      out.write("                                        <th>Actions</th>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                </thead>\r\n");
      out.write("                                <tbody>\r\n");
      out.write("                                    ");

                                        if (productList != null) {
                                            for (ProductDTO product : productList) {
                                                UserObjectDTO userObject = categoriesDao.getUserObjectById(product.getUserOjectId());
                                                BrandDTO brandShow = brandDao.getBrandById(product.getBrandId());
                                    
      out.write("\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td><img src=\"");
      out.print( product.getAvatarPath());
      out.write("\" alt=\"");
      out.print( product.getName());
      out.write("\" style=\"width: 70px; height: 70px;\"></td>\r\n");
      out.write("                                        <td>");
      out.print( product.getName());
      out.write("</td>\r\n");
      out.write("                                        <td>");
      out.print( userObject.getUserObjectName());
      out.write("</td> \r\n");
      out.write("                                        <td>");
      out.print( brandShow.getBrandName());
      out.write("</td>\r\n");
      out.write("                                        <td>");
      out.print( product.getPrice());
      out.write("$</td>\r\n");
      out.write("                                        <td>\r\n");
      out.write("                                            ");

                                                double salePercentage = product.getSale() * 100;
                                                double roundedSalePercentage = Math.ceil(salePercentage * 100) / 100.0;
                                                double salePrice = product.getPrice() - (product.getSale() * product.getPrice());
                                            
      out.write("\r\n");
      out.write("                                            ");
      out.print( String.format("%.0f%%", roundedSalePercentage));
      out.write("\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td>\r\n");
      out.write("                                            ");
      out.print( String.format("%.2f", salePrice));
      out.write("$\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td>\r\n");
      out.write("                                            <form action=\"ToggleFlashSaleController\" method=\"POST\">\r\n");
      out.write("                                                <input type=\"hidden\" name=\"productId\" value=\"");
      out.print( product.getProductId());
      out.write("\"/>\r\n");
      out.write("                                                <input type=\"hidden\" name=\"action\" value=\"toggleFlashSale\"/>\r\n");
      out.write("                                                <select name=\"Hot\" onchange=\"this.form.submit()\">\r\n");
      out.write("                                                    <option value=\"1\" ");
      out.print( product.isHot() ? "selected" : "");
      out.write(">On</option>\r\n");
      out.write("                                                    <option value=\"0\" ");
      out.print( !product.isHot() ? "selected" : "");
      out.write(">Off</option>\r\n");
      out.write("                                                </select>\r\n");
      out.write("                                            </form>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td>\r\n");
      out.write("                                            ");
      out.print( "Cái này in ra stock mà tao chưa làm_Kí tên: Quý"
                                            
                                            );
      out.write("\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td>\r\n");
      out.write("                                            <form action=\"ToggleProductStatusController\" method=\"POST\">\r\n");
      out.write("                                                <input type=\"hidden\" name=\"productId\" value=\"");
      out.print( product.getProductId());
      out.write("\"/>\r\n");
      out.write("                                                <input type=\"hidden\" name=\"action\" value=\"toggleProductStatus\"/>\r\n");
      out.write("                                                <select name=\"Product_Status\" onchange=\"this.form.submit()\">\r\n");
      out.write("                                                    <option value=\"1\" ");
      out.print( product.isProductStatus()? "selected" : "");
      out.write(">Active</option>\r\n");
      out.write("                                                    <option value=\"0\" ");
      out.print( !product.isProductStatus() ? "selected" : "");
      out.write(">Inactive</option>\r\n");
      out.write("                                                </select>\r\n");
      out.write("                                            </form>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td>\r\n");
      out.write("                                            <input type=\"hidden\" name=\"productId\"  value=\"");
      out.print(product.getProductId());
      out.write("\" />\r\n");
      out.write("                                            <button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#updateModal");
      out.print(product.getProductId());
      out.write("\">\r\n");
      out.write("                                                <i class=\"fa-solid fa-pen-to-square\"></i>\r\n");
      out.write("                                            </button>\r\n");
      out.write("\r\n");
      out.write("                                            <!-- Modal Update -->\r\n");
      out.write("                                            <div class=\"modal fade\" id=\"updateModal");
      out.print(product.getProductId());
      out.write("\" tabindex=\"-1\" aria-labelledby=\"updateModalLabel\" aria-hidden=\"true\">\r\n");
      out.write("                                                <div class=\"modal-dialog\">\r\n");
      out.write("                                                    <div class=\"modal-content\">\r\n");
      out.write("                                                        <div class=\"modal-header\">\r\n");
      out.write("                                                            <h1 class=\"modal-title fs-5\" id=\"updateModalLabel\">Update product '");
      out.print(product.getName());
      out.write("' information</h1>\r\n");
      out.write("                                                            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n");
      out.write("                                                        </div>\r\n");
      out.write("                                                        <form action=\"UpdateProductController\" method=\"POST\">\r\n");
      out.write("                                                            <div class=\"modal-body\">\r\n");
      out.write("                                                                <input type=\"hidden\" name=\"productId\"  value=\"");
      out.print(product.getProductId());
      out.write("\" />\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Product Name</span>\r\n");
      out.write("                                                                    <input name=\"name\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" value=\"");
      out.print( product.getName());
      out.write("\">\r\n");
      out.write("                                                                </div>\r\n");
      out.write("\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Detail</span>\r\n");
      out.write("                                                                    <textarea name=\"detail\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\">");
      out.print( product.getDetail());
      out.write("</textarea>\r\n");
      out.write("                                                                </div>\r\n");
      out.write("\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Avatar(URL)</span>\r\n");
      out.write("                                                                    <input name=\"avatar\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" value=\"");
      out.print( product.getAvatarPath());
      out.write("\">\r\n");
      out.write("                                                                </div>\r\n");
      out.write("                                                                ");

                                                                    int selectedBrandID = product.getBrandId();
                                                                
      out.write("\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Brand</span>\r\n");
      out.write("                                                                    <select name=\"brandID\" class=\"form-control\">\r\n");
      out.write("                                                                        <");

                                                                            for (BrandDTO brand : brandList) {
                                                                                String selected = (brand.getBrandId() == selectedBrandID) ? "selected" : "";
                                                                        
      out.write("\r\n");
      out.write("                                                                        <option value=\"");
      out.print( brand.getBrandId());
      out.write('"');
      out.write(' ');
      out.print( selected);
      out.write('>');
      out.print( brand.getBrandName());
      out.write("</option>\r\n");
      out.write("                                                                        ");
 } 
      out.write("\r\n");
      out.write("                                                                    </select>\r\n");
      out.write("                                                                </div>\r\n");
      out.write("                                                                ");

                                                                    int selectedCategoryID = product.getUserOjectId();
                                                                
      out.write("\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Category</span>\r\n");
      out.write("                                                                    <select name=\"userObjectID\" class=\"form-control\">\r\n");
      out.write("                                                                        ");

                                                                            for (UserObjectDTO uOb : userObjectList) {
                                                                                String selected = (uOb.getUserObjectId()== selectedCategoryID) ? "selected" : "";
                                                                        
      out.write("\r\n");
      out.write("                                                                        <option value=\"");
      out.print( uOb.getUserObjectId());
      out.write('"');
      out.write(' ');
      out.print( selected);
      out.write('>');
      out.print( uOb.getUserObjectName());
      out.write("</option>\r\n");
      out.write("                                                                        ");
 }
      out.write("\r\n");
      out.write("                                                                    </select>\r\n");
      out.write("                                                                </div>\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Price</span>\r\n");
      out.write("                                                                    <input name=\"price\" type=\"number\" step=\"0.01\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" value=\"");
      out.print( product.getPrice());
      out.write("\">\r\n");
      out.write("                                                                </div>\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Color</span>\r\n");
      out.write("                                                                    <input name=\"color\" type=\"text\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" value=\"");
      out.print( product.getColor());
      out.write("\">\r\n");
      out.write("                                                                </div>\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Size</span>\r\n");
      out.write("                                                                    <input name=\"size\" type=\"number\" step=\"0.01\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" value=\"");
      out.print( "Cái này ở trang productDetail");
      out.write("\">\r\n");
      out.write("                                                                </div>\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Stock</span>\r\n");
      out.write("                                                                    <input name=\"stock\" type=\"number\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" value=\"");
      out.print( "cái này cũng rứa");
      out.write("\">\r\n");
      out.write("                                                                </div>\r\n");
      out.write("                                                                <div class=\"input-group input-group-sm mb-3\">\r\n");
      out.write("                                                                    <span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">Sale</span>\r\n");
      out.write("                                                                    <input name=\"sale\" type=\"number\" step=\"0.01\" class=\"form-control\" aria-label=\"Sizing example input\" aria-describedby=\"inputGroup-sizing-sm\" value=\"");
      out.print( product.getSale());
      out.write("\">\r\n");
      out.write("                                                                </div>\r\n");
      out.write("\r\n");
      out.write("                                                            </div>\r\n");
      out.write("                                                            <div class=\"modal-footer\">\r\n");
      out.write("                                                                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\r\n");
      out.write("                                                                <input type=\"submit\" name=\"action\" value=\"Update Product\" class=\"btn btn-primary\"/>\r\n");
      out.write("                                                            </div>\r\n");
      out.write("                                                        </form>\r\n");
      out.write("                                                    </div>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                    ");

                                            }
                                        }
                                    
      out.write("\r\n");
      out.write("                                </tbody>\r\n");
      out.write("                            </table>\r\n");
      out.write("                            ");
 } else { 
      out.write("\r\n");
      out.write("                            <p>No products found.</p>\r\n");
      out.write("                            ");
 }
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("            </section>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </main>\r\n");
      out.write("\r\n");
      out.write("        <script src=\"js/app.js\"></script>\r\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
