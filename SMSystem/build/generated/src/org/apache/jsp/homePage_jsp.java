package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import model.cart.CartItems;
import model.cart.CartDTO;
import java.util.List;
import model.product.ProductDTO;
import model.user.UserDTO;

public final class homePage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"description\" content=\"codelean Template\">\r\n");
      out.write("        <meta name=\"keywords\" content=\"codelean, unica, creative, html\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n");
      out.write("        <title>Home page</title>\r\n");
      out.write("\r\n");
      out.write("        <!-- Google Font -->\r\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Css Styles -->\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/font-awesome.min.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/themify-icons.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/elegant-icons.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/owl.carousel.min.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/nice-select.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/jquery-ui.min.css\" type=\"text/css\"> \r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/slicknav.min.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style1.css\" type=\"text/css\">\r\n");
      out.write("        <link rel=\"icon\" href=\"favicon_io (1)/favicon.ico\" type=\"img/x-icon\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style3.css\" type=\"text/css\">\r\n");
      out.write("        <link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css\">\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("        <!-- Start coding here -->\r\n");
      out.write("        <!-- Page PreOrder -->\r\n");
      out.write("        <div id=\"preloder\">\r\n");
      out.write("            <div class=\"loader\"></div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- Header section begin -->\r\n");
      out.write("        <header class=\"header-section\">\r\n");
      out.write("            <div class=\"header-top\">\r\n");
      out.write("                <div class=\"container\">\r\n");
      out.write("                    <div class=\"ht-left\">\r\n");
      out.write("                        <div class=\"mail-service\">\r\n");
      out.write("                            <i class=\"fa fa-envelope\">\r\n");
      out.write("                                smsystem@gmail.com\r\n");
      out.write("                            </i>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"phone-service\">\r\n");
      out.write("                            <i class=\"fa fa-phone\">\r\n");
      out.write("                                +84 123456789\r\n");
      out.write("                            </i>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    ");

                        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    
      out.write("\r\n");
      out.write("                    <div class=\"ht-right\">\r\n");
      out.write("                        <div class=\"login-panel\" id=\"user-btn\">\r\n");
      out.write("                            <i class=\"fa fa-user\">  ");
      out.print( (loginUser != null) ? loginUser.getFullName() : "Guest");
      out.write("</i>\r\n");
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <section class=\"user\">\r\n");
      out.write("                            <div class=\"user-setting\">\r\n");
      out.write("                                <div class=\"content\">\r\n");
      out.write("                                    <div><a href=\"myAccount.jsp\">My account</a></div>\r\n");
      out.write("                                    <div><a href=\"myOrder.jsp\">Order Status</a></div>\r\n");
      out.write("                                    <div><a href=\"LogoutController\">Logout</a></div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                        </section>\r\n");
      out.write("                        <div class=\"lan-selector\">\r\n");
      out.write("                            <select class=\"language_drop\" name=\"countries\" id=\"countries\" style=\"width: 300px;\">\r\n");
      out.write("                                <option value=\"yt\" data-image=\"img/flag-1.jpg\" data-imagecss=\"flag yt\" data-title=\"English\">\r\n");
      out.write("                                    English</option>\r\n");
      out.write("                                <option value=\"yu\" data-image=\"img/flag-2.jpg\" data-imagecss=\"flag yu\" data-title=\"German\">\r\n");
      out.write("                                    German</option>\r\n");
      out.write("                            </select>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"top-social\">\r\n");
      out.write("                            <a href=\"#\"><i class=\"ti-facebook\"></i></a>\r\n");
      out.write("                            <a href=\"#\"><i class=\"ti-twitter-alt\"></i></a>\r\n");
      out.write("                            <a href=\"#\"><i class=\"ti-linkedin\"></i></a>\r\n");
      out.write("                            <a href=\"#\"><i class=\"ti-pinterest\"></i></a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"inner-header\">\r\n");
      out.write("                    <div class=\"row\">\r\n");
      out.write("                        <div class=\"col-lg-2 col-md-2\">\r\n");
      out.write("                            <div class=\"logo\">\r\n");
      out.write("                                <a href=\"index.jsp\">\r\n");
      out.write("                                    <img src=\"img/logoweb.png\" height=\"100%\" width=\"100%\">\r\n");
      out.write("                                </a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"col-lg-7 col-md-7\">\r\n");
      out.write("                            <form action=\"MainController\" method=\"get\">\r\n");
      out.write("                                <div class=\"advanced-search\">\r\n");
      out.write("                                    <button type=\"button\" class=\"category-btn\">All Categories</button>\r\n");
      out.write("                                    <div class=\"input-group\">\r\n");
      out.write("                                        <input style=\"color: black;\" type=\"text\" name=\"text\" placeholder=\"What do you need?\">\r\n");
      out.write("                                        <button type=\"submit\" name=\"action\" value=\"Search\"><i class=\"ti-search\"></i></button>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"col-lg-3 col-md-3 text-right\">\r\n");
      out.write("                            <ul class=\"nav-right\">\r\n");
      out.write("                                ");

                                    String sizeWishlist = (String) session.getAttribute("sizeWishlist");
                                    if(sizeWishlist==null){
                                        sizeWishlist="0";
                                    }
                                    
                                    
      out.write("\r\n");
      out.write("                                <li class=\"heart-icon\">\r\n");
      out.write("                                    <a href=\"wishlist.jsp\">\r\n");
      out.write("                                        <i class=\"icon_heart_alt\"></i>\r\n");
      out.write("                                        <span>");
      out.print( sizeWishlist );
      out.write("</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                ");

                                    String size = (String) session.getAttribute("size");
                                    if (size == null) {
                                        size = "0";
                                    }
                                    boolean isEmptyCart = size.equals("0");
                                
      out.write("\r\n");
      out.write("                                <li class=\"cart-icon\">\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <i class=\"icon_bag_alt\"></i>\r\n");
      out.write("                                        <span>");
      out.print( size);
      out.write("</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                    <div class=\"cart-hover\">\r\n");
      out.write("                                        <div class=\"select-items\">\r\n");
      out.write("                                            ");
 if (isEmptyCart) { 
      out.write("\r\n");
      out.write("                                            <p>No product in cart. Buy more</p>\r\n");
      out.write("                                            <div class=\"select-total\">\r\n");
      out.write("                                                <span>total:</span>\r\n");
      out.write("                                                <h5>$00.00</h5>\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div class=\"select-button\">\r\n");
      out.write("                                                <a href=\"shopping-cart.jsp\" class=\"primary-btn view-card\">VIEW CART</a>\r\n");
      out.write("                                               \r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            ");
 } else { 
      out.write("\r\n");
      out.write("                                            <table>\r\n");
      out.write("                                                <tbody>\r\n");
      out.write("                                                    ");

                                                        CartDTO cart = (CartDTO) session.getAttribute("CART");
                                                        if (cart == null) {
                                                            cart = new CartDTO();
                                                        }
                                                        List<CartItems> ls = cart.getCartItemsList();
                                                        if (ls != null) {
                                                            double total = 0;
                                                            int count = 0;
                                                            for (CartItems ele : ls) {
                                                                total += (ele.getCartItemId()* ele.getQuantity());
                                                    
      out.write("\r\n");
      out.write("                                                    <tr>\r\n");
      out.write("                                                        <td class=\"si-pic\"><img src=\"");
      out.print( ele.getProduct().getAvatarPath());
      out.write("\" style=\"height: 76px\"></td>\r\n");
      out.write("                                                        <td class=\"si-text\">\r\n");
      out.write("                                                            <div class=\"product-selected\">\r\n");
      out.write("                                                                <p>$");
      out.print( String.format("%.1f", ele.getProduct().getPrice()));
      out.write(" x ");
      out.print( ele.getQuantity());
      out.write("</p>\r\n");
      out.write("                                                                <h6>");
      out.print( ele.getProduct().getName());
      out.write("</h6>\r\n");
      out.write("                                                                <h6>Size ");
      out.print(ele.getSize());
      out.write("</h6>\r\n");
      out.write("                                                            </div>\r\n");
      out.write("                                                        </td>\r\n");
      out.write("                                                        <td class=\"si-close\">\r\n");
      out.write("                                                            <a href=\"RemoveServlet?pId=");
      out.print( count);
      out.write("&url=index.jsp\" onclick=\"doDelete('");
      out.print(ele.getProduct().getName());
      out.write("', event)\">\r\n");
      out.write("                                                                <i class=\"ti-close\"></i>\r\n");
      out.write("                                                            </a>\r\n");
      out.write("                                                        </td>\r\n");
      out.write("                                                    </tr>\r\n");
      out.write("                                                    ");

                                                            count++;
                                                        }
                                                    
      out.write("\r\n");
      out.write("                                                </tbody>\r\n");
      out.write("                                            </table>\r\n");
      out.write("                                            <div class=\"select-total\">\r\n");
      out.write("                                                <span>total:</span>\r\n");
      out.write("                                                <h5>$");
      out.print( String.format("%.1f", total));
      out.write("</h5>\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            ");

                                                }
                                            
      out.write("\r\n");
      out.write("                                            <div class=\"select-button\">\r\n");
      out.write("                                                <a href=\"shopping-cart.jsp\" class=\"primary-btn view-card\">VIEW CART</a>\r\n");
      out.write("                                            \r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            ");
 } 
      out.write("\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"nav-item\">\r\n");
      out.write("                <div class=\"container\">\r\n");
      out.write("                    <div class=\"nav-depart\">\r\n");
      out.write("                        <div class=\"depart-btn\">\r\n");
      out.write("                            <i class=\"ti-menu\"></i>\r\n");
      out.write("                            <span>All Departments</span>\r\n");
      out.write("                            <ul class=\"depart-hover\">\r\n");
      out.write("                                <li><a href=\"SearchServlet?type=2\">Women's Clothing</a></li>\r\n");
      out.write("                                <li><a href=\"SearchServlet?type=1\">Men's Clothing</a></li>\r\n");
      out.write("                                <li><a href=\"SearchServlet?type=3\">Kid's Clothing</a></li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <nav class=\"nav-menu mobile-menu\">\r\n");
      out.write("                        <ul>\r\n");
      out.write("                            <li class=\"active\"><a href=\"index.jsp\">Home</a></li>\r\n");
      out.write("                            <li><a href=\"shop.jsp\">Shop</a></li>\r\n");
      out.write("                            <li><a href=\"contact.jsp\">Contact</a></li>\r\n");
      out.write("                            <li><a href=\"\">Pages</a>\r\n");
      out.write("                                <ul class=\"dropdown\">\r\n");
      out.write("                                    <li><a href=\"shopping-cart.jsp\">Shopping Cart</a></li>\r\n");
      out.write("                                  \r\n");
      out.write("                                </ul>\r\n");
      out.write("                            </li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                    </nav>\r\n");
      out.write("                    <div id=\"mobile-menu-wrap\"></div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </header>\r\n");
      out.write("        <!-- Header Section End -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!--  -->\r\n");
      out.write("        <!-- Hero Section Begin -->\r\n");
      out.write("        <section class=\"hero-section\">\r\n");
      out.write("            <div class=\"hero-items owl-carousel\">\r\n");
      out.write("                <div class=\"single-hero-items set-bg\" data-setbg=\"https://xwatch.vn/upload_images/images/2023/01/09/slogan-nike.jpg\">\r\n");
      out.write("                    <div class=\"container\">\r\n");
      out.write("                        <div class=\"row\">\r\n");
      out.write("                            <div class=\"col-lg-5\">\r\n");
      out.write("                                <span>Bag,kids</span>\r\n");
      out.write("                                <h1 style=\"color: #007bff\">Summer Event</h1>\r\n");
      out.write("                                <p style=\"color: white\">Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores corrupti fuga ratione.\r\n");
      out.write("                                    Voluptates voluptatibus illo quaerat? Ea et, dignissimos dolor, dolore impedit odio\r\n");
      out.write("                                    officia ullam eaque obcaecati eligendi ipsam placeat?</p>\r\n");
      out.write("                                <a href=\"SearchServlet?is=sale\" class=\"primary-btn\">Shop Now</a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"off-card\">\r\n");
      out.write("                            <h2>Sale <span>30%</span></h2>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"single-hero-items set-bg\" data-setbg=\"https://wwd.com/wp-content/uploads/2020/07/sport-hub.jpg?w=1000&h=563&crop=1\">\r\n");
      out.write("                    <div class=\"container\">\r\n");
      out.write("                        <div class=\"row\">\r\n");
      out.write("                            <div class=\"col-lg-5\">\r\n");
      out.write("                                <span>Bag,kids</span>\r\n");
      out.write("                                <h1 style=\"color: #007bff\">Summer Event</h1>\r\n");
      out.write("                                <p style=\"color: white\">Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores corrupti fuga ratione.\r\n");
      out.write("                                    Voluptates voluptatibus illo quaerat? Ea et, dignissimos dolor, dolore impedit odio\r\n");
      out.write("                                    officia ullam eaque obcaecati eligendi ipsam placeat?</p>\r\n");
      out.write("                                <a href=\"SearchServlet?is=sale\" class=\"primary-btn\">Shop Now</a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"off-card\">\r\n");
      out.write("                            <h2>Sale <span>30%</span></h2>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- Hero Section End -->\r\n");
      out.write("\r\n");
      out.write("        <!-- Banner Section Begin -->\r\n");
      out.write("        <div class=\"banner-section spad\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-4\">\r\n");
      out.write("                        <div class=\"single-banner\">\r\n");
      out.write("                            <img src=\"https://i.pinimg.com/564x/c9/7b/8d/c97b8d8f1c6aa4c0df304167347bc3e8.jpg\" alt=\"\">\r\n");
      out.write("                            <div class=\"inner-text\">\r\n");
      out.write("                                <h4>Men's</h4>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-4\">\r\n");
      out.write("                        <div class=\"single-banner\">\r\n");
      out.write("                            <img src=\"https://i.pinimg.com/564x/f7/38/25/f7382542648a417ae5f4a222c25bd962.jpg\" alt=\"\">\r\n");
      out.write("                            <div class=\"inner-text\">\r\n");
      out.write("                                <h4>Women's</h4>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-4\">\r\n");
      out.write("                        <div class=\"single-banner\">\r\n");
      out.write("                            <img src=\"https://i.pinimg.com/564x/ce/22/b3/ce22b33873f87e269fa4afc86a7c704e.jpg\" style=\"height: 333px\" alt=\"\">\r\n");
      out.write("                            <div class=\"inner-text\">\r\n");
      out.write("                                <h4>Kid's</h4>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- Banner Section End -->\r\n");
      out.write("\r\n");
      out.write("        <!-- Women Banner Section Begin -->\r\n");
      out.write("        <section class=\"women-banner spad\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-3\">\r\n");
      out.write("                        <div class=\"product-large set-bg\" data-setbg=\"https://i.pinimg.com/564x/13/f5/64/13f5644533af0b5ac27069eeaf5c1a61.jpg\">\r\n");
      out.write("                            <h2>Women's</h2>\r\n");
      out.write("                            <a href=\"SearchServlet?type=2\">Discover More</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-8 offset-lg-1\">\r\n");
      out.write("                        <div class=\"filter-control\">\r\n");
      out.write("                            <ul>\r\n");
      out.write("                                <li class=\"active\">Shoes</li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"product-slider owl-carousel\">\r\n");
      out.write("                            ");

                                List<ProductDTO> ls1 = (List<ProductDTO>) session.getAttribute("womenList");
                                for (ProductDTO ele : ls1) {
                            
      out.write("\r\n");
      out.write("                            <div class=\"product-item\">\r\n");
      out.write("                                <div class=\"pi-pic\">\r\n");
      out.write("                                    <img src=\"");
      out.print(ele.getAvatarPath());
      out.write("\" alt=\"\">\r\n");
      out.write("                                    ");

                                        if (ele.getSale() != 0) {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"sale\">Sale</div>\r\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\r\n");
      out.write("                                    <form action=\"InsertWishlist\" method=\"post\"> \r\n");
      out.write("                                    \r\n");
      out.write("                                    <div class=\"icon\">\r\n");
      out.write("                                       <button id=\"btn-icon\" type=\"submit\">\r\n");
      out.write("                                        <input type=\"hidden\" name=\"id\" value=\"");
      out.print( ele.getProductId() );
      out.write("\">\r\n");
      out.write("                                        <input type=\"hidden\" name=\"url\" value=\"index.jsp\">\r\n");
      out.write("                                        <i class=\"icon_heart_alt\"></i>\r\n");
      out.write("                                        </button>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                        \r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    <ul>\r\n");
      out.write("                                        <form action=\"MainController\" method=\"post\">\r\n");
      out.write("                                            <input type=\"hidden\" name=\"productId\" value=\"");
      out.print(ele.getProductId());
      out.write("\">\r\n");
      out.write("                                            <!--<li class=\"w-icon active\"><a href=\"AddToCart?pId=");
      out.print(ele.getProductId());
      out.write("&qnt=1&url=index.jsp\"><i class=\"icon_bag_alt\"></i></a></li>-->\r\n");
      out.write("                                            <li class=\"quick-view\"><a href=\"product.jsp\"><input type=\"submit\" style=\"background-color: #ffffff;\r\n");
      out.write("                                                                                                font-weight: bold;\r\n");
      out.write("                                                                                                border: none;\" name=\"action\" value=\"View\"></a></li>\r\n");
      out.write("                                            <!--<li ><input type=\"submit\" class=\"quick-view\" name=\"action\" value=\"View\"></li>-->\r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    </ul>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"pi-text\">\r\n");
      out.write("                                    <div class=\"catagory-name\">Shoes</div>\r\n");
      out.write("                                    <a href=\"\">\r\n");
      out.write("                                        <h5>");
      out.print(ele.getName());
      out.write("</h5>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                    ");

                                        if (ele.getSale() != 0) {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"product-price\">\r\n");
      out.write("                                        $");
      out.print(ele.getPrice() * 0.9);
      out.write("\r\n");
      out.write("                                        <span>$");
      out.print(ele.getPrice());
      out.write("</span>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    ");

                                    } else {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"product-price\">\r\n");
      out.write("                                        $");
      out.print(ele.getPrice());
      out.write(" \r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            ");
                                   }
                            
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- Women Banner Section End -->\r\n");
      out.write("\r\n");
      out.write("        <!-- Man Banner Section Begin -->\r\n");
      out.write("        <section class=\"man-banner spad\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-8\">\r\n");
      out.write("                        <div class=\"filter-control\">\r\n");
      out.write("                            <ul>\r\n");
      out.write("                                <li class=\"active\">Shoes</li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"product-slider owl-carousel\">\r\n");
      out.write("                            ");

                                List<ProductDTO> ls = (List<ProductDTO>) session.getAttribute("menList");
                                for (ProductDTO ob : ls) {

                            
      out.write("\r\n");
      out.write("                            <div class=\"product-item\">\r\n");
      out.write("                                <div class=\"pi-pic\">\r\n");
      out.write("                                    <img src=\"");
      out.print(ob.getAvatarPath());
      out.write("\" alt=\"\">\r\n");
      out.write("                                    ");
if (ob.getSale() != 0) {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"sale\">Sale</div>\r\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\r\n");
      out.write("                                    <form action=\"InsertWishlist\" method=\"post\"> \r\n");
      out.write("                                    \r\n");
      out.write("                                    <div class=\"icon\">\r\n");
      out.write("                                       <button id=\"btn-icon\" type=\"submit\">\r\n");
      out.write("                                        <input type=\"hidden\" name=\"id\" value=\"");
      out.print( ob.getProductId() );
      out.write("\">\r\n");
      out.write("                                        <input type=\"hidden\" name=\"url\" value=\"index.jsp\">\r\n");
      out.write("                                        <i class=\"icon_heart_alt\"></i>\r\n");
      out.write("                                        </button>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                        \r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    <ul>\r\n");
      out.write("                                        <form action=\"MainController\" method=\"post\">\r\n");
      out.write("                                            <input type=\"hidden\" name=\"productId\" value=\"");
      out.print(ob.getProductId());
      out.write("\">\r\n");
      out.write("                                            <!--<li class=\"w-icon active\"><a href=\"AddToCart?pId=");
      out.print(ob.getProductId());
      out.write("&qnt=1&url=index.jsp\"><i class=\"icon_bag_alt\"></i></a></li>-->\r\n");
      out.write("                                            <li class=\"quick-view\"><a href=\"product.jsp\"><input type=\"submit\" style=\"background-color: white;\r\n");
      out.write("                                                                                                font-weight: bold;\r\n");
      out.write("                                                                                                border: none;\" name=\"action\" value=\"View\"></a></li>\r\n");
      out.write("                                            <!--<li class=\"quick-view\"><a href=\"product.jsp\"><button type=\"submit\" class=\"btn btn-warning\" value=\"View\"><i class=\"fa fa-eye\"></i></button></a></li>-->\r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    </ul>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"pi-text\">\r\n");
      out.write("                                    <div class=\"catagory-name\">SHOES</div>\r\n");
      out.write("                                    <a href=\"\">\r\n");
      out.write("                                        <h5>");
      out.print(ob.getName());
      out.write("</h5>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                    ");

                                        if (ob.getSale() != 0) {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"product-price\">\r\n");
      out.write("                                        $");
      out.print( String.format("%.1f", ob.getPrice() * (1 - ob.getSale())));
      out.write("\r\n");
      out.write("                                        <span>$");
      out.print(ob.getPrice());
      out.write("</span>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    ");

                                    } else {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"product-price\">\r\n");
      out.write("                                        $");
      out.print(ob.getPrice());
      out.write(" \r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            ");
                                   }
                            
      out.write("\r\n");
      out.write("                            <script>\r\n");
      out.write("                                function doDelete(name, event) {\r\n");
      out.write("                                    if (confirm(\"Are you sure you want to remove \" + name + \" from the cart?\")) {\r\n");
      out.write("                                    } else {\r\n");
      out.write("                                        event.preventDefault();\r\n");
      out.write("                                    }\r\n");
      out.write("                                }\r\n");
      out.write("                            </script>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-3 offset-lg-1\">\r\n");
      out.write("                        <div class=\"product-large set-bg\" data-setbg=\"https://i.pinimg.com/564x/3b/89/4d/3b894d8d394ad4dfa667bec6b073cf04.jpg\">\r\n");
      out.write("                            <h2>Men's</h2>\r\n");
      out.write("                            <a href=\"SearchServlet?type=1\">Discover More</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- Man Banner Section End -->\r\n");
      out.write("\r\n");
      out.write("        <section class=\"women-banner spad\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-3\">\r\n");
      out.write("                        <div class=\"product-large set-bg\" data-setbg=\"https://assets.adidas.com/images/w_940,f_auto,q_auto/e1de913ec5f94fbdb4f7af8801289871_9366/IC3030_23_hover_model.jpg\">\r\n");
      out.write("                            <h2>Kid's</h2>\r\n");
      out.write("                            <a href=\"SearchServlet?type=3\">Discover More</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-8 offset-lg-1\">\r\n");
      out.write("                        <div class=\"filter-control\">\r\n");
      out.write("                            <ul>\r\n");
      out.write("                                <li class=\"active\">Shoes</li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"product-slider owl-carousel\">\r\n");
      out.write("                            ");

                                List<ProductDTO> ls2 = (List<ProductDTO>) session.getAttribute("kidList");
                                for (ProductDTO ele : ls2) {
                            
      out.write("\r\n");
      out.write("                            <div class=\"product-item\">\r\n");
      out.write("                                <div class=\"pi-pic\">\r\n");
      out.write("                                    <img src=\"");
      out.print(ele.getAvatarPath());
      out.write("\" alt=\"\">\r\n");
      out.write("                                    ");

                                        if (ele.getSale() != 0) {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"sale\">Sale</div>\r\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\r\n");
      out.write("                                    <form action=\"InsertWishlist\" method=\"post\"> \r\n");
      out.write("                                    \r\n");
      out.write("                                    <div class=\"icon\">\r\n");
      out.write("                                       <button id=\"btn-icon\" type=\"submit\">\r\n");
      out.write("                                        <input type=\"hidden\" name=\"id\" value=\"");
      out.print( ele.getProductId() );
      out.write("\">\r\n");
      out.write("                                        <input type=\"hidden\" name=\"url\" value=\"index.jsp\">\r\n");
      out.write("                                        <i class=\"icon_heart_alt\"></i>\r\n");
      out.write("                                        </button>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                        \r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    <ul>\r\n");
      out.write("                                        <form action=\"MainController\" method=\"post\">\r\n");
      out.write("                                            <input type=\"hidden\" name=\"productId\" value=\"");
      out.print(ele.getProductId());
      out.write("\">\r\n");
      out.write("                                            <!--<li class=\"w-icon active\"><a href=\"AddToCart?pId=");
      out.print(ele.getProductId());
      out.write("&qnt=1&url=index.jsp\"><i class=\"icon_bag_alt\"></i></a></li>-->\r\n");
      out.write("                                            <li class=\"quick-view\"><a href=\"product.jsp\"><input type=\"submit\" style=\"background-color: #ffffff;\r\n");
      out.write("                                                                                                font-weight: bold;\r\n");
      out.write("                                                                                                border: none;\" name=\"action\" value=\"View\"></a></li>\r\n");
      out.write("                                            <!--<li ><input type=\"submit\" class=\"quick-view\" name=\"action\" value=\"View\"></li>-->\r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    </ul>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"pi-text\">\r\n");
      out.write("                                    <div class=\"catagory-name\">Shoes</div>\r\n");
      out.write("                                    <a href=\"\">\r\n");
      out.write("                                        <h5>");
      out.print(ele.getName());
      out.write("</h5>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                    ");

                                        if (ele.getSale() != 0) {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"product-price\">\r\n");
      out.write("                                        $");
      out.print(ele.getPrice() * 0.9);
      out.write("\r\n");
      out.write("                                        <span>$");
      out.print(ele.getPrice());
      out.write("</span>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    ");

                                    } else {
                                    
      out.write("\r\n");
      out.write("                                    <div class=\"product-price\">\r\n");
      out.write("                                        $");
      out.print(ele.getPrice());
      out.write(" \r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            ");
                                   }
                            
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- Instagram Section Begin -->\r\n");
      out.write("        <div class=\"instagram-photo\">\r\n");
      out.write("            <div class=\"insta-item set-bg\" data-setbg=\"img/insta-1.jpg\">\r\n");
      out.write("                <div class=\"inside-text\">\r\n");
      out.write("                    <i class=\"ti-instagram\"></i>\r\n");
      out.write("                    <h5><a href=\"#\">MinQan_Collection</a></h5>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"insta-item set-bg\" data-setbg=\"img/insta-2.jpg\">\r\n");
      out.write("                <div class=\"inside-text\">\r\n");
      out.write("                    <i class=\"ti-instagram\"></i>\r\n");
      out.write("                    <h5><a href=\"#\">MinQan_Collection</a></h5>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"insta-item set-bg\" data-setbg=\"img/insta-3.jpg\">\r\n");
      out.write("                <div class=\"inside-text\">\r\n");
      out.write("                    <i class=\"ti-instagram\"></i>\r\n");
      out.write("                    <h5><a href=\"#\">MinQan_Collection</a></h5>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"insta-item set-bg\" data-setbg=\"img/insta-4.jpg\">\r\n");
      out.write("                <div class=\"inside-text\">\r\n");
      out.write("                    <i class=\"ti-instagram\"></i>\r\n");
      out.write("                    <h5><a href=\"#\">MinQan_Collection</a></h5>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"insta-item set-bg\" data-setbg=\"img/insta-5.jpg\">\r\n");
      out.write("                <div class=\"inside-text\">\r\n");
      out.write("                    <i class=\"ti-instagram\"></i>\r\n");
      out.write("                    <h5><a href=\"#\">MinQan_Collection</a></h5>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"insta-item set-bg\" data-setbg=\"img/insta-6.jpg\">\r\n");
      out.write("                <div class=\"inside-text\">\r\n");
      out.write("                    <i class=\"ti-instagram\"></i>\r\n");
      out.write("                    <h5><a href=\"#\">MinQan_Collection</a></h5>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- Instagram Section End -->\r\n");
      out.write("\r\n");
      out.write("        <!-- Latest Blog Section Begin -->\r\n");
      out.write("        <section class=\"latest-blog spad\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-12\">\r\n");
      out.write("                        <div class=\"section-title\">\r\n");
      out.write("                            <h2>From The BLog</h2>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-4 col-md-6\">\r\n");
      out.write("                        <div class=\"single-latest-blog\">\r\n");
      out.write("                            <img src=\"img/latest-1.jpg\" alt=\"\">\r\n");
      out.write("                            <div class=\"latest-text\">\r\n");
      out.write("                                <div class=\"tag-list\">\r\n");
      out.write("                                    <div class=\"tag-item\">\r\n");
      out.write("                                        <i class=\"fa fa-calendar-o\"></i>\r\n");
      out.write("                                        June 11,2024\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"tag-item\">\r\n");
      out.write("                                        <i class=\"fa fa-comment-o\"></i>\r\n");
      out.write("                                        7\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <a href=\"\">\r\n");
      out.write("                                    <h4>The Best Street Style From London MinQan Week</h4>\r\n");
      out.write("                                </a>\r\n");
      out.write("                                <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quibusdam aut quisquam\r\n");
      out.write("                                    blanditiis quidem exercitationem ipsam repellendus.</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-4 col-md-6\">\r\n");
      out.write("                        <div class=\"single-latest-blog\">\r\n");
      out.write("                            <img src=\"img/latest-2.jpg\" alt=\"\">\r\n");
      out.write("                            <div class=\"latest-text\">\r\n");
      out.write("                                <div class=\"tag-list\">\r\n");
      out.write("                                    <div class=\"tag-item\">\r\n");
      out.write("                                        <i class=\"fa fa-calendar-o\"></i>\r\n");
      out.write("                                        June 08,2024\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"tag-item\">\r\n");
      out.write("                                        <i class=\"fa fa-comment-o\"></i>\r\n");
      out.write("                                        15\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <a href=\"\">\r\n");
      out.write("                                    <h4>Vogue's Ultimate Guide To Autumn/Winter 2024 Shoes</h4>\r\n");
      out.write("                                </a>\r\n");
      out.write("                                <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quibusdam aut quisquam\r\n");
      out.write("                                    blanditiis quidem exercitationem ipsam repellendus.</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-4 col-md-6\">\r\n");
      out.write("                        <div class=\"single-latest-blog\">\r\n");
      out.write("                            <img src=\"img/latest-3.jpg\" alt=\"\">\r\n");
      out.write("                            <div class=\"latest-text\">\r\n");
      out.write("                                <div class=\"tag-list\">\r\n");
      out.write("                                    <div class=\"tag-item\">\r\n");
      out.write("                                        <i class=\"fa fa-calendar-o\"></i>\r\n");
      out.write("                                        June 16,2024\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"tag-item\">\r\n");
      out.write("                                        <i class=\"fa fa-comment-o\"></i>\r\n");
      out.write("                                        40\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <a href=\"\">\r\n");
      out.write("                                    <h4>How To Brighten Your Wardrobe With A Dash Of Lime</h4>\r\n");
      out.write("                                </a>\r\n");
      out.write("                                <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quibusdam aut quisquam\r\n");
      out.write("                                    blanditiis quidem exercitationem ipsam repellendus.</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"benefit-items\">\r\n");
      out.write("                    <div class=\"row\">\r\n");
      out.write("                        <div class=\"col-lg-4\">\r\n");
      out.write("                            <div class=\"single-benefit\">\r\n");
      out.write("                                <div class=\"sb-icon\">\r\n");
      out.write("                                    <img src=\"img/icon-1.png\" alt=\"\">\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"sb-text\">\r\n");
      out.write("                                    <h6>Free Shipping</h6>\r\n");
      out.write("                                    <p>For all orders over 99$</p>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"col-lg-4\">\r\n");
      out.write("                            <div class=\"single-benefit\">\r\n");
      out.write("                                <div class=\"sb-icon\">\r\n");
      out.write("                                    <img src=\"img/icon-2.png\" alt=\"\">\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"sb-text\">\r\n");
      out.write("                                    <h6>Delivery on time</h6>\r\n");
      out.write("                                    <p>Whenever in work time</p>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"col-lg-4\">\r\n");
      out.write("                            <div class=\"single-benefit\">\r\n");
      out.write("                                <div class=\"sb-icon\">\r\n");
      out.write("                                    <img src=\"img/icon-3.png\" alt=\"\">\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"sb-text\">\r\n");
      out.write("                                    <h6>Secure Payment</h6>\r\n");
      out.write("                                    <p>100% secure payment</p>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- Latest Blog Section End -->\r\n");
      out.write("        <!-- Partner Logo Section Begin -->\r\n");
      out.write("        <div class=\"partner-logo\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"logo-carousel owl-carousel\">\r\n");
      out.write("                    <div class=\"logo-item\">\r\n");
      out.write("                        <div class=\"tablecell-inner\">\r\n");
      out.write("                            <img src=\"img/nike.png\" width=\"150\" height=\"150\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"logo-item\">\r\n");
      out.write("                        <div class=\"tablecell-inner\">\r\n");
      out.write("                            <img src=\"img/adidas.png\" width=\"150\" height=\"150\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"logo-item\">\r\n");
      out.write("                        <div class=\"tablecell-inner\">\r\n");
      out.write("                            <img src=\"img/puma.png\" width=\"150\" height=\"150\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"logo-item\">\r\n");
      out.write("                        <div class=\"tablecell-inner\">\r\n");
      out.write("                            <img src=\"img/asics.png\" width=\"150\" height=\"150\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"logo-item\">\r\n");
      out.write("                        <div class=\"tablecell-inner\">\r\n");
      out.write("                            <img src=\"img/vans.png\" width=\"150\" height=\"150\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"logo-item\">\r\n");
      out.write("                        <div class=\"tablecell-inner\">\r\n");
      out.write("                            <img src=\"img/newbalance.png\" width=\"150\" height=\"150\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- Partner Logo Section End -->\r\n");
      out.write("\r\n");
      out.write("        <!-- Footer Section Begin -->\r\n");
      out.write("        <footer class=\"footer-section\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-3\">\r\n");
      out.write("                        <div class=\"footer-left\">\r\n");
      out.write("                            <div class=\"footer-logo\">\r\n");
      out.write("                                <a href=\"index.jsp\">\r\n");
      out.write("                                    <img src=\"img/logoweb.png\" alt=\"\">\r\n");
      out.write("                                </a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <ul>\r\n");
      out.write("                                <li>Lô E2a-7, Đường D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Hồ Chí Minh 700000</li>\r\n");
      out.write("                                <li>Phone: +84 123456789</li>\r\n");
      out.write("                                <li>Email: smsystem@gmail.com</li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                            <div class=\"footer-social\">\r\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-facebook\"></i></a>\r\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-instagram\"></i></a>\r\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-twitter\"></i></a>\r\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-pinterest\"></i></a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-2 offset-lg-1\">\r\n");
      out.write("                        <div class=\"footer-widget\">\r\n");
      out.write("                            <h5>Information</h5>\r\n");
      out.write("                            <ul>\r\n");
      out.write("                                <li><a href=\"\">About Us</a></li>\r\n");
      out.write("                                <li><a href=\"\">Checkout</a></li>\r\n");
      out.write("                                <li><a href=\"\">Contact</a></li>\r\n");
      out.write("                                <li><a href=\"\">Services</a></li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-2\">\r\n");
      out.write("                        <div class=\"footer-widget\">\r\n");
      out.write("                            <h5>My Account</h5>\r\n");
      out.write("                            <ul>\r\n");
      out.write("                                <li><a href=\"\">My Account</a></li>\r\n");
      out.write("                                <li><a href=\"\">Contact</a></li>\r\n");
      out.write("                                <li><a href=\"\">Shopping Cart</a></li>\r\n");
      out.write("                                <li><a href=\"\">Shop</a></li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-lg-4\">\r\n");
      out.write("                        <div class=\"newsletter-item\">\r\n");
      out.write("                            <h5>Join Out Newsletter Now</h5>\r\n");
      out.write("                            <p>Get E-mail updates about our latest shop and special offers.</p>\r\n");
      out.write("                            <form action=\"#\" class=\"subcribe-form\">\r\n");
      out.write("                                <input type=\"text\" placeholder=\"Enter Your Email\">\r\n");
      out.write("                                <button type=\"button\">Subscribe</button>\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"copyright-reserved\">\r\n");
      out.write("                <div class=\"container\">\r\n");
      out.write("                    <div class=\"row\">\r\n");
      out.write("                        <div class=\"col-lg-12\">\r\n");
      out.write("                            <div class=\"copyright-text\">\r\n");
      out.write("                                Copyright ©2024 All reserved | MinQan\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"payment-pic\">\r\n");
      out.write("                                <img src=\"img/payment-method.png\" alt=\"\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </footer>\r\n");
      out.write("        <!-- Footer Section End -->\r\n");
      out.write("\r\n");
      out.write("        <!-- Js Plugins -->\r\n");
      out.write("        <script src=\"js/jquery-3.3.1.min.js\"></script>\r\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("        <script src=\"js/jquery-ui.min.js\"></script>\r\n");
      out.write("        <script src=\"js/jquery.countdown.min.js\"></script>\r\n");
      out.write("        <script src=\"js/jquery.nice-select.min.js\"></script>\r\n");
      out.write("        <script src=\"js/jquery.zoom.min.js\"></script>\r\n");
      out.write("        <script src=\"js/jquery.dd.min.js\"></script>\r\n");
      out.write("        <script src=\"js/jquery.slicknav.js\"></script>\r\n");
      out.write("        <script src=\"js/owl.carousel.min.js\"></script>\r\n");
      out.write("        <script src=\"js/main.js\"></script>\r\n");
      out.write("        <script src=\"js/main2.js\"></script>\r\n");
      out.write("        <script src=\"js/main3.js\"></script>\r\n");
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
