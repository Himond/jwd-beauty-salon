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
    <title><fmt:message key="cart.page.title"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid body-back" style="padding-top:50px;padding-bottom:50px">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm">
            </div>

            <div class="col-8">

                <center><h3><fmt:message key="cart.page.basket"/></h3></center>
                <hr>
                <center><table class="table_blur">
                    <thead>
                    <tr>
                        <th colspan="2"><fmt:message key="cart.page.tableOne"/></th>
                        <th><fmt:message key="cart.page.tableTwo"/></th>
                        <th><fmt:message key="cart.page.tableThree"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="product" items="${sessionScope.cart.getServices()}">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=product_detail&current_product_id=${product.id()}">
                                    <c:choose>
                                        <c:when test="${empty currentProduct.image()}">
                                            <img class="mr-3" style="width: 5rem;" src="${pageContext.request.contextPath}/static/core/img/no_image.jpg"  alt="...">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="mr-3" style="width: 5rem;"  src="${pageContext.request.contextPath}/uploads/${currentProduct.image()}"  alt="..." >
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </td>

                            <td><h6>${product.name()}</h6></td>
                            <td><a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/controller?command=remove_product&current_product_id=${product.id()}"><fmt:message key="cart.page.tableTwo"/></a></td>
                            <td class="num">${product.price()} <fmt:message key="shop.page.byn"/></td>
                        </tr>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${not empty sessionScope.cart.getCoupon()}">
                            <tr class="subtotal">
                                <td><fmt:message key="cart.create.discount.price"/></td>
                                <td colspan="2"></td>
                                <td class="num">${sessionScope.cart.getTotalPrice()} <fmt:message key="shop.page.byn"/></td>
                            </tr>
                            <tr>
                                <td>
                                    "${sessionScope.cart.getCoupon().getCode()}" <fmt:message key="cart.create.coupon"/>
                                    (<fmt:message key="cart.create.discount"/> ${sessionScope.cart.getCoupon().getDiscount()} %)
                                </td>
                                <td colspan="2"></td>
                                <td class="num neg">
                                    - ${sessionScope.cart.getTotalPrice() - sessionScope.cart.getTotalPriceAfterDiscount()} <fmt:message key="shop.page.byn"/>
                                </td>
                            </tr>
                            <tr class="total">
                                <td><fmt:message key="cart.page.row"/></td>
                                <td colspan="2"></td>
                                <td class="num">
                                        ${sessionScope.cart.getTotalPriceAfterDiscount()} <fmt:message key="shop.page.byn"/>
                                </td>
                            </tr>

                        </c:when>
                        <c:otherwise>
                            <tr class="total">
                                <td><fmt:message key="cart.page.row"/></td>
                                <td colspan="2"></td>
                                <td class="num">
                                        ${sessionScope.cart.getTotalPrice()} <fmt:message key="shop.page.byn"/>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                    </tbody>

                </table></center>
                <center>${couponNotFound}</center>
                <hr>
                <center><form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="add_coupon">
                    <label for="id_coupon"><fmt:message key="cart.page.coupon"/></label>
                    <input type="text" name="coupon" pattern="^[a-zA-Z0-9_]{6}$" id="id_coupon" required>
                    <button class="btn btn-outline-dark" type="submit" value="Apply"><fmt:message key="cart.button.accept"/></button>
                </form></center>
                <hr>
                <center><h3>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_shop_page" class="btn btn-outline-success"><fmt:message key="cart.button.continue"/></a>
                        <c:if test="${sessionScope.cart.getTotalPrice() != 0}">
                            <c:choose>
                                <c:when test="${not empty user}">
                                    <a href="${pageContext.request.contextPath}/controller?command=created_order" class="btn btn-outline-info">
                                        <fmt:message key="cart.page.order"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/controller?command=go_to_signup_page" class="btn btn-outline-info">
                                        <fmt:message key="button.name.register"/>
                                    </a>
                                </c:otherwise>
                            </c:choose>

                        </c:if>
                    </div>
                </h3></center>

            </div>
            <div class="col-sm">
            </div>
        </div>

    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
