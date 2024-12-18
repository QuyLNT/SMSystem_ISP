
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.product.ProductImageDTO"%>
<%@page import="model.cart.CartItems"%>
<%@page import="model.cart.CartDTO"%>
<%@page import="model.user.UserDTO"%>
<%@page import="model.product.ProductDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Product</title>

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
        <link rel="icon" href="img/icon-logoweb.png" type="img/x-icon" />
    </head>

    <body>

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
                                                <a href="MainController?action=ViewCart&url=product.jsp" class="primary-btn view-card">VIEW CART</a>
                                            </div>
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
        <!-- Breadcrumb Section Begin -->
        <div class="breadcrumb-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-text">
                            <a href="homePage.jsp"><i class="fa fa-home"></i>Home</a>
                            <a href="MainController?action=ShopPage">Shop</a>
                            <span>Detail</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb Section End -->

        <!--Popup start-->
        <div id="modalOverlay" class="modal-overlay" style="display: none;">
            <div id="deleteConfirmation" class="card">
                <div class="card-content">
                    <p class="card-heading">SMSystem</p>
                    <p class="card-description">Please sign in to buy</p>
                </div>
                <div class="card-button-wrapper">
                    <a href="register.jsp" class="card-button secondary btn">Sign up</a>
                    <a href="login.jsp" class="card-button primary btn">Sign in</a>
                </div> 
                <button class="exit-button" onclick="cancelDelete()">
                    <svg height="20px" viewBox="0 0 384 512">
                    <path
                        d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z">
                    </path>
                    </svg>
                </button>
            </div>
        </div>

        <!--Popup end-->

        <!-- Product Shop Section Begin -->
        <div class="product-shop spad page-details">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-6">
                                <%
                                    ProductDTO product = (ProductDTO) session.getAttribute("PRODUCT");
                                    if (product.getListImages() != null) {
                                        for (ProductImageDTO ele : product.getListImages()) {
                                %>
                                <div class="product-pic-zoom">
                                    <img src="<%=ele.getImagePath()%>"
                                         class="product-big-img" alt="">
                                    <div class="zoom-icon">
                                        <i class="fa fa-search"></i>
                                    </div>
                                </div>
                                <div class="product-thumbs">

                                    <div class="product-thumbs-track ps-slider owl-carousel">
                                        <%
                                            for (ProductImageDTO elem : product.getListImages()) {
                                        %>
                                        <div class="pt"
                                             data-imgbigurl="<%=elem.getImagePath()%>">
                                            <img src="<%=elem.getImagePath()%>"
                                                 alt="">
                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                                <%                                            break;
                                    }
                                } else {
                                %><h4>LOAD PRODUCT FAILED</h4><%
                                    }
                                %>
                            </div>
                            <div class="col-lg-6">
                                <div class="product-details">
                                    <div class="pd-title">
                                        <span>Product Detail</span>
                                        <h3><%=product.getName()%></h3>
                                    </div>
                                    <div class="pd-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star-o"></i>
                                        <span>(5)</span>
                                    </div>
                                    <div class="pd-desc">
                                        <p><%=product.getDetail()%></p>
                                        <%
                                            if (product.getSale() != 0) {
                                        %>
                                        <h4>$<%= String.format("%.1f", product.getPrice() * (1 - product.getSale()))%> <span>$<%=product.getPrice()%></span></h4>
                                        <%
                                        } else {
                                        %>
                                        <h4>$<%=product.getPrice()%></h4>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <%
                                        List<Float> validSizes = product.getAvaiableSize();
                                        List<Float> allSizes = product.getAllSize();
                                    %>
                                    <style>
                                        .unavailable-size {
                                            color: gray;
                                            cursor: not-allowed;
                                        }
                                    </style>

                                    <div class="pd-size-choose">
                                        <%
                                            for (float sz : allSizes) {
                                                boolean isAvailable = validSizes.contains(sz);
                                        %>
                                        <div class="sc-item">
                                            <input type="radio" id="<%=sz%>-size" name="size" value="<%=sz%>" <%= isAvailable ? "" : "disabled"%> onchange="updateSize('<%=sz%>')">
                                            <label for="<%=sz%>-size" style="<%= isAvailable ? "" : "color: gray; cursor: not-allowed;"%>"><%=sz%></label>
                                        </div>
                                        <%
                                            }
                                        %>
                                        <%
                                            String msg = (String) request.getAttribute("msg");
                                            if (msg != null) {
                                        %>
                                        <p style="color: red"><%=msg%></p>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <%
                                        String userRole = "";
                                        if (loginUser != null) {
                                            userRole = loginUser.getRoleId();
                                        }
                                    %>
                                    <script>
                                        var userRole = "<%= userRole%>";
                                    </script>
                                    <script>
                                        function validateForm(event) {
                                            if (userRole !== "CUS") {
                                                event.preventDefault();
                                                alert("Only customer can add product to cart");
                                            }
                                        }
                                    </script>
                                    <div class="quantity">
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.USER_ID}">
                                                <form action="MainController" method="get" onsubmit="validateForm(event)">
                                                    <div class="pro-qty">
                                                        <input type="text" name="qnt" value="1" min="0">
                                                    </div>
                                                    <input type="hidden" name="pId" value="<%=product.getProductId()%>">
                                                    <input type="hidden" name="size" id="size-input">
                                                    <input style="border: none" type="submit" class="primary-btn pd-cart" name="action" value="Add To Cart">
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="pro-qty">
                                                    <input type="text" name="qnt" value="1" min="0">
                                                </div>
                                                <input style="border: none" type="submit" class="primary-btn pd-cart" value="Add To Cart" onclick="openDeleteModal(this, event)">
                                            </c:otherwise>
                                        </c:choose>
                                        <script>
                                            function openDeleteModal(button, event) {
                                                event.preventDefault();
                                                deleteButtonRef = button;
                                                document.getElementById('deleteConfirmation').style.display = 'block';
                                                document.getElementById('modalOverlay').style.display = 'block';
                                            }

                                            function cancelDelete() {
                                                document.getElementById('deleteConfirmation').style.display = 'none';
                                                document.getElementById('modalOverlay').style.display = 'none';
                                            }

                                        </script>
                                        <%
                                            String successMessage = (String) request.getAttribute("ms");
                                            String errorMessage = (String) request.getAttribute("err");
                                        %>
                                    </div>
                                    <% if (successMessage != null) {%>
                                    <div class="alert alert-success">
                                        <%= successMessage%>
                                    </div>
                                    <% } %>

                                    <% if (errorMessage != null) {%>
                                    <div class="alert alert-danger">
                                        <%= errorMessage%>
                                    </div>
                                    <% }%>
                                    <ul class="pd-tags">
                                        <li><span>CATEGORIES</span>: More Shoes</li>
                                        <li><span>TAGS</span>: LifeStyle, Running</li>
                                    </ul>
                                    <div class="pd-share">
                                        <div class="p-code">Sku: </div>
                                        <div class="p-code">SNK00<%=product.getProductId()%></div>

                                        <div class="pd-social">
                                            <a href="#"><i class="ti-facebook"></i></a>
                                            <a href="#"><i class="ti-twitter-alt"></i></a>
                                            <a href="#"><i class="ti-linkedin"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <script>
                                function updateSize(size) {
                                    document.getElementById('size-input').value = size;
                                }
                                function doDelete(name, event) {
                                    if (confirm("Are you sure you want to remove " + name + " from the cart?")) {
                                    } else {
                                        event.preventDefault();
                                    }
                                }


                            </script>
                        </div>
                        <div class="product-tab">
                            <div class="tab-item">
                                <ul class="nav" role="tablist">
                                    <li><a class="active" href="#tab-1" data-toggle="tab" role="tab" >SIZE CHART</a></li>
                                    <li><a href="#tab-2" data-toggle="tab" role="tab">SPECIFICATIONS</a></li>
                                    <li><a href="#tab-3" data-toggle="tab" role="tab">Customer Reviews(06)</a></li>
                                </ul>
                            </div
                            <div class="tab-item-content">
                                <div class="tab-content">
                                    <div class="tab-pane fade-in active" id="tab-1" role="tabpanel">
                                        <div class="product-content">
                                            <div class="row" style="display: flex; justify-content: center;">
                                                <div class="col-lg-8" >
                                                    <img src="img/size.png" alt="" width="500px" height="450px">
                                                </div>
                                                <div class="col-lg-4" ></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tab-2" role="tabpanel">
                                        <div class="specification-table">
                                            <table>
                                                <tr>
                                                    <td class="p-catagory">Customer Rating</td>
                                                    <td>
                                                        <div class="pd-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <span>(5)</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="p-catagory">Price</td>
                                                    <td>
                                                        <div class="p-price">
                                                            $<%=product.getPrice()%>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="p-catagory">Size</td>
                                                    <td>
                                                        <div class="p-size"><%= allSizes.get(0)%> - <%= allSizes.get(allSizes.size() - 1)%></div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="p-catagory">Color</td>
                                                    <td>
                                                        <span><%=product.getColor()%></span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="p-catagory">Sku</td>
                                                    <td>
                                                        <div class="p-code">SNK00<%=product.getProductId()%></div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tab-3" role="tabpanel">
                                        <div class="customer-review-option">
                                            <h4>2 Comments</h4>
                                            <div class="comment-option">
                                                <div class="co-item">
                                                    <div class="avatar-pic">
                                                        <img src="img/product-single/avatar-2.png" alt="">
                                                    </div>
                                                    <div class="avatar-text">
                                                        <div class="at-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <h5>Brandon Kelly <span>27 May 2024</span></h5>
                                                        <div class="at-reply">Nice!</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="personal-rating">
                                                <h6>Your Rating</h6>
                                                <div class="rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </div>
                                            </div>
                                            <div class="leave-comment">
                                                <h4>Leave a comment</h4>
                                                <form action="" class="comment-form">
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <input type="text" placeholder="Name">
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <input type="text" placeholder="Email">
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <textarea placeholder="Messages"></textarea>
                                                            <button type="submit" class="site-btn">Send
                                                                message</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Product Shop Section End -->

        <!-- Related Products Section Begin -->
        <div class="realated-products spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h2>Related Products</h2>
                        </div>
                    </div>
                </div>
                <div class="product-list" style="width: 70rem">
                    <%
                        List<ProductDTO> list = (List<ProductDTO>) session.getAttribute("RELATED_LIST");
                        for (ProductDTO ele : list) {
                    %>
                    <div class="product-item">
                        <div class="pi-pic">
                            <img src="<%=ele.getAvatarPath()%>" alt="">
                            <%
                                if (ele.getSale() != 0) {
                            %>
                            <div class="sale pp-sale">Sale</div>
                            <%
                                }
                            %>
                            <ul>
                                <form action="MainController" method="post">
                                    <input type="hidden" name="productId" value="<%=ele.getProductId()%>">
                                    <!--<li class="w-icon active"><a href="AddToCart?pId=<%=ele.getProductId()%>&qnt=1&url=shop.jsp"><i class="icon_bag_alt"></i></a></li>-->
                                    <li class="quick-view"><!--<a href="product.jsp">--><input type="submit" style="background-color: white;
                                                                                               font-weight: bold;
                                                                                               border: none;" name="action" value="View"></a></li>
                                </form>
                            </ul>
                        </div>
                        <div class="pi-text">
                            <%
                                if (ele.getUserOjectId() == 1) {
                            %>
                            <div class="catagory-name">Men</div>
                            <%
                            } else if (ele.getUserOjectId() == 2) {
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
                    %>
                </div>
            </div>
        </div>
        <!-- Related Products Section End -->

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
        <script>

        </script>

    </body>
</html>
