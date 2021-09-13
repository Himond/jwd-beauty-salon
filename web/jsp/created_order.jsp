<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>Title</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back" style="padding-top:50px;padding-bottom:50px">
    <div class="row">
        <div class="col-sm">
        </div>

        <div class="col-8">
            <center><h3>Запись на услугу</h3></center>
            <hr>

            <center><table class="table_blur">
                <thead>
                <tr>
                    <th >Имя</th>
                    <th>Фамилия</th>
                    <th>Электронный адрес </th>
                    <th >Телефон</th>
                    <th>Номер заказа</th>
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
            <center><h3>Спасибо!</h3></center>
            <center><h3>Ваш заказ был успешно сохранен. Наш менеджер свяжется с вами в ближайшее время!</h3></center>

        </div>
        <div class="col-sm">
        </div>
    </div>


</div></div>
<jsp:include page="footer.jsp"/>
</body>
</html>
