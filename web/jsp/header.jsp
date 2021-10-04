
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/static/core/img/ico.png"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/fontawesome/css/fontawesome.css" rel="stylesheet"/>
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
        <a class="navbar-brand center-block" href="${pageContext.request.contextPath}/jsp/main.jsp">
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
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/controller?command=go_to_main_page"><h5><fmt:message key="salon.base"/></h5></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_shop_page"><b><h5><fmt:message key="salon.services"/></h5></b></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_contacts_page"><b><h5><fmt:message key="salon.contacts"/></h5></b></a>
                </li>
                <c:if test="${user.role() == 'MASTER' or user.role() == 'CLIENT'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_profile_page"><b><h5><fmt:message key="salon.profile"/></h5></b></a>
                    </li>
                </c:if>
                <c:if test="${user.role() == 'ADMINISTRATOR'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><b><h5><fmt:message key="salon.admin"/></h5></b></a>
                    </li>
                </c:if>

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
                    <form  action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="change_locale">
                        <button class="btn btn-social-icon btn-circle" style="margin-top: 16px" type="submit" name="locale" value="en_En" >
                            <img src="${pageContext.request.contextPath}/static/core/img/EN.png" alt="">
                        </button>
                    </form>
                </li>
                <li class="list-inline-item">
                    <form  action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="change_locale">
                        <button class="btn btn-social-icon btn-circle" style="margin-top: 16px" type="submit" name="locale" value="ru_Ru" >
                            <img src="${pageContext.request.contextPath}/static/core/img/RU.png" alt="">
                        </button>
                    </form>
                </li>
            </div>
        </div>

        <div class="navbar-nav flex-row ml-md-auto d-none d-md-flex form-row text-center" style="padding-left:20px">
            <div class="col-12">
                 <c:choose>
                     <c:when test="${empty user}">
                     <div class="btn-group" role="group" aria-label="Basic example">
                         <button class="btn btn-outline-light"  style="margin-top: 14px; margin-bottom: 16px" data-bs-toggle="modal" data-bs-target="#exampleModal" type="submit"><fmt:message key="button.name.enter"/></button>
                         <!-- Modal -->
                         <div class="modal fade" style="padding: 15px" id="exampleModal" role="dialog" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                             <div class="modal-dialog" role="document">
                                 <div class="modal-content">
                                     <div class="modal-body bg-footer">
                                         <form class="transparent" method="POST" action="${pageContext.request.contextPath}/controller">
                                             <input type="hidden" name="command" value="sign_in">
                                             <div class="form-inner">
                                                 <h6><fmt:message key="salon.signIn.enter"/></h6>
                                                 <br/>
                                                 <label for="id_username_enter"><fmt:message key="placeholder.name.username"/></label>
                                                 <input type="text" name="username" pattern="^[a-zA-Z0-9_]{4,16}$" id="id_username_enter" required>
                                                 <label for="id_password_enter"><fmt:message key="placeholder.name.password"/></label>
                                                 <input  type="password" name="password" pattern="^.{4,18}$" id="id_password_enter" minlength="2" maxlength="30" required>
                                                 <hr>
                                                 <button type="submit" class="btn btn-outline-dark"><fmt:message key="salon.signIn.enter"/></button>
                                                 <hr>
                                                 <label><a href="${pageContext.request.contextPath}/controller?command=go_to_forgot_password_page" style="color:white; font-size: 15px; text-decoration: none;"><fmt:message key="button.name.forgotPassword"/></a></label>
                                                 <label><a href="${pageContext.request.contextPath}/controller?command=go_to_signup_page" style="color:white; font-size: 15px; text-decoration: none;"><fmt:message key="button.name.createAnAccount"/></a></label>
                                             </div>
                                         </form>
                                     </div>
                                 </div>
                             </div>
                         </div>
                         <form name="CartForm" method="POST" action="${pageContext.request.contextPath}/controller">
                             <input type="hidden" name="command" value="go_to_cart_page"/>
                             <button type="submit" style="margin-top: 14px" class="btn btn-outline-light"><i class="fas fa-cart-arrow-down"></i> ${sessionScope.cart.getTotalPrice()} <fmt:message key="shop.page.byn"/></button>
                         </form>
                     </div>
                     </c:when>
                     <c:otherwise>
                         <div class="btn-group" role="group" aria-label="Basic example">
                             <form name="LogOutForm" method="POST" action="${pageContext.request.contextPath}/controller">
                                 <input type="hidden" name="command" value="log_out"/>
                                 <button type="submit" style="margin-top: 14px" class="btn btn-outline-light"><fmt:message key="button.name.exit"/></button>
                             </form>

                             <c:choose>
                                 <c:when test="${user.role() == 'ADMINISTRATOR'}">
                                     <form name="CartForm" method="POST" action="${pageContext.request.contextPath}/controller">
                                         <input type="hidden" name="command" value="go_to_admin_order_page"/>
                                         <button type="submit" style="margin-top: 14px" class="btn btn-outline-light"><fmt:message key="button.name.currentAdminOrders"/></button>
                                     </form>
                                 </c:when>
                                 <c:otherwise>
                                     <c:choose>
                                         <c:when test="${user.role() == 'MASTER'}">
                                             <form name="CartForm" method="POST" action="${pageContext.request.contextPath}/controller">
                                                 <input type="hidden" name="command" value="go_to_master_order_page"/>
                                                 <button type="submit" style="margin-top: 14px" class="btn btn-outline-light"><fmt:message key="button.name.currentMasterOrders"/></button>
                                             </form>
                                         </c:when>
                                         <c:otherwise>
                                             <form name="CartForm" method="POST" action="${pageContext.request.contextPath}/controller">
                                                 <input type="hidden" name="command" value="go_to_cart_page"/>
                                                 <button type="submit" style="margin-top: 14px" class="btn btn-outline-light"><i class="fas fa-cart-arrow-down"></i> ${sessionScope.cart.getTotalPrice()} <fmt:message key="shop.page.byn"/></button>
                                             </form>
                                         </c:otherwise>
                                     </c:choose>
                                 </c:otherwise>
                             </c:choose>

                         </div>
                     </c:otherwise>
                 </c:choose>
            </div>
        </div>
    </div>
</nav>

</body>
</html>
