

<%@page import="java.util.ArrayList"%>
<%@page import="model.discount.DiscountDTO"%>
<%@page import="java.util.Arrays"%>
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
        <title>cart</title>

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
                            <li><a href="MainController?action=ViewCart&url=shopping-cart.jsp">Shopping Cart</a></li>
                            <li><a href="warrantyPage.jsp">Warranty</a></li>
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
                            <a href="homePage.jsp"><i class="fa fa-home"></i> Home</a>
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
                        <form id="checkout-form" action="MainController" method="post">
                            <div class="cart-table">
                                <table>
                                    <thead>
                                        <tr>                                                                                
                                            <th>Select</th>
                                            <th>Image</th>
                                            <th class="p-name">Product Name</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                            <th>Size</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            CartDTO cart = (CartDTO) session.getAttribute("CART");
                                            if (cart == null) {
                                                cart = new CartDTO();
                                            }
                                            List<CartItems> ls = cart.getCartItemsList();
                                            if (ls != null) {
                                                int count = 1;
                                                double total = 0;
                                                for (CartItems ele : ls) {
                                                    if (ele.isIsSelected()) {
                                                        total += (ele.getPrice() * ele.getQuantity());
                                                    }
                                        %>
                                        <tr>
                                            <%
                                                boolean status = ele.isStatus();
                                                if (status) {
                                            %>
                                            <td class="checkbox-card">
                                                <input type="checkbox" 
                                                       name="selectedProductId" 
                                                       value="<%= ele.getProduct().getProductId()%>" 
                                                       <%= ele.isIsSelected() ? "checked" : ""%> 
                                                       onchange="toggleSelected(<%= ele.getCartItemId()%>, this.checked)">
                                            </td>
                                            <%
                                            } else {
                                            %>
                                            <td class="checkbox-card">
                                                <input type="checkbox" disabled />
                                            </td>
                                            <%
                                                }
                                            %>
                                    <script>
                                        function toggleSelected(cartItemId, select) {
                                            var url = "MainController?action=toggleSelectProduct&cartItemId=" + cartItemId + "&isSelected=" + (select ? 1 : 0);
                                            window.location.href = url;
                                        }
                                    </script>    
                                    <td class="cart-pic first-row"><img src="<%=ele.getProduct().getAvatarPath()%>" style="height: 100px; width: 100px" alt=""></td>
                                    <td class="cart-title first-row">
                                        <h5><%=ele.getProduct().getName()%></h5>
                                        <%
                                            if (!status) {
                                        %>
                                        <div class="alert alert-danger"> Sorry, this size is out of stock</div>

                                        <%
                                            }
                                        %>
                                    </td>
                                    <td class="p-price first-row">$<%=String.format("%.1f", ele.getPrice())%></td>
                                    <td class="qua-col first-row">
                                        <div class="quantity">
                                            <!-- Nút trừ số lượng -->
                                            <button style="border: none">
                                                <a href="MainController?action=Edit quantity&num=-1&cartItemId=<%=ele.getCartItemId()%>">-</a>
                                            </button>

                                            <!-- Hiển thị số lượng hiện tại, người dùng có thể chỉnh sửa thủ công -->
                                            <input type="number" min="1" value="<%=ele.getQuantity()%>" readonly="">

                                            <!-- Nút cộng số lượng -->
                                            <button style="border: none">
                                                <a href="MainController?action=Edit quantity&num=1&cartItemId=<%=ele.getCartItemId()%>">+</a>
                                            </button>
                                        </div>
                                    </td>
                                    <td class="total-price first-row">$<%= String.format("%.1f", ele.getPrice() * ele.getQuantity())%></td>
                                    <td>
                                        <select style="margin-top:26px; width: 65px;" id="size-select-<%=count%>" onchange="updateSize(this.value, '<%=ele.getCartItemId()%>', 'Edit Size')">
                                            <%
                                                List<Float> validSizes = ele.getProduct().getAvaiableSize();
                                                List<Float> allSizes = ele.getProduct().getAllSize();
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
                                    </td>
                                    <td class="close-td first-row"><a href="MainController?cartItemId=<%= ele.getCartItemId()%>&action=doDelete&url=shopping-cart.jsp" onclick="doDelete('<%=ele.getProduct().getName()%>', event)">
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
                            </div>
                            <script>
                                function updateSize(size, cartItemId, action) {
                                    var url = "MainController?action=" + action + "&cartItemId=" + cartItemId + "&size=" + size;
                                    window.location.href = url;
                                }
                                function doDelete(name, event) {
                                    if (confirm("Are you sure you want to remove " + name + " from the cart?")) {
                                    } else {
                                        event.preventDefault();
                                    }
                                }
                            </script>
                            <%
                                String err = (String) request.getAttribute("err");
                                if (err != null) {
                            %>
                            <div class="alert alert-danger"><%= err%></div>

                            <%
                                }
                            %>
                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="cart-buttons">
                                        <a href="MainController?action=ShopPage" class="primary-btn up-cart">Continue Shopping</a>
                                    </div>
                                    <div class="discount-coupon">
                                        <h6>Discount Codes</h6>
                                        <div class="coupon-form">
                                            <input style="color: black;" type="text" id="coupon-code" placeholder="Enter your codes" value="<%= (request.getParameter("code") != null ? request.getParameter("code") : "")%>">
                                            <button type="button" class="site-btn coupon-btn" onclick="applyCoupon()">Apply</button>
                                        </div>

                                        <%
                                            DiscountDTO c = (DiscountDTO) session.getAttribute("code");
                                            String err_discount = (String) request.getAttribute("err_discount");
                                            String ms_discount = (String) request.getAttribute("ms_discount");
                                            if (err_discount != null) {
                                        %>
                                        <div class="alert alert-danger"><%=err_discount%></div>
                                        <%
                                            }
                                        %>
                                        <%if (ms_discount != null) {
                                        %>
                                        <div class="alert alert-success"><%=ms_discount%></div>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <script>
                                        function applyCoupon() {
                                            var codeInput = document.getElementById('coupon-code');
                                            var code = codeInput ? codeInput.value.trim() : '';
                                            var url = "MainController?action=Apply&code=" + encodeURIComponent(code);
                                            window.location.href = url;
                                        }
                                    </script>                                </div>

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
                                        <a href="MainController?action=ProceedCheckOut" class="proceed-btn">PROCEED TO CHECKOUT</a>
                                    </div>
                                    <% if (request.getAttribute("STOCK_ERR") != null) {%>
                                    <div class="alert alert-danger"><%= request.getAttribute("STOCK_ERR")%></div>
                                    <% } %>

                                    <% if (request.getAttribute("EMPTY_CART_ERROR") != null) {%>
                                    <div class="alert alert-danger"><%= request.getAttribute("EMPTY_CART_ERROR")%></div>
                                    <% } %>
                                </div>
                                <%}
                                %>
                            </div>
                        </form>
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
