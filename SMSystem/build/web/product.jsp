<%-- 
    Document   : product
    Created on : Jun 13, 2024, 11:25:06 PM
    Author     : Luu Minh Quan
--%>

<%@page import="java.util.Arrays"%>
<%@page import="model.UserDTO"%>
<%@page import="model.ItemDTO"%>
<%@page import="model.CartDTO"%>
<%@page import="model.ProductDTO"%>
<%@page import="model.ProductIMG"%>
<%@page import="java.util.List"%>
<%@page import="model.ProductDAO"%>
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
                                                            <a href="RemoveServlet?pId=<%= count%>&url=product.jsp" onclick="doDelete('<%=ele.getProduct().getName()%>', event)">
                                                                <i class="ti-close"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                    <%
                                                            count++;
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
                                              
                                            </div>
                                            <% } %>
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
        <!-- Breadcrumb Section Begin -->
        <div class="breadcrumb-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-text">
                            <a href="index.jsp"><i class="fa fa-home"></i> Home</a>
                            <a href="shop.jsp">Shop</a>
                            <span>Detail</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb Section End -->

        <!-- Product Shop Section Begin -->
        <div class="product-shop spad page-details">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-6">
                                <%
//                                    String pId = request.getParameter("productId");
                                    List<ProductIMG> ls = (List<ProductIMG>) session.getAttribute("listImg");
                                    ProductDTO ob = (ProductDTO) session.getAttribute("object");
                                    if (ls != null) {
                                        for (ProductIMG ele : ls) {


                                %>
                                <div class="product-pic-zoom">
                                    <img src="<%=ele.getPath()%>"
                                         class="product-big-img" alt="">
                                    <div class="zoom-icon">
                                        <i class="fa fa-search"></i>
                                    </div>
                                </div>

                                <div class="product-thumbs">

                                    <div class="product-thumbs-track ps-slider owl-carousel">
                                        <%
                                            for (ProductIMG elem : ls) {
                                        %>
                                        <div class="pt"
                                             data-imgbigurl="<%=elem.getPath()%>">
                                            <img src="<%=elem.getPath()%>"
                                                 alt="">
                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                                <%                                            break;
                                        }
                                    }
                                %>
                            </div>
                            <div class="col-lg-6">
                                <div class="product-details">
                                    <div class="pd-title">
                                        <span>oranges</span>
                                        <h3><%=ob.getName()%></h3>
                                       <form action="InsertWishlist" method="post"> 
                                    
                                    <div class="icon">
                                       <button id="btn-icon" type="submit">
                                        <input type="hidden" name="id" value="<%= ob.getProductId() %>">
                                        <input type="hidden" name="url" value="product.jsp">
                                        <i class="icon_heart_alt"></i>
                                        </button>
                                    </div>
                                        
                                        </form>
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
                                        <p><%=ob.getDescription()%></p>
                                        <%
                                            if (ob.getSale() != 0) {
                                        %>
                                        <h4>$<%= String.format("%.1f", ob.getPrice() * (1 - ob.getSale()))%> <span>$<%=ob.getPrice()%></span></h4>
                                        <%
                                        } else {
                                        %>
                                        <h4>$<%=ob.getPrice()%></h4>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <%
                                        if (ob.getUoId() == 1 || ob.getUoId() == 2) {
                                            List<Float> validSizes = (List<Float>) session.getAttribute("sizes");
                                            List<Float> allSizes = Arrays.asList(6f, 6.5f, 7f, 7.5f, 8f, 8.5f, 9f, 9.5f, 10f, 10.5f, 11f); // Tất cả các size
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
                                    <%                                                } else {
                                        List<Float> validSizes = (List<Float>) session.getAttribute("sizes");
                                        List<Float> allSizes = Arrays.asList(3f, 4f, 5f, 6f);
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
                                    </div>
                                    <%
                                        String msg = (String) request.getAttribute("msg");
                                        if (msg != null) {
                                    %>
                                    <p style="color: red"><%=msg%></p>
                                    <%
                                            }
                                        }
                                    %>
                                    <div class="quantity">
                                        <form action="AddToCart" method="get">
                                            <div class="pro-qty">
                                                <input type="text" name="qnt" value="1" min="0">
                                            </div>
                                            <input type="hidden" name="pId" value="<%=ob.getProductId()%>">
                                            <input type="hidden" name="url" value="product.jsp">
                                            <input type="hidden" name="size" id="size-input">
                                            <input style="border: none" type="submit" class="primary-btn pd-cart" value="Add To Cart">
                                        </form>
                                    </div>
                                    <ul class="pd-tags">
                                        <li><span>CATEGORIES</span>: More Shoes</li>
                                        <li><span>TAGS</span>: Men, LifeStyle, Running</li>
                                    </ul>
                                    <div class="pd-share">
                                        <div class="p-code">Sku: 00012</div>
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
                                    <li><a class="active" href="#tab-1" data-toggle="tab" role="tab">DESCRIPTION</a></li>
                                    <li><a href="#tab-2" data-toggle="tab" role="tab">SPECIFICATIONS</a></li>
                                    <li><a href="#tab-3" data-toggle="tab" role="tab">Customer Reviews(06)</a></li>
                                </ul>
                            </div>
                            <div class="tab-item-content">
                                <div class="tab-content">
                                    <div class="tab-pane fade-in active" id="tab-1" role="tabpanel">
                                        <div class="product-content">
                                            <div class="row">
                                                <div class="col-lg-7">
                                                    <h5>Introduction</h5>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas minima
                                                        sint libero quo quisquam ipsum nam cum adipisci praesentium
                                                        consequatur? Modi ab eaque voluptatibus a totam qui obcaecati
                                                        ratione quos?</p>
                                                    <h5>Features</h5>
                                                    <p><%=ob.getDescription()%></p>
                                                </div>
                                                <div class="col-lg-5">
                                                    <img src="https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2017%2F08%2Ftyshawn-jones-adidas-pro-model-vulc-adv-white-green-python-1.jpg?cbr=1&q=90" alt="">
                                                </div>
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
                                                            $<%=ob.getPrice()%>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="p-catagory">Size</td>
                                                    <td>
                                                        <div class="p-size"><%=ob.getSize()%></div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="p-catagory">Color</td>
                                                    <td>
                                                        <span><%=ob.getColor()%></span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="p-catagory">Sku</td>
                                                    <td>
                                                        <div class="p-code">00012</div>
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
                        List<ProductDTO> list = (List) session.getAttribute("RelatedList");
                        for (ProductDTO ele : list) {
                    %>
                    <div class="product-item">
                        <div class="pi-pic">
                            <img src="<%=ele.getImg()%>" alt="">
                            <%
                                if (ele.getSale() != 0) {
                            %>
                            <div class="sale pp-sale">Sale</div>
                            <%
                                }
                            %>
                            <div class="icon">
                                <i class="icon_heart_alt"></i>
                            </div>
                            <ul>
                                <form action="MainController" method="post">
                                    <input type="hidden" name="productId" value="<%=ele.getProductId()%>">
                                    <li class="quick-view"><a href="product.jsp"><input type="submit" style="background-color: white;
                                                                                        font-weight: bold;
                                                                                        border: none;" name="action" value="View"></a></li>
                                </form>
                            </ul>
                        </div>

                        <div class="pi-text">
                            <%
                                if (ele.getUoId() == 1) {
                            %>
                            <div class="catagory-name">Men</div>
                            <%
                            } else if (ele.getUoId() == 2) {
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
