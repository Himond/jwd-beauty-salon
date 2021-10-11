<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="pagecontent"/>
<%@ taglib uri="customtag" prefix="mytag" %>
<html>
<head>
    <title><fmt:message key="cart.create.title"/></title>
    <mytag:image/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back" style="padding-top:50px;padding-bottom:50px">
    <div class="row">
        <div class="col-sm">
        </div>

        <div class="col-8">
            <center><h3><fmt:message key="cart.create.rowOne"/></h3></center>
            <hr>

            <center><table class="table_blur">
                <thead>
                <tr>
                    <th><fmt:message key="dashboard.page.name"/></th>
                    <th><fmt:message key="dashboard.page.surname"/></th>
                    <th><fmt:message key="dashboard.page.email"/></th>
                    <th><fmt:message key="dashboard.page.phone"/></th>
                    <th><fmt:message key="cart.create.number"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${user.firstName()}
                    </td>
                    <td>
                        ${user.lastName()}
                    </td>
                    <td>${user.email()}</td>
                    <td>${user.phone()}</td>
                    <td>${orderId}</td>
                </tr>
                </tbody>
            </table></center>
            <hr>
            <center><h3><fmt:message key="cart.create.rowTwo"/></h3></center>
            <center><h3><fmt:message key="cart.create.rowThree"/></h3></center>

        </div>
        <div class="col-sm">
        </div>
    </div>


</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
