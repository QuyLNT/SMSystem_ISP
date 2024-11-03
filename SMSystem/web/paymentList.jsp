<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.payment.OnlinePaymentDTO"%>
<%@page import="model.payment.OnlinePaymentDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.order.OrderDTO"%>
<%@page import="model.order.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Payment List</title>
        <link rel="stylesheet" href="css/order.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
            />
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
                                    <a href="MainController?action=LoadManagerHomeData" class="nav-link">
                                        <i class="fa-solid fa-house"></i>
                                        <span class="link-text">Home</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadProductList" class="nav-link">
                                        <i class="fa-solid fa-capsules"></i>
                                        <span class="link-text">Products</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadOrderList" class="nav-link">
                                        <i class="fa-solid fa-file-invoice"></i>
                                        <span class="link-text">Order</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadPaymentList" class="nav-link">
                                        <i class="fa-solid fa-money-bill-wave"></i>                                        
                                        <span class="link-text">Payment</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadDiscountList" class="nav-link">
                                        <i class="fa-solid fa-percent"></i>
                                        <span class="link-text">Discount</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="categoriesList.jsp" class="nav-link">
                                        <i class="fa-solid fa-list"></i>
                                        <span class="link-text">Categories</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadBrandList" class="nav-link">
                                        <i class="fa-solid fa-tag"></i>
                                        <span class="link-text">Brand</span>
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
                <div class="head">
                    <button class="toggler">
                        <i class="fa-solid fa-bars"></i>
                    </button>
                </div>
                <div class="container">
                    <div class="welcome">
                        <form action="MainController" method="POST">
                            <input type="hidden" name="action" value="LoadPaymentList"/>
                            <button type="submit" class="btn btn-primary">
                                View All
                            </button>
                        </form>

                        <%
                            String search = request.getParameter("search");
                            if (search == null) {
                                search = "";
                            }
                        %>
                        <div class="search-form">
                            <form action="MainController" method="POST">
                                Search Payment: <input type="text" name="search" placeholder="Enter OrderID" value="<%= search%>"/>
                                <button type="submit" name="action" value="SearchPayment" class="btn btn-primary">
                                    Search
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="welcome">
                        <div class="table-title">Payment Table</div>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Transaction ID</th>
                                    <th>Order ID</th>
                                    <th>Amount</th>
                                    <th>Description</th>
                                    <th>Create At</th>
                                    <th>Update At</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <%
                                List<OnlinePaymentDTO> payList = (List<OnlinePaymentDTO>) session.getAttribute("PAY_LIST");
                                if (payList != null) {
                                    for (OnlinePaymentDTO pay : payList) {


                            %>
                            <tbody>
                                <tr>
                                    <td><%= pay.getPaymentId()%></td>
                                    <td><%= pay.getOrderId()%></td>
                                    <td><%= pay.getAmount()%></td>
                                    <td><%= pay.getDescription()%></td>
                                    <%
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    %>
                                    <td><%= pay.getCreateAt().format(formatter)%></td>
                                    <td><%= pay.getUpdateAt().format(formatter)%></td>
                                    <td><%= pay.getStatus()%></td>
                                    <td>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="transId" value="<%= pay.getPaymentId()%>"/>
                                            <button type="submit" name="action" value="GetStatus" class="btn btn-primary">
                                                <i class="fa-solid fa-magnifying-glass"></i>                                            
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                            <%
                                }
                            } else {
                            %>
                            ${requestScope.err}
                            <%
                                }
                            %>


                        </table>
                    </div>
                </div>
            </section>
        </main>

        <script src="js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
