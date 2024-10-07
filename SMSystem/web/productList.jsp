<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

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
        <title>Products</title>
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
                                    <img src="favicon_io/favicon-32x32.png" alt="anh chu cho" />
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
                    <div class="content">
                        <div class="welcome">

                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal">
                                <i class="fa-solid fa-plus"></i> Add new Product
                            </button>
                            <%

                                ProductDAO productDao = new ProductDAO();
                                List<ProductDTO> productList = null;
                                String noResults = (String) request.getAttribute("NO_RESULTS");

                                productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
                                if (productList == null) {
                                    productList = productDao.getAllProduct();
                                }

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

                            <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">  
                                            <h1 class="modal-title fs-5" id="addModalLabel">Create new product </h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="CreateProductController" method="POST">
                                            <div class="modal-body">

                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Name</span>
                                                    <input name="Name" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Category</span>
                                                    <select name="userObjectID" class="form-control" required>
                                                        <%
                                                            UserObjectDAO categoriesDao = new UserObjectDAO();
                                                            List<UserObjectDTO> userObjectList = categoriesDao.getAllUserObject();
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
                                                            BrandDAO brandDao = new BrandDAO();
                                                            List<BrandDTO> brandList = brandDao.getAllBrand();
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
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Size</span>
                                                    <input name="size" type="number" step="0.01" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
                                                </div>
                                                <div class="input-group input-group-sm mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Stock</span>
                                                    <input name="stock" type="number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required="">
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
                                <form action="SearchProductByNameController" method="POST">
                                    Search Product: <input type="text" name="searchProductName" placeholder="Enter product name" value="<%= searchProductName%>"/>
                                    <button type="submit" value="SearchProductName">Search</button>
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
                                        <th>Flash Sale</th>
                                        <th>Stock</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        if (productList != null) {
                                            for (ProductDTO product : productList) {
                                                UserObjectDTO userObject = categoriesDao.getUserObjectById(product.getUserOjectId());
                                                BrandDTO brandShow = brandDao.getBrandById(product.getBrandId());
                                    %>
                                    <tr>
                                        <td><img src="<%= product.getAvatarPath()%>" alt="<%= product.getName()%>" style="width: 70px; height: 70px;"></td>
                                        <td><%= product.getName()%></td>
                                        <td><%= userObject.getUserObjectName()%></td> 
                                        <td><%= brandShow.getBrandName()%></td>
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
                                            <form action="ToggleFlashSaleController" method="POST">
                                                <input type="hidden" name="productId" value="<%= product.getProductId()%>"/>
                                                <input type="hidden" name="action" value="toggleFlashSale"/>
                                                <select name="Hot" onchange="this.form.submit()">
                                                    <option value="1" <%= product.isHot() ? "selected" : ""%>>On</option>
                                                    <option value="0" <%= !product.isHot() ? "selected" : ""%>>Off</option>
                                                </select>
                                            </form>
                                        </td>
                                        <td>
                                            <%= "Cái này in ra stock mà tao chưa làm_Kí tên: Quý"
                                            
                                            %>
                                        </td>
                                        <td>
                                            <form action="ToggleProductStatusController" method="POST">
                                                <input type="hidden" name="productId" value="<%= product.getProductId()%>"/>
                                                <input type="hidden" name="action" value="toggleProductStatus"/>
                                                <select name="Product_Status" onchange="this.form.submit()">
                                                    <option value="1" <%= product.isProductStatus()? "selected" : ""%>>Active</option>
                                                    <option value="0" <%= !product.isProductStatus() ? "selected" : ""%>>Inactive</option>
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
                                                        <form action="UpdateProductController" method="POST">
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
                                                                                String selected = (uOb.getUserObjectId()== selectedCategoryID) ? "selected" : "";
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
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Size</span>
                                                                    <input name="size" type="number" step="0.01" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= "Cái này ở trang productDetail"%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Stock</span>
                                                                    <input name="stock" type="number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= "cái này cũng rứa"%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Sale</span>
                                                                    <input name="sale" type="number" step="0.01" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= product.getSale()%>">
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
