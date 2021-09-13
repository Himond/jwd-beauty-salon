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
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm">
            </div>

            <div class="col-8">
                <center><h3>Корзина покупок</h3></center>
                <hr>

                <center><table class="table_blur">
                    <thead>
                    <tr>
                        <th colspan="2">Услуга</th>
                        <th>Удалить</th>
                        <th>Цена</th>
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
                            <td><a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/controller?command=remove_product&current_product_id=${product.id()}">Удалить</a></td>
                            <td class="num">${product.price()} BYN</td>
                        </tr>
                    </c:forEach>


                    <c:if test="${not empty sessionScope.cart.getCoupon()}">
                        <tr class="subtotal">
                            <td>Цена до скидки</td>
                            <td colspan="2"></td>
                            <td class="num">${sessionScope.cart.getTotalPrice()} BYN</td>
                        </tr>
                        <tr>
                            <td>
                                "${sessionScope.cart.getCoupon().code()}" купон
                                (скидка ${sessionScope.cart.getCoupon().discount()} %)
                            </td>
                            <td colspan="2"></td>
                            <td class="num neg">
                                - ${sessionScope.cart.getTotalPrice() - sessionScope.cart.getTotalPriceAfterDiscount()} BYN
                            </td>
                        </tr>
                    </c:if>

                    <tr class="total">
                        <td>Цена со скидкой</td>
                        <td colspan="2"></td>
                        <td class="num">
                            ${sessionScope.cart.getTotalPrice()} BYN
                        </td>
                    </tr>

                    </tbody>

                </table></center>
                <hr>
                <center><form action="{% url 'apply' %}" method="post">
                    <label >Скидочный купон:</label>
                    <button class="btn btn-outline-dark" type="submit" value="Apply">Применить</button>
                </form></center>
                <hr>
                <center><h3>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_shop_page" class="btn btn-outline-success">Продолжить покупки</a>
                        <c:if test="${sessionScope.cart.getTotalPrice() != 0}">
                            <c:choose>
                                <c:when test="${not empty user}">
                                    <a href="${pageContext.request.contextPath}/controller?command=created_order" class="btn btn-outline-info">
                                        Оформить заказ
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/controller?command=go_to_signup_page" class="btn btn-outline-info">
                                        Зарегистрироваться
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
