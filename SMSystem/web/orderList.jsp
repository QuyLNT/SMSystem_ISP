<%@page import="java.util.Map"%>
<%@page import="model.user.UserDAO"%>
<%@page import="model.user.UserDTO"%>
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
        <link rel="icon" href="img/icon-logoweb.png" type="img/x-icon" />
        <link rel="stylesheet" href="css/style1.css" type="text/css">
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
                <div class="overlay">
                    <div class="head">
                        <button class="toggler">
                            <i class="fa-solid fa-bars"></i>
                        </button>
                    </div>
                    <%
                        String dateFilter = request.getParameter("dateFilter");
                        String statusFilter = request.getParameter("statusFilter");
                        if (dateFilter == null && statusFilter == null) {
                            dateFilter = "";
                            statusFilter = "";
                        }
                    %>
                    <div class="container">
                        <div class="welcome">
                            <form action="MainController" class="d-flex row justify-content-around">
                                <div class="select-option-province col-lg-6">
                                    <select name="dateFilter">
                                        <option value="" <%= dateFilter.equals("") == true ? "selected" : ""%>>View All</option>                            
                                        <option value="Today"<%= dateFilter.equals("Today") == true ? "selected" : ""%>>Today</option>
                                        <option value="This Week"<%= dateFilter.equals("This Week") == true ? "selected" : ""%>>This Week</option>
                                        <option value="This Month"<%= dateFilter.equals("This Month") == true ? "selected" : ""%>>This Month</option>
                                    </select>
                                </div>
                                <div class="select-option-province col-lg-6 mb-3">
                                    <select name="statusFilter">
                                        <option value="" <%= statusFilter.equals("") == true ? "selected" : ""%>>All Statuses</option>
                                        <option value="Waiting For Accept"<%= statusFilter.equals("Waiting For Accept") == true ? "selected" : ""%>>Waiting For Accept</option>
                                        <option value="Waiting For Pickup"<%= statusFilter.equals("Waiting For Pickup") == true ? "selected" : ""%>>Waiting For Pickup</option>
                                        <option value="Delivering" <%= statusFilter.equals("Delivering") == true ? "selected" : ""%>>Delivering</option>
                                        <option value="Completed" <%= statusFilter.equals("Completed") == true ? "selected" : ""%>>Completed</option>
                                        <option value="Not Completed" <%= statusFilter.equals("Not Completed") == true ? "selected" : ""%>>Not Completed</option>
                                    </select>
                                </div>
                                <button type="submit" name="action" value="FilterOrder"class="btn btn-primary col-lg-3 ">Filter</button>
                            </form>
                        </div>

                        <div class="welcome">
                            <%
                                String message = (String) request.getAttribute("message");
                                if (message != null) {
                            %>
                            <p style="color: black"><%= message%></p>
                            <%
                                }
                            %>
                            <%
                                String ms = "";
                                String orderid = "";
                                OrderDAO d = new OrderDAO();
                                List<OrderDTO> list = null;
                                List<UserDTO> shippers = null;
                                Map<Integer, Integer> shipperMap = null;

                                try {
                                    list = d.getAllOrder();
                                    if (request.getAttribute("ORDER_LIST") != null) {
                                        list = (List<OrderDTO>) request.getAttribute("ORDER_LIST");
                                    }
                                    UserDAO userDAO = new UserDAO();
                                    shippers = userDAO.getAllShippers();
                                    shipperMap = d.getShipperMap();

                                    if (request.getAttribute("ms") != null && request.getAttribute("orderId") != null) {
                                        ms = request.getAttribute("ms").toString();
                                        orderid = request.getAttribute("orderId").toString();
                            %>                        
                            <div style="color: red"><%= ms%> Order ID: <%= orderid%></div>
                            <%
                                }
                            %>
                            <div class="table-tilte">Order Table</div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Order Code</th>
                                        <th>Customer</th>
                                        <th>Discount</th>
                                        <th>Payment Method</th>
                                        <th>Shipment Method</th>
                                        <th>Create At</th>
                                        <th>Order Status</th>
                                        <th>Assign Shipper</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        if (list != null && !list.isEmpty()) {
                                            // Loop through the orderList and display each order
                                            for (OrderDTO a : list) {
                                                int selectedShipperId = shipperMap != null && shipperMap.containsKey(a.getOrderId())
                                                        ? shipperMap.get(a.getOrderId())
                                                        : -1;
                                    %>
                                    <tr>
                                        <td><%= a.getOrderId()%></td>
                                        <td><%= a.getCustomer().getUserName()%></td>
                                        <td><%= a.getDiscountCode()%></td>
                                        <td><%= a.getPaymentMethod()%></td>
                                        <td><%= a.getShippingMethod()%></td>
                                        <td><%= a.getCreatedAt()%></td>
                                        <td>
                                            <form action="UpdateOrderStatusController" method="POST" >
                                                <input type="hidden" name="orderId" value="<%= a.getOrderId()%>">
                                                <div class="select-option-province">
                                                    <select name="status" onchange="this.form.submit()" <%= (a.getOrderStatus().equalsIgnoreCase("Completed") || a.getOrderStatus().equalsIgnoreCase("Not Completed")) ? "disabled" : "" %>>
                                                        <option value="Waiting For Accept" <%= a.getOrderStatus().equalsIgnoreCase("Waiting For Accept") ? "selected" : ""%>>Waiting For Accept</option>
                                                        <option value="Waiting For Pickup" <%= a.getOrderStatus().equalsIgnoreCase("Waiting For Pickup") ? "selected" : ""%>>Waiting For Pickup</option>
                                                        <option value="Delivering" <%= a.getOrderStatus().equalsIgnoreCase("Delivering") ? "selected" : ""%>>Delivering</option>
                                                        <option value="Completed" <%= a.getOrderStatus().equalsIgnoreCase("Completed") ? "selected" : ""%>>Completed</option>
                                                        <option value="Not Completed" <%= a.getOrderStatus().equalsIgnoreCase("Not Completed") ? "selected" : ""%>>Not Complete</option>
                                                    </select>
                                                </div>
                                                <input type="hidden" name="action" value="UpdateStatus"/>
                                            </form>


                                        <td>
                                            <form action="AssignShipperController" method="POST">
                                                <input type="hidden" name="orderId" value="<%= a.getOrderId()%>">
                                                <input type="hidden" name="ship" value="<%= a.getShippingMethod()%>">
                                                <div class="select-option-province">
                                                    <select name="shipperId" onchange="this.form.submit()" 
                                                            <%= ("Delivering".equalsIgnoreCase(a.getOrderStatus())
                                                                    || "Completed".equalsIgnoreCase(a.getOrderStatus())
                                                                    || "Not Completed".equalsIgnoreCase(a.getOrderStatus())) ? "disabled" : ""%>>
                                                        <option value="">Select Shipper</option>
                                                        <% for (UserDTO shipper : shippers) {
                                                                boolean isSelected = (shipper.getUserId() == selectedShipperId);
                                                        %>
                                                        <option value="<%= shipper.getUserId()%>" <%= isSelected ? "selected" : ""%>>
                                                            <%= shipper.getFullName()%>
                                                        </option>
                                                        <% }%>
                                                    </select>
                                                </div>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="orderId" value="<%= a.getOrderId()%>" />
                                                <button type="submit" class="btn btn-primary" name="action" value="View-Detail">
                                                    <i class="fa-solid fa-eye"></i>
                                                </button>
                                            </form>
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
                </div>
            </section>
        </main>

        <script src="js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
