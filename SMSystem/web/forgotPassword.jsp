<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="styles.css">
        <style>
            .container {
                max-width: 400px;
                margin: 50px auto;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            h2 {
                color: #333;
            }

            .notification {
                padding: 10px;
                margin-bottom: 15px;
                margin-top: 20px;
                color: #155724;
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
                border-radius: 5px;
                font-size: 14px;
            }

            .success {
                color: #155724;
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
            }

            .error {
                color: #D8000C; 
                background-color: #FFBABA; 
                border: 1px solid #D8000C;
            }
            
            #emailSection label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
                color: #555;
            }

            #emailSection input[type="email"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                background-color: #007bff;
                color: #fff;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease;
            }

            .btn:hover {
                background-color: #0056b3;
            }

            .back-link {
                display: inline-block;
                color: #007bff;
                text-decoration: none;
                text-align: left;
                margin-top: 15px;
            }

            .back-link:hover {
                text-decoration: underline;
            }

            .footer-link {
                text-align: left;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Forgot Password</h2>
            <% String ms = (String) request.getAttribute("ms"); %>
            <% String err = (String) request.getAttribute("err"); %>

            <% if (ms == null) { %>
            <div id="emailSection">
                <form action="ForgotPasswordController" method="post">
                    <label for="email">Enter your registered email:</label>
                    <input type="email" id="email" name="email" placeholder="Email" required />
                    <input type="submit" class="btn" value="Submit" />
                </form>
            </div>
            <% } %>

            <% if (err != null) {%>
            <div class="notification error"><%= err%></div>
            <% } %>

            <% if (ms != null) {%>
            <div class="notification success"><%= ms%></div>
            <% }%>

            <div class="footer-link">
                <a href="login.jsp" class="back-link">Back to Login</a>
            </div>
        </div>
    </body>
</html>
