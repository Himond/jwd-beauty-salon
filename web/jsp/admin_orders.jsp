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
  <title><fmt:message key="button.name.currentAdminOrders"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back center-block" style="padding-top:50px;padding-bottom:50px">
  <div class="row">
    <div class="col-sm">
    </div>

    <div class="col-8">
      <center><h5><fmt:message key="button.name.currentAdminOrders"/></h5></center>
      ${operationFail}
      <hr>
      <center><table class="table_blur">
        <thead>
        <tr>
          <th><fmt:message key="admin.orders.number"/></th>
          <th><fmt:message key="admin.orders.clientName"/></th>
          <th><fmt:message key="admin.orders.clientSurname"/></th>
          <th><fmt:message key="admin.orders.clientPhone"/></th>
          <th><fmt:message key="admin.orders.product"/></th>
          <th><fmt:message key="admin.orders.master"/></th>
          <th><fmt:message key="admin.orders.complete"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${newOrderItem}" var="entry">
          <tr>
            <td>
                ${entry.value.get(1).id()}
            </td>
            <td>
                ${entry.value.get(0).lastName()}
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
            <td>
              <c:choose>
                <c:when test="${not empty entry.value.get(2)}">
                  ${entry.value.get(2).firstName()} ${entry.value.get(2).lastName()} ${entry.value.get(2).position()}
                </c:when>
                <c:otherwise>
                  <form method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="add_master_in_order">
                    <input type="hidden" name="order_item_id" value=${entry.key}>
                    <div class="mb-3">
                      <br/>
                      <div class="mb-3">
                        <select class="form-select" aria-label="Default select example" name="select_master_id" required>
                          <option disabled><fmt:message key="admin.orders.select"/></option>
                          <c:forEach var="master" items="${masterList}">
                              <option value="${master.masterId()}">${master.firstName()} ${master.lastName()}</option>
                          </c:forEach>
                        </select>
                      </div>

                    </div>
                    <center><button type="submit" class="btn btn-primary"><fmt:message key="admin.master.button"/></button></center>
                  </form>
                </c:otherwise>
              </c:choose>
            </td>
            <td>
              <c:choose>
              <c:when test="${entry.value.get(1).isActive()}">
                <form method="POST" action="${pageContext.request.contextPath}/controller">
                  <input type="hidden" name="command" value="completed_order">
                  <input type="hidden" name="orderId" value=${entry.value.get(1).id()}>
                  <center><button type="submit" class="btn btn-success"><fmt:message key="admin.orders.completed"/></button></center>
                </form>
              </c:when>
              <c:otherwise>
                 Completed
              </c:otherwise>
              </c:choose>
            </td>
          </tr>
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
