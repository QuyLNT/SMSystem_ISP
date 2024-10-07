<%-- 
    Document   : contact
    Created on : Jun 13, 2024, 11:24:15 PM
    Author     : Luu Minh Quan
--%>

<%@page import="model.ItemDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.CartDTO"%>
<%@page import="model.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Contact</title>

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
                        UserDTO user = (UserDTO) session.getAttribute("user");
                    %>
                    <div class="ht-right">
                        <div class="login-panel" id="user-btn">
                            <i class="fa fa-user">  <%=user.getFullName()%></i>

                        </div>
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
                                                            double total = 0;
                                                            int count = 0;
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
                                                        <td class="si-close">
                                                            <a href="RemoveServlet?pId=<%=count%>&url=index.jsp"  onclick="doDelete('<%=ele.getProduct().getName()%>', event)">
                                                                <i class="ti-close"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                    <%
                                                            count++;
                                                        }
                                                    %>
                                                </tbody>
                                                <script>
                                                    function doDelete(name, event) {
                                                        if (confirm("Bạn có chắc muốn bỏ " + name + " ra khỏi giỏ?")) {
//                    window.location="RemoveServlet?mobileId = " + id;
                                                        } else {
                                                            event.preventDefault();
                                                        }
                                                    }</script>
                                            </table>
                                            <div class="select-total">
                                                <span>total:</span>
                                                <h5>$<%=String.format("%.1f", total)%></h5>
                                            </div>
                                            <%
                                                }
                                            %>
                                            <div class="select-button">
                                                <a href="shopping-cart.jsp" class="primary-btn view-card">VIEW CART</a>
                                               
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
                                <li><a href="SearchServlet?type=Women">Women's Clothing</a></li>
                                <li><a href="SearchServlet?type=Men">Men's Clothing</a></li>
                                <li><a href="SearchServlet?type=Kids">Kid's Clothing</a></li>
                            </ul>
                        </div>
                    </div>
                    <nav class="nav-menu mobile-menu">
                        <ul>
                            <li ><a href="index.jsp">Home</a></li>
                            <li><a href="shop.jsp">Shop</a></li>
                            <li class="active"><a href="contact.jsp">Contact</a></li>
                            <li><a href="">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="shopping-cart.jsp">Shopping Cart</a></li>
                                    <li><a href="check-out.jsp">Checkout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                    <div id="mobile-menu-wrap"></div>
                </div>
            </div>
        </header>
        <!-- Header Section End -->

        <div class="breadcrumb-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-text">
                            <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                            <span>Contact</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Map Section Begin -->
        <div class="map spad">
            <div class="container">
                <div class="map-inner">
                    <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.609941530518!2d106.80730807578458!3d10.841132857995323!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752731176b07b1%3A0xb752b24b379bae5e!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgVFAuIEhDTQ!5e0!3m2!1svi!2s!4v1719080885479!5m2!1svi!2s"
                        height="610" style="border:0;" allowfullscreen="" loading="lazy"
                        referrerpolicy="no-referrer-when-downgrade">
                    </iframe>
                    <div class="icon">
                        <i class="fa fa-map-marker"></i>
                    </div>
                </div>
            </div>
        </div>
        <!-- Map Section End -->

        <section class="contact-section spad">
            <section class="container">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="cw-item">
                            <div class="contact-title">
                                <h4>Contact Us</h4>
                                <p>Contact Lorem ipsum dolor, sit amet consectetur adipisicing elit. Vero, similique
                                    illo
                                    mollitia quam earum rerum adipisci. Aliquam, officia tenetur. Autem omnis distinctio
                                    quaerat qui quod dolorum sit officiis delectus laudantium?</p>
                            </div>
                            <div class="contact-widget">
                                <div class="cw-item">
                                    <div class="ci-icon">
                                        <i class="ti-location-pin"></i>
                                    </div>
                                    <div class="ci-text">
                                        <span>Address:</span>
                                        <p>1A Yet Kieu . HaNoi</p>
                                    </div>
                                </div>
                                <div class="cw-item">
                                    <div class="ci-icon">
                                        <i class="ti-mobile"></i>
                                    </div>
                                    <div class="ci-text">
                                        <span>Phone:</span>
                                        <p>+84 785 663 033</p>
                                    </div>
                                </div>
                                <div class="cw-item">
                                    <div class="ci-icon">
                                        <i class="ti-email"></i>
                                    </div>
                                    <div class="ci-text">
                                        <span>Email:</span>
                                        <p>minhquan141104@gmail.com</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 offset-lg-1">
                        <div class="contact-form">
                            <div class="leave-comment">
                                <h4>Leave A Comment</h4>
                                <p>Our Staff will call back later.</p>
                                <form action="#" class="comment-form">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <input type="text" placeholder="Your name">
                                        </div>
                                        <div class="col-lg-6">
                                            <input type="text" placeholder="Your email">
                                        </div>
                                        <div class="col-lg-12">
                                            <textarea placeholder="Your message"></textarea>
                                            <button type="submit" class="site-btn">Send message</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>

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
    </body>
</html>
