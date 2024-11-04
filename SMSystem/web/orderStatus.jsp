


<%@page import="model.cart.CartItems"%>
<%@page import="model.cart.CartDTO"%>
<%@page import="model.user.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.order.OrderDetailDTO"%>
<%@page import="model.order.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.order.OrderDAO"%>

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
                            <i class="fa fa-user">GUEST</i>

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
                        <section class="step-wizard">
                            <ul class="step-wizard-list">
                                <li class="step-wizard-item" data-status="1">
                                    <a href="#">
                                        <span class="progress-count">
                                            <i class="fa fa-hourglass-half"></i>
                                            <span class="order-count">15</span>
                                        </span>
                                        <span class="progress-label">Waiting for confirmation</span>
                                    </a>
                                </li>
                                <li class="step-wizard-item" data-status="2">
                                    <a href="#">
                                        <span class="progress-count">
                                            <i class="fa fa-hourglass-half"></i>
                                            <span class="order-count">10</span>
                                        </span>
                                        <span class="progress-label">Waiting for pickup</span>
                                    </a>
                                </li>
                                <li class="step-wizard-item" data-status="3">
                                    <a href="#">
                                        <span class="progress-count">
                                            <i class="fa fa-truck"></i>
                                            <span class="order-count">8</span>
                                        </span>
                                        <span class="progress-label">Waiting for delivery</span>
                                    </a>
                                </li>
                                <li class="step-wizard-item" data-status="4">
                                    <a href="#">
                                        <span class="progress-count">
                                            <i class="fa fa-clipboard-check"></i>
                                            <span class="order-count">20</span>
                                        </span>
                                        <span class="progress-label">Delivered successfully</span>
                                    </a>
                                </li>
                            </ul>   
                        </section>
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
                                                            <a href="MainController?cartItemId=<%= ele.getCartItemId()%>&action=doDelete&url=orderStatus.jsp" onclick="doDelete('<%= ele.getProduct().getName()%>', event)">
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
                                                <a href="MainController?action=ViewCart&url=orderStatus.jsp" class="primary-btn view-card">VIEW CART</a>
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
                    <nav class="nav-menu mobile-menu">
                        <ul>
                            <li><a href="MainController?action=HomePage">Home</a></li>
                            <li><a href="MainController?action=ShopPage">Shop</a></li>
                            <li><a href="contact.jsp">Contact</a></li>
                            <li><a href="MainController?action=ViewCart&url=orderStatus.jsp">Shopping Cart</a></li>
                            <li><a href="warrantyPage.jsp">Warranty</a></li>
                        </ul>
                    </nav>
                    <div id="mobile-menu-wrap"></div>
                </div>
            </div>
    </header>
    <!-- Header Section End -->
    <!-- Header Section End -->
    <!--Breadcrumb Section Begin-->
    <div class="breadcrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="homePage.jsp"><i class="fa fa-home"></i> Home</a>
                        <span>My Order</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Breadcrumb Section End-->
    <body>
        <main class="main-wrap">
            <section class="showcase">
                <div class="overlay">
                    <div class="container"> 
                             <section class="step-wizard">
                            <ul class="step-wizard-list">
                                <li class="step-wizard-item" data-status="1">
                                    <a href="#">
                                        <span class="progress-count">
                                            <i class="fa fa-hourglass-half"></i>
                                            <span class="order-count">15</span>
                                        </span>
                                        <span class="progress-label">Waiting for confirmation</span>
                                    </a>
                                </li>
                                <li class="step-wizard-item" data-status="2">
                                    <a href="#">
                                        <span class="progress-count">
                                            <i class="fa fa-box"></i>
                                            <span class="order-count">10</span>
                                        </span>
                                        <span class="progress-label">Waiting for pickup</span>
                                    </a>
                                </li>
                                <li class="step-wizard-item" data-status="3">
                                    <a href="#">
                                        <span class="progress-count">
                                            <i class="fa fa-truck"></i>
                                            <span class="order-count">8</span>
                                        </span>
                                        <span class="progress-label">Waiting for delivery</span>
                                    </a>
                                </li>
                                <li class="step-wizard-item" data-status="4">
                                    <a href="#">
                                        <span class="progress-count">
                                            <i class="fa fa-clipboard-check"></i>
                                            <span class="order-count">20</span>
                                        </span>
                                        <span class="progress-label">Delivered successfully</span>
                                    </a>
                                </li>
                            </ul>   
                        </section>
                        <div class="welcome">
                            <%

                                OrderDTO ord = new OrderDTO();
                                if (request.getAttribute("ORDER") != null) {
                                    ord = (OrderDTO) request.getAttribute("ORDER");
                                }

                                if (ord != null) {
                            %>
                            <div class="table-tilte"> 
                                <a href="myOrder.jsp" class="nav-link">
                                    <i class="fa-solid fa-arrow-left"></i>
                                </a>
                                Customer Information
                            </div>
                            <div class="row">
                                <div class="col">
                                    <dl class="row">
                                        <dt class="col-sm-3">User Name</dt>
                                        <dd class="col-sm-9"> <%=ord.getCustomer().getFullName()%></dd>

                                        <dt class="col-sm-3">Address</dt>
                                        <dd class="col-sm-9">
                                            <p>Street: <%=ord.getStreet()%> </p>
                                            <p>District: <%=ord.getDistrict()%> </p>
                                            <p>City: <%=ord.getCity()%> </p>
                                        </dd>

                                        <dt class="col-sm-3">Phone</dt>
                                        <dd class="col-sm-9"> <%=ord.getCustomer().getPhoneNumber()%></dd>

                                        <dt class="col-sm-3">Email</dt>
                                        <dd class="col-sm-9"><%=ord.getCustomer().getEmail()%></dd>
                                </div>
                                <div class="col">
                                    <dt class="col-sm-3">Payment Method</dt>
                                    <dd class="col-sm-9"><%=ord.getPaymentMethod()%></dd>
                                    <dt class="col-sm-3">Shipment Method</dt>
                                    <dd class="col-sm-9"><%=ord.getShippingMethod()%></dd>
                                    <dt class="col-sm-3">Date</dt>
                                    <dd class="col-sm-9"> <%=ord.getCreatedAt()%></dd>
                                    <dt class="col-sm-3">Status</dt>
                                    <dd class="col-sm-9"><%=ord.getOrderStatus()%></dd>
                                    <dt class="col-sm-3">Total Price</dt>
                                    <dd class="col-sm-9"><%= String.format("%.2f", ord.getTotalPrice())%>$</dd>  
                                </div>
                            </div>

                            <%
                                }
                            %>     
                        </div>
                        <div class="welcome">
                            <%
                                List<OrderDetailDTO> listOrderDetail = new ArrayList<>();
                                if (request.getAttribute("ORDER_DETAILS") != null) {
                                    listOrderDetail = (List<OrderDetailDTO>) request.getAttribute("ORDER_DETAILS");
                                }

                                if (listOrderDetail != null && !listOrderDetail.isEmpty()) {
                            %>
                            <div class="table-tilte">Order Detail Table</div>

                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Picture</th>
                                        <th>Product's Name</th>
                                        <th>Price</th>
                                        <th>Sale Price</th>
                                        <th>Quantity</th>
                                        <th>Total Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%  for (OrderDetailDTO od : listOrderDetail) {
                                            float totalPrice = 0;
                                            totalPrice = od.getProduct().getPrice() * (1 - od.getProduct().getSale()) * od.getQuantity();
                                    %>

                                    <tr>
                                        <td class="cart-pic first-row"><img src="<%=od.getProduct().getAvatarPath()%>" style="height: 100px; width: 100px" alt=""></td>                                       
                                        <td><%=od.getProduct().getName()%></td>
                                        <td><%=od.getProduct().getPrice()%></td>
                                        <td><%= String.format("%.2f", od.getProduct().getPrice() * (1 - od.getProduct().getSale()))%>$</td>
                                        <td><%=od.getQuantity()%></td>
                                        <td><%=String.format("%.2f", totalPrice)%>$</td>    
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table> 


                            <%}%>

                        </div>

                    </div>

                </div>

            </section>


        </main>
    </body>
        <script src="js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
