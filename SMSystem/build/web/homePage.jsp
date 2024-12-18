 
<%@page import="java.util.ArrayList"%>
<%@page import="model.cart.CartItems"%>
<%@page import="model.cart.CartDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.product.ProductDTO"%>
<%@page import="model.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Home page - SMSystem</title>
        <link rel="icon" href="img/icon-logoweb.png" type="image/png">
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
        <link rel="icon" href="img/icon-logoweb.png" type="img/x-icon" />
        <link rel="stylesheet" href="css/style3.css" type="text/css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
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
                                <a href="MainController?action=HomePage">
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
                                                                <h6><%= ele.getProduct().getName()%></h6>
                                                                <h6>Size <%= ele.getSize()%></h6>
                                                                <p>$<%= String.format("%.1f", ele.getProduct().getPrice() * (1 - ele.getProduct().getSale()))%> x <%= ele.getQuantity()%></p>

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
                                                <a href="MainController?action=ViewCart&url=homePage.jsp" class="primary-btn view-card">VIEW CART</a>
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
            <div class="nav-item"  >
                <div class="container">
                    <div class="nav-depart">
                        <div class="depart-btn">
                            <i class="ti-menu"></i>
                            <span>All Departments</span>

                            <ul class="depart-hover">
                                <li><a href="MainController?action=SearchCategories&type=1">Men's Shoes</a></li>
                                <li><a href="MainController?action=SearchCategories&type=2">Women's Shoes</a></li>
                                <li><a href="MainController?action=SearchCategories&type=3">Kid's Shoes</a></li>
                            </ul>
                        </div>
                    </div>
                    <nav class="nav-menu mobile-menu">
                        <ul>
                            <li><a href="MainController?action=HomePage">Home</a></li>
                            <li><a href="MainController?action=ShopPage">Shop</a></li>
                            <li><a href="contact.jsp">Contact</a></li>
                            <li><a href="MainController?action=ViewCart&url=homePage.jsp">Shopping Cart</a></li>
                            <li><a href="warrantyPage.jsp">Warranty</a></li>
                        </ul>
                    </nav>
                    <div id="mobile-menu-wrap"></div>
                </div>
            </div>
        </header>
        <!-- Header Section End -->

        <!--  -->
        <!-- Hero Section Begin -->
        <div style="width: 1684px; margin: 0 auto; box-sizing: border-box;">
            <section class="hero-section">
                <div class="hero-items owl-carousel">
                    <div class="single-hero-items set-bg" data-setbg="https://wwd.com/wp-content/uploads/2020/07/sport-hub.jpg?w=1000&h=563&crop=1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-5">
                                    <span>Bag,kids</span>
                                    <h1 style="color: #007bff">Summer Event</h1>
                                    <p style="color: white">Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores corrupti fuga ratione.
                                        Voluptates voluptatibus illo quaerat? Ea et, dignissimos dolor, dolore impedit odio
                                        officia ullam eaque obcaecati eligendi ipsam placeat?</p>
                                    <a href="MainController?action=ShopPage" class="primary-btn">Shop Now</a>
                                </div>
                            </div>
                            <div class="off-card">
                                <h2>Sale <span>30%</span></h2>
                            </div>
                        </div>
                    </div>
                    <div class="single-hero-items set-bg" data-setbg="https://xwatch.vn/upload_images/images/2023/01/09/slogan-nike.jpg">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-5">
                                    <span>Bag,kids</span>
                                    <h1 style="color: #007bff">Summer Event</h1>
                                    <p style="color: white">Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores corrupti fuga ratione.
                                        Voluptates voluptatibus illo quaerat? Ea et, dignissimos dolor, dolore impedit odio
                                        officia ullam eaque obcaecati eligendi ipsam placeat?</p>
                                    <a href="MainController?action=ShopPage" class="primary-btn">Shop Now</a>
                                </div>
                            </div>
                            <div class="off-card">
                                <h2>Sale <span>30%</span></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Hero Section End -->

            <!-- Banner Section Begin -->
            <div class="banner-section spad">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="single-banner">
                                <img src="https://i.pinimg.com/564x/c9/7b/8d/c97b8d8f1c6aa4c0df304167347bc3e8.jpg" alt="">
                                <div class="inner-text">
                                    <a href="MainController?action=SearchCategories&type=1"><h4>Men's</h4></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="single-banner">
                                <img src="https://i.pinimg.com/564x/f7/38/25/f7382542648a417ae5f4a222c25bd962.jpg" alt="">
                                <div class="inner-text">
                                    <a href="MainController?action=SearchCategories&type=2"><h4>Women's</h4></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="single-banner">
                                <img src="https://i.pinimg.com/564x/ce/22/b3/ce22b33873f87e269fa4afc86a7c704e.jpg" style="height: 333px" alt="">
                                <div class="inner-text">
                                    <a href="MainController?action=SearchCategories&type=3"><h4>Kid's</h4></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Banner Section End -->

            <!-- Women Banner Section Begin -->
            <section class="women-banner spad">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="product-large set-bg" data-setbg="https://i.pinimg.com/564x/13/f5/64/13f5644533af0b5ac27069eeaf5c1a61.jpg">
                                <h2>Women's</h2>
                                <a href="MainController?action=SearchCategories&type=2">Discover More</a>
                            </div>
                        </div>
                        <div class="col-lg-8 offset-lg-1">
                            <div class="filter-control">
                                <ul>
                                    <li class="active">Women Shoes</li>
                                </ul>
                            </div>
                            <div class="product-slider owl-carousel">
                                <%
                                    List<ProductDTO> ls1 = (List<ProductDTO>) session.getAttribute("WOMEN_LIST");
                                    if (ls1 != null) {

                                        for (ProductDTO ele : ls1) {
                                %>
                                <div class="product-item">
                                    <div class="pi-pic">
                                        <img src="<%=ele.getAvatarPath()%>" alt="">
                                        <%
                                            if (ele.getSale() != 0) {
                                        %>
                                        <div class="sale">Sale</div>
                                        <%
                                            }
                                        %>

                                        <ul>
                                            <form action="MainController" method="post">
                                                <input type="hidden" name="productId" value="<%=ele.getProductId()%>">
    <!--                                            <li class="w-icon active"><a href="AddToCart?pId=<%/*=ele.getProductId()*/%>&qnt=1&url=homePage.jsp"><i class="icon_bag_alt"></i></a></li>-->
                                                <li class="quick-view"><a href="product.jsp"><input type="submit" style="background-color: #ffffff;
                                                                                                    font-weight: bold;
                                                                                                    border: none;" name="action" value="View"></a></li>

                                            </form>
                                        </ul>
                                    </div>
                                    <div class="pi-text">
                                        <div class="catagory-name">Women Shoes</div>
                                        <a href="">
                                            <h5><%=ele.getName()%></h5>
                                        </a>
                                        <%
                                            if (ele.getSale() != 0) {
                                        %>

                                        <div class="product-price">
                                            $<%= String.format("%.1f", ele.getPrice() * (1 - ele.getSale()))%>
                                            <span>$<%=ele.getPrice()%></span>
                                        </div>

                                        <%
                                        } else {
                                        %>
                                        <div class="product-price">
                                            $<%=ele.getPrice()%> 
                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                                <%
                                        }
                                    }

                                %>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
            <!-- Women Banner Section End -->

            <!-- Man Banner Section Begin -->
            <section class="man-banner spad">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="filter-control">
                                <ul>
                                    <li class="active">Men Shoes</li>
                                </ul>
                            </div>
                            <div class="product-slider owl-carousel">
                                <%                                List<ProductDTO> ls = (List<ProductDTO>) session.getAttribute("MEN_LIST");
                                    for (ProductDTO ob : ls) {

                                %>
                                <div class="product-item">
                                    <div class="pi-pic">
                                        <img src="<%=ob.getAvatarPath()%>" alt="">
                                        <%if (ob.getSale() != 0) {
                                        %>
                                        <div class="sale">Sale</div>
                                        <%
                                            }
                                        %>

                                        <ul>
                                            <form action="MainController" method="post">
                                                <input type="hidden" name="productId" value="<%=ob.getProductId()%>">
    <!--                                            <li class="w-icon active"><a href="AddToCart?pId=<%/*=ob.getProductId()*/%>&qnt=1&url=homePage.jsp"><i class="icon_bag_alt"></i></a></li>-->
                                                <li class="quick-view"><input type="submit" style="background-color: white;
                                                                                                    font-weight: bold;
                                                                                                    border: none;" name="action" value="View"></a></li>

                                            </form>
                                        </ul>
                                    </div>
                                    <div class="pi-text">
                                        <div class="catagory-name">Men Shoes</div>
                                        <a href="">
                                            <h5><%=ob.getName()%></h5>
                                        </a>
                                        <%
                                            if (ob.getSale() != 0) {
                                        %>
                                        <div class="product-price">
                                            $<%= String.format("%.1f", ob.getPrice() * (1 - ob.getSale()))%>
                                            <span>$<%=ob.getPrice()%></span>
                                        </div>
                                        <%
                                        } else {
                                        %>
                                        <div class="product-price">
                                            $<%=ob.getPrice()%> 
                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                                <%                                  }
                                %>
                            </div>
                        </div>
                        <div class="col-lg-3 offset-lg-1">
                            <div class="product-large set-bg" data-setbg="https://i.pinimg.com/564x/3b/89/4d/3b894d8d394ad4dfa667bec6b073cf04.jpg">
                                <h2>Men's</h2>
                                <a href="MainController?action=SearchCategories&type=1">Discover More</a>
                            </div>
                        </div>
                    </div>
                </div>

            </section>
            <!-- Man Banner Section End -->

            <!-- Kid Banner Section Begin -->

            <section class="women-banner spad">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="product-large set-bg" data-setbg="https://assets.adidas.com/images/w_940,f_auto,q_auto/e1de913ec5f94fbdb4f7af8801289871_9366/IC3030_23_hover_model.jpg">
                                <h2>Kid's</h2>
                                <a href="MainController?action=SearchCategories&type=3">Discover More</a>
                            </div>
                        </div>
                        <div class="col-lg-8 offset-lg-1">
                            <div class="filter-control">
                                <ul>
                                    <li class="active">Kids Shoes</li>
                                </ul>
                            </div>
                            <div class="product-slider owl-carousel">
                                <%
                                    List<ProductDTO> ls2 = (List<ProductDTO>) session.getAttribute("KID_LIST");
                                    for (ProductDTO ele : ls2) {
                                %>
                                <div class="product-item">
                                    <div class="pi-pic">
                                        <img src="<%=ele.getAvatarPath()%>" alt="">
                                        <%
                                            if (ele.getSale() != 0) {
                                        %>
                                        <div class="sale">Sale</div>
                                        <%
                                            }
                                        %>

                                        <ul>
                                            <form action="MainController" method="post">
                                                <input type="hidden" name="productId" value="<%=ele.getProductId()%>">
    <!--                                            <li class="w-icon active"><a href="AddToCart?pId=<%/*=ele.getProductId()*/%>&qnt=1&url=homePage.jsp"><i class="icon_bag_alt"></i></a></li>-->
                                                <li class="quick-view"><a href="product.jsp"><input type="submit" style="background-color: #ffffff;
                                                                                                    font-weight: bold;
                                                                                                    border: none;" name="action" value="View"></a></li>

                                            </form>
                                        </ul>
                                    </div>
                                    <div class="pi-text">
                                        <div class="catagory-name">Kids Shoes</div>
                                        <a href="">
                                            <h5><%=ele.getName()%></h5>
                                        </a>
                                        <%
                                            if (ele.getSale() != 0) {
                                        %>
                                        <div class="product-price">
                                            $<%= String.format("%.1f", ele.getPrice() * (1 - ele.getSale()))%>
                                            <span>$<%=ele.getPrice()%></span>
                                        </div>


                                    </div>
                                    <%
                                    } else {
                                    %>
                                    <div class="product-price">
                                        $<%=ele.getPrice()%> 
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                                <%                                }
                                %>
                            </div>

                        </div>

                    </div>
                </div>
            </section>
                <div class="benefit-items">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="single-benefit">
                                <div class="sb-icon">
                                    <img src="img/icon-1.png" alt="">
                                </div>
                                <div class="sb-text">
                                    <h6>Free Shipping</h6>
                                    <p>For all orders over 99$</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="single-benefit">
                                <div class="sb-icon">
                                    <img src="img/icon-2.png" alt="">
                                </div>
                                <div class="sb-text">
                                    <h6>Delivery on time</h6>
                                    <p>Whenever in work time</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="single-benefit">
                                <div class="sb-icon">
                                    <img src="img/icon-3.png" alt="">
                                </div>
                                <div class="sb-text">
                                    <h6>Secure Payment</h6>
                                    <p>100% secure payment</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <!-- Latest Blog Section End -->
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
    </body>
</html>
