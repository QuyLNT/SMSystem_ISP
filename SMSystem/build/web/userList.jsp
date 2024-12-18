

<%@page import="model.user.UserDTO"%>
<%@page import="model.user.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <title></title>
        <link rel="stylesheet" href="css/user1.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
            />
        <link rel="icon" href="img/icon-logoweb.png" type="img/x-icon" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style1.css" type="text/css">

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
                            <!-- Search -->                            
                            <%
                                String searchUserName = request.getParameter("searchUserName");
                                if (searchUserName == null) {
                                    searchUserName = "";
                                }
                            %>
                            <div class="search-form">
                                <form action="MainController" method="POST">
                                    Search User: <input type="text" name="searchUserName" placeholder="Enter user name" value="<%= searchUserName%>"/>
                                    <button type="submit" name="action" value="SearchUserName"class="btn btn-primary">Search</button>
                                </form>
                            </div>
                            <% if (request.getAttribute("ms") != null) {%>
                            <div class="alert alert-success"><%= request.getAttribute("ms")%></div>
                            <% }%>
                            <% if (request.getAttribute("err") != null) {%>
                            <div class="alert alert-danger"><%= request.getAttribute("err")%></div>
                            <% }%>
                        </div>

                        <div class="welcome">
                            <%
                                List<UserDTO> userList = (List<UserDTO>) session.getAttribute("USER_LIST");
                            %>
                            <div class="table-tilte">User Table</div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>User Name</th>
                                        <th>Full Name</th>
                                        <th>Email</th>
                                        <th>Phone Number</th>                                        
                                        <th>Role</th>
                                        <th>Created Date</th>
                                        <th>Status</th>
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
                                        <td><%= user.getPhoneNumber()%></td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="userId" value="<%= user.getUserId()%>"/>
                                                <input type="hidden" name="action" value="toggleUserRole"/>
                                                <div class="select-option-province">
                                                    <select name="roleId" onchange="this.form.submit()">
                                                        <option value="AD" <%= user.getRoleId().equalsIgnoreCase("AD") ? "selected" : ""%>>Admin</option>
                                                        <option value="CUS" <%= user.getRoleId().equalsIgnoreCase("CUS") ? "selected" : ""%>>Customer</option>
                                                        <option value="MN" <%= user.getRoleId().equalsIgnoreCase("MN") ? "selected" : ""%>>Manager</option>
                                                        <option value="SP" <%= user.getRoleId().equalsIgnoreCase("SP") ? "selected" : ""%>>Shipper</option>
                                                    </select>
                                                </div>
                                            </form>
                                        </td>
                                        <td><%= user.getCreatedAt()%></td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="userId" value="<%= user.getUserId()%>"/>
                                                <input type="hidden" name="action" value="toggleUserStatus"/>
                                                <div class="select-option-province">
                                                    <select name="User_Status" onchange="this.form.submit()">
                                                        <option value="1" <%= user.isActive() == true ? "selected" : ""%>>Active</option>
                                                        <option value="0" <%= user.isActive() == false ? "selected" : ""%>>Inactive</option>
                                                    </select>
                                                </div>
                                            </form>
                                        </td>
                                        <td>
                                            <input type="hidden" name="userId"  value="<%=user.getUserId()%>" />
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal<%=user.getUserId()%>">
                                                <i class="fas fa-eye"></i>
                                            </button>

                                            <!-- Modal Update -->
                                            <div class="modal fade" id="updateModal<%=user.getUserId()%>" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="updateModalLabel">View user '<%=user.getUserName()%>' information</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form action="MainController" method="POST">
                                                            <div class="modal-body">
                                                                <input type="hidden" name="userId"  value="<%=user.getUserId()%>" />
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">User Name</span>
                                                                    <input name="userName" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getUserName()%>" readonly="">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Full Name</span>
                                                                    <input name="fullName" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getFullName()%>" readonly="">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Password</span>
                                                                    <input name="userPass" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getPassword()%>"readonly="">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Phone Number</span>
                                                                    <input name="phoneNumber" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getPhoneNumber()%>" readonly="">
                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Sex</span>
                                                                    <select name="Sex" class="form-control">
                                                                        <option value="Male" <%= "Male".equals(user.getSex()) ? "selected" : ""%>>Male</option>
                                                                        <option value="Female" <%= "Female".equals(user.getSex()) ? "selected" : ""%>>Female</option>
                                                                        <option value="Others" <%= "Others".equals(user.getSex()) ? "selected" : ""%>>Others</option>
                                                                    </select>

                                                                </div>
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Email</span>
                                                                    <input name="email" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="<%= user.getEmail()%>" readonly="">
                                                                </div> 
                                                                <div class="input-group input-group-sm mb-3">
                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Role</span>
                                                                    <select name="userRole" class="form-control">
                                                                        <option value="AD" <%= user.getRoleId().equalsIgnoreCase("AD") ? "selected" : ""%>>Admin</option>
                                                                        <option value="CUS" <%= user.getRoleId().equalsIgnoreCase("CUS") ? "selected" : ""%>>Customer</option>
                                                                        <option value="MN" <%= user.getRoleId().equalsIgnoreCase("MN") ? "selected" : ""%>>Manager</option>
                                                                        <option value="SP" <%= user.getRoleId().equalsIgnoreCase("SP") ? "selected" : ""%>>Shipper</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                            </div>

                                                        </form>

                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Nút Xóa -->
<!--                                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal<%= user.getUserId()%>">
                                                <i class="fas fa-trash"></i>
                                            </button>

                                             Modal Xóa 
                                            <div class="modal fade" id="deleteModal<%= user.getUserId()%>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="deleteModalLabel">Confirm User deletion</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form action="MainController" method="POST">
                                                            <div class="modal-body">
                                                                <input type="hidden" name="userId" value="<%= user.getUserId()%>" />
                                                                Are you sure you want to delete the user '<%= user.getUserName()%>'?
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                                                                <input type="hidden" name="userId" value="<%= user.getUserId()%>" />
                                                                <button type="submit" name="action" value="Delete User" class="btn btn-danger">Delete</button>

                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>-->
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
