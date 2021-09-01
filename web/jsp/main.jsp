<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="main.page.title"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid bg-class text-center" style="padding-top:200px;padding-bottom:200px">

    <h1 class="tx-cool"><fmt:message key="main.page.mainRowOne"/></h1>
    <h1 class="tx-cool"><fmt:message key="main.page.mainRowTwo"/></h1>
    <i><h5 style="font-size: 25px;"><b><fmt:message key="main.page.mainRowThree"/></b></h5></i>
    ${session_id}
    ${current_page}

</div>
<div class="container-fluid bg-back-studio" style="padding-top:50px;padding-bottom:50px">



    <div class="content">
        <center><h5 style="font-size: 35px;"><b><fmt:message key="main.page.block.first"/></b></h5></center>
        <hr>

        <h6 style="font-size: 20px;"><i>
            <img src="${pageContext.request.contextPath}/static/core/img/stud2.jpg" alt="..." class="img-thumbnail rightimg" width="544" height="279">

            <img src="${pageContext.request.contextPath}/static/core/img/stud1.jpg" alt="..." class="img-thumbnail rightimg" width="544" height="279">
            <div style=" text-indent: 20px;"><fmt:message key="main.page.row.one"/></div>

            <ul><fmt:message key="main.page.row.two"/>
                <li><fmt:message key="main.page.row.three"/></li>
                <li><fmt:message key="main.page.row.four"/></li>
                <li><fmt:message key="main.page.row.five"/></li>
                <li><fmt:message key="main.page.row.six"/></li>
        </i></h6>

    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
