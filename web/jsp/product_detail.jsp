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
    <title><fmt:message key="shop.page.detail.title"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid body-back center-block" style="padding-top:50px;padding-bottom:50px">
    <div class="row">

        <div class="col-sm">

        </div>

        <div class="col-8">

            <div class="card mb-3" style="max-width: 1000px;">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <c:choose>
                            <c:when test="${empty currentProduct.image()}">
                                <img class="card-img" src="${pageContext.request.contextPath}/static/core/img/no_image.jpg"  alt="...">
                            </c:when>
                            <c:otherwise>
                                <img class="card-img"  src="${pageContext.request.contextPath}/uploads/${currentProduct.image()}"  alt="..." >
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-md-8 center-block">
                        <div class="card-body">
                            <div class="card-header"><h3 class="card-title">${currentProduct.name()}</h3></div>
                            <i class="card-text">${currentProduct.description()}</i>
                            <h6><fmt:message key="shop.page.service.time"/> ${currentProduct.serviceTime()} <fmt:message key="shop.page.time"/></h6>
                            <div class="card-footer center-block text-center" style="padding: 10px"><big class=" text-black">
                                <fmt:message key="shop.page.price"/> ${currentProduct.price()} <fmt:message key="shop.page.byn"/>
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="add_to_cart"/>
                                        <input type="hidden" name="current_product_id" value=${currentProduct.id()}>
                                        <button type="submit" class="btn btn-outline-success" value="Add to cart"> <i class="fas fa-shopping-cart"></i> <fmt:message key="shop.page.service.button"/></button>
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
<div class="container-fluid body-back text-center" style="padding-top:20px;padding-bottom:20px;">
    <h5 style="font-size: 35px;"><b><fmt:message key="shop.page.service.review"/> ${currentProduct.name()}</b></h5>
    <hr>
    <c:choose>
        <c:when test="${currentReviewList.size() == 0}">
            <h6 style="font-size: 25px;" class="city-name"><fmt:message key="shop.page.service.emptyReview"/></h6>
        </c:when>
        <c:when test="${currentReviewList.size() == 1}">
            <c:forEach var="review" items="${currentReviewList}">
                <h6 style="font-size: 25px;" class="city-name">${review.getClientFirstName()} ${review.getClientLastName()}</h6>
                <i class="text-testimonial" >${review.getReview()}</i>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="owl-carousel block-items-review body-back" >
                <c:forEach var="review" items="${currentReviewList}">
                    <div class="item body-back">
                        <div class="inner-testimonial body-back">
                            <h6 style="font-size: 25px;" class="city-name">${review.getClientFirstName()} ${review.getClientLastName()}</h6>
                            <i class="text-testimonial" >${review.getReview()}</i>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div class="container-fluid body-back text-center center-block" style="padding-top:20px;padding-bottom:20px;">
<c:choose>
    <c:when test="${empty user}">
        <i><fmt:message key="shop.page.service.addReview"/> <a href="${pageContext.request.contextPath}/controller?command=go_to_signin_page"><fmt:message key="shop.page.service.enter"/></a> <fmt:message key="shop.page.service.orReview"/> <a href="${pageContext.request.contextPath}/controller?command=go_to_signup_page"><fmt:message key="shop.page.service.register"/></a>!</i>
    </c:when>
    <c:otherwise>
        <c:if test="${user.role() == 'CLIENT'}">
            <form class="transparent center-block" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="add_review">
                <div class="form-inner center-block">
                    <label for="message-com"><fmt:message key="shop.page.service.comment"/></label>
                    <input type="hidden" name="current_product_id" value=${currentProduct.id()}>
                    <input type="text" pattern="^[a-zA-Zа-яА-я0-9_.,!?]{5,100}$" name="review_body" rows="2" id="message-com" required>
                        ${wrongDataSignUp}
                    <button type="submit" class="btn btn-outline-dark btn-block btn-rounded" ><b><fmt:message key="shop.page.service.addComment"/></b></button>
                </div>
            </form>
        </c:if>
    </c:otherwise>
</c:choose>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
