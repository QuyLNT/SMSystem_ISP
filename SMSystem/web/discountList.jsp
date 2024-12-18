<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.discount.DiscountDAO"%>
<%@page import="model.discount.DiscountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discount</title>
        <title></title>
        <link rel="stylesheet" type="text/css" href="css/discount1.css" />
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
                    <div class="container">
                        <div class="welcome" >

                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal">
                                <i class="fa-solid fa-plus"></i> Add new Discount Code
                            </button>
                            <%
                                List<DiscountDTO> discountList = (List<DiscountDTO>) session.getAttribute("DISCOUNT_LIST");
                                if (discountList == null) {
                                    discountList = new ArrayList<>();
                                }%>
                            <% if (request.getAttribute("ms") != null) {%>
                            <div class="alert alert-success"><%= request.getAttribute("ms")%></div>
                            <% }%>
                            <% if (request.getAttribute("err") != null) {%>
                            <div class="alert alert-danger"><%= request.getAttribute("err")%></div>
                            <% }%>
                            <!-- Modal Add -->

                            <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="addModalLabel">Create a new discount </h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="MainController" method="POST">
                                            <div class="modal-body">

                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Code</span>
                                                    <input name="code" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Detail</span>
                                                    <input name="detail" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Amount</span>
                                                    <input name="amount" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Start Day</span>
                                                    <input name="startDay" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">End Day</span>
                                                    <input name="endDay" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Limit</span>
                                                    <input name="limit" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <input type="submit" name="action" value="Add Discount" class="btn btn-primary"/>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="welcome">
                            <%

                                if (discountList != null) {

                            %>
                            <div class="table-tilte">Discount Table</div>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Discount Code</th>
                                            <th>Detail</th>
                                            <th>Amount</th>
                                            <th>Date Start</th>
                                            <th>Date End</th>
                                            <th>Limit</th>
                                            <th>Used</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%                                            int count = 1;
                                            for (DiscountDTO discount : discountList) {
                                        %>

                                        <tr>
                                            <td><%=count++%></td>
                                            <td><%=discount.getDiscountCode()%></td>
                                            <td><%=discount.getDetail()%></td>                                       
                                            <td>
                                                <%= discount.getDiscountAmount()%>$
                                            </td>
                                            <td><%=discount.getStartDay()%></td>
                                            <td><%=discount.getEndDay()%></td>
                                            <td><%=discount.getUsageLimit()%></td>
                                            <td><%=discount.getUsed()%></td>                                                                                
                                            <td>
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="discountId" value="<%= discount.getDiscountId()%>"/>
                                                    <input type="hidden" name="action" value="toggleDiscountStatus"/>                                                   
                                                         <select name="status" onchange="this.form.submit()">
                                                            <option value="Active" <%= discount.getStatus().equalsIgnoreCase("Active") ? "selected" : ""%>>Active</option>
                                                            <option value="Expired" <%= discount.getStatus().equalsIgnoreCase("Expired") ? "selected" : ""%>>Expired</option>
                                                            <option value="Limit Use" <%= discount.getStatus().equalsIgnoreCase("Limit Use") ? "selected" : ""%>>Limit Use</option>
                                                        </select>
                                                </form>
                                            </td>
<!--                                            <td>
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="discountId"  value="<%=discount.getDiscountId()%>" />

                                                    <button type="submit" class="btn btn-primary" name="action" value="Remove">
                                                        <i class="fa-solid fa-delete-left"></i>     
                                                    </button>
                                                </form>
                                            </td>-->
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
