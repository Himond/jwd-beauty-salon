
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<!-- Footer -->
<footer class="page-footer font-small bg-footer pt-4">
    <!-- Footer Links -->
    <div class="container">

        <div class="row">
            <div class="col-lg-2 col-md-6 mb-4">
                <!--Image-->
                <div class="scale">
                    <img src="${pageContext.request.contextPath}/static/core/img/model1.jpg"  class="img-fluid scale"
                         alt="">
                    <a href="#">
                        <div class="mask rgba-white-light"></div>
                    </a>
                </div>
            </div>
            <div class="col-lg-2 col-md-6 mb-4">
                <div class="scale">
                    <img src="${pageContext.request.contextPath}/static/core/img/model2.jpg"  class="img-fluid scale"
                         alt="">
                    <a href="#">
                        <div class="mask rgba-white-light"></div>
                    </a>
                </div>

            </div>
            <div class="col-lg-2 col-md-6 mb-4">
                <div class="scale">
                    <img src="${pageContext.request.contextPath}/static/core/img/model3.jpg" class="img-fluid scale"
                         alt="">
                    <a href="#">
                        <div class="mask rgba-white-light"></div>
                    </a>
                </div>
            </div>
            <div class="col-lg-2 col-md-6 mb-4">
                <div class="scale">
                    <img src="${pageContext.request.contextPath}/static/core/img/model4.jpg" class="img-fluid scale"
                         alt="">
                    <a href="#">
                        <div class="mask rgba-white-light"></div>
                    </a>
                </div>

            </div>
            <div class="col-lg-2 col-md-6 mb-4">

                <div class="scale">
                    <img src="${pageContext.request.contextPath}/static/core/img/model5.jpg" class="img-fluid scale"
                         alt="">
                    <a href=#">
                        <div class="mask rgba-white-light"></div>
                    </a>
                </div>

            </div>

            <div class="col-lg-2 col-md-6 mb-4">
                <div class="scale">
                    <img src="${pageContext.request.contextPath}/static/core/img/model6.jpg" class="img-fluid scale"
                         alt="">
                    <a href="#">
                        <div class="mask rgba-white-light"></div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <hr>

    <ul class="list-unstyled list-inline text-center py-2">
    <c:choose>
        <c:when test="${empty user}">
            <button class="btn btn-dark btn-lg btn-rounded"  data-bs-toggle="modal" data-bs-target="#modalSignUp" type="submit"><fmt:message key="button.name.createAnAccount"/></button>
            <!-- Modal -->
            <div class="modal fade" style="padding: 15px" id="modalSignUp" role="dialog" tabindex="-1" aria-labelledby="modalLabelSignUp" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-body bg-footer">
                            <form class="transparent" method="POST" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="sign_up">
                                <div class="form-inner">
                                    <h6><fmt:message key="salon.signIn.register"/></h6>
                                    <br/>
                                    <label for="username_sign_up"><fmt:message key="placeholder.name.username"/></label>
                                    <input type="text" name="username" pattern="^[a-zA-Z0-9_]{4,16}$" id="username_sign_up" required>
                                    <label for="firstname_sign_up"><fmt:message key="placeholder.name.firstName"/></label>
                                    <input type="text" name="first_name"  pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$" id="firstname_sign_up" required>
                                    <label for="lastname_sign_up"><fmt:message key="placeholder.name.lastName"/></label>
                                    <input type="text" name="last_name"  pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$" id="lastname_sign_up" required>
                                    <label for="phone_sign_up"><fmt:message key="placeholder.name.phone"/></label>
                                    <input type="tel" name="phone" pattern="^\+\d{12}$"  id="phone_sign_up" placeholder="+375291234567" required>
                                    <label for="email_sign_up"><fmt:message key="placeholder.name.email"/></label>
                                    <input type="email" name="email" pattern="^(?=.{3,30}$)[^\s]+@[^\s]+\.[^\s]+$" id="email_sign_up" placeholder="example@example.com" required>
                                    <label for="password_sign_up"><fmt:message key="placeholder.name.password"/></label>
                                    <input  type="password" name="password" pattern="^.{4,18}$" id="password_sign_up"  required>
                                    <label for="password_sign_up_rep"><fmt:message key="placeholder.name.passwordRe"/></label>
                                    <input  type="password" name="password_rep" pattern="^.{4,18}$"  id="password_sign_up_rep" required>
                                    <hr>
                                    <button type="submit" class="btn btn-outline-dark"><fmt:message key="button.name.register"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
    </ul>
    <hr>
    <ul class="list-unstyled list-inline text-center">
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

    </ul>

    <div class="footer-copyright text-center py-3" > <b><i><fmt:message key="footer.copyRight"/></i></b>
        <b><i><a href="https://vk.com/himond" style="text-decoration: none;" > <h5 style="color: black"><b><fmt:message key="footer.author"/></b></h5> </a></i></b>
    </div>
</footer>
</body>
</html>
