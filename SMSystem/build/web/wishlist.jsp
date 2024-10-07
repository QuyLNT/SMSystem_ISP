<%-- 
    Document   : wishlist
    Created on : Jun 22, 2024, 7:08:51 PM
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
        <title>wishlist</title>

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
                                                            <a href="RemoveServlet?pId=<%=count%>&url=shop.jsp" onclick="doDelete('<%=ele.getProduct().getName()%>', event)">
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
                                                <h5>$<%=String.format("%.1f", total)%></h5>
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
                            <span>Wishlist</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Breadcrumb Section End-->

        <div class="wishlist-heading">My Wishlist</div>

        <!-- Shopping Cart Section Begin -->
        <div class="shopping-cart-wishlist spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <form id="sortingForm" method="get" action="SortWishlistServlet">
                            <select class="sorting" name="id" onchange="document.getElementById('sortingForm').submit();">
                                <option value="1" ${selectedId == null || selectedId.equals("1") ? "selected" : ""}>Recently added</option>
                                <option value="2" ${selectedId != null && selectedId.equals("2") ? "selected" : ""}>On Sale</option>
                                <option value="3" ${selectedId != null && selectedId.equals("3") ? "selected" : ""}>Price: High - Low</option>
                                <option value="4" ${selectedId != null && selectedId.equals("4") ? "selected" : ""}>Price: Low - High</option>
                                <option value="5" ${selectedId != null && selectedId.equals("5") ? "selected" : ""}>Alphabetical</option>
                            </select>
                        </form>
                        <div class="cart-table">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th class="p-name">Product Name</th>
                                        <th>Brand</th>
                                        <th>Color</th>
                                        <th></th>
                                        <th>Price</th>
                                        <th>View In Shop</th>
                                        <th><i class="ti-close"></i></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<ProductDTO> ls = (List<ProductDTO>) session.getAttribute("productWishlistList");
                                        ProductDAO pDAO = new ProductDAO();
                                        if (ls == null) {
                                            ls = pDAO.getAllProductListWish(user.getUserId());
                                        }
                                        if (ls != null) {
                                            int count = 1;
                                            for (ProductDTO ele : ls) {

                                    %>
                                    <tr>
                                        <td class="cart-pic first-row"><img src="<%=ele.getImg()%>" style="height: 100px; width: 100px" alt=""></td>
                                        <td class="cart-title first-row">
                                            <h5><%=ele.getName()%></h5>
                                        </td>
                                        <td class="p-price-wishlist first-row"><%= pDAO.getBrandName(ele.getBrandId())%></td>
                                        <td class="p-price-wishlist first-row"><%= ele.getColor()%></td>
                                        <%if(ele.getSale()!=0){ %>
                                        <td class="p-price-wishlist first-row" style="text-decoration: line-through">$<%=ele.getPrice() %></td>
                                        <%}else{ %>
                                             <td class="p-price-wishlist first-row" ></td>
                                        <%} %>
                                        <td class="p-price-wishlist first-row">$<%= String.format("%.2f", (1-ele.getSale())* ele.getPrice())%></td>
                                
                                        <td class="p-price-wishlist first-row">
                                            <form action="ProductInfoServlet" method="POST">
                                                <input type="hidden" name="productId" value="<%= ele.getProductId() %>">
                                                <button type="submit" name="action" value="View">View</button>
                                            </form>
                                        </td>
                                
                                        <td class="close-td first-row"><a href="RemoveWishlistServlet?pId=<%= ele.getProductId()%>" onclick="doDelete('<%=ele.getName()%>', event)">
                                                <i class="ti-close"></i>
                                            </a></td>
                                            
                                    </tr>
                                    <%
                                        }
                                    } else { %>
                                <div>
                                    No result found
                                </div>
                                <%}%>
                                
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
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
        <script>
                                function doDelete(name, event) {
                                    if (confirm("Are you sure you want to remove " + name + " from the wishlist?")) {
                                    } else {
                                        event.preventDefault();
                                    }
                                }
                            </script>
    </body>
</html>

