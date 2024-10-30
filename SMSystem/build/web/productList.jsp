<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

<%@page import="model.product.ProductError"%>
<%@page import="java.util.Map"%>
<%@page import="model.product.ProductDAO"%>
<%@page import="model.product.ProductDTO"%>
<%@page import="model.category.UserObjectDAO"%>
<%@page import="model.category.UserObjectDTO"%>
<%@page import="model.category.BrandDAO"%>
<%@page import="model.category.BrandDTO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ProductsList</title>
        <title>SMSystem</title>
        <link rel="stylesheet" href="css/user1.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
            />
        <!--        <link rel="icon" href="favicon_io/favicon.ico" type="img/x-icon" />-->
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
                                    <img src="img/logoweb.png" alt="" width="32px" height="32px"/>
                                    <span class="title-text">SMS</span>
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
                <div class="overlay">
                    <div class="head">
                        <button class="toggler">
                            <i class="fa-solid fa-bars"></i>
                        </button>
                    </div>
                    <div class="content">
                        <div class="welcome">

                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal1">
                                <i class="fa-solid fa-plus"></i> Add new Product
                            </button>
                            <%
                                ProductDAO productDao = new ProductDAO();
                                List<ProductDTO> productList = null;
                                String noResults = (String) session.getAttribute("NO_RESULTS");

                                productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");

                                String ms = "";
                                String err = "";
                                if (request.getAttribute("ms") != null) {
                                    ms = (String) request.getAttribute("ms");
                                }
                                if (request.getAttribute("err") != null) {
                                    err = (String) request.getAttribute("err");
                                }
                                if (ms != null || err != null) {

                            %>
                            <div class="mes-suc">
                                <%=ms%> <%=err%>
                            </div>                          
                            <%}%>
                            <!-- Modal Add -->

                            <div class="modal fade" id="addModal1" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">  
                                            <h1 class="modal-title fs-5" id="addModalLabel">Create new product </h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="MainController" method="POST">
                                            <div class="modal-body">
                                                <% if (request.getAttribute("PRODUCT_ERROR") != null) {
                                                        ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
                                                %>
                                                <div class="alert alert-danger">
                                                    <% if (productError.getNameError() != null) {%>
                                                    <p><%= productError.getNameError()%></p>
                                                    <% } %>
                                                    <% if (productError.getDetailError() != null) {%>
                                                    <p><%= productError.getDetailError()%></p>
                                                    <% } %>
                                                    <% if (productError.getPriceError() != null) {%>
                                                    <p><%= productError.getPriceError()%></p>
                                                    <% } %>
                                                    <% if (productError.getSaleError() != null) {%>
                                                    <p><%= productError.getSaleError()%></p>
                                                    <% } %>
                                                    <% if (productError.getErrorMessage() != null) {%>
                                                    <p><%= productError.getErrorMessage()%></p>
                                                    <% } %>
                                                </div>
                                                <% } %>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Name</span>
                                                    <input name="Name" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Category</span>
                                                    <select name="userObjectID" class="form-control" required>
                                                        <%

                                                            List<UserObjectDTO> userObjectList = (List<UserObjectDTO>) session.getAttribute("USER_OBJECT_LIST");
                                                            for (UserObjectDTO uo : userObjectList) {
                                                        %>
                                                        <option value="<%= uo.getUserObjectId()%>"><%= uo.getUserObjectName()%></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Brand</span>
                                                    <select name="brandID" class="form-control" required>
                                                        <%
                                                            List<BrandDTO> brandList = (List<BrandDTO>) session.getAttribute("BRAND_LIST");
                                                            for (BrandDTO brand : brandList) {
                                                        %>
                                                        <option value="<%= brand.getBrandId()%>"><%= brand.getBrandName()%></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Price</span>
                                                    <input name="price" type="number" step="0.01" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Sale</span>
                                                    <input name="sale" type="number" step="0.01" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Avatar(URL)</span>
                                                    <input name="avatar" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Color</span>
                                                    <input name="color" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Warrant Period (Month)</span>
                                                    <input name="warrantyPeriod" type="number" step="1" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Detail</span>
                                                    <textarea name="detail" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" ></textarea>
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Product Image 1 (URL)</span>
                                                    <input name="productImage1" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" >
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Product Image 2 (URL)</span>
                                                    <input name="productImage2" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" >
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Product Image 3 (URL)</span>
                                                    <input name="productImage3" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" >
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Product Image 4 (URL)</span>
                                                    <input name="productImage4" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" >
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Product Image 5 (URL)</span>
                                                    <input name="productImage5" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" >
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <input type="submit" name="action" value="Create Product" class="btn btn-primary"/>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <%
                                String searchProductName = request.getParameter("searchProductName");
                                if (searchProductName == null) {
                                    searchProductName = "";
                                }
                            %>
                            <div class="search-form">
                                <form action="MainController" method="POST">
                                    Search Product: <input type="text" name="searchProductName" placeholder="Enter product name" value="<%= searchProductName%>"/>
                                    <button type="submit" name="action" value="SearchProductName">Search</button>
                                </form>
                            </div>
                        </div>
                        <div class="welcome">

                            <div class="table-tilte">Product Table</div>

                            <% if (noResults != null) {%>
                            <p><%= noResults%></p>
                            <% } else if (productList != null && !productList.isEmpty()) { %>

                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Avatar</th>
                                        <th>Name</th>
                                        <th>Category</th>
                                        <th>Brand</th>
                                        <th>Price</th>
                                        <th>Sale</th>
                                        <th>Sale Price</th>
                                        <th>Hot</th>
                                        <th>Stock</th>
                                        <th>Warranty Period</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        if (productList != null) {
                                            for (ProductDTO product : productList) {
                                    %>
                                    <tr>
                                        <td><img src="<%= product.getAvatarPath()%>" alt="<%= product.getName()%>" style="width: 70px; height: 70px;"></td>
                                        <td><%= product.getName()%></td>
                                        <td><%
                                            String userObjectName = "";
                                            for (UserObjectDTO u : userObjectList) {
                                                if (u.getUserObjectId() == product.getUserOjectId()) {
                                                    userObjectName = u.getUserObjectName();
                                                }
                                            }
                                            %>
                                            <%= userObjectName%>
                                        </td> 
                                        <td>
                                            <%
                                                String brandName = "";
                                                for (BrandDTO b : brandList) {
                                                    if (b.getBrandId() == product.getBrandId()) {
                                                        brandName = b.getBrandName();
                                                    }
                                                }
                                            %>
                                            <%= brandName%>
                                        </td>
                                        <td><%= product.getPrice()%>$</td>
                                        <td>
                                            <%
                                                double salePercentage = product.getSale() * 100;
                                                double roundedSalePercentage = Math.ceil(salePercentage * 100) / 100.0;
                                                double salePrice = product.getPrice() - (product.getSale() * product.getPrice());
                                            %>
                                            <%= String.format("%.0f%%", roundedSalePercentage)%>
                                        </td>
                                        <td>
                                            <%= String.format("%.2f", salePrice)%>$
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="productId" value="<%= product.getProductId()%>"/>
                                                <input type="hidden" name="action" value="toggleFlashSale"/>
                                                <select name="Hot" onchange="this.form.submit()">
                                                    <option value="1" <%= product.isHot() == true ? "selected" : ""%>>Active</option>
                                                    <option value="0" <%= product.isHot() == false ? "selected" : ""%>>Inactive</option>
                                                </select>
                                            </form>
                                        </td>
                                        <td>
                                            <%
                                                int stock = 0;
                                                List<ProductDTO> stockByProduct = (List<ProductDTO>) session.getAttribute("STOCK_OF_PRODUCT");
                                                if (stockByProduct != null) {
                                                    for (ProductDTO p : stockByProduct) {
                                                        if (p.getProductId() == product.getProductId()) {
                                                            stock = p.getTotalStock();
                                                        }
                                                    }
                                                }

                                            %>
                                            <%=stock%>
                                        </td>
                                        <td>
                                            <%= product.getWarrantyPeriod()%> Months
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="productId" value="<%= product.getProductId()%>"/>
                                                <input type="hidden" name="action" value="toggleProductStatus"/>
                                                <select name="Product_Status" onchange="this.form.submit()">
                                                    <option value="1" <%= product.isProductStatus() == true ? "selected" : ""%>>Active</option>
                                                    <option value="0" <%= product.isProductStatus() == false ? "selected" : ""%>>Inactive</option>
                                                </select>
                                            </form>
                                        </td>
                                        <td>
                                            <input type="hidden" name="productId"  value="<%=product.getProductId()%>" />
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal<%=product.getProductId()%>">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </button>

                                            <!-- Modal Update -->
                                            <div class="modal fade" id="updateModal<%=product.getProductId()%>" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="updateModalLabel">Update product '<%=product.getName()%>' information</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form action="MainController" method="POST">
                                                            <div class="modal-body">
                                                                <input type="hidden" name="productId"  value="<%=product.getProductId()%>" />
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Product Name</span>
                                                                    <input name="name" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= product.getName()%>">
                                                                </div>

                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Detail</span>
                                                                    <textarea name="detail" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"><%= product.getDetail()%></textarea>
                                                                </div>

                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Avatar(URL)</span>
                                                                    <input name="avatar" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= product.getAvatarPath()%>">
                                                                </div>
                                                                <%
                                                                    int selectedBrandID = product.getBrandId();
                                                                %>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Brand</span>
                                                                    <select name="brandID" class="form-control">
                                                                        <<%
                                                                            for (BrandDTO brand : brandList) {
                                                                                String selected = (brand.getBrandId() == selectedBrandID) ? "selected" : "";
                                                                        %>
                                                                        <option value="<%= brand.getBrandId()%>" <%= selected%>><%= brand.getBrandName()%></option>
                                                                        <% } %>
                                                                    </select>
                                                                </div>
                                                                <%
                                                                    int selectedCategoryID = product.getUserOjectId();
                                                                %>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Category</span>
                                                                    <select name="userObjectID" class="form-control">
                                                                        <%
                                                                            for (UserObjectDTO uOb : userObjectList) {
                                                                                String selected = (uOb.getUserObjectId() == selectedCategoryID) ? "selected" : "";
                                                                        %>
                                                                        <option value="<%= uOb.getUserObjectId()%>" <%= selected%>><%= uOb.getUserObjectName()%></option>
                                                                        <% }%>
                                                                    </select>
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Price</span>
                                                                    <input name="price" type="number" step="0.01" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= product.getPrice()%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Color</span>
                                                                    <input name="color" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= product.getColor()%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Sale</span>
                                                                    <input name="sale" type="number" step="0.01" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= product.getSale()%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Warranty Period</span>
                                                                    <input name="warranty" type="number" step="1" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= product.getWarrantyPeriod()%>">
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <input type="submit" name="action" value="Update Product" class="btn btn-primary"/>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td>


                                            <!-- Nút Xem Chi Tiết Sản Phẩm -->
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#viewModal<%= product.getProductId()%>">
                                                <i class="fas fa-eye"></i>
                                            </button>

                                            <!-- Modal Chi Tiết Sản Phẩm (View Modal) -->
                                            <div class="modal fade" id="viewModal<%= product.getProductId()%>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="deleteModalLabel">Size product detail</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form action="DeleteProductController" method="POST">
                                                            <div class="modal-body">
                                                                Name: 
                                                                <div class="row">
                                                                    <div class="col-sm-6">Size</div>
                                                                    <div class="col-sm-6">Stock</div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <!-- Nút mở Modal Add new Brand -->
                                                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal">
                                                                    <i class="fa-solid fa-plus"></i> Add new Size
                                                                </button>

                                                                <!-- Nút đóng modal hiện tại -->
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Modal Add New Brand để thêm Size và Stock -->
                                            <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">  
                                                            <h1 class="modal-title fs-5" id="addModalLabel">Add Size and Stock</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form action="MainController" method="POST">
                                                            <div class="modal-body">
                                                                <!-- Input cho Size -->
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Size</span>
                                                                    <input name="size" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                                </div>
                                                                <!-- Input cho Total (Stock) -->
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Total</span>
                                                                    <input name="stock" type="number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <input type="submit" name="action" value="Add Size and Stock" class="btn btn-primary"/>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>

                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                </tbody>
                            </table>
                            <% } else { %>
                            <p>No products found.</p>
                            <% }%>
                        </div>
                    </div>

                </div>

            </section>

        </main>

        <script src="js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
