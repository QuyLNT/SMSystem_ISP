<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

<%@page import="admin.sample.order.AdminOrderDetailDTO"%>
<%@page import="admin.sample.order.AdminOrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="admin.sample.order.AdminOrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OrderDetail</title>
        <title>Kẻ kiểm soát thông tin</title>
        <link rel="stylesheet" href="css/orderDetail1.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
            />
        <link rel="icon" href="favicon_io/favicon.ico" type="img/x-icon" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    </head>
    <body>
        <main class="main-wrap">
            <header class="main-head">
                <div class="main-nav">
                    <nav class="navbar">
                        <div class="navbar-nav">
                            <div class="title">
                                <h3>
                                    <img src="favicon_io/favicon-32x32.png" alt="anh giay nike" />
                                    <span class="title-text">Nice</span>
                                </h3>
                            </div>
                            <ul class="nav-list">
                                <li class="nav-list-item">
                                    <a href="adminHome.jsp" class="nav-link">
                                        <i class="fa-solid fa-house"></i>
                                        <span class="link-text">Home</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="userList.jsp" class="nav-link">
                                        <i class="fa-solid fa-user"></i>
                                        <span class="link-text">User</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="categoriesList.jsp" class="nav-link">
                                        <i class="fa-solid fa-list"></i>
                                        <span class="link-text">Categories</span>
                                    </a>
                                </li>

                                <li class="nav-list-item">
                                    <a href="productList.jsp" class="nav-link">
                                        <i class="fa-solid fa-capsules"></i>
                                        <span class="link-text">Products</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="discountList.jsp" class="nav-link">
                                        <i class="fa-solid fa-percent"></i>
                                        <span class="link-text">Discount</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="orderList.jsp" class="nav-link">
                                        <i class="fa-solid fa-file-invoice"></i>
                                        <span class="link-text">Order</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="LogoutController" class="nav-link">
                                        <i class="fa-solid fa-right-from-bracket"></i>
                                        <span class="link-text">Log out</span>
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

                                AdminOrderDTO a = new AdminOrderDTO();
                                if (request.getAttribute("Order") != null) {
                                    a = (AdminOrderDTO) request.getAttribute("Order");
                                }

                                if (a != null) {

                            %>
                            <div class="table-tilte">Customer Information</div>
                            <div class="row">
                                <div class="col">
                                    <dl class="row">
                                        <dt class="col-sm-3">User Name</dt>
                                        <dd class="col-sm-9"> <%=a.getFullName()%></dd>

                                        <dt class="col-sm-3">Address</dt>
                                        <dd class="col-sm-9">
                                            <p>Street: <%=a.getStreet()%> </p>
                                            <p>District: <%=a.getDistrict()%> </p>
                                            <p>City: <%=a.getCity()%> </p>
                                        </dd>

                                        <dt class="col-sm-3">Phone</dt>
                                        <dd class="col-sm-9"> <%=a.getPhone()%></dd>

                                        <dt class="col-sm-3">Email</dt>
                                        <dd class="col-sm-9"><%=a.getEmail()%></dd>
                                </div>
                                <div class="col">
                                    <dt class="col-sm-3">Payment Method</dt>
                                    <dd class="col-sm-9"><%=a.getPayMethod()%></dd>
                                    <dt class="col-sm-3">Shipment Method</dt>
                                    <dd class="col-sm-9"><%=a.getShipMethod()%></dd>
                                    <dt class="col-sm-3">Total Price</dt>
                                    <dd class="col-sm-9"><%=a.getTotalPrice()%></dd>
                                    <dt class="col-sm-3">Date</dt>
                                    <dd class="col-sm-9"> <%=a.getCreateAt()%></dd>
                                    <dt class="col-sm-3">Status</dt>
                                    <dd class="col-sm-9"><%=a.getStatus()%></dd>  
                                </div>
                            </div>


                            </dl>
                            <%}%>                       
                        </div>
                        <div class="welcome">
                            <%
                                ArrayList<AdminOrderDetailDTO> listOrderDetail = new ArrayList<>();

                                if (request.getAttribute(
                                        "listOrderDetail") != null) {
                                    listOrderDetail = (ArrayList<AdminOrderDetailDTO>) request.getAttribute("listOrderDetail");
                                }

                                if (listOrderDetail
                                        != null) {

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
                                    <%                                        for (AdminOrderDetailDTO od : listOrderDetail) {
                                            float totalPrice = 0;
                                            totalPrice = od.getSalePrice() * od.getQuantity();

                                    %>

                                    <tr>
                                        <td><img src="<%=od.getPathImg()%>" class="img-thumbnail img-items" alt="..."></td>                                       
                                        <td><%=od.getProductName()%></td>
                                        <td><%=od.getUnitPrice()%></td>
                                         <td><%=od.getSalePrice()%></td>
                                        <td><%=od.getQuantity()%></td>
                                        <td>
                                            <%=totalPrice%>                                           
                                        </td>

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
