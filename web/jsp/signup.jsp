<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title><fmt:message key="title.signUp"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back text-center" style="padding-top:50px;padding-bottom:50px">
<c:choose>
    <c:when test="${empty user}">
        <form class="transparent center-block" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="sign_up">
            <div class="form-inner">
                <h6><fmt:message key="salon.signIn.register"/></h6>
                <br/>
                <label for="username_sign_up"><fmt:message key="placeholder.name.username"/></label>
                <input type="text" name="username" value="${requestScope.mapData.get("username")}" pattern="^[a-zA-Z0-9_]{4,16}$" id="username_sign_up" required>
                <label for="firstname_sign_up"><fmt:message key="placeholder.name.firstName"/></label>
                <input type="text" name="first_name" value="${requestScope.mapData.get("first_name")}" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$" id="firstname_sign_up" required>
                <label for="lastname_sign_up"><fmt:message key="placeholder.name.lastName"/></label>
                <input type="text" name="last_name" value="${requestScope.mapData.get("last_name")}" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$"  id="lastname_sign_up" required>
                <label for="phone_sign_up"><fmt:message key="placeholder.name.phone"/></label>
                <input type="tel" name="phone" pattern="^\+\d{12}$" value="${requestScope.mapData.get("phone")}" id="phone_sign_up" placeholder="+375291234567" required>
                <label for="email_sign_up"><fmt:message key="placeholder.name.email"/></label>
                <input type="email" name="email" pattern="^(?=.{3,30}$)[^\s]+@[^\s]+\.[^\s]+$" value="${requestScope.mapData.get("email")}" id="email_sign_up" placeholder="example@example.com" required>
                <label for="password_sign_up"><fmt:message key="placeholder.name.password"/></label>
                <input  type="password" name="password" pattern="^.{4,18}$" value="${requestScope.mapData.get("password")}" id="password_sign_up" minlength="2" maxlength="30" required>
                <label for="password_sign_up_rep"><fmt:message key="placeholder.name.passwordRe"/></label>
                <input  type="password" name="password_rep" pattern="^.{4,18}$" minlength="2" maxlength="30" id="password_sign_up_rep" required>
                    ${wrongDataSignUp}
                    ${errorPasswordSignUp}
                    ${errorLoginExists}
                <hr>
                <button type="submit" class="btn btn-outline-dark"><fmt:message key="button.name.register"/></button>
            </div>
        </form>
    </c:when>
    <c:otherwise>

        <div class="card border-light mb-3 center-block" style="max-width: 40rem;">
            <div class="card-header"><h3><fmt:message key="signUp.page.rowOne"/></h3></div>
            <div class="card-body">
                <h5 class="card-title"><fmt:message key="signUp.page.rowTwo"/></h5>
                <h3>${user.firstName()} ${user.lastName()}.</h3>
                <hr>
                <h5 class="card-text"><fmt:message key="signUp.page.rowThree"/></h5>
            </div>
            <div class="card-footer text-muted">
                <button type="submit" class="btn btn-outline-dark"><fmt:message key="button.name.editPersonalData"/></button>
            </div>
        </div>

    </c:otherwise>
</c:choose>


</div>




<jsp:include page="footer.jsp"/>
</body>
</html>
