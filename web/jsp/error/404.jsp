<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="customtag" prefix="mytag" %>
<html>
<head>
    <title>404</title>
    <mytag:image/>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div class="container-fluid bg-class text-center" style="padding-top:200px;padding-bottom:200px">
    <h1 class="tx-cool">404</h1>
    <h1 class="tx-cool"><fmt:message key="error.404"/></h1>
</div>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>
