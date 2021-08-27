
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>


<html>
<head>
    <title>Title</title>

    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/static/core/img/ico.png"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <!-- Bootstrap core CSS -->
    <link href="../static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="../static/fontawesome/css/fontawesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/core/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-social.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/fontawesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/brands.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/solid.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/regular.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/owl-carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/owl-carousel/owl.theme.default.min.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Arvo:700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway:300,100' rel='stylesheet' type='text/css'>

    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/owl-carousel/owl.carousel.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/custom.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/profile.js"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark" style="background: #0d0d0d radial-gradient(circle farthest-corner at 100px 50px, #0d0d0d, #412a10);">
    <div class="navbar-header ">
        <a class="navbar-brand center-block" href="">
            <img src="${pageContext.request.contextPath}/static/core/img/main_logo3.jpg" width="220" height="68"   alt="">
        </a>
    </div>

    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#"><h5>Beauty salon</h5></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><b><h5>Services</h5></b></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><b><h5>Contacts</h5></b></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><b><h5>Profile</h5></b></a>
                </li>
            </ul>
        </div>

        <li class="navbar-nav flex-row ml-md-auto d-none d-md-flex form-row text-center">
            <div class="col-12">
                <ul class="list-group">
                    <li><a href="tel:+8(044)5866555"><i class="fas fa-phone" style="color: white;" ><b class="text-white"> +375 (44) 586-65-55</b></i></a></li>
                    <li><a title="Viber" href="viber://chat?number=+375445866555"> <i class="fab fa-viber" style="color: white;"><b class="text-white"> +375 (44) 586-65-55</b></i></a></li>
                </ul>
            </div>
        </li>

        <div class="form-row text-center navbar-nav flex-row ml-md-auto d-none d-md-flex" style="padding-left:20px">
            <div class="col-12">
                <li class="list-inline-item">
                    <a class="btn btn-social-icon btn-vk btn-circle" href="https://vk.com/viktoria_makijag" >
                        <span class="fab fa-vk"></span>
                    </a>
                </li>
                <li class="list-inline-item">
                    <a class="btn btn-social-icon btn-facebook btn-circle" href="https://www.facebook.com/gorbacheva.makijag">
                        <span class="fab fa-facebook"></span>
                    </a>
                </li>
                <li class="list-inline-item">
                    <a class="btn btn-social-icon btn-instagram btn-circle" href="https://www.instagram.com/gorbaceva_muah/">
                        <span class="fab fa-instagram"></span>
                    </a>
                </li>
            </div>
        </div>

        <div class="navbar-nav flex-row ml-md-auto d-none d-md-flex form-row text-center" style="padding-left:20px">
            <div class="col-12">
                 <c:choose>
                     <c:when test="${empty user}">
                         <button class="btn btn-outline-light"  data-bs-toggle="modal" data-bs-target="#exampleModal" type="submit">Sign In</button>
                         <!-- Modal -->
                         <div class="modal fade" style="padding: 15px" id="exampleModal" role="dialog" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                             <div class="modal-dialog" role="document">
                                 <div class="modal-content">
                                     <div class="modal-body bg-footer">
                                         <form class="transparent" method="POST" action="${pageContext.request.contextPath}/controller">
                                             <input type="hidden" name="command" value="sign_in">
                                             <div class="form-inner">
                                                 <h6>Sign in to Salon</h6>
                                                 <br/>
                                                 <label for="id_username_enter">Login</label>
                                                 <input type="text" name="username" maxlength="30" id="id_username_enter" placeholder="example@example.com" required>
                                                 <label for="id_password_enter">Password</label>
                                                 <input  type="password" name="password" id="id_password_enter" minlength="2" maxlength="30" required>
                                                 <hr>
                                                 <button type="submit" class="btn btn-outline-dark">Sign In</button>
                                                 <hr>
                                                 <label><a href="" style="color:white; font-size: 15px; text-decoration: none;">Forgot password?</a></label>
                                                 <label><a href="" style="color:white; font-size: 15px; text-decoration: none;">Create an account?</a></label>
                                             </div>
                                         </form>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </c:when>
                     <c:otherwise>
                         <form name="LogOutForm" method="POST" action="${pageContext.request.contextPath}/controller">
                             <input type="hidden" name="command" value="log_out"/>
                             <button type="submit" style="margin-top: 14px" class="btn btn-outline-light">Logout</button>
                         </form>
                     </c:otherwise>
                 </c:choose>
            </div>
        </div>
    </div>
</nav>
</body>
</html>
