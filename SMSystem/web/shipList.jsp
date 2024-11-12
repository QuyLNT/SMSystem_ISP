<%@page import="model.shipment.ShipmentDTO"%>
<%@page import="java.util.List"%>
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
                                String searchUserName = request.getParameter("searchUserName");
                                if (searchUserName == null) {
                                    searchUserName = "";
                                }
                            %>
                            <div class="welcome">

                                <div class="search-form">
                                    <form action="MainController" method="POST">
                                        Search Shipment <input type="text" name="searchOrder" placeholder="Enter order ID" value="<%= searchUserName%>"/>
                                        <button type="submit" name="action" value="SearchUserName" class="btn btn-primary">Search</button>
                                    </form>
                                </div>
                            </div>
                            <%
                                String dateFilter = request.getParameter("dateFilter");
                                if (dateFilter == null) {
                                    dateFilter = "";
                                }
                            %>
                            <div class="welcome">
                                <form action="MainController" class="d-flex row justify-content-around">
                                    <div class="select-option-province col-lg-2">Filter Shipment</div>
                                    <div class="select-option-province col-lg-6">
                                        <select name="dateFilter">
                                            <option value="" <%= dateFilter.equals("") == true ? "selected" : ""%>>View All</option>                            
                                            <option value="Today"<%= dateFilter.equals("Today") == true ? "selected" : ""%>>Today</option>
                                            <option value="This Week"<%= dateFilter.equals("This Week") == true ? "selected" : ""%>>This Week</option>
                                            <option value="This Month"<%= dateFilter.equals("This Month") == true ? "selected" : ""%>>This Month</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-4">
                                        <button type="submit" name="action" value="FilterShip"class="btn btn-primary col-lg-3 ">Filter</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="welcome">

                            <% if (request.getAttribute("ms") != null) {%>
                            <div class="alert alert-success"><%= request.getAttribute("ms")%></div>
                            <% }%>
                            <div class="table-title">Shipment Table</div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Order ID</th>
                                        <th>Shipping Method</th>
                                        <th>Shipped Date</th>
                                        <th>Estimated Arrival</th>
                                        <th>Shipment Status</th>
                                        <th>Update</th>
                                        <th>View Detail</th>
                                        <th>Mark Completed</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<ShipmentDTO> shipmentList = (List<ShipmentDTO>) session.getAttribute("shipments");
                                        if (shipmentList != null && !shipmentList.isEmpty()) {
                                            int no = 1;
                                            for (ShipmentDTO shipment : shipmentList) {
                                    %>
                                    <tr>
                                        <td><%= no++%></td>
                                        <td><%= shipment.getOrderId()%></td>
                                        <td><%= shipment.getMethodName()%></td>
                                        <td><%= shipment.getShippedDate()%></td>
                                        <td>
                                            <input type="date" name="newEstimatedArrival" form="updateForm<%= shipment.getShipmentId()%>" 
                                                   value="<%= shipment.getEstimatedArrival()%>" 
                                                   min="" class="date-picker"/>
                                        </td>
                                        <td>
                                            <input type="text" name="newStatus" form="updateForm<%= shipment.getShipmentId()%>" placeholder="Enter new status" value="<%= shipment.getShipmentStatus()%>"/>
                                        </td>
                                        <td>
                                            <form id="updateForm<%= shipment.getShipmentId()%>" action="UpdateShipmentController" method="POST">
                                                <input type="hidden" name="shipmentId" value="<%= shipment.getShipmentId()%>"/>
                                                <button type="submit" class="btn btn-primary">Update</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="orderId" value="<%= shipment.getOrderId()%>" />
                                                <button type="submit" class="btn btn-primary" name="action" value="View-Detail">
                                                    <i class="fa-solid fa-eye"></i>
                                                </button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="UpdateOrderStatusController" method="POST" onsubmit="doUpdate(event)">
                                                <input type="hidden" name="orderId" value="<%= shipment.getOrderId()%>">
                                                <input type="hidden" name="status" value="Completed">
                                                <button type="submit" class="btn btn-primary" name="action" value="UpdateStatus">
                                                    <i class="fa-solid fa-square-check"></i>                                                
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    } else {
                                    %>
                                    <tr>
                                        <td colspan="7" class="text-center">No shipments available</td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                                <script>
                                    document.addEventListener("DOMContentLoaded", function () {
                                        const today = new Date().toISOString().split("T")[0]; // Lấy ngày hiện tại ở định dạng yyyy-mm-dd
                                        document.querySelectorAll(".date-picker").forEach(function (dateField) {
                                            dateField.min = today; // Đặt thuộc tính min cho các trường có class "date-picker"
                                        });
                                    });
                                </script>
                                <script>
                                    function doUpdate(event) {
                                        if (confirm("Ensure this order is competed?")) {
                                        } else {
                                            event.preventDefault();
                                        }
                                    }
                                </script>
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
