<%-- 
    Document   : wishlist
    Created on : Jun 13, 2024, 11:09:49 PM
    Author     : Luu Minh Quan
--%>

<%-- 
    Document   : wishlist
    Created on : Jun 13, 2024, 11:09:49 PM
    Author     : Luu Minh Quan
--%>

<%@page import="model.OrderDTO"%>
<%@page import="model.OrderDAO"%>
<%@page import="model.OrderItemDTO"%>
<%@page import="model.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductDTO"%>
<%@page import="model.UserDTO"%>
<%@page import="model.ItemDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>My Order</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">

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
        <link rel="stylesheet" href="css/style3.css" type="text/css">
        <link rel="icon" href="favicon_io/favicon.ico" type="img/x-icon" />
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
                    <div class="ht-right">
                        <%
                            UserDTO user = (UserDTO) session.getAttribute("user");
                            if (user.getFullName() != null) {
                        %>
                        <div class="login-panel" id="user-btn">
                            <i class="fa fa-user"><%=user.getFullName()%></i>
                        </div>
                        <% } else { %>
                        <div class="login-panel" id="user-btn">
                            <i class="fa fa-user"></i>
                        </div>
                        <% } %>
                        <section class="user">
                            <div class="user-setting">
                                <div class="content">
                                    <div><a href="myAccount.jsp">My account</a></div>
                                    <div><a href="myOrder.jsp">Order Status</a></div>
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
                                    String sizeWishlist = (String) session.getAttribute("sizeWishlist");
                                    if (sizeWishlist == null) {
                                        sizeWishlist = "0";
                                    }

                                %>
                                <li class="heart-icon">
                                    <a href="wishlist.jsp">
                                        <i class="icon_heart_alt"></i>
                                        <span><%= sizeWishlist%></span>
                                    </a>
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
                                <li class="active"><a href="#">Women's Clothing</a></li>
                                <li><a href="#">a</a></li>
                                <li><a href="#">a</a></li>
                            </ul>
                        </div>
                    </div>
                    <nav class="nav-menu mobile-menu">
                        <ul>
                            <li class="active"><a href="index.jsp">Home</a></li>
                            <li><a href="shop.jsp">Shop</a></li>
                           
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
                            <a href="index.jsp"><i class="fa fa-home"></i> Home</a>
                            <span>My Order</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Breadcrumb Section End-->

        <div class="wishlist-heading">My Order</div>

        <!-- Shopping Cart Section Begin -->
        <div class="shopping-cart-wishlist spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <form action="SearchOrderServlet" method="get">
                            <div class="advanced-search">
                                <div class="input-group" style="justify-content: center;">
                                    <input style="color: black; width: 80%;height: 3rem" type="text" name="OrderID" placeholder="Input OrderID">
                                    <button id="search-btn-order" type="submit"  value="Search"><i class="ti-search"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <style>
                    #search-btn-order{
                        font-size: 16px;
                        color: #ffffff;
                        position: absolute;
                        right: 63px;
                        top: -1px;
                        border: 1px solid #e7ab3c;
                        background: #e7ab3c;
                        padding: 12px 16px 12px;
                        cursor: pointer;
                    }
                </style>
                <section class="showcase">
                    <div class="overlay">
                        <div class="container-fluid">                                                 
                            <div class="welcome">


                                <table class="table table-hover" style="margin: 50px;">

                                    <thead>
                                        <tr>
                                            <th>Code</th>
                                            <th>Customer</th>
                                            <th>Email</th>
                                            <th>Status</th>
                                            <th>Payment method</th>
                                            <th>Shipping method</th>
                                            <th>total</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <%

                                            OrderDAO d = new OrderDAO();

                                            List<OrderDTO> ls = d.getAllOrder(user.getUserId());
                                            if (session.getAttribute("orderList") != null) {
                                                ls = (List<OrderDTO>) session.getAttribute("orderList");
                                            }
                                            if (ls != null) {
                                                for (OrderDTO ele : ls) {
                                                    if (!ele.getOrderStatus().equals("Completed") && !ele.getOrderStatus().equals("Not Completed")) {

                                        %>
                                        <tr>


                                            <td><%=ele.getOrderID()%></td>                                       
                                            <td><%=ele.getFullName()%></td>
                                            <td><%=ele.getEmail()%></td>
                                            <td>
                                                <input type="text" style="border: none; outline: none;" value="<%= ele.getOrderStatus()%>" readonly="">
                                            </td>
                                            <td>
                                                <%
                                                    String payment = "";
                                                    if (ele.getPaymentMethodID() == 1) {
                                                        payment = "Cash";
                                                    } else if (ele.getPaymentMethodID() == 2) {
                                                        payment = "Credit Card";
                                                    } else if (ele.getPaymentMethodID() == 3) {
                                                        payment = "PayPal";
                                                    }
                                                %>
                                                <input type="text" style="border: none; outline: none;" value="<%= payment%>" readonly=""/>
                                            </td>
                                            <td>
                                                <%
                                                    String shipping = "";
                                                    if (ele.getShippingMethodID() == 1) {
                                                        shipping = "Standard Shipping";
                                                    } else if (ele.getShippingMethodID() == 2) {
                                                        shipping = "Express Shipping";
                                                    } else if (ele.getShippingMethodID() == 3) {
                                                        shipping = "Rocket Shipping";
                                                    }
                                                %>
                                                <input type="text" style="border: none; outline: none;"  value="<%= shipping%>"  readonly=""/>
                                            </td>
                                            <td class="p-price-wishlist first-row">$<%= String.format("%.2f", ele.getTotalPrice())%></td>

                                        </tr> 
                                        <% }
                                            }%>
                                    </tbody>
                                </table> 
                                <%}%>

                            </div>

                        </div>

                    </div>

                </section>
            </div>
        </div>
        <!-- Shopping Cart Section Begin -->

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

    </body>
</html>
