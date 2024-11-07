

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="model.category.BrandDTO"%>
<%@page import="model.category.UserObjectDTO"%>
<%@page import="java.util.List"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brand</title>
        <title></title>
        <link rel="stylesheet" href="css/user1.css" />
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
                    <div class="content">
                        <div class="welcome">

                            <form action="MainController" method="POST">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal">
                                    <i class="fa-solid fa-plus"></i> Add new Brand
                                </button>
                            </form>
                            <%
                                String ms = "";
                                String err = "";
                                String ms_create = "";
                                String err_create = "";
                                if (request.getAttribute("ms") != null) {
                                    ms = (String) request.getAttribute("ms");
                                }
                                if (request.getAttribute("err") != null) {
                                    err = (String) request.getAttribute("err");
                                }
                                if (request.getAttribute("ms_create") != null) {
                                    ms = (String) request.getAttribute("ms_create");
                                }
                                if (request.getAttribute("err_create") != null) {
                                    err = (String) request.getAttribute("err_create");
                                }
                                if (err != "") {

                            %>
                            <div class="alert alert-danger">
                                <%=err%>
                            </div>    
                            <%}%>
                            <%
                                if (ms != "") {
                            %>
                            <div class="alert alert-success">
                                <%=ms%>
                            </div> 
                            <%
                                }
                            %>
                            <%
                                if (ms_create != "") {
                            %>

                            <div class="alert alert-success">
                                <%=ms_create%>
                            </div> 
                            <%
                                }
                            %>
                            <%
                                if (err_create != "") {
                            %>
                            <div class="alert alert-danger">
                                <%=err_create%>
                            </div> 
                            <%
                                }
                            %>
                            <%
                                String searchBrandName = request.getParameter("searchBrandName");
                                if (searchBrandName == null) {
                                    searchBrandName = "";
                                }
                            %>

                            <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">  
                                            <h1 class="modal-title fs-5" id="addModalLabel">Create new brand </h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-header">
                                            
                                            
                                        </div>

                                        <form action="MainController" method="POST">
                                            <div class="modal-body">
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Name</span>
                                                    <input name="Name" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <input type="submit" name="action" value="Create Brand" class="btn btn-primary"/>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                            <div class="search-form">
                                <form action="MainController" method="POST">
                                    Search Brand: <input type="text" name="searchBrandName" placeholder="Enter brand name" value="<%= searchBrandName%>"/>
                                    <button type="submit" name="action" value="SearchBrandName" class="btn btn-primary">Search</button>
                                </form>
                            </div>
                        </div>
                        <div class="welcome" >

                            <div class="table-tilte">Brand Table</div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Brand Name</th>
                                        <th>Total products</th>    
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>   
                                    <%
                                        List<BrandDTO> brandList = (List<BrandDTO>) session.getAttribute("BRAND_LIST");
                                        if (brandList != null && !brandList.isEmpty()) {
                                    %>
                                    <%
                                        int count = 1;
                                        for (BrandDTO b : brandList) {
                                    %>
                                    <tr>
                                        <td> <%=count++%></td>    
                                        <td> <%=b.getBrandName()%></td> 
                                        <td> <%=b.getProductCount()%></td> 
                                        <td> <!-- Nút Xóa -->
                                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal<%= b.getBrandId()%>">
                                                <i class="fas fa-trash"></i>
                                            </button>

                                            <!-- Modal Xóa -->
                                            <div class="modal fade" id="deleteModal<%= b.getBrandId()%>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="deleteModalLabel">Confirm User deletion</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form action="MainController" method="POST">
                                                            <div class="modal-body">
                                                                <input type="hidden" name="userId" value="<%= b.getBrandId()%>" />
                                                                Are you sure you want to delete the user '<%= b.getBrandName()%>'?
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                                                                <input type="hidden" name="brandId" value="<%= b.getBrandId()%>" />
                                                                <button type="submit" name="action" value="DeleteBrand" class="btn btn-danger">Delete</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>  
                                    <%
                                        }
                                    } else {
                                    %>
                                    <% if (request.getAttribute("err_search") != null) {%>
                                <div class="alert alert-danger"><%= request.getAttribute("err_search")%></div>
                                <% }%>
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

