
<%@page import="model.user.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Thanks page</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
            />
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
        </header>
        <!-- Header Section End -->

        <%
            String status = request.getParameter("status");
            if (status != null) {
        %>
        <%
            if (status.equals("1")) {
        %>
        <div class="wishlist-heading tk">Thank you for Buying</div>
        <div id="tick-img">
            <img src="img/happy-face.png">
        </div>
        <div class="back">
            <i class="fa-solid fa-arrow-right">  </i>
            <a href="MainController?action=HomePage" style="color: black;"> Click here to go back</a>
        </div>  
        <%
        } else {
        %>
        <div class="wishlist-heading tk">Thank you for Buying</div>
        <div class="wishlist-heading">But there was an Error during your payment process.</div>

        <div id="tick-img">
            <img src="img/sad-face.png">
        </div>
        <p style="color: black; text-align: center; font-size: 30px">If you would like to continue purchasing the product, please repeat the purchase process.</p>
        <div class="back">
            <i class="fa-solid fa-arrow-right">  </i>
            <a href="MainController?action=HomePage" style="color: black;"> Click here to go back</a>
        </div>  
        <%
            }
        %>
        <%
        } else {
        %>
        <div class="wishlist-heading tk">Thank you for Buying</div>
        <div id="tick-img">
            <img src="img/happy-face.png">
        </div>
        <div class="back">
            <i class="fa-solid fa-arrow-right">  </i>
            <a href="MainController?action=HomePage" style="color: black;"> Click here to go back</a>
        </div> 
        <%
            }
        %>


        <style>
            #tick-img {
                display: flex;
                align-items: center;  /* Center vertically */
                justify-content: center;  /* Center horizontally */
            }

            #tick-img img {
                width: 250px; /* Set desired image width */
                height: 250px; /* Set desired image height */
            }

            .tk{
                margin-top: 2rem;
            }

            .back{
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 30px;
            }


            a:hover{
                color: #e7ab3c;
            }
        </style>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>