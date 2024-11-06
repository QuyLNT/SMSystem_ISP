

<%@page import="model.methods.PaymentMethodDTO"%>
<%@page import="model.methods.ShippingMethodDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.discount.DiscountDTO"%>
<%@page import="model.user.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.cart.CartDTO"%>
<%@page import="model.cart.CartItems"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>check-out</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
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
        <link rel="icon" href="img/icon-logoweb.png" type="img/x-icon" />
        <link rel="stylesheet" href="css/style1.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="css/style3.css" type="text/css">
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
                                            <% }%>
                                            <div class="select-button">
                                                <a href="shopping-cart.jsp" class="primary-btn view-card">VIEW CART</a>
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
                            <li><a href="MainController?action=ViewCart&url=check-out.jsp">Shopping Cart</a></li>
                            <li><a href="warrantyPage.jsp">Warranty</a></li>
                        </ul>
                    </nav>
                    <div id="mobile-menu-wrap"></div>
                </div>
            </div>
        </header>

        <!--Breadcrumb Section Begin-->
        <div class="breadcrumb-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-text">
                            <a href="MainController?action=HomePage"><i class="fa fa-home"></i> Home</a>
                            <a href="MainController?action=ViewCart&url=check-out.jsp">Cart</a>
                            <span>Check Out</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Breadcrumb Section End-->

        <!-- Shopping Cart Section Begin -->
        <div class="checkout-section spad">
            <div class="container">
                <form action="MainController" method="POST" accept-charset="UTF-8" onsubmit="return validateOrder()" class="checkout-form">
                    <div class="row">
                        <div class="col-lg-6">
                            <h4>Billing Details</h4>
                            <div class="row">
                                <div class="col-lg-12">
                                    <label for="fir">Full Name <span>*</span></label>
                                    <input type="text" id="fir" name="fullname" value="<%= loginUser.getFullName()%>">
                                </div>
                                <div class="col-lg-12 col-md-7">
                                    <div class="select-option-province ">
                                        <label for="province">Province / City <span>*</span></label>
                                        <select id="province" name="province" required aria-live="polite" onchange="fetchDistricts()">
                                            <option value="" disabled selected>Select Province / City</option>
                                        </select>
                                        <input type="hidden" id="provinceName" name="provinceName">
                                    </div>
                                </div>

                                <div class="col-lg-12 col-md-7">
                                    <div class="select-option-province">
                                        <label for="district">District <span>*</span></label>
                                        <select id="district" name="district" required aria-live="polite">
                                            <option value="" disabled selected>Select District</option>
                                        </select>
                                        <input type="hidden" id="districtName" name="districtName">
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <label for="street">Street Address <span>*</span></label>
                                    <input type="text" id="street" name="street" required="" placeholder="House Number, Street Name"class="street-first">
                                </div>
                                <div class="col-lg-6">
                                    <label for="email">Email Address<span>*</span></label>
                                    <input type="text" id="email" name="email" value="<%= loginUser.getEmail()%>">
                                </div>
                                <div class="col-lg-6">
                                    <label for="phone">Phone <span>*</span></label>
                                    <input type="text" id="phone" name="phone" value="<%= loginUser.getPhoneNumber()%>">
                                </div>
                            </div>
                            <p id="error-message" style="display:none; color:red;">Error fetching districts. Please try again.</p>

                        </div>
                        <div class="col-lg-6">
                            <div class="place-order">
                                <h4>Your Order</h4>
                                <div class="order-total">
                                    <ul class="order-table">
                                        <li>Product <span>Total</span></li>
                                            <%
                                                if (cart == null) {
                                                    cart = new CartDTO();
                                                }
                                                DiscountDTO c = (DiscountDTO) session.getAttribute("code");
                                                CartDTO selectedCart = (CartDTO) session.getAttribute("SELECTED_CART");
                                                List<CartItems> ls = selectedCart.getCartItemsList();

                                                if (ls != null) {
                                                    int count = 1;
                                                    double total = 0;
                                                    for (CartItems ele : ls) {
                                                        total += (ele.getPrice() * ele.getQuantity());


                                            %>
                                        <li style="display: none">                                           
                                            <input type="hidden" name="userId" value="<%= loginUser.getUserId()%>" />
                                            <input type="hidden" name="total" value="<%= total%>"/>
                                        </li>
                                        <li class="fw-normal"><%=ele.getProduct().getName()%> x <%=ele.getQuantity()%><br>Size: US <%=ele.getSize()%><span>$<%= String.format("%.1f", ele.getPrice())%> x <%= ele.getQuantity()%></span></li>
                                            <%
                                                }
                                            %>
                                        <li class="fw-normal">Subtotal <span>$<%= String.format("%.1f", total)%></span></li>

                                        <%
                                            if (c != null) {
                                        %>
                                        <li class="fw-normal">Discount <span>-$ <input type="hidden" name="discount" value="<%=c.getDiscountId()%>"/><%=c.getDiscountAmount()%></span></li>
                                        <li class="fw-normal">Total <span>$<%= String.format("%.1f", total - c.getDiscountAmount())%></span></li>
                                            <%
                                            } else {
                                            %>
                                        <li class="fw-normal">Total <span>$<%= String.format("%.1f", total * 1)%></span></li>
                                            <%
                                                }
                                            %>
                                        <li class="fw-normal">  <label>PayMethod:</label>
                                            <select name="pay" id="pay" class="form-select" required aria-label="Default select example">
                                                <option value="" disabled="" selected>Choose your payment method</option>
                                                <%
                                                    List<PaymentMethodDTO> payMethods = (List<PaymentMethodDTO>) session.getAttribute("PAYMENT_METHOD");
                                                    if (payMethods != null) {
                                                        for (PaymentMethodDTO pay : payMethods) {
                                                %>
                                                <option value="<%= pay.getId()%>"><%= pay.getName()%></option>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </select>
                                        </li>
                                        <li class="fw-normal"><label>ShipMethod:</label>
                                            <select name="ship" id="ship" class="form-select" required="" aria-label="Default select example" >
                                                <option value="" disabled="" selected>Choose your shipment method</option>
                                                <%
                                                    List<ShippingMethodDTO> shippingMethods = (List<ShippingMethodDTO>) session.getAttribute("SHIPPING_METHOD");
                                                    if (shippingMethods != null) {
                                                        for (ShippingMethodDTO shipping : shippingMethods) {
                                                %>
                                                <option value="<%= shipping.getId()%>"><%= shipping.getName()%></option>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </select>
                                        </li>
                                        <%                                            }
                                        %>
                                    </ul>
                                    <div class="order-btn">
                                        <button type="submit" name="action" value="PlaceOrder" class="site-btn place-btn">Place Order</button>
                                        <script>
                                            function validateOrder() {
                                                var pay = document.getElementById('pay').value;
                                                var ship = document.getElementById('ship').value;

                                                if (!pay || !ship) {
                                                    return false;
                                                } else {
                                                    return true;
                                                }
                                            }
                                        </script>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Shopping Cart Section End -->

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
                                <a href="MainController?action=HomePage">
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
                                <li><a href="contact.jsp">About Us</a></li>
                                <li><a href="MainController?action=ProceedCheckOut">Checkout</a></li>
                                <li><a href="contact.jsp">Contact</a></li>
                                <li><a href="contact.jsp">Services</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-2">
                        <div class="footer-widget">
                            <h5>My Account</h5>
                            <ul>
                                <li><a href="myAccount.jsp">My Account</a></li>
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
                                            // Fetch provinces on page load
                                            document.addEventListener('DOMContentLoaded', fetchProvinces);

                                            function fetchProvinces() {
                                                fetch('https://provinces.open-api.vn/api/')
                                                        .then(response => response.json())
                                                        .then(data => {
                                                            const provinceSelect = document.getElementById("province");
                                                            data.forEach(province => {
                                                                const option = document.createElement("option");
                                                                option.value = province.code;
                                                                option.textContent = province.name;
                                                                provinceSelect.appendChild(option);
                                                            });
                                                        })
                                                        .catch(error => console.error("Error fetching provinces:", error));
                                            }

                                            function fetchDistricts() {
                                                const provinceCode = document.getElementById("province").value;
                                                const districtSelect = document.getElementById("district");
                                                districtSelect.innerHTML = '<option value="" disabled selected>Select District</option>';
                                                const url = "https://provinces.open-api.vn/api/p/" + provinceCode + "?depth=2";

                                                fetch(url)
                                                        .then(response => response.json())
                                                        .then(data => {
                                                            if (data.districts) {
                                                                data.districts.forEach(district => {
                                                                    const option = document.createElement("option");
                                                                    option.value = district.code;
                                                                    option.textContent = district.name;
                                                                    districtSelect.appendChild(option);
                                                                });
                                                            } else {
                                                                console.error("No districts found for the selected province.");
                                                            }
                                                        })
                                                        .catch(error => {
                                                            console.error("Error fetching districts:", error);
                                                            document.getElementById("error-message").style.display = "block";
                                                        });
                                            }
                                            document.getElementById("province").addEventListener("change", function () {
                                                const provinceName = this.options[this.selectedIndex].text;
                                                document.getElementById("provinceName").value = provinceName;
                                            });

                                            document.getElementById("district").addEventListener("change", function () {
                                                const districtName = this.options[this.selectedIndex].text;
                                                document.getElementById("districtName").value = districtName;
                                            });
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
