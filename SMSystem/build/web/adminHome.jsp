<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

<%@page import="model.product.ProductDAO"%>
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
                                    <span class="title-text">SMS</span>
                                </h3>
                            </div>
                            <ul class="nav-list">
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadAdminHome" class="nav-link">
                                        <i class="fa-solid fa-house"></i>
                                        <span class="link-text">Home</span>
                                    </a>
                                </li>
                                <li class="nav-list-item">
                                    <a href="MainController?action=LoadUserList" class="nav-link">
                                        <i class="fa-solid fa-user"></i>
                                        <span class="link-text">Accounts</span>
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
                        <div class="row">
                            <!-- Left Table for Accounts -->
                            <div class="col-md-4">
                                <div class="card mb-" style="max-width: 100%;">
                                    <div class="row g-0">
                                        <div class="col-md-5">
                                            <img src="https://i.pinimg.com/236x/7b/12/d2/7b12d287221c0adf5b4efcdf326c178f.jpg" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-md-12">
                                            <%
                                                ProductDAO productDao = new ProductDAO();
                                            %>
                                            <div class="card-body">
                                                <h5 class="card-title">Accounts</h5>
                                                <p class="card-text"><%=999%></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
            
                            <!-- Right Table for Other Metrics -->
                            <div class="col-md-4">
                                <div class="row">
                                    <div class="col">
                                        <div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/736x/2e/79/00/2e790030135a071a105f649de7367a10.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="card-body">
                                                        <h5 class="card-title">Account Shipper</h5>
                                                        <p class="card-text"><%=999.9%>$</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/736x/2e/79/00/2e790030135a071a105f649de7367a10.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="card-body">
                                                        <h5 class="card-title">Account Manager</h5>
                                                        <p class="card-text"><%=999.9%>$</p>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="row">
                                    <div class="col">
                                        <div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/736x/2e/79/00/2e790030135a071a105f649de7367a10.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="card-body">
                                                        <h5 class="card-title">Account User</h5>
                                                        <p class="card-text"><%=999.9%>$</p>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                        </div>

                                        <div class="card mb-4" style="max-width: 540px;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="https://i.pinimg.com/736x/2e/79/00/2e790030135a071a105f649de7367a10.jpg" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="card-body">
                                                        <h5 class="card-title">Account Admin</h5>
                                                        <p class="card-text"><%=999.9%>$</p>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                        </div>

                                    </div>
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
