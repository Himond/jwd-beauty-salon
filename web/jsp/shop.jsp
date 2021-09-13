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
    <title><fmt:message key="shop.page.title"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid body-back" style="padding-top:20px;padding-bottom:20px">
    <div class="container center-block text-center">
        <h5 style="font-size: 35px;"><fmt:message key="shop.page.categories"/></h5>
        <hr>
        <div class="btn-group" role="group" aria-label="Basic example">
            <input type="hidden" name="current_category" value="all_category">
            <a href="${pageContext.request.contextPath}/controller?command=go_to_shop_page" class="btn btn-outline-dark"><fmt:message key="shop.page.button.categories"/></a>

            <c:forEach var="category" items="${categoryList}">
                <input type="hidden" name="current_category" value=${category.getName()}>
                <a href="${pageContext.request.contextPath}/controller?command=go_to_shop_page&current_category=${category.getName()}"  class="btn btn-outline-dark">${category.getName()}</a>
            </c:forEach>
        </div>
        <hr>
        <h3>
            <c:choose>
            <c:when test="${not empty currentCategory}">
                <h5 style="font-size: 35px;">${currentCategory}</h5>
            </c:when>
            <c:otherwise>
                <h5 style="font-size: 35px;"><fmt:message key="shop.page.services"/></h5>
            </c:otherwise>
            </c:choose>
        </h3>
        ${productNotFound}
    </div>


    <c:forEach var="product" items="${productList}">
    <div class="container center-block">
        <div class="projcard-container">
            <div class="projcard projcard-blue">
                <div class="projcard-innerbox text-left ">
                    <a href="${pageContext.request.contextPath}/controller?command=product_detail&current_product_id=${product.id()}">
                        <c:choose>
                            <c:when test="${empty product.image()}">
                                <img class="projcard-img" src="${pageContext.request.contextPath}/static/core/img/course.jpg"  alt="...">
                            </c:when>
                            <c:otherwise>
                                <img class="projcard-img"  src="${pageContext.request.contextPath}/uploads/${product.image()}"  alt="..." >

                            </c:otherwise>
                        </c:choose>
                    </a>
                    <div class="projcard-textbox">
                        <div class="projcard-title text-left"><a href="${pageContext.request.contextPath}/controller?command=product_detail&current_product_id=${product.id()}" style="color: black; text-decoration: none;">${product.name()}</a></div>
                        <div class="projcard-subtitle text-left"><i class="far fa-clock"> ${product.serviceTime()} <fmt:message key="shop.page.time"/></i></div>
                        <div class="projcard-bar"></div>
                        <div class="projcard-description text-left">${product.description()}</div>
                        <div class="projcard-tagbox">
                            <span class="projcard-tag"><b><fmt:message key="shop.page.price"/> ${product.price()} <fmt:message key="shop.page.byn"/></b></span>
                            <span class="projcard-tag" style="color:red"><fmt:message key="shop.page.discount"/></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
</div>

<div class="container-fluid bg-wall" style="padding-top:20px;padding-bottom:20px; ">
    <center><h5 style="font-size: 35px;"><b><fmt:message key="shop.page.gallery"/></b></h5></center>


    <div class="container">
        <hr>
        <div class="gallery">
            <img src="${pageContext.request.contextPath}/static/core/img/11.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/12.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/13.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/14.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/15.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/16.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/17.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/18.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/19.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/20.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/21.jpg" alt="" width="100%" height="auto" class="gallery-img" />
            <img src="${pageContext.request.contextPath}/static/core/img/22.jpg" alt="" width="100%" height="auto" class="gallery-img" />
        </div>
        <hr>
    </div>

    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
