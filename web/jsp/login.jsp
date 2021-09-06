
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>

    <title><fmt:message key="title.signIn"/></title>
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
                <h6><fmt:message key="salon.signIn.enter"/></h6>
                <label for="id_username_enter"><fmt:message key="placeholder.name.username"/></label>
                <input type="text" name="username" pattern="^[a-zA-Z0-9_]{4,16}$" id="id_username_enter" required>
                <label for="id_password_enter"><fmt:message key="placeholder.name.password"/></label>
                <input  type="password" name="password" pattern="^.{4,18}$" id="id_password_enter" minlength="2" maxlength="30" required>
                    ${errorLoginPassMessage}
                    ${emailSent}
                <hr>
                <button type="submit" class="btn btn-outline-dark"><fmt:message key="salon.signIn.enter"/></button>
                <hr>
                <label><a href="${pageContext.request.contextPath}/jsp/forgot_password.jsp" style="color:white; font-size: 15px; text-decoration: none;"><fmt:message key="button.name.forgotPassword"/></a></label>
                <label><a href="${pageContext.request.contextPath}/jsp/signup.jsp.jsp" style="color:white; font-size: 15px; text-decoration: none;"><fmt:message key="button.name.createAnAccount"/></a></label>
            </div>
        </form>

    </c:when>
    <c:otherwise>
        <div class="form-inner">
            <h6 class="text-center" style="color:black"><fmt:message key="signIn.page.rowOne"/> ${user.firstName()}!</h6>
        </div>
        <hr>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card h-100 border-dark bg-back">
                    <img src="${pageContext.request.contextPath}/static/core/img/login-start.jpg" class="card-img-top" alt="...">
                    <div class="card-header"><h4><fmt:message key="signIn.page.rowTwo"/></h4></div>
                    <div class="card-body">
                        <h4 class="card-text"><fmt:message key="signIn.page.rowThree"/></h4>
                        <ul>
                            <li><fmt:message key="signIn.page.rowFour"/></li>
                            <li><fmt:message key="signIn.page.rowFive"/></li>
                            <li><fmt:message key="signIn.page.rowSix"/></li>
                            <li><fmt:message key="signIn.page.rowSeven"/></li>
                        </ul>
                        <hr>
                        <a  href="Controller?command=go_to_item_page_command" class="btn btn-outline-dark "><fmt:message key="button.name.ourServices"/></a>
                    </div>

                </div>
            </div>
            <div class="col">
                <div class="card h-100 border-dark bg-back">
                    <img src="${pageContext.request.contextPath}/static/core/img/blog.jpg" class="card-img-top" alt="...">
                    <div class="card-header"><h4><fmt:message key="signIn.page.rowEight"/></h4></div>
                    <div class="card-body ">
                        <h6 class="card-text" style="font-size: 20px;"><fmt:message key="signIn.page.rowNine"/></h6>
                        <hr>
                        <h6 class="card-text" style="font-size: 20px;"><fmt:message key="signIn.page.rowTen"/></h6>
                        <hr>
                    </div>
                </div>
            </div>
            <div class="col">
            <div class="card h-100 border-dark bg-back">
                <img src="${pageContext.request.contextPath}/static/core/img/comments.jpg" class="card-img-top" alt="...">
                <div class="card-header"><h4><fmt:message key="signIn.page.rowOOne"/></h4></div>
                <div class="card-body">
                    <h6 class="card-text" style="font-size: 20px;"><fmt:message key="signIn.page.rowOTwo"/></h6>
                    <hr>
                    <h6 class="card-text" style="font-size: 20px;"><fmt:message key="signIn.page.rowOThree"/></h6>
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
