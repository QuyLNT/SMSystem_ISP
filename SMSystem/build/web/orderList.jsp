<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

<%@page import="admin.sample.order.AdminOrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="admin.sample.order.AdminOrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <title>Kẻ kiểm soát thông tin</title>
        <link rel="stylesheet" href="css/order.css" />
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
                                        <span class="link-text">Accounts</span>
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
                                String ms = "";
                                String orderid = "";
                                AdminOrderDAO d = new AdminOrderDAO();
                                ArrayList<AdminOrderDTO> list = (ArrayList<AdminOrderDTO>) d.getOrderList();
                                if (request.getAttribute("list") != null) {
                                    list = (ArrayList<AdminOrderDTO>) request.getAttribute("list");
                                }

                                if (list != null) {
                                    if(request.getAttribute("ms-u")!=null && request.getAttribute("orderId")!=null){
                                       ms = request.getAttribute("ms-u").toString();
                                       orderid = request.getAttribute("orderId").toString();
                                    }
                                  
                            %>
                            <div><%=ms%> Order ID: <%=orderid%><div>
                            <div class="table-tilte">Order Table</div>                         
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Order Code</th>
                                        <th>Customer</th>
                                        <th>Date Time</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%                                            for (AdminOrderDTO a : list) {

                                    %>

                                    <tr>
                                <form action="MainOrderController" method="POST">

                                    <td><%=a.getOrderID()%></td>                                       
                                    <td><%=a.getFullName()%></td>
                                    <td><%=a.getCreateAt()%></td>
                                    <td>
                                        <select name="status" class="form-select" aria-label="Default select example">
                                            <option selected><%=a.getStatus()%></option>
                                            <option value="Accepted,waiting for Delivering">Accepted,waiting for Delivering</option>
                                            <option value="Delivering">Delivering</option>
                                            <option value="Delivered">Delivered</option>
                                            <option value="Completed">Completed</option>
                                            <option value="Not Completed">Not Completed</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="hidden" name="code2"  value="<%=a.getOrderID()%>" />
                                          <button type="submit" class="btn btn-primary" name="action" value="Update">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </button>  
                                        <button type="submit" class="btn btn-primary" name="action" value="View">
                                            <i class="fa-solid fa-eye"></i>
                                        </button>         
                                      
                                    </td>
                                </form>
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
