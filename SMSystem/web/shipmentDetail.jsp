

<%@page import="model.order.OrderDTO"%>
<%@page import="model.shipment.ShipmentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.order.OrderDetailDTO"%>
<%@page import="model.product.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SM Shop</title>
        <title></title>
        <link rel="stylesheet" type="text/css" href="css/home.css"/>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
            />
        <link rel="icon" href="img/icon-logoweb.png" type="img/x-icon" />

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    </head>
    <body> 
        <main class="main-wrap">
            <header class="main-head">
                <div class="main-nav">
                    <nav class="navbar">
                        <div class="navbar-nav">
                            <div class="title">
                                <a href="MainController?action=HomePage">
                                    <h3>
                                        <img src="img/icon-logoweb.png" alt="" width="32px" height="32px"/>
                                        <span class="title-text">SMSystem</span>
                                    </h3>
                                </a>
                            </div>
                            <ul class="nav-list">
                                <li class="nav-list-item">
                                    <a href="shipperPage.jsp" class="nav-link">
                                        <i class="fa-solid fa-house"></i>
                                        <span class="link-text">Home</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadShipment" class="nav-link">
                                        <i class="fa-solid fa-truck"></i>
                                        <span class="link-text">Shipment</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="LogoutController" class="nav-link">
                                        <i class="fa-solid fa-right-from-bracket"></i>
                                        <span class="link-text">Logout</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </header>
            <section class="showcase">
                <div class="overlay">
                    <div class="head">
                        <button class="toggler">
                            <i class="fa-solid fa-bars"></i>
                        </button>
                    </div>
                    <div class="container"> 
                        <div class="welcome">
                            <%

                                OrderDTO ord = new OrderDTO();
                                if (request.getAttribute("ORDER") != null) {
                                    ord = (OrderDTO) request.getAttribute("ORDER");
                                }

                                if (ord != null) {
                            %>
                            <div class="table-tilte"> 
                                <a href="shipList.jsp" class="nav-link">
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
                                            <p><%=ord.getStreet()%> </p>
                                            <p><%=ord.getDistrict()%> </p>
                                            <p><%=ord.getCity()%> </p>
                                        </dd>

                                        <dt class="col-sm-3">Phone</dt>
                                        <dd class="col-sm-9"> <%=ord.getCustomer().getPhoneNumber()%></dd>

                                        <dt class="col-sm-3">Email</dt>
                                        <dd class="col-sm-9"><%=ord.getCustomer().getEmail()%></dd>
                                    </dl>
                                </div>
                                <div class="col">
                                    <dl class="row">
                                        <dt class="col-sm-4">Payment Method</dt>
                                        <dd class="col-sm-8"><%=ord.getPaymentMethod()%></dd>
                                        <dt class="col-sm-4">Shipment Method</dt>
                                        <dd class="col-sm-8"><%=ord.getShippingMethod()%></dd>
                                        <dt class="col-sm-4">Date</dt>
                                        <dd class="col-sm-8"> <%=ord.getCreatedAt()%></dd>
                                        <dt class="col-sm-4">Status</dt>
                                        <dd class="col-sm-8"><%=ord.getOrderStatus()%></dd>
                                        <dt class="col-sm-9">
                                            <div class="table-tilte">
                                            </div>
                                        </dt>
                                        <dt class="col-sm-4">Total Price</dt>

                                        <% if (ord.getPaymentMethod().equalsIgnoreCase("Bank Transfer")) {
                                        %>
                                        <dd class="col-sm-8" style="font-size: 60px">0$</dd>  

                                        <%
                                        } else {
                                        %>
                                        <dd class="col-sm-8" style="font-size: 25px"><%= String.format("%.2f", ord.getTotalPrice())%>$</dd>  

                                        <%
                                            }
                                        %>
                                    </dl>
                                </div>
                            </div>

                            <%
                                }
                            %>     
                        </div>

                        <%
                            ShipmentDTO ship = (ShipmentDTO) request.getAttribute("SHIP_STATUS");
                            if (ship != null) {
                        %>
                        <div class="welcome">
                            <div class="table-tilte">Shipping status</div>
                            <div class="row">
                                <div class="col">
                                    <dl class="row">
                                        <dt class="col-sm-5">Expected ship date</dt>
                                        <dd class="col-sm-5"> <%=ship.getFormattedEstimatedArrival()%></dd>
                                    </dl>
                                </div>
                                <div class="col">
                                    <dl class="row">
                                        <dt class="col-sm-2">Status</dt>
                                        <dd class="col-sm-10"> <%=ship.getShipmentStatus()%></dd>
                                    </dl>
                                </div>
                            </div>
                        </div>         

                        <%
                            }
                        %>

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

        <script src="js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
