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
    <mytag:image/>
    <title><fmt:message key="button.name.currentMasterOrders"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back center-block" style="padding-top:50px;padding-bottom:50px">
    <div class="row">
        <div class="col-sm">
        </div>

        <div class="col-8">
            <center><h3><fmt:message key="master.orders.title"/></h3></center>
            ${operationFail}
            <hr>
            <center><table class="table_blur">
                <thead>
                <tr>
                    <th><fmt:message key="master.orders.number"/></th>
                    <th><fmt:message key="master.orders.clientName"/></th>
                    <th><fmt:message key="master.orders.clientSurname"/></th>
                    <th><fmt:message key="master.orders.clientPhone"/></th>
                    <th><fmt:message key="master.orders.product"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${newOrderItem}" var="entry">

                    <c:if test="${entry.value.get(1).isActive()}">
                        <tr>
                            <td>
                                    ${entry.value.get(1).id()}
                            </td>
                            <td>
                                    ${entry.value.get(0).firstName()}
                            </td>
                            <td>
                                    ${entry.value.get(0).lastName()}
                            </td>
                            <td>
                                    ${entry.value.get(0).phone()}
                            </td>
                            <td>
                                    ${entry.value.get(1).products().get(0).name()}
                            </td>
                        </tr>
                    </c:if>

                </c:forEach>

                </tbody>
            </table></center>
        </div>
        <div class="col-sm">
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
