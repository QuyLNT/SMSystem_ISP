

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="codelean Template">
        <meta name="keywords" content="codelean, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Register</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/themify-icons.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style1.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="icon" href="img/icon-logoweb.png" type="img/x-icon" />

    </head>

    <body>
        <!-- Start coding here -->
        <!-- Page PreOrder -->
        <!-- Header section begin -->
        <header class="header-section">
            <div class="header-top">
                <div class="container">
                    <div class="ht-left">
                        <div class="mail-service">
                            <i class="fa fa-envelope">
                            </i>smsystem8386@gmail.com

                        </div>
                        <div class="phone-service">
                            <i class="fa fa-phone">

                            </i>+84 123456789
                        </div>
                    </div>
                    <div class="ht-right">
                        <!--                        <a href="login.jsp" class="login-panel">
                                                    <i class="fa fa-user">Login</i>
                                                </a>-->
                        <div class="lan-selector">
                            <select class="language_drop" name="countries" id="countries" style="width: 300px;">
                                <option value="yt" data-image="img/flag-1.jpg" data-imagecss="flag yt" data-title="English">
                                    English</option>
                                <option value="yu" data-image="img/flag-2.jpg" data-imagecss="flag yu" data-title="German">
                                    German</option>
                            </select>
                        </div>
                        <div class="top-social">
                            <a href="#"><i class="ti-facebook"></i></a>
                            <a href="#"><i class="ti-twitter-alt"></i></a>
                            <a href="#"><i class="ti-linkedin"></i></a>
                            <a href="#"><i class="ti-pinterest"></i></a>
                        </div>
                    </div>
                </div>

            </div>
        </header>

        <div class="container1">
            <div class="signin-signup">
                <div class="signin">
                    <h2>Sign up</h2>

                    <form action="MainController" method="get" id="form">
                        <input type="text" placeholder="Full Name" name="fullName" required="" value="<%= (request.getParameter("fullName") != null) ? request.getParameter("fullName") : ""%>" />
                        <input type="text" placeholder="User Name" name="userName" required="" value="<%= (request.getParameter("userName") != null) ? request.getParameter("userName") : ""%>" />
                        <input type="text" placeholder="Email" name="email" required="" value="<%= (request.getParameter("email") != null) ? request.getParameter("email") : ""%>" />
                        <input type="text" placeholder="PhoneNumber" name="phoneNumber" required="" value="<%= (request.getParameter("phoneNumber") != null) ? request.getParameter("phoneNumber") : ""%>" />
                        <input type="password" placeholder="Password" name="password" required="" />
                        <input type="password" placeholder="Confirm Password" name="confirmPass" required="" />
                        <a href="login.jsp" class="haveAccount">Have account ?</a>
                        <% if (request.getAttribute("ms") != null) {%>
                        <div class="alert alert-success"><%= request.getAttribute("ms")%></div>
                        <% }%>
                        <% if (request.getAttribute("err") != null) {%>
                        <div class="alert alert-danger"><%= request.getAttribute("err")%></div>
                        <% }%>
                        <div class="g-recaptcha" data-sitekey="6LdXPwgqAAAAADH4aZqMia8RCAPe-jw1GISS5lHp" style="display: flex;justify-content: center"></div>
                        <input type="submit" name="action" class="btn signin-btn" value="Sign Up" />
                    </form>
                </div>
            </div>

            <div class="overlay">
                <div class="overlay-content">
                    <h2 style="color: #2196F3">Hello, Friend!</h2>
                    <p>Enter your personal details and start journey with us</p>
                    <a href="login.jsp"><button class="btn signup-btn">Sign In</button></a>
                    <a  href="MainController?action=HomePage"><button class="btn signup-btn">Back to Shop</button></a>
                </div>
            </div>
        </div>
        <!-- Register Section Begin -->

        <!-- Register Section End -->




        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/jquery.dd.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <!--     <script src="https://www.google.com/recaptcha/api.js" async defer></script>
                   <script>
                        window.onload = function () {
                            let isValid = false;
                            const form = document.getElementById("form");
                            const error = document.getElementById("error");
            
                            form.addEventListener("submit", function (event) {
                                event.preventDefault();
                                const response = grecaptcha.getResponse();
                                if (response) {
                                    form.submit();
                                } else {
                                    error.innerHTML = "Please check";
                                }
                            });
                        };
                    </script>-->
    </body>
</html>
