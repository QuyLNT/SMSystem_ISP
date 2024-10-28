<%@page import="java.util.List"%>
<%@page import="model.order.OrderDTO"%>
<%@page import="model.order.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Order List</title>
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
                                <h3>
                                    <img src="img/logoweb.png" alt="" width="100%" height="100%"/>
                                    <span class="title-text">Nice</span>
                                </h3>
                            </div>
                            <ul class="nav-list">
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadManagerHomeData" class="nav-link">
                                        <i class="fa-solid fa-house"></i>
                                        <span class="link-text">Home</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="categoriesList.jsp" class="nav-link">
                                        <i class="fa-solid fa-list"></i>
                                        <span class="link-text">Categories</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadProductList" class="nav-link">
                                        <i class="fa-solid fa-capsules"></i>
                                        <span class="link-text">Products</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadDiscountList" class="nav-link">
                                        <i class="fa-solid fa-percent"></i>
                                        <span class="link-text">Discount</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadOrderList" class="nav-link">
                                        <i class="fa-solid fa-file-invoice"></i>
                                        <span class="link-text">Order</span>
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
                                        <span class="link-text">Log out</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </header>
            <section class="showcase">
                <div class="container">
                    <div class="welcome">
                        <%
                            String ms = "";
                            String orderid = "";
                            OrderDAO d = new OrderDAO();
                            List<OrderDTO> list = null;

                            try {
                                list = d.getAllOrder();
                                if (request.getAttribute("ORDER_LIST") != null) {
                                    list = (List<OrderDTO>) request.getAttribute("ORDER_LIST");
                                }
                                if (request.getAttribute("ms") != null && request.getAttribute("orderId") != null) {
                                    ms = request.getAttribute("ms").toString();
                                    orderid = request.getAttribute("orderId").toString();
                                }
                        %>
                        <div><%= ms%> Order ID: <%= orderid%></div>
                        <div class="table-title">Order Table</div>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Order Code</th>
                                    <th>Customer</th>
                                    <th>Street</th>
                                    <th>District</th>
                                    <th>City</th>
                                    <th>Discount</th>
                                    <th>Payment Method</th>
                                    <th>Shipment Method</th>
                                    <th>Create At</th>
                                    <th>Order Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if (list != null && !list.isEmpty()) {
                                        // Loop through the orderList and display each order
                                        for (OrderDTO a : list) {
                                %>
                                <tr>
                                    <td><%= a.getOrderId()%></td>
                                    <td><%= a.getCustomer().getUserName()%></td>
                                    <td><%= a.getStreet()%></td>
                                    <td><%= a.getDistrict()%></td>
                                    <td><%= a.getCity()%></td>
                                    <td><%= a.getDiscountCode()%></td>
                                    <td><%= a.getPaymentName()%></td>
                                    <td><%= a.getShippingMethodName()%></td>
                                    <td><%= a.getCreatedAt()%></td>
                                    <td>
                                        <form action="UpdateOrderStatusController" method="POST">
                                            <input type="hidden" name="orderId" value="<%= a.getOrderId()%>">
                                            <select name="status" onchange="this.form.submit()">
                                                <option value="Accepted,waiting for Delivering" <%= a.getOrderStatus().equalsIgnoreCase("Accepted,waiting for Delivering") ? "selected" : ""%>>Accepted,waiting for Delivering</option>
                                                <option value="Delivering" <%= a.getOrderStatus().equalsIgnoreCase("Delivering") ? "selected" : ""%>>Delivering</option>
                                                <option value="Delivered" <%= a.getOrderStatus().equalsIgnoreCase("Delivered") ? "selected" : ""%>>Delivered</option>
                                                <option value="Completed" <%= a.getOrderStatus().equalsIgnoreCase("Completed") ? "selected" : ""%>>Completed</option>
                                                <option value="Not Completed" <%= a.getOrderStatus().equalsIgnoreCase("Not Completed") ? "selected" : ""%>>Not Completed</option>
                                            </select>
                                            <input type="hidden" name="action" value="UpdateStatus"/>
                                            <!--                                            <button type="submit" class="btn btn-primary" name="action" value="c">
                                                                                            <i class="fa-solid fa-pen-to-square"></i>
                                                                                        </button>    -->

                                        </form>
                                    <td>
                                        <button type="submit" class="btn btn-primary" name="action" value="View-Detail">
                                            <i class="fa-solid fa-eye"></i>
                                        </button>
                                    </td>

                                    </td>

                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr><td colspan="11">No orders available or an error occurred.</td></tr>
                                <%
                                    }
                                } catch (Exception e) {
                                %>
                            <div class="alert alert-danger">Error loading orders: <%= e.getMessage()%></div>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </main>

        <script src="js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
