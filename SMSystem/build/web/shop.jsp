
<%@page import="model.category.BrandDTO"%>
<%@page import="model.category.UserObjectDTO"%>
<%@page import="model.cart.CartItems"%>
<%@page import="model.cart.CartDTO"%>
<%@page import="model.user.UserDTO"%>
<%@page import="model.product.ProductDAO"%>
<%@page import="model.product.ProductImageDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Shop</title>

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
                    <%
                        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                        if (loginUser != null) {
                    %>
                    <div class="ht-right">
                        <div class="login-panel" id="user-btn">
                            <i class="fa fa-user">  <%=loginUser.getFullName()%></i>

                        </div>
                        <section class="user">
                            <div class="user-setting">
                                <div class="content">
                                    <div><a href="myAccount.jsp">My account</a></div>
                                    <div><a href="myOrder.jsp">Order Status</a></div>
                                    <div><a href="LogoutController">Logout</a></div>
                                </div>
                        </section>
                        <%
                        } else {
                        %>
                        <div class="ht-right">
                            <div class="login-panel" id="user-btn">
                                <i class="fa fa-user">  GUEST</i>
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
                                    <a href="MainController?action=ShopPage">
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
                                                <p>No product in cart. Buy more</p>
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
                                                                    <p>$<%= String.format("%.1f", ele.getProduct().getPrice() * (1 - ele.getProduct().getSale()))%> x <%= ele.getQuantity()%></p>
                                                                    <h6><%= ele.getProduct().getName()%></h6>
                                                                    <h6>Size <%= ele.getSize()%></h6>
                                                                </div>
                                                            </td>
                                                            <td class="si-close">
                                                                <a href="MainController?cartItemId=<%= ele.getCartItemId()%>&action=doDelete&url=shop.jsp" onclick="doDelete('<%= ele.getProduct().getName()%>', event)">
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
                                                    <a href="MainController?action=ViewCart&url=shop.jsp" class="primary-btn view-card">VIEW CART</a>
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
                                <li><a href="MainController?action=ViewCart&url=product.jsp">Shopping Cart</a></li>
                                <li><a href="warrantyPage.jsp">Warranty</a></li>
                            </ul>
                        </nav>
                        <div id="mobile-menu-wrap"></div>
                    </div>
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
                            <a href="homePage.jsp"><i class="fa fa-home"></i> Home</a>
                            <span>Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Breadcrumb Section End-->

        <!-- Product Shop Section Begin -->
        <section class="product-shop spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6 col-sm-8 order-2 order-lg-1 product-sidebar-filter">
                        <div class="filter-widget">
                            <form method="get" action="MainController">
                                <h4 class="fw-title">Brand</h4>
                                <div class="fw-brand-check">
                                    <%
                                        List<BrandDTO> brandList = (List<BrandDTO>) session.getAttribute("BRAND_LIST");
                                        if (brandList != null) {
                                            for (BrandDTO b : brandList) {
                                    %>
                                    <div class="bc-item">
                                        <label for="bc-<%=b.getBrandId()%>">
                                            <%=b.getBrandName()%>
                                            <input type="checkbox" id="bc-<%=b.getBrandId()%>" name="brand" value="<%=b.getBrandId()%>"/>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <%  }

                                    } else {
                                    %>
                                    <h5> No brand found. </h5>
                                    <%
                                        }
                                    %>
                                </div>

                                <!--                                <h4 class="fw-title">Color</h4>
                                                                <div class="dropdown">
                                                                    <div class="dropdown-header"></div>
                                                                    <ul class="color-options">
                                                                        <li>
                                                                            <span class="color-circle" style="background-color: white;"></span>
                                                                            Trắng
                                                                        </li>
                                                                        <li>
                                                                            <span class="color-circle" style="background-color: #FFB6C1;"></span>
                                                                            HỒNG
                                                                        </li>
                                                                        <li>
                                                                            <span class="color-circle rainbow"></span>
                                                                            NHIỀU MÀU
                                                                        </li>
                                                                        <li>
                                                                            <span class="color-circle" style="background-color: #000;"></span>
                                                                            Đen
                                                                        </li>
                                                                        <li>
                                                                            <span class="color-circle" style="background-color: #cc2424;"></span>
                                                                            Đỏ
                                                                        </li>
                                                                        <li>
                                                                            <span class="color-circle" style="background-color: green;"></span>
                                                                            Xanh lá cây
                                                                        </li>
                                                                        <li>
                                                                            <span class="color-circle" style="background-color: blue;"></span>
                                                                            Xanh nước biển
                                                                        </li>
                                                                    </ul>
                                                                </div>-->
                                <h4 class="fw-title">Color</h4>
                                <div class="fw-brand-check">
                                    <div class="bc-item">
                                        <label for="bc-white">
                                            White
                                            <input type="checkbox" id="bc-white" name="color" value="White"/>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <div class="bc-item">
                                        <label for="bc-black">
                                            Black
                                            <input type="checkbox" id="bc-black" name="color" value="Black"/>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <div class="bc-item">
                                        <label for="bc-green">
                                            Green
                                            <input type="checkbox" id="bc-green" name="color" value="Green"/>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <div class="bc-item">
                                        <label for="bc-blue">
                                            Blue
                                            <input type="checkbox" id="bc-blue" name="color" value="Blue"/>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <div class="bc-item">
                                        <label for="bc-yellow">
                                            Yellow
                                            <input type="checkbox" id="bc-yellow" name="color" value="Yellows"/>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <div class="bc-item">
                                        <label for="bc-order">
                                            Order
                                            <input type="checkbox" id="bc-order" name="color" value="Order"/>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                </div>

                                <h4 class="fw-title">Price</h4>
                                <div class="filter-range-wrap">
                                    <div class="range-slider">
                                        <div class="price-input">
                                            $<input type="text" id="minamount" name="minPrice">
                                            $<input type="text" id="maxamount" name="maxPrice">
                                        </div>
                                    </div>
                                    <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget-content"
                                         data-min="50" data-max="300">
                                        <div class="ui-slider-range ui-corner-all ui-widget-header"></div> 
                                        <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>  
                                        <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>  
                                    </div>
                                </div>
                                <%
                                    Integer cate = (Integer) session.getAttribute("CATE");
                                    if (cate != null && cate > 0) {
                                %>
                                <input type="hidden" name="uOb" value="<%= cate%>"/>
                                <%
                                    }
                                %>
                                <input style="border: none" type="submit" name="action" class="filter-btn" value="Filter">
                            </form>
                        </div>
                        <div class="filter-widget">
                        </div>
                    </div>
                    <div class="col-lg-9 order-1 order-lg-2">
                        <div class="product-show-option">
                            <div class="row">
                                <div class="col-lg-7 col-md-7">
                                    <div class="select-option">
                                        <%
                                            String selectedId = (String) session.getAttribute("ID");
                                            if (selectedId != null) {
                                        %>
                                        <form id="sortingForm" method="get" action="MainController">
                                            <input type="hidden" name="action" value="SortShopPage"/>
                                            <select class="sorting" name="id" onchange="document.getElementById('sortingForm').submit();">
                                                <option value="1" <%= selectedId.equals("1") == true ? "selected":"" %>>Default Sorting</option>
                                                <option value="2" <%= selectedId.equals("2") == true ? "selected":"" %>>On Sale</option>
                                                <option value="3" <%= selectedId.equals("3") == true ? "selected":"" %>>Price: High - Low</option>
                                                <option value="4" <%= selectedId.equals("4") == true ? "selected":"" %>>Price: Low - High</option>
                                            </select>
                                        </form>
                                        <%
                                        } else {
                                        %>
                                        <form id="sortingForm" method="get" action="MainController">
                                            <input type="hidden" name="action" value="SortShopPage"/>
                                            <select class="sorting" name="id" onchange="document.getElementById('sortingForm').submit();">
                                                <option value="1" selected="">Default Sorting</option>
                                                <option value="2" >On Sale</option>
                                                <option value="3" >Price: High - Low</option>
                                                <option value="4" >Price: Low - High</option>
                                            </select>
                                        </form>
                                        <%
                                            }
                                        %>

                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="product-list">
                            <%
                                List<ProductDTO> ls = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");
                                if (ls != null && !ls.isEmpty()) {
                                    for (ProductDTO p : ls) {

                            %>
                            <!-- Mot san pham o day -->
                            <div class="product-item">
                                <div class="pi-pic">
                                    <img src="<%=p.getAvatarPath()%>" alt="">
                                    <%
                                        if (p.getSale() != 0) {
                                    %>
                                    <div class="sale pp-sale">Sale</div>
                                    <%
                                        }
                                    %>
                                    <form action="InsertWishlist" method="post"> 

                                        <div class="icon">
                                            <button id="btn-icon" type="submit">
                                                <input type="hidden" name="id" value="<%= p.getProductId()%>">
                                                <input type="hidden" name="url" value="shop.jsp">
                                                <i class="icon_heart_alt"></i>
                                            </button>
                                        </div>

                                    </form>
                                    <ul>
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="productId" value="<%=p.getProductId()%>">
                                            <!--<li class="w-icon active"><a href="AddToCart?pId=<%=p.getProductId()%>&qnt=1&url=shop.jsp"><i class="icon_bag_alt"></i></a></li>-->
                                            <li class="quick-view"><!--<a href="product.jsp">--><input type="submit" style="background-color: white;
                                                                                                       font-weight: bold;
                                                                                                       border: none;" name="action" value="View"></a></li>
                                        </form>
                                    </ul>
                                </div>

                                <div class="pi-text">
                                    <%
                                        if (p.getUserOjectId() == 1) {
                                    %>
                                    <div class="catagory-name">Men</div>
                                    <%
                                    } else if (p.getUserOjectId() == 2) {
                                    %>
                                    <div class="catagory-name">Women</div>
                                    <%
                                    } else {
                                    %>
                                    <div class="catagory-name">Kids</div>
                                    <%
                                        }
                                    %>
                                    <a href="#">
                                        <h5><%=p.getName()%></h5>
                                    </a>
                                    <%
                                        if (p.getSale() != 0) {
                                    %>
                                    <div class="product-price">
                                        $<%= String.format("%.1f", p.getPrice() * (1 - p.getSale()))%>
                                        <span>$<%=p.getPrice()%></span>
                                    </div>
                                    <%
                                    } else {
                                    %>
                                    <div class="product-price">
                                        $<%=p.getPrice()%>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                            <%
                                }
                            } else {
                            %>
                            <p>No products available.</p>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Product Shop Section End -->

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
        <script> var rangeSlider = $(".price-range"),
                                                        minamount = $("#minamount"),
                                                        maxamount = $("#maxamount"),
                                                        minPrice = rangeSlider.data('min'),
                                                        maxPrice = rangeSlider.data('max');
                                                rangeSlider.slider({
                                                    range: true,
                                                    min: minPrice,
                                                    max: maxPrice,
                                                    values: [minPrice, maxPrice],
                                                    slide: function (event, ui) {
                                                        minamount.val(ui.values[0]);
                                                        maxamount.val(ui.values[1]);
                                                    }
                                                });
                                                minamount.val(rangeSlider.slider("values", 0));
                                                maxamount.val(rangeSlider.slider("values", 1));
        </script>
    </body>
</html>
