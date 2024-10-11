<%-- 
    Document   : index
    Created on : Jun 18, 2024, 10:17:29 PM
    Author     : DELL
--%>

<%@page import="model.user.UserDTO"%>
<%@page import="model.user.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
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
                                    <img src="img/logoweb.png" alt="" width="32px" height="32px"/>
                                    <span class="title-text">SM System</span>
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
                        <div class="welcome" >

                            <!-- Button trigger modal -->
                            
                            <!-- Modal Add -->                                                                              <%
                                String searchUserName = request.getParameter("searchUserName");
                                if (searchUserName == null) {
                                    searchUserName = "";
                                }
                            %>
                            <div class="search-form">
                                <form action="MainController" method="POST">
                                    Search order: <input type="text" name="searchUserName" placeholder="Enter user name" value="<%= searchUserName%>"/>
                                    <button type="submit" name="action" value="SearchUserName">Search</button>
                                </form>
                            </div>
                        </div>

                        <div class="welcome">
                            <%
                                List<UserDTO> userList = (List<UserDTO>) session.getAttribute("USER_LIST");   
                            %>
                            <div class="table-tilte">Ship Table</div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Order ID</th>
                                        <th>Customer</th>
                                        <th>Order Item</th>
                                        <th>Phone Number</th>                                        
                                        <th>Address</th>
                                        <th>Shipping method</th>
                                        <th>Delivery Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        if (userList != null) {
                                            int stt = 1;
                                            for (UserDTO user : userList) {
                                    %>
                                    <tr>
                                        <td><%= stt++%></td>
                                        <td><%= user.getUserName()%></td>
                                        <td><%= user.getFullName()%></td>
                                        <td><%= user.getEmail()%></td>
                                        <td>
<!--                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="userId" value="<%= user.getUserId()%>"/>
                                                <input type="hidden" name="action" value="toggleUserStatus"/>
                                                <select name="isActive" onchange="this.form.submit()">
                                                    <option value="1" <%= user.isIsActive() ? "selected" : ""%>>Active</option>
                                                    <option value="0" <%= !user.isIsActive() ? "selected" : ""%>>Inactive</option>
                                                </select>
                                            </form>-->
                                            <%= user.getPhoneNumber()%>
                                        </td>
                                        <td>

                                        </td>

                                        <td>
                                            
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST">
                                               <input type="hidden" name="userId" value="<%= user.getUserId()%>"/>
                                               <input type="hidden" name="action" value="toggleUserRole"/>
                                               <select name="roleId" onchange="this.form.submit()">
                                                   <option value="AD" <%= user.getRoleId().equalsIgnoreCase("AD") ? "selected" : ""%>>Admin</option>
                                                   <option value="CUS" <%= user.getRoleId().equalsIgnoreCase("CUS") ? "selected" : ""%>>Customer</option>
                                                   <option value="MN" <%= user.getRoleId().equalsIgnoreCase("MN") ? "selected" : ""%>>Manager</option>
                                                   <option value="SP" <%= user.getRoleId().equalsIgnoreCase("SP") ? "selected" : ""%>>Shipper</option>
                                               </select>
                                           </form>
                                       </td>
                                        <td>
                                            <input type="hidden" name="userId"  value="<%=user.getUserId()%>" />
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal<%=user.getUserId()%>">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </button>

                                            <!-- Modal Update -->
                                            <div class="modal fade" id="updateModal<%=user.getUserId()%>" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="updateModalLabel">Update user '<%=user.getUserName()%>' information</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form action="UpdateUserController" method="POST">
                                                            <div class="modal-body">
                                                                <input type="hidden" name="userId"  value="<%=user.getUserId()%>" />
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">No</span>
                                                                    <input name="userName" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getUserName()%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Order ID</span>
                                                                    <input name="fullName" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getFullName()%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Customer</span>
                                                                    <input name="userPass" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getPassword()%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Order item</span>
                                                                    <input name="phoneNumber" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getPhoneNumber()%>">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Phone number</span>
                                                                    <input name="phoneNumber" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getPhoneNumber()%>">
                                                                </div>
                                                                
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Address</span>
                                                                    <input name="email" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getEmail()%>">
                                                                </div> 
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Delivery status</span>
                                                                    <input name="email" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getEmail()%>">
                                                                </div> 
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <input type="submit" name="action" value="Update User" class="btn btn-primary"/>
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
                        </div>
                    </div>
                </div>

            </section>


        </main>

        <script src="js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>