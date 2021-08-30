<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.08.2021
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
    <link rel="shortcut icon" href="../static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back text-center" style="padding-top:50px;padding-bottom:50px">
<c:choose>
    <c:when test="${empty user}">

        <form class="transparent center-block" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="sign_up">
            <div class="form-inner">
                <h6>Sign up to Salon</h6>
                <br/>
                <label for="username_sign_up">Login</label>
                <input type="text" name="username" maxlength="30" id="username_sign_up" placeholder="example@example.com" required>
                <label for="firstname_sign_up">Name</label>
                <input type="text" name="first_name" maxlength="20" id="firstname_sign_up" required>
                <label for="lastname_sign_up">Surname</label>
                <input type="text" name="last_name" maxlength="20" id="lastname_sign_up" required>
                <label for="email_sign_up">Email</label>
                <input type="email" name="email"  id="email_sign_up" placeholder="example@example.com" required>
                <label for="phone_sign_up">Phone</label>
                <input type="tel" name="phone"  id="phone_sign_up" placeholder="+375291234567" required>
                <label for="password_sign_up">Password</label>
                <input  type="password" name="password" id="password_sign_up" minlength="2" maxlength="30" required>
                <label for="password_sign_up_rep">Repeat password</label>
                <input  type="password" name="password_rep" minlength="2" maxlength="30" id="password_sign_up_rep" required>
                    ${errorPasswordSignUp || errorLoginExists}
                <hr>
                <button type="submit" class="btn btn-outline-dark">Sign Up</button>
            </div>
        </form>
    </c:when>
    <c:otherwise>
        <div class="card mb-3 text-center">
            <img src="${pageContext.request.contextPath}/static/core/img/registration.jpg" class="card-img-top" alt="...">
            <div class="card-header"><h6 class="card-title" style="font-size:40px">Вы уже зарегистрированны!</h6></div>
            <div class="card-body">
                <h6 class="card-text" style="font-size:30px">Спасибо что Вы с нами!</h6>
            </div>
            <div class="card-footer text-muted">
                <h6 class="card-text" style="font-size:30px"><small class="text-muted">Вернуться на <a href="">главную страницу!</a></small></h6>
            </div>
        </div>

    </c:otherwise>
</c:choose>


</div>




<jsp:include page="footer.jsp"/>
</body>
</html>
