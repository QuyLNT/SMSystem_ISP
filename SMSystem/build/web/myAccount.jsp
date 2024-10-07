<%-- 
    Document   : wishlist
    Created on : Jun 13, 2024, 11:09:49 PM
    Author     : Luu Minh Quan
--%>

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
        <title>My Account</title>

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
        <link rel="stylesheet" href="css/style.css" type="text/css">
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
                            if(user.getFullName()!=null){
                        %>
                        <div class="login-panel" id="user-btn">
                            <i class="fa fa-user"><%=user.getFullName()%></i>
                        </div>
                        <% }else { %>
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
                                    if(sizeWishlist==null){
                                        sizeWishlist="0";
                                    }
                                    
                                    %>
                                <li class="heart-icon">
                                    <a href="wishlist.jsp">
                                        <i class="icon_heart_alt"></i>
                                        <span><%= sizeWishlist %></span>
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
                            <span>My Account</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Breadcrumb Section End-->

        <div class="wishlist-heading">Account setting</div>

        <!-- Shopping Cart Section Begin -->
        <div class="checkout-section spad">
    <div class="container">
        <form action="UpdateUserServlet" class="checkout-form">
            <div class="row setting-center">
                <div class="col-lg-6">
                    <div class="row">
                        <div class="col-lg-12">
                            <label for="userName">userName <span>*</span></label>
                            <% if(user.getUserName()==null){ %>
                            <input type="text" id="userName" name="userName">
                            <% }else{ %>
                            
                            <input type="text" id="userName-exist" name="userName" value="<%=user.getUserName()%>" readonly>
                            <button type="button" id="edit-btn-userName">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                         <div class="col-lg-12">
                            <label for="password">password <span>*</span></label>
                            <% if(user.getPass()==null){ %>
                            <input type="text" id="pass" name="pass">
                            <% }else{ %>
                            
                            <input type="text" id="password-exist" name="pass" value="<%=user.getPass()%>" readonly>
                            <button type="button" id="edit-btn-pass">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                        <div class="col-lg-12">
                            <label for="fullName">Full Name <span>*</span></label>
                            <% if(user.getFullName()==null){ %>
                            <input type="text" id="fullName" name="fullName">
                            <% }else{ %>
                            <input type="text" id="fullName-exist" name="fullName" value="<%=user.getFullName()%>" readonly>
                            <button type="button" id="edit-btn-fullName">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                        <div class="col-lg-12">
                            <label for="phone">Phone <span>*</span></label>
                            <% if(user.getPhone()==null){ %>
                            <input type="text" id="phone" name="phone" >
                            <% }else{ %>
                            <input type="text" id="phone-exist" name="phone" value="<%=user.getPhone()%>" readonly>
                            <button type="button" id="edit-btn-phone">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                        <div class="col-lg-12">
                            <label for="street">Street <span>*</span></label>
                            <% if(user.getStreet()==null){ %>
                            <input type="text" id="street" name="street" >
                            <% }else{ %>
                            <input type="text" id="street-exist" name="street" value="<%=user.getStreet()%>" readonly>
                            <button type="button" id="edit-btn-street">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                        <div class="col-lg-12">
                            <label for="district">District <span>*</span></label>
                             <% if(user.getDistrict()==null){ %>
                           <input type="text" id="district" name="district">
                            <% }else{ %>
                            <input type="text" id="district-exist" name="district" value="<%=user.getDistrict()%>" readonly>
                            <button type="button" id="edit-btn-district">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                        <div class="col-lg-12">
                            <label for="city">City <span>*</span></label>
                            <% if(user.getCity()==null){ %>
                           <input type="text" id="city" name="city" >
                            <% }else{ %>
                            <input type="text" id="city-exist" name="city" value="<%=user.getCity()%>" readonly>
                            <button type="button" id="edit-btn-city">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                        <div class="col-lg-12">
                            <label for="sex">Sex <span>*</span></label>
                            <% if(user.getSex()==null){ %>
                            <select id="sex" name="sex">
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                                <option value="other">Other</option>
                            </select>
                            <% }else{ %>
                            <input type="text" id="sex-exist" name="sex" value="<%=user.getSex()%>" readonly>
                            <button type="button" id="edit-btn-sex">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                        <div class="col-lg-12">
                            <label for="birth">Birth Date <span>*</span></label>
                            <% if(user.getBirth()==null){ %>
                           <input type="date" id="birth" name="birth">
                            <% }else{ %>
                            <input type="date" id="birth-exist" name="birth" value="<%=user.getBirth()%>" readonly>
                            <button type="button" id="edit-btn-birth">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            <% } %>
                        </div>
                        <div class="col-lg-12">
                            <label for="email">Email Address <span>*</span></label>
                            <% if(user.getEmail()==null){ %>
                            <input type="email" id="email" name="email">
                            <% }else{ %>
                            
                            <input type="email" id="email-exist" name="email" value="<%=user.getEmail() %>" readonly>
                            <button type="button" id="edit-btn-email">
                                <i class="fa fa-pencil-square-o"></i>
                            </button>
                            
                            <% } %>
                        </div>
                    </div>
                        <%
                            String message = (String) request.getAttribute("MESSAGE");
                            if(message==null) message="";
                        %>
                        <h4 style="color:red "> <%= message %> </h4> 
                    <input type="submit" id="UpdateUser" name="action" value="Update">
                </div>
            </div>
        </form>
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
