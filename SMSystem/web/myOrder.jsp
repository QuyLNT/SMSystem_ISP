

<%@page import="java.util.Map"%>
<%@page import="model.discount.DiscountDTO"%>


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
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
            />
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
        <link rel="stylesheet" href="css/style3.css" type="text/css">
        <link rel="icon" href="img/icon-logoweb.png" type="img/x-icon" />
    </head>

    <body>
        <!-- Start coding here -->
        <!-- Page PreOrder -->

        <!-- Header section begin -->
        <header class="header-section">
            <div class="header-top">
                <div class="container">
                    <div class="ht-left">
                        <div class="mail-service">
                            <i class="fa fa-envelope"></i>smsystem8386@gmail.com
                        </div>
                        <div class="phone-service">
                            <i class="fa fa-phone"></i>+84 123456789
                        </div>
                    </div>
                    <div class="ht-right">
                        <%
                            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                            if (loginUser != null) {
                        %>

                        <div class="login-panel" id="user-btn">
                            <i class="fa fa-user"></i><%=loginUser.getFullName()%>
                        </div>
                        <section class="user">
                            <div class="user-setting">
                                <div class="content">
                                    <div><a href="myAccount.jsp">My account</a></div>
                                    <div><a href="MainController?action=LoadMyOrder">My Order</a></div>
                                    <div><a href="LogoutController">Sign Out</a></div>
                                </div>
                        </section>
                        <%
                        } else {
                        %>
                        <div class="login-panel" id="user-btn">
                            <i class="fa fa-user"></i>Guest
                        </div>
                        <section class="user">
                            <div class="user-setting">
                                <div class="content">
                                    <div><a href="login.jsp">Sign In</a></div>
                                    <div><a href="register.jsp">Sign Up</a></div>
                                </div>
                        </section>
                        <%
                            }
                        %>
                        <%
                            if (loginUser != null) {
                                if (!loginUser.getRoleId().equals("CUS")) {
                        %>
                        <div class="lan-selector">
                            <a href="MainController?action=Back&role=<%=loginUser.getRoleId()%>" style="color: black;">
                                <i class="fa fa-home">  Management</i>
                            </a>
                        </div>
                        <%
                        } else {
                        %>
                        <div class="lan-selector">
                            <select class="language_drop" name="countries" id="countries" style="width: 300px;">
                                <option value="yt" data-image="img/flag-1.jpg" data-imagecss="flag yt" data-title="English">
                                    English</option>
                                <option value="yu" data-image="img/flag-2.jpg" data-imagecss="flag yu" data-title="German">
                                    German</option>
                            </select>
                        </div>
                        <%
                            }
                        } else {
                        %>
                        <div class="lan-selector">
                            <select class="language_drop" name="countries" id="countries" style="width: 300px;">
                                <option value="yt" data-image="img/flag-1.jpg" data-imagecss="flag yt" data-title="English">
                                    English</option>
                                <option value="yu" data-image="img/flag-2.jpg" data-imagecss="flag yu" data-title="German">
                                    German</option>
                            </select>
                        </div>
                        <%
                            }
                        %>
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
                                <a href="homePage.jsp">
                                    <img src="img/logoweb.png" height="65px" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-7 col-md-7">
                            <form action="MainController" method="get">
                                <div class="advanced-search">
                                    <button type="button" class="category-btn">All Categories</button>

                                    <div class="input-group">
                                        <input style="color: black;" type="text" name="text" placeholder="What do you need?">
                                        <button type="submit" name="action" value="Search"><i class="ti-search"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-lg-3 col-md-3 text-right">
                            <ul class="nav-right">
                                <%
                                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                                    boolean isEmptyCart = cart == null || (cart.getCartItemsList() == null || cart.getCartItemsList().isEmpty());
                                    int itemCount = isEmptyCart ? 0 : cart.getCartItemsList().size();
                                %>
                                <li class="cart-icon">
                                    <a href="#">
                                        <i class="icon_bag_alt"></i>
                                        <span><%= itemCount%></span>
                                    </a>
                                    <div class="cart-hover">
                                        <div class="select-items">
                                            <% if (isEmptyCart) { %>
                                            <p style="color: #4C4C4C">No product in cart. Buy more</p>
                                            <% } else { %>
                                            <table>
                                                <tbody>
                                                    <%
                                                        List<CartItems> ls = cart.getCartItemsList();
                                                        double total = 0;
                                                        for (CartItems ele : ls) {
                                                            total += (ele.getProduct().getPrice() * (1 - ele.getProduct().getSale())) * ele.getQuantity();
                                                    %>
                                                    <tr>
                                                        <td class="si-pic"><img src="<%= ele.getProduct().getAvatarPath()%>" style="height: 76px"></td>
                                                        <td class="si-text">
                                                            <div class="product-selected">
                                                                <p style="color: #4C4C4C">$<%= String.format("%.1f", ele.getProduct().getPrice() * (1 - ele.getProduct().getSale()))%> x <%= ele.getQuantity()%></p>
                                                                <h6><%= ele.getProduct().getName()%></h6>
                                                                <h6>Size <%= ele.getSize()%></h6>
                                                            </div>
                                                        </td>
                                                        <td class="si-close">
                                                            <a href="MainController?cartItemId=<%= ele.getCartItemId()%>&action=doDelete&url=homePage.jsp" onclick="doDelete('<%= ele.getProduct().getName()%>', event)">
                                                                <i class="ti-close"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                    <% }%>
                                                </tbody>
                                            </table>
                                            <div class="select-total">
                                                <span>total:</span>
                                                <h5>$<%= String.format("%.1f", total)%></h5>
                                            </div>
                                            <% } %>
                                            <div class="select-button">
                                                <a href="MainController?action=ViewCart&url=myOrder.jsp" class="primary-btn view-card">VIEW CART</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                function doDelete(name, event) {
                    if (confirm("Are you sure you want to remove " + name + " from the cart?")) {
                    } else {
                        event.preventDefault();
                    }
                }
            </script>
            <div class="nav-item">
                <div class="container">
                    <div class="nav-depart">
                        <div class="depart-btn">
                            <i class="ti-menu"></i>
                            <span>All Departments</span>
                            <ul class="depart-hover">
                                <li><a href="MainController?action=SearchCategories&type=1">Men's Clothing</a></li>
                                <li><a href="MainController?action=SearchCategories&type=2">Women's Clothing</a></li>
                                <li><a href="MainController?action=SearchCategories&type=3">Kid's Clothing</a></li>
                            </ul>
                        </div>
                    </div>
                    <nav class="nav-menu mobile-menu">
                        <ul>
                            <li><a href="MainController?action=HomePage">Home</a></li>
                            <li><a href="MainController?action=ShopPage">Shop</a></li>
                            <li><a href="contact.jsp">Contact</a></li>
                            <li><a href="MainController?action=ViewCart&url=myOrder.jsp">Shopping Cart</a></li>
                            <li><a href="warrantyPage.jsp">Warranty</a></li>
                        </ul>
                    </nav>
                    <div id="mobile-menu-wrap"></div>
                </div>
            </div>
        </header>
        <!-- Header Section End -->

        <!--Breadcrumb Section Begin-->
        <div class="breadcrumb-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-text">
                            <a href="MainController?action=HomePage"><i class="fa fa-home"></i> Home</a>
                            <span>My Order</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Breadcrumb Section End-->
        <div class="wishlist-heading">My Order</div>

        <%
            Map<String, Integer> orderStatusCount = (Map<String, Integer>) session.getAttribute("STATUS_COUNT");
            if (orderStatusCount != null) {
        %>
        <!-- Shopping Cart Section Begin -->
        <div class="container">
            <section class="step-wizard">
                <ul class="step-wizard-list">
                    <li class="step-wizard-item" data-status="1">
                        <a href="MainController?action=SearchOrderByStatus&status=Waiting For Accept">
                            <span class="progress-count">
                                <i class="fa fa-hourglass-half"></i>
                                <span class="order-count"><%= orderStatusCount.get("Waiting For Accept")%></span>
                            </span>
                            <span class="progress-label" style="color: #273c75;">Waiting For Accept</span>
                        </a>
                    </li>
                    <li class="step-wizard-item" data-status="2">
                        <a href="MainController?action=SearchOrderByStatus&status=Waiting For Pickup">
                            <span class="progress-count">
                                <i class="fa fa-box"></i>
                                <span class="order-count"><%= orderStatusCount.get("Waiting For Pickup")%></span>
                            </span>
                            <span class="progress-label" style="color: #273c75;">Waiting For Pickup</span>
                        </a>
                    </li>
                    <li class="step-wizard-item" data-status="1">
                        <a href="MainController?action=SearchOrderByStatus&status=Delivering">
                            <span class="progress-count">
                                <i class="fa fa-truck"></i>
                                <span class="order-count"><%= orderStatusCount.get("Delivering")%></span>
                            </span>
                            <span class="progress-label" style="color: #273c75;">Delivering</span>
                        </a>
                    </li>
                    <li class="step-wizard-item" data-status="3">
                        <a href="MainController?action=SearchOrderByStatus&status=Completed">
                            <span class="progress-count">
                                <i class="fa-solid fa-circle-check"></i>
                                <span class="order-count"><%= orderStatusCount.get("Completed")%></span>
                            </span>
                            <span class="progress-label" style="color: #273c75;">Completed</span>
                        </a>
                    </li>
                    <li class="step-wizard-item" data-status="4">
                        <a href="MainController?action=SearchOrderByStatus&status=Not Completed">
                            <span class="progress-count">
                                <i class="fa-solid fa-circle-xmark"></i>                                
                                <span class="order-count"><%= orderStatusCount.get("Not Complete")%></span>
                            </span>
                            <span class="progress-label" style="color: #273c75;">Not Complete</span>
                        </a>
                    </li>
                </ul>   
            </section>
        </div>
        <%
            }
        %>
        <div class="container">
            <section class="showcase">
                <div class="overlay">
                    <div class="welcome">
                        <%
                            String message = (String) session.getAttribute("ms");
                            session.removeAttribute("ms");
                        %>
                        <% if (message != null) {%>
                        <div class="alert alert-info">
                            <%= message%>
                        </div>
                        <% } %>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Payment method</th>
                                    <th>Shipping method</th>
                                    <th>Status</th>
                                    <th>Total</th>
                                    <th>View</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<OrderDTO> ls = (List<OrderDTO>) session.getAttribute("MY_ORDERS");
                                    if (ls == null || ls.isEmpty()) {
                                %>
                                <tr>
                                    <td colspan="7" style="text-align: center;">You do not have any orders</td>
                                </tr>
                                <%
                                } else {
                                    for (OrderDTO ele : ls) {
                                %>
                                <tr>
                                    <td><%= ele.getOrderId()%></td>                                       
                                    <td>
                                        <input type="text" style="border: none; outline: none;" value="<%= ele.getPaymentMethod()%>" readonly="">
                                    </td>
                                    <td>
                                        <input type="text" style="border: none; outline: none;" value="<%= ele.getShippingMethod()%>" readonly="">
                                    </td>
                                    <td>
                                        <input type="text" style="border: none; outline: none;" value="<%= ele.getOrderStatus()%>" readonly="">
                                    </td>
                                    <td class="p-price-wishlist first-row">$<%= String.format("%.1f", ele.getTotalPrice())%>
                                    </td>
                                    <td>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="orderId" value="<%= ele.getOrderId()%>" />
                                            <button style="background: #273c75" type="submit" class="btn btn-primary" name="action" value="View-Detail">
                                                <i class="fa-solid fa-eye" style="background: #273c75"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </div>
    </div>


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


    <!-- Footer Section Begin -->
    <footer class="footer-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="footer-left">
                        <div class="footer-logo">
                            <a href="homePage.jsp">
                                <img src="img/logoweb.png" alt="">
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
    <script src="js/app.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
