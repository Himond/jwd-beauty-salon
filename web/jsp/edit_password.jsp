<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="pagecontent"/>
<%@ taglib uri="customtag" prefix="mytag" %>
<html>
<head>
    <title><fmt:message key="changePassword.page.title"/></title>
    <mytag:image/>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid body-back text-center" style="padding-top:50px;padding-bottom:50px">
    <form class="transparent center-block" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="edit_password">
        <input type="hidden" name="user_id" value="${user.userId()}">
        <div class="form-inner">
            <h6><fmt:message key="changePassword.page.title"/></h6>
            <br/>
            <label for="old_password"><fmt:message key="changePassword.page.rowOld"/></label>
            <input  type="password" name="password" pattern="^.{4,18}$" id="old_password" minlength="2" maxlength="30" required>
            <label for="new_password"><fmt:message key="changePassword.page.rowNew"/></label>
            <input  type="password" name="new_password" pattern="^.{4,18}$" minlength="2" maxlength="30" id="new_password" required>
            <label for="new_password_rep"><fmt:message key="changePassword.page.rowNewRe"/></label>
            <input  type="password" name="password_rep" pattern="^.{4,18}$" minlength="2" maxlength="30" id="new_password_rep" required>
            ${editPasswordError}
            ${errorPasswordSignUp}
            <hr>
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="changePassword.button"/></button>
        </div>
    </form>
</div>


<jsp:include page="footer.jsp"/>
</body>
</html>
