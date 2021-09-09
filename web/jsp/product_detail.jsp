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

            <div class="card mb-3" style="max-width: 1000px;">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <c:choose>
                            <c:when test="${empty product.image()}">
                                <img class="card-img" src="${pageContext.request.contextPath}/static/core/img/course.jpg"  alt="...">
                            </c:when>
                            <c:otherwise>
                                <img class="card-img"  src="${pageContext.request.contextPath}/uploads/${product.image()}"  alt="..." >

                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <div class="card-header"><h3 class="card-title">{{ product.name }}</h3></div>
                            <i class="card-text"> {{ product.description|linebreaksbr }}</i>

                            <h6>Время процедуры: {{product.time}} мин.</h6>
                            <div class="card-footer center-block text-center" style="padding: 10px"><big class=" text-black">
                                 Цена: {{ product.price }} BYN
                                    <form action="{% url 'cart_add' product.id %}" method="post">
                                        {{ cart_product_form }}
                                        {% csrf_token %}
                                        <button type="submit" class="btn btn-outline-success" value="Add to cart"> <i class="fas fa-shopping-cart"></i> В корзину</button>
                                    </form>
                            </big>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm">
        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
