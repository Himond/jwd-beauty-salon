
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<html>
<head>
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
            <button class="btn btn-dark btn-lg btn-rounded"  data-bs-toggle="modal" data-bs-target="#modalSignUp" type="submit">Create an account</button>
            <!-- Modal -->
            <div class="modal fade" style="padding: 15px" id="modalSignUp" role="dialog" tabindex="-1" aria-labelledby="modalLabelSignUp" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-body bg-footer">
                            <form class="transparent" method="POST" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="sign_up">
                                <div class="form-inner">
                                    <h6>Sign up to Salon</h6>
                                    <br/>
                                    <label for="username_sign_up">Login</label>
                                    <input type="text" name="username" maxlength="30" id="username_sign_up" required>
                                    <label for="firstname_sign_up">Name</label>
                                    <input type="text" name="first_name" maxlength="20" id="firstname_sign_up" required>
                                    <label for="lastname_sign_up">Surname</label>
                                    <input type="text" name="last_name" maxlength="20" id="lastname_sign_up" required>
                                    <label for="phone_sign_up">Phone</label>
                                    <input type="tel" name="phone"  id="phone_sign_up" placeholder="+375291234567" required>
                                    <label for="email_sign_up">Email</label>
                                    <input type="email" name="email"  id="email_sign_up" placeholder="example@example.com" required>
                                    <label for="password_sign_up">Password</label>
                                    <input  type="password" name="password" id="password_sign_up" minlength="2" maxlength="30" required>
                                    <label for="password_sign_up_rep">Repeat password</label>
                                    <input  type="password" name="password_rep" minlength="2" maxlength="30" id="password_sign_up_rep" required>
                                    <hr>
                                    <button type="submit" class="btn btn-outline-dark">Sign Up</button>
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

    <div class="footer-copyright text-center py-3"  > <b><i>© Minsk 2021:</i></b>
        <b><i><a href="https://vk.com/himond" style="text-decoration: none;" > <h5 style="color: black"><b>Litvinko Ihar</b></h5> </a></i></b>
    </div>
</footer>
</body>
</html>
