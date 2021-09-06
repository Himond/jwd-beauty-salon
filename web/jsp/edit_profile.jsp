<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="editProfile.page.title"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back text-center" style="padding-top:50px;padding-bottom:50px">
    <form class="transparent center-block" method="POST" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
        <input type="hidden" name="command" value="edit_profile">
        <div class="form-inner">
            <h6><fmt:message key="editProfile.page.rowOne"/></h6>
            <br/>
            <label for="firstname_edit"><fmt:message key="placeholder.name.firstName"/></label>
            <input type="text" name="first_name" value="${user.firstName()}" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$" id="firstname_edit">
            <label for="lastname_edit"><fmt:message key="placeholder.name.lastName"/></label>
            <input type="text" name="last_name" value="${user.lastName()}" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$"  id="lastname_edit">
            <label for="phone_edit"><fmt:message key="placeholder.name.phone"/></label>
            <input type="tel" name="phone" pattern="^\+\d{12}$" value="${user.phone()}" id="phone_edit" placeholder="+375291234567">
            <label for="email_edit"><fmt:message key="placeholder.name.email"/></label>
            <input type="email" name="email" pattern="^(?=.{3,30}$)[^\s]+@[^\s]+\.[^\s]+$" value="${user.email()}" id="email_edit" placeholder="example@example.com">
            <label for="birth_edit"><fmt:message key="placeholder.name.birth"/></label>
            <input type="date" name="birthday" pattern="^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$" value="${user.dateOfBirth()}"  id="birth_edit">
            <label for="id_photo"><fmt:message key="placeholder.name.photo"/></label>
            <div class="custom-file">
                <input type="file" class="custom-file-input" name="image" id="id_photo">
            </div>
            ${wrongDataSignUp}
            <hr>
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="editProfile.button"/></button>
        </div>
    </form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
