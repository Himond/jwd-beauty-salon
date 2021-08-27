
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>

    <title>Login</title>
    <link rel="shortcut icon" href="../static/core/img/ico.png" type="image/png">

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back text-center" style="padding-top:50px;padding-bottom:50px">
<c:choose>
    <c:when test="${empty user}">

        <form class="transparent center-block" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="sign_in">
            <div class="form-inner">
                <h6>Sign in to Salon</h6>
                <label for="id_username_enter">Login</label>
                <input type="text" name="username" maxlength="30" id="id_username_enter" placeholder="example@example.com" required>
                <label for="id_password_enter">Password</label>
                <input  type="password" name="password" id="id_password_enter" minlength="2" maxlength="30" required>
                    ${errorLoginPassMessage}
                <hr>
                <button type="submit" class="btn btn-outline-dark">Sign In</button>
                <hr>
                <label><a href="" style="color:white; font-size: 15px; text-decoration: none;">Forgot password?</a></label>
                <label><a href="" style="color:white; font-size: 15px; text-decoration: none;">Create an account?</a></label>
            </div>
        </form>

    </c:when>
    <c:otherwise>
        <div class="form-inner">
            <h6 class="text-center" style="color:black"> Спасибо что вы с нами ${user.getUserName()}! </h6>
        </div>
        <hr>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card h-100 border-dark bg-back">
                    <img src="${pageContext.request.contextPath}/static/core/img/login-start.jpg" class="card-img-top" alt="...">
                    <div class="card-header"><h4>Ближайшие старты и не только</h4></div>
                    <div class="card-body">
                        <h4 class="card-text">Мы будем оповещать вас: </h4>
                        <ul>
                            <li>О начале старта наших курсов.</li>
                            <li>Об открытии новых курсов и специализаций.</li>
                            <li>О проводимых акциях и скидках на наши курсы.</li>
                            <li>О преимуществах наших курсов и успехах наших выпускников.</li>
                        </ul>
                        <hr>
                        <a href="" class="btn btn-outline-dark ">Наши курсы</a>
                    </div>

                </div>
            </div>
            <div class="col">
                <div class="card h-100 border-dark bg-back">
                    <img src="${pageContext.request.contextPath}/static/core/img/blog.jpg" class="card-img-top" alt="...">
                    <div class="card-header"><h4>Наш блог</h4></div>
                    <div class="card-body ">
                        <h6 class="card-text" style="font-size: 20px;">Мы рады делиться с Вами всеми секретами для создания идеального образа как на каждый
                            день, так и для любого праздника! </h6>
                        <hr>
                        <h6 class="card-text" style="font-size: 20px;">Тонкости нанесения макияжа, подборка актуальных причесок на каждый день,
                            секреты фотокопирования и многое другое Вы найдете в нашем блоге.</h6>
                        <hr>
                        <a href="" class="btn btn-outline-dark " >Наш блог</a>
                    </div>
                </div>
            </div>
            <div class="col">
            <div class="card h-100 border-dark bg-back">
                <img src="${pageContext.request.contextPath}/static/core/img/comments.jpg" class="card-img-top" alt="...">
                <div class="card-header"><h4>Оставляйте ваши отзывы</h4></div>
                <div class="card-body">
                    <h6 class="card-text" style="font-size: 20px;">Вы сможете делиться Вашими впечатлениями о нашей работе, услугах,
                        пожеланиями по курсам и т.д. Ваша оценка очень важна для нас. </h6>
                    <hr>
                    <h6 class="card-text" style="font-size: 20px;">Мы постоянно усовершенствуем себя, реагируя
                        на Ваши пожелания и отзывы. И наш коллектив, предлагает сотрудничать вместе — оставляйте Ваши отзывы на сайте,
                        чтобы мы могли организовать работу нашей школы на высоком уровне.</h6>
                </div>
            </div>
            </div>
        </div>

    </c:otherwise>
</c:choose>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
