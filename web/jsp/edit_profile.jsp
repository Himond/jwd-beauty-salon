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
    <title><fmt:message key="editProfile.page.title"/></title>
    <mytag:image/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back text-center" style="padding-top:50px;padding-bottom:50px">
    <form class="transparent center-block" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="edit_profile">
        <div class="form-inner">
            <h6><fmt:message key="editProfile.page.rowOne"/></h6>
            <br/>
            <input type="hidden" type="text" name="user_id" value="${user.userId()}">
            <label for="firstname_edit"><fmt:message key="placeholder.name.firstName"/></label>
            <input type="text" name="first_name" value="${user.firstName()}" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$" id="firstname_edit">
            <label for="lastname_edit"><fmt:message key="placeholder.name.lastName"/></label>
            <input type="text" name="last_name" value="${user.lastName()}" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$"  id="lastname_edit">
            <label for="phone_edit"><fmt:message key="placeholder.name.phone"/></label>
            <input type="tel" name="phone" pattern="^\+\d{12}$" value="${user.phone()}" id="phone_edit" placeholder="+375291234567">
            <label for="email_edit"><fmt:message key="placeholder.name.email"/></label>
            <input type="email" name="email" pattern="^(?=.{3,30}$)[^\s]+@[^\s]+\.[^\s]+$" value="${user.email()}" id="email_edit" placeholder="example@example.com">
            <label for="birth_edit"><fmt:message key="placeholder.name.birth"/></label>
            <input type="date" name="date_of_birth" value="${user.dateOfBirth()}"  id="birth_edit" required>
            ${wrongDataSignUp}
            ${editProfileError}
            <hr>
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="editProfile.button"/></button>
        </div>
    </form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
