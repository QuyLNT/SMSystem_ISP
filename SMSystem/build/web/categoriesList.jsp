<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

<%@page import="model.category.UserObjectDTO"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
        <title>Kẻ kiểm soát thông tin</title>
        <link rel="stylesheet" href="css/user1.css" />
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
                                    <a href="MainController?action=LoadBrandList" class="nav-link">
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
                <div class="overlay">
                    <div class="head">
                        <button class="toggler">
                            <i class="fa-solid fa-bars"></i>
                        </button>
                    </div>
                    <div class="content">
                        <div class="welcome">

                        <div class="welcome" >
                            <%
                                List<UserObjectDTO> categoriesList =(List<UserObjectDTO>) session.getAttribute("USER_OBJECT_LIST");
                                if(categoriesList != null){
                            %>
                            <div class="table-tilte">Categories Table</div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>CategoryName</th>
                                        <th>CategoryDetail</th>
                                        <th>Total products</th>
                                        
                                        <th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count=1;
                                        for (UserObjectDTO category : categoriesList) {
                                    %>
                                    <tr>
                                        <td><%= count++%></td>
                                        <td><%= category.getUserObjectName()%></td>
                                        <td><%= category.getDetail()%></td>
                                        <td><%= category.getProductCount()%></td>
                                        
                                    </tr>
                                    <%
                                            }
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
