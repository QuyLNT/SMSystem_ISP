<%-- 
    Document   : shopping-cart
    Created on : Jun 13, 2024, 11:09:49 PM
    Author     : Luu Minh Quan
--%>

<%@page import="model.Code"%>
<%@page import="java.util.Arrays"%>
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
        <title>cart</title>

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
                            <span>Shopping Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Breadcrumb Section End-->

        <!-- Shopping Cart Section Begin -->
        <div class="shopping-cart spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="cart-table">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th class="p-name">Product Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                        <th>Size</th>
                                        <th></th>
                                        <th><i class="ti-close"></i></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        CartDTO cart = (CartDTO) session.getAttribute("CART");
                                        if (cart == null) {
                                            cart = new CartDTO();
                                        }
                                        List<ItemDTO> ls = cart.getList();
                                        if (ls != null) {
                                            int count = 0;
                                            double total = 0;
                                            for (ItemDTO ele : ls) {
                                                total += (ele.getPrice() * ele.getQuantity());
                                    %>
                                    <tr>
                                        <td class="cart-pic first-row"><img src="<%=ele.getProduct().getImg()%>" style="height: 100px; width: 100px" alt=""></td>
                                        <td class="cart-title first-row">
                                            <h5><%=ele.getProduct().getName()%></h5>
                                        </td>
                                        <td class="p-price first-row">$<%=String.format("%.1f", ele.getPrice())%></td>
                                        <td class="qua-col first-row">
                                            <div class="quantity">
                                                <!--<div class="pro-qty">-->
                                                <button style="border: none"><a href="UpdateCartServlet?num=-1&id=<%=count%>">-</a></button>
                                                <input type="number" min="1" value="<%=ele.getQuantity()%>">
                                                <button style="border: none"><a href="UpdateCartServlet?num=1&id=<%=count%>">+</a></button>
                                                <!--</div>-->
                                            </div>
                                        </td>
                                        <td class="total-price first-row">$<%= String.format("%.1f", ele.getPrice() * ele.getQuantity())%></td>
                                        <td>
                                            <%
                                                if (ele.getProduct().getUoId() == 3) {
                                            %>
                                            <select  style="margin-top:26px; width: 65px;" id="size-select-<%=count%>" onchange="updateSize(this.value, <%=count%>)">
                                                <%
                                                    List<Float> validSizes = (List<Float>) session.getAttribute("sizes");
                                                    List<Float> allSizes = Arrays.asList(3f, 4f, 5f, 6f);
                                                    for (float sz : allSizes) {
                                                        boolean isAvailable = validSizes.contains(sz);
                                                        if (isAvailable) {
                                                %>
                                                <option value="<%=sz%>" <%=ele.getSize() == sz ? "selected" : ""%>><%=sz%></option>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </select>
                                            <%
                                            } else {
                                            %>
                                            <select id="size-select-<%=count%>" onchange="updateSize(this.value, <%=count%>)">
                                                <%
                                                    List<Float> validSizes = (List<Float>) session.getAttribute("sizes");
                                                    List<Float> allSizes = Arrays.asList(6f, 6.5f, 7f, 7.5f, 8f, 8.5f, 9f, 9.5f, 10f, 10.5f, 11f);
                                                    for (float sz : allSizes) {
                                                        boolean isAvailable = validSizes.contains(sz);
                                                        if (isAvailable) {
                                                %>
                                                <option value="<%=sz%>" <%=ele.getSize() == sz ? "selected" : ""%>><%=sz%></option>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </select>
                                            <%
                                                }
                                            %>
                                        </td>
                                        <td>
                                            <form id="form-<%=count%>" action="UpdateSize" method="get">
                                                <input type="hidden" name="index" value="<%=count%>">
                                                <input type="hidden" name="size" id="size-input-<%=count%>">
                                            </form>
                                        </td>

                                <script>
                                    function updateSize(size, index) {
                                        document.getElementById('size-input-' + index).value = size;
                                        document.getElementById('form-' + index).submit();
                                    }
                                    function doDelete(name, event) {
                                        if (confirm("Are you sure you want to remove " + name + " from the cart?")) {
                                        } else {
                                            event.preventDefault();
                                        }
                                    }
                                </script>
                                <td class="close-td first-row"><a href="RemoveServlet?pId=<%= count%>&url=shopping-cart.jsp" onclick="doDelete('<%=ele.getProduct().getName()%>', event)">
                                        <i class="ti-close"></i>
                                    </a></td>
                                </tr>
                                <%
                                        count++;
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                        <%
                            String err = (String) request.getParameter("err");
                            if (err != null) {
                        %>
                        <p><%= err%></p>
                        <%
                            }
                        %>
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="cart-buttons">
                                    <a href="shop.jsp" class="primary-btn up-cart">Continue Shopping</a>
                                </div>
                                <div class="discount-coupon">
                                    <h6>Discount Codes</h6>
                                    <form action="MainController" method="Post" class="coupon-form">
                                        <input style="color: black;" type="text" name="code" placeholder="Enter your codes">
                                        <button type="submit" value="Apply" name="action" class="site-btn coupon-btn">Apply</button>
                                    </form>
                                    <%
                                        Code c = (Code) session.getAttribute("code");
                                        String ms = (String) request.getAttribute("msg");
                                        if (ms != null) {
                                    %>
                                    <p style="color: red"><%=ms%></p>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>

                            <div class="col-lg-4 offset-lg-4">
                                <div class="proceed-checkout">
                                    <ul>
                                        <li class="subtotal">Subtotal <span>$<%=String.format("%.1f", total)%></span></li>

                                        <%
                                            if (c != null) {
                                        %>
                                        <li class="subtotal" style="margin-top: 15px;">Discount <span>-$<%=c.getDiscountAmount()%></span></li>
                                        <li class="cart-total">Total <span>$<%= String.format("%.1f", total * 1 - c.getDiscountAmount())%></span></li>
                                            <%
                                            } else {
                                            %>
                                        <li class="cart-total">Total <span>$<%= String.format("%.1f", total * 1)%></span></li>
                                            <%
                                                }
                                            %>
                                    </ul>
                                    <a href="check-out.jsp" class="proceed-btn">PROCEED TO CHECK OUT</a>
                                </div>
                            </div>
                            <%}
                            %>
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
    </body>
</html>
