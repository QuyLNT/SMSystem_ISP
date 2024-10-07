<%-- 
    Document   : checkout
    Created on : Jun 13, 2024, 11:17:40 PM
    Author     : Luu Minh Quan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="admin.sample.order.InforOrderDetail"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.Code"%>
<%@page import="model.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.CartDTO"%>
<%@page import="model.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>check-out</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/themify-icons.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">

        <link rel="stylesheet" href="css/style1.css" type="text/css">
        <link rel="stylesheet" href="css/style2.css" type="text/css">
        <link rel="stylesheet" href="css/style3.css" type="text/css">
    </head>

    <body>
        <!-- Start coding here -->
        <!-- Page PreOrder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <!-- Header section begin -->
        <header class="header-section">
            <div class="header-top">
                <div class="container">
                    <div class="ht-left">
                        <div class="mail-service">
                            <i class="fa fa-envelope">
                                minhquan141104@gmail.com
                            </i>
                        </div>
                        <div class="phone-service">
                            <i class="fa fa-phone">
                                +84 78 566 3033
                            </i>
                        </div>
                    </div>
                    <%
                        UserDTO u = (UserDTO) session.getAttribute("user");
                    %>
                    <div class="ht-right">
                        <div class="login-panel" id="user-btn">
                            <i class="fa fa-user">  <%=u.getFullName()%></i>

                        </div>
                        <section class="user">
                            <div class="user-setting">
                                <div class="content">
                                    <div><a href="myAccount.jsp">My account</a></div>
                                    <div><a href="myOrder.jsp">Order Status</a></div>
                                    <div><a href="myInvoice.jsp">My Invoice</a></div>
                                    <div><a href="LogoutController">Logout</a></div>

                                    </ul>
                                </div>
                        </section>
                        <div class="lan-selector">
                            <select class="language_drop" name="countries" id="countries" style="width: 300px;">
                                <option value="yt" data-image="img/flag-1.jpg" data-imagecss="flag yt" data-title="English">
                                    English</option>
                                <option value="yu" data-image="img/flag-2.jpg" data-imagecss="flag yu" data-title="German">
                                    German</option>
                            </select>
                        </div>
                        <div class="top-social">
                            <a href="#"><i class="ti-facebook"></i></a>
                            <a href="#"><i class="ti-twitter-alt"></i></a>
                            <a href="#"><i class="ti-linkedin"></i></a>
                            <a href="#"><i class="ti-pinterest"></i></a>
                        </div>
                    </div>
                </div>

            </div>
            <div class="container">
                <div class="inner-header">
                    <div class="row">
                        <div class="col-lg-2 col-md-2">
                            <div class="logo">
                                <a href="index.jsp">
                                    <img src="favicon_io/android-chrome-192x192.png" height="65px" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-7 col-md-7">
                            <div class="advanced-search">
                                <button type="button" class="category-btn">All Categories</button>
                                <div class="input-group">
                                    <input type="text" placeholder="What do you need?">
                                    <button type="button"><i class="ti-search"></i></button>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 text-right">
                            <ul class="nav-right">
                               
                                <%
                                    String size = (String) session.getAttribute("size");
                                    if (size == null) {
                                        size = "0";
                                    }
                                    boolean isEmptyCart = size.equals("0");
                                %>
                                <li class="cart-icon">
                                    <a href="#">
                                        <i class="icon_bag_alt"></i>
                                        <span><%= size%></span>
                                    </a>
                                    <div class="cart-hover">
                                        <div class="select-items">
                                            <% if (isEmptyCart) { %>
                                            <p>No product in cart. Buy more</p>
                                            <div class="select-total">
                                                <span>total:</span>
                                                <h5>$00.00</h5>
                                            </div>
                                            <div class="select-button">
                                                <a href="shopping-cart.jsp" class="primary-btn view-card">VIEW CART</a>
                                                <a href="check-out.jsp" class="primary-btn checkout-btn">CHECK OUT</a>
                                            </div>
                                            <% } else { %>
                                            <table>
                                                <tbody>
                                                    <%
                                                        CartDTO cart = (CartDTO) session.getAttribute("CART");
                                                        if (cart == null) {
                                                            cart = new CartDTO();
                                                        }
                                                        List<ItemDTO> ls = cart.getList();
                                                        if (ls != null) {
                                                            int count = 1;
                                                            double total = 0;
                                                            for (ItemDTO ele : ls) {
                                                                total += (ele.getPrice() * ele.getQuantity());
                                                    %>
                                                    <tr>
                                                        <td class="si-pic"><img src="<%= ele.getProduct().getImg()%>" style="height: 76px"></td>
                                                        <td class="si-text">
                                                            <div class="product-selected">
                                                                <p>$<%= String.format("%.1f", ele.getPrice())%> x <%= ele.getQuantity()%></p>
                                                                <h6><%= ele.getProduct().getName()%></h6>
                                                                <h6>Size <%=ele.getSize()%></h6>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                            <div class="select-total">
                                                <span>total:</span>
                                                <h5>$<%= String.format("%.1f", total)%></h5>
                                            </div>
                                            <%
                                                }
                                            %>
                                            <div class="select-button">
                                                <a href="shopping-cart.jsp" class="primary-btn view-card">VIEW CART</a>
                                                <a href="check-out.jsp" class="primary-btn checkout-btn">CHECK OUT</a>
                                            </div>
                                            <% }%>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="nav-item">
                <div class="container">
                    <div class="nav-depart">
                        <div class="depart-btn">
                            <i class="ti-menu"></i>
                            <span>All Departments</span>
                            <ul class="depart-hover">
                                <li><a href="SearchServlet?type=2">Women's Clothing</a></li>
                                <li><a href="SearchServlet?type=1">Men's Clothing</a></li>
                                <li><a href="SearchServlet?type=3">Kid's Clothing</a></li>
                            </ul>
                        </div>
                    </div>
                    <nav class="nav-menu mobile-menu">
                        <ul>
                            <li><a href="index.jsp">Home</a></li>
                            <li class="active"><a href="shop.jsp">Shop</a></li>
                            <li><a href="contact.jsp">Contact</a></li>
                            <li><a href="">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="shopping-cart.jsp">Shopping Cart</a></li>
                                   
                                </ul>
                            </li>
                        </ul>
                    </nav>
                    <div id="mobile-menu-wrap"></div>
                </div>
            </div>
        </header>
        <!-- Header Section End -->



        <!--  -->

        <!--Breadcrumb Section Begin-->
        <div class="breadcrumb-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-text">
                            <a href="./index.jsp"><i class="fa fa-home"></i> Home</a>
                            <a href="./shop.jsp">Shop</a>
                            <span>Check Out</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Breadcrumb Section End-->

        <!-- Shopping Cart Section Begin -->
        <div class="checkout-section spad">
            <div class="container">
                <form action="MainOrderController" method="POST" class="checkout-form">
                    <div class="row">
                        <div class="col-lg-6">
                            <h4>Billing Details</h4>
                            <div class="row">
                                <div class="col-lg-12">
                                    <label for="fir">Full Name <span>*</span></label>
                                    <input type="text" id="fir" name="fullname" value="<%= u.getFullName()%>">
                                </div>
                                <div class="col-lg-12">
                                    <label for="street">Street Address <span>*</span></label>
                                    <input type="text" id="street" name="street" value="<%= u.getStreet()%>" class="street-first">
                                </div>
                                <div class="col-lg-12">
                                    <label for="cun">District <span>*</span></label>
                                    <input type="text" id="cun" name="district" value="<%= u.getDistrict()%>">
                                </div>
                                <div class="col-lg-12">
                                    <label for="town">Town / City <span>*</span></label>
                                    <input type="text" id="town" name="city" value="<%= u.getCity()%>">
                                </div>
                                <div class="col-lg-6">
                                    <label for="email">Email Address<span>*</span></label>
                                    <input type="text" id="email" name="email" value="<%= u.getEmail()%>">
                                </div>
                                <div class="col-lg-6">
                                    <label for="phone">Phone <span>*</span></label>
                                    <input type="text" id="phone" name="phone" value="<%= u.getPhone()%>">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="place-order">
                                <h4>Your Order</h4>
                                <div class="order-total">
                                    <ul class="order-table">
                                        <li>Product <span>Total</span></li>
                                            <%
                                                CartDTO cart = (CartDTO) session.getAttribute("CART");
                                                if (cart == null) {
                                                    cart = new CartDTO();
                                                }
                                                Code c = (Code) session.getAttribute("code");
                                                List<ItemDTO> ls = cart.getList();

                                                if (ls != null) {
                                                    int count = 1;
                                                    double total = 0;
                                                    for (ItemDTO ele : ls) {
                                                        total += (ele.getPrice() * ele.getQuantity());


                                            %>
                                        <li style="display: none">                                           
                                            <input type="hidden" name="userId" value="<%=ele.getProduct().getUoId()%>" />
                                        </li>
                                        <li class="fw-normal"><%=ele.getProduct().getName()%> x <%=ele.getQuantity()%><br>Size: US <%=ele.getSize()%><span>$<%= String.format("%.1f", ele.getPrice())%> x <%= ele.getQuantity()%></span></li>
                                            <%
                                                }
                                            %>
                                        <li class="fw-normal">Subtotal <span>$<%= String.format("%.1f", total)%></span></li>

                                        <%
                                            if (c != null) {
                                        %>
                                        <li class="fw-normal">Discount <span>-$ <input type="hidden" name="discount" value="<%=c.getCode()%>"/><%=c.getDiscountAmount()%></span></li>
                                        <li class="fw-normal">Total <span>$<%= String.format("%.1f", total - c.getDiscountAmount())%></span></li>
                                            <%
                                            } else {
                                            %>
                                        <li class="fw-normal">Total<%= String.format("%.1f", total * 1)%></span></li>
                                            <%
                                                }
                                            %>

                                        <li class="fw-normal">  <label>PayMethod:</label>
                                            <select name="pay" class="form-select" aria-label="Default select example">
                                                <option selected>Choose your payment method</option>
                                                <option value="1">Cash</option>
                                                <option value="2">Credit Card</option>
                                                <option value="3">Pay Pal</option>
                                            </select>
                                        </li>
                                        <li class="fw-normal"><label>ShipMethod:</label>

                                            <select name="ship" class="form-select" aria-label="Default select example">
                                                <option selected>Choose your shipment method</option>
                                                <option value="1">Standard Shipping</option>
                                                <option value="2">Express Shipping</option>
                                                <option value="3">Rocket Shipping</option>
                                            </select>
                                        </li>
                                        <%
                                            }
                                        %>
                                    </ul>
                                    <div class="order-btn">
                                        <button type="submit" name="action" value="Submit" class="site-btn place-btn">Place Order</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Shopping Cart Section End -->

        <!-- Partner Logo Section Begin -->
        <div class="partner-logo">
            <div class="container">
                <div class="logo-carousel owl-carousel">
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/logo-carousel/logo-1.png">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/logo-carousel/logo-2.png">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/logo-carousel/logo-3.png">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/logo-carousel/logo-4.png">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/logo-carousel/logo-5.png">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/logo-carousel/logo-1.png">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Partner Logo Section End -->

        <!-- Footer Section Begin -->
        <footer class="footer-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="footer-left">
                            <div class="footer-logo">
                                <a href="index.jsp">
                                    <img src="favicon_io/android-chrome-192x192.png" alt="">
                                </a>
                            </div>
                            <ul>
                                <li>1A Yet Kieu . Ha Noi</li>
                                <li>Phone: +84 78 566 3033</li>
                                <li>Email: minhquan141104@gmail.com</li>
                            </ul>
                            <div class="footer-social">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-instagram"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-pinterest"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 offset-lg-1">
                        <div class="footer-widget">
                            <h5>Information</h5>
                            <ul>
                                <li><a href="">About Us</a></li>
                                <li><a href="">Checkout</a></li>
                                <li><a href="">Contact</a></li>
                                <li><a href="">Services</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-2">
                        <div class="footer-widget">
                            <h5>My Account</h5>
                            <ul>
                                <li><a href="">My Account</a></li>
                                <li><a href="">Contact</a></li>
                                <li><a href="">Shopping Cart</a></li>
                                <li><a href="">Shop</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="newsletter-item">
                            <h5>Join Out Newsletter Now</h5>
                            <p>Get E-mail updates about our latest shop and special offers.</p>
                            <form action="#" class="subcribe-form">
                                <input type="text" placeholder="Enter Your Email">
                                <button type="button">Subscribe</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="copyright-reserved">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="copyright-text">
                                Copyright ©2024 All reserved | MinQan
                            </div>
                            <div class="payment-pic">
                                <img src="img/payment-method.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer Section End -->

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/jquery.dd.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/main2.js"></script>
        <script src="js/main3.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
