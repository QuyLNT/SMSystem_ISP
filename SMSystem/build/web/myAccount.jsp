

<%@page import="model.product.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.product.ProductDTO"%>
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
                                                        </td>
                                                        <td class="si-close">
                                                            <a href="MainController?cartItemId=<%= ele.getCartItemId()%>&action=doDelete&url=myAccount.jsp" onclick="doDelete('<%= ele.getProduct().getName()%>', event)">
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
                                            <% }%>
                                            <div class="select-button">
                                                <a href="MainController?action=ViewCart&url=myAccount.jsp" class="primary-btn view-card">VIEW CART</a>
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
                            <li><a href="MainController?action=ViewCart&url=myAccount.jsp">Shopping Cart</a></li>
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
                <form action="MainController" class="checkout-form">
                    <div class="row setting-center">
                        <div class="col-lg-6">
                            <div class="row">
                                <div class="col-lg-12">
                                    <label for="userName">userName <span>*</span></label>
                                    <input type="text" id="userName-exist" name="userName" value="<%=loginUser.getUserName()%>" readonly class="form-control">
                                </div>

                                <div class="col-lg-12">
                                    <label for="password">password <span>*</span></label>
                                    <% if (loginUser.getPassword() == null) { %>
                                    <input type="password" id="pass" name="pass">
                                    <% } else {%>

                                    <input type="password" id="password-exist" name="pass" value="<%= loginUser.getPassword()%>" readonly>
                                    <button type="button" id="edit-btn-pass" data-bs-toggle="modal" data-bs-target="#changePasswordModal">
                                        <i class="fa fa-pencil-square-o"></i>
                                    </button>


                                    <div class="modal fade" id="changePasswordModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title fs-5" id="updateModalLabel">Change password</h5>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="input-group input-group-sm mb-3">
                                                        <span class="input-group-text" id="inputGroup-sizing-sm">Current password</span>
                                                        <input name="currentPassword" type="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="">
                                                    </div>
                                                    <% if (request.getAttribute("CURRENT_PASS_ERROR") != null) {%>
                                                    <span style="color:red;">
                                                        <%= request.getAttribute("CURRENT_PASS_ERROR")%>
                                                    </span>
                                                    <% } %>
                                                </div>

                                                <div class="modal-body">
                                                    <div class="input-group input-group-sm mb-3">
                                                        <span class="input-group-text" id="inputGroup-sizing-sm">New Password</span>
                                                        <input name="newPassword" type="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="">
                                                    </div>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="input-group input-group-sm mb-3">
                                                        <span class="input-group-text" id="inputGroup-sizing-sm">Confirrm new password</span>
                                                        <input name="confirmNewPassword" type="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="">
                                                        <div id="passwordErrorMessage" style="color:red;">
                                                            <% if (request.getAttribute("PASS_ERROR") != null) {%>
                                                            <%= request.getAttribute("PASS_ERROR")%>
                                                            <% } %>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer1">
                                                    <button type="button" class="btn1 btn-secondary1" data-bs-dismiss="modal">Close</button>
                                                    <button type="submit" name="action" value="Update" class="btn1 btn-primary1">Update</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <% }%>
                                </div>

                                <div class="col-lg-12">
                                    <label for="fullName">Full Name <span>*</span></label>
                                    <input type="text" id="fullName-exist" name="fullName" value="<%=loginUser.getFullName()%>" readonly class="form-control">
                                    <button type="button" id="edit-btn-fullName" class="edit-btn" onclick="enableField('fullName-exist')"><i class="fa fa-pencil-square-o"></i></button>
                                    <span class="text-danger"><%= request.getAttribute("FULLNAME_ERROR") != null ? request.getAttribute("FULLNAME_ERROR") : ""%></span>
                                </div>

                                <div class="col-lg-12">
                                    <label for="phone">Phone <span>*</span></label>
                                    <input type="text" id="phone-exist" name="phone" value="<%=loginUser.getPhoneNumber()%>" readonly class="form-control">
                                    <button type="button" id="edit-btn-phone" class="edit-btn" onclick="enableField('phone-exist')"><i class="fa fa-pencil-square-o"></i></button>
                                    <span class="text-danger"><%= request.getAttribute("PHONE_ERROR") != null ? request.getAttribute("PHONE_ERROR") : ""%></span>
                                </div>

                                <div class="col-lg-12">
                                    <label for="sex">Sex <span>*</span></label>
                                    <select id="sex" name="sex" class="form-control" style="background-color: #cce7f0;">
                                        <option value="" disabled selected>-- Select Gender --</option> 
                                        <option value="Male" <% if ("Male".equals(loginUser.getSex())) {
                                                out.print("selected");
                                            } %>>Male</option>
                                        <option value="Female" <% if ("Female".equals(loginUser.getSex())) {
                                                out.print("selected");
                                            } %>>Female</option>
                                        <option value="Other" <% if ("Other".equals(loginUser.getSex())) {
                                                out.print("selected");
                                            }%>>Other</option>
                                    </select>
                                    <button type="button" id="edit-btn-sex" class="edit-btn" onclick="enableSelect('sex')">
                                        <i class="fa fa-pencil-square-o"></i>
                                    </button>
                                    <span class="text-danger"><%= request.getAttribute("SEX_ERROR") != null ? request.getAttribute("SEX_ERROR") : ""%></span>
                                </div>

                                <div class="col-lg-12">
                                    <label for="email">Email Address <span>*</span></label>
                                    <input type="email" id="email-exist" name="email" value="<%=loginUser.getEmail()%>" readonly class="form-control">
                                    <button type="button" id="edit-btn-email" class="edit-btn" onclick="enableField('email-exist')"><i class="fa fa-pencil-square-o"></i></button>
                                    <span class="text-danger"><%= request.getAttribute("EMAIL_ERROR") != null ? request.getAttribute("EMAIL_ERROR") : ""%></span>
                                </div>
                            </div>
                            <!--                            <input type="submit" id="UpdateUser" name="action" value="Update" class="btn btn-primary">-->
                            <%
                                String message = (String) request.getAttribute("MESSAGE");
                                if (message == null) {
                                    message = "";
                                }
                            %>
                            <h4 style="color:red "> <%= message%> </h4> 
                            <input type="submit" id="UpdateUser" name="action" value="Update" class="btn signin-btn">
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script>
            function enableField(fieldId) {
                var field = document.getElementById(fieldId);
                if (field) {
                    field.readOnly = false;
                    field.style.backgroundColor = "white";
                    field.focus();
                }
            }

            function enableSelect(selectId) {
                var select = document.getElementById(selectId);
                if (select) {
                    select.disabled = false;
                    select.style.backgroundColor = "white";
                    select.focus();
                }
            }

            document.addEventListener("DOMContentLoaded", function () {
                var currentPasswordError = "<%= request.getAttribute("CURRENT_PASS_ERROR") != null ? request.getAttribute("CURRENT_PASS_ERROR") : ""%>";
                var passwordError = "<%= request.getAttribute("PASS_ERROR") != null ? request.getAttribute("PASS_ERROR") : ""%>";

                if (currentPasswordError.trim().length > 0 || passwordError.trim().length > 0) {
                    var passwordModal = new bootstrap.Modal(document.getElementById('changePasswordModal'));
                    passwordModal.show();
                }
            });
        </script>
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
