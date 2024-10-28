<%-- 
    Document   : wishlist
    Created on : Jun 13, 2024, 11:09:49 PM
    Author     : Luu Minh Quan
--%>

<%@page import="model.discount.DiscountDTO"%>
<%-- 
    Document   : wishlist
    Created on : Jun 13, 2024, 11:09:49 PM
    Author     : Luu Minh Quan
--%>

<%@page import="model.order.OrderDTO"%>
<%@page import="model.order.OrderDAO"%>
<%@page import="model.order.OrderDetailDTO"%>
<%@page import="model.product.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.product.ProductDTO"%>
<%@page import="model.user.UserDTO"%>
<%@page import="model.cart.CartItems"%>
<%@page import="java.util.List"%>
<%@page import="model.cart.CartDTO"%>
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
        <!--                <div id="preloder">
                            <div class="loader"></div>
                        </div>-->
        <!-- Header section begin -->
        <header class="header-section">
            <div class="header-top">
                <div class="container">
                    <div class="ht-left">
                        <div class="mail-service">
                            <i class="fa fa-envelope">
                                smsystem@gmail.com
                            </i>
                        </div>
                        <div class="phone-service">
                            <i class="fa fa-phone">
                                +84 123456789
                            </i>
                        </div>
                    </div>
                    <div class="ht-right">
                        <%
                            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                            if (user != null && user.getFullName() != null) {
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
                                    <div><a href="LoadMyOrderController">Order Status</a></div>
                                    <div><a href="LogoutController">Logout</a></div>
                                </div>
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
                                    <img src="img/logoweb.png" height="65px" alt="">
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
                        <form action="SearchOrderController" method="get">
                            <div class="advanced-search">
                                <div class="input-group" style="justify-content: center;">
                                    <input style="color: black; width: 80%; height: 3rem" type="text" name="OrderID" placeholder="Input OrderID">
                                    <button id="search-btn-order" type="submit" name="action" value="Search"><i class="ti-search"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <section class="showcase">
                    <div class="overlay">
                        <div class="container-fluid">                                                  

                            <div class="welcome">
                                <%
                                    // Lấy thông báo lỗi từ session
                                    String message = (String) session.getAttribute("ms");
                                    session.removeAttribute("ms"); // Xóa thông báo sau khi đã hiển thị
                                %>
                                <% if (message != null) {%>
                                <div class="alert alert-info">
                                    <%= message%>
                                </div>
                                <% } %>

                                <table class="table table-hover" style="margin: 50px;">
                                    <thead>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Customer</th>
                                            <th>Email</th>
                                            <th>Status</th>
                                            <th>Payment method</th>
                                            <th>Shipping method</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            // Lấy danh sách đơn hàng
                                            OrderDAO orderDAO = new OrderDAO();
                                            List<OrderDTO> ls = orderDAO.getAllOrdersByCustomerId(user.getUserId());

                                            // Kiểm tra nếu không có đơn hàng nào
                                            if (ls == null || ls.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="7" style="text-align: center;">You do not have any orders</td>
                                        </tr>
                                        <%
                                        } else {
                                            // Lấy OrderID từ request
                                            String orderIdStr = request.getParameter("OrderID");
                                            List<OrderDTO> searchResults = new ArrayList<>();

                                            // Nếu có OrderID, tìm kiếm trong danh sách đơn hàng
                                            if (orderIdStr != null && !orderIdStr.isEmpty()) {
                                                int orderId = Integer.parseInt(orderIdStr);
                                                for (OrderDTO order : ls) {
                                                    if (order.getOrderId() == orderId) {
                                                        searchResults.add(order);
                                                    }
                                                }
                                            } else {
                                                searchResults = ls; // Nếu không có OrderID thì hiển thị tất cả
                                            }

                                            // Kiểm tra kết quả tìm kiếm
                                            if (searchResults.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="7" style="text-align: center;">No orders found with the given Order ID.</td>
                                        </tr>
                                        <%
                                        } else {
                                            // Hiển thị các đơn hàng tìm được
                                            for (OrderDTO ele : searchResults) {
                                        %>
                                        <tr>
                                            <td><%= ele.getOrderId()%></td>                                       
                                            <td><%= ele.getCustomer().getFullName()%></td>
                                            <td><%= ele.getCustomer().getEmail()%></td>
                                            <td>
                                                <input type="text" style="border: none; outline: none;" value="<%= ele.getOrderStatus()%>" readonly="">
                                            </td>
                                            <td>
                                                <input type="text" style="border: none; outline: none;" value="<%= ele.getPaymentMethod()%>" readonly="">
                                            </td>
                                            <td>
                                                <input type="text" style="border: none; outline: none;" value="<%= ele.getShippingMethod()%>" readonly="">
                                            </td>
                                            <td class="p-price-wishlist first-row">$<%= String.format("%.2f", ele.getTotalPrice()) %></td>
                                        </tr>
                                        <%
                                                    }
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
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
                            <img src="img/nike.png" width="150" height="150">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/adidas.png" width="150" height="150">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/puma.png" width="150" height="150">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/asics.png" width="150" height="150">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/vans.png" width="150" height="150">
                        </div>
                    </div>
                    <div class="logo-item">
                        <div class="tablecell-inner">
                            <img src="img/newbalance.png" width="150" height="150">
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
                                <li>Lô E2a-7, Đường D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Hồ Chí Minh 700000</li>
                                <li>Phone: +84 123456789</li>
                                <li>Email: smsystem@gmail.com</li>
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
                                Copyright ©2024 All reserved | SMSystem
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
