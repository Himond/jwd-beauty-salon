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
    <title><fmt:message key="forgotPassword.page.title"/></title>
    <mytag:image/>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid body-back text-center" style="padding-top:50px;padding-bottom:50px">
        <form class="transparent center-block" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="forgot_password">
            <div class="form-inner">
                <h6><fmt:message key="forgotPassword.page.title"/></h6>
                <br/>
                <label for="email" ><fmt:message key="forgotPassword.page.rowOld"/></label>
                <input type="email" name="email" value="${requestScope.email}" pattern="^(?=.{3,30}$)[^\s]+@[^\s]+\.[^\s]+$" id="email" placeholder="example@example.com" required>
                ${emailNotExists}
                <hr>
                <button type="submit" class="btn btn-outline-dark"><fmt:message key="forgotPassword.button"/></button>
            </div>
        </form>
</div>


<jsp:include page="footer.jsp"/>
</body>
</html>
