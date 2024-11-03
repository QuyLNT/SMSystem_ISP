<%@page import="model.shipment.ShipmentDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Shipment</title>
        <link rel="stylesheet" href="css/user1.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
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
                                    <img src="img/logoweb.png" alt="Logo" width="32" height="32" />
                                    <span class="title-text">SM System</span>
                                </h3>
                            </div>
                            <ul class="nav-list">
                                <li class="nav-list-item"><a href="shipperPage.jsp" class="nav-link"><i class="fa-solid fa-house"></i> Home</a></li>
                                <li class="nav-list-item"><a href="shipList.jsp" class="nav-link"><i class="fa-solid fa-user"></i> Shipment</a></li>
                                <li class="nav-list-item"><a href="LogoutController" class="nav-link"><i class="fa-solid fa-right-from-bracket"></i> Log out</a></li>
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

                    <div class="content">
                        <%
                            String ms = (String) session.getAttribute("ms");
                            if (ms != null) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <%= ms %>
                        </div>
                        <%
                                session.removeAttribute("ms");
                            }
                        %>
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
                                            <button type="submit" class="btn btn-primary btn-sm">Update</button>
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
                        </table>
                    </div>
                </div>
            </section>
        </main>

        <script>
            // JavaScript để thiết lập giá trị min cho tất cả các trường ngày là ngày hiện tại
            document.addEventListener("DOMContentLoaded", function () {
                const today = new Date().toISOString().split("T")[0]; // Lấy ngày hiện tại ở định dạng yyyy-mm-dd
                document.querySelectorAll(".date-picker").forEach(function (dateField) {
                    dateField.min = today; // Đặt thuộc tính min cho các trường có class "date-picker"
                });
            });
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
