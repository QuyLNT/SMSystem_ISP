<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

<%@page import="admin.sample.home.AdminHomeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SM Shop</title>
        
        <link rel="stylesheet" type="text/css" href="css/home.css"/>
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
                                    <img src="img/logoweb.png" alt="" width="100%" height="100%"/>
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
                <div class="overlay">
                    <div class="head">
                        <button class="toggler">
                            <i class="fa-solid fa-bars"></i>
                        </button>
                    </div>
                    <div class=" container">



                        <div class="welcome">
                            <div class="container text-center">
                                <div class="row">
                                    <div class="col">
                                        <div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/236x/7b/12/d2/7b12d287221c0adf5b4efcdf326c178f.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <%
                                                        AdminHomeDAO d = new AdminHomeDAO();
                                                        int a = d.getTheNumberOfAccounts();

                                                    %>
                                                    <div class="card-body">
                                                        <h5 class="card-title">Accounts</h5>
                                                        <p class="card-text"><%=a%></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div></div>
                                    <div class="col"><div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/736x/2e/79/00/2e790030135a071a105f649de7367a10.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <%
                                                        float r = d.getRevenue();
                                                    %>
                                                    <div class="card-body">
                                                        <h5 class="card-title">Revenue</h5>
                                                        <p class="card-text"><%=r%>$</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div></div>
                                    <div class="col"><div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/736x/81/25/15/8125155827666176f762074043410998.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <%
                                                        int i = d.getTotalQuantityOfProducts();

                                                    %>
                                                    <div class="card-body">
                                                        <h5 class="card-title">Products</h5>
                                                        <p class="card-text"><%=i%></p>

                                                    </div>
                                                </div>
                                            </div>
                                        </div></div>                           
                                </div>
                            </div>
                        </div>
                        <div class="welcome">
                            <div class="container text-center">
                                <div class="row">
                                    <div class="col">
                                        <div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/564x/e9/fe/65/e9fe6586390ead07047dcc4dea84769a.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <%

                                                        int o = d.getTheNumberOfOrders();
                                                        int oc = d.getTheNumberOfOrders_Completed();
                                                        int onc = o - oc;

                                                    %>
                                                    <div class="card-body">
                                                        <h5 class="card-title">Order</h5>
                                                        <p class="card-text"><%=o%></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div></div>
                                    <div class="col"><div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/236x/76/61/87/7661877310594e9096e62bceba362a25.jpg"  class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <%

                                                    %>
                                                    <div class="card-body">
                                                        <h5 class="card-title">Order completed</h5>
                                                        <p class="card-text"><%=oc%></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div></div>
                                    <div class="col"><div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/236x/c2/1b/42/c21b42b7622d89af053450385d5e1d72.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <%


                                                    %>
                                                    <div class="card-body">
                                                        <h5 class="card-title">Order not completed</h5>
                                                        <p class="card-text"><%=onc%></p>

                                                    </div>
                                                </div>
                                            </div>
                                        </div></div>                           
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>


        </main>

        <script src="js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
