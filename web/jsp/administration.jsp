<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <link rel="shortcut icon" href="../static/core/img/ico.png" type="image/png">
    <title><fmt:message key="admin.orders.title"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back center-block" style="padding-top:50px;padding-bottom:50px">
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <h5><fmt:message key="admin.orders.category"/></h5>
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="add_category">
                <div class="mb-3">
                    <label for="category" class="form-label"><fmt:message key="admin.orders.category"/></label>
                    <input type="text" class="form-control"  name="new_category_name" id="category" aria-describedby="categoryHelp">
                    <div id="categoryHelp" class="form-text"><fmt:message key="admin.orders.categoryHelp"/></div>
                </div>
                ${wrongCategory}
                <button type="submit" class="btn btn-primary"><fmt:message key="admin.orders.categoryButton"/></button>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>
    <hr>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <h5><fmt:message key="admin.orders.coupon"/></h5>
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="create_coupon">
                <div class="mb-3">
                    <label for="coupon" class="form-label"><fmt:message key="admin.orders.coupon"/></label>
                    <input type="text" class="form-control" pattern="^[a-zA-Z0-9_]{6}$" name="coupon_code" id="coupon" aria-describedby="couponHelp">
                    <div id="couponHelp" class="form-text"><fmt:message key="admin.orders.couponHelp"/></div>
                </div>
                <div class="mb-3">
                    <label for="discount" class="form-label"><fmt:message key="admin.orders.couponDiscount"/></label>
                    <input type="text" class="form-control"  name="coupon_discount" id="discount" aria-describedby="couponDiscount">
                    <div id="couponDiscount" class="form-text"><fmt:message key="admin.orders.couponDiscountHelp"/></div>
                </div>
                <div class="mb-3">
                    <label for="coupon_add" class="form-label"><fmt:message key="admin.orders.couponValid"/></label>
                    <input type="date" class="form-control" name="coupon_valid_to" id="coupon_add" required>
                </div>
                ${wrongCoupon}
                <button type="submit" class="btn btn-primary"><fmt:message key="admin.orders.couponButton"/></button>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>


    <hr>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <h5><fmt:message key="admin.product.title"/></h5>
            <form method="POST" action="${pageContext.request.contextPath}/controller" enctype="multipart/form-data">
                <input type="hidden" name="command" value="add_product">
                <div class="mb-3">
                    <br/>
                    <div class="mb-3">
                        <label for="product_name" class="form-label"><fmt:message key="admin.product.name"/></label>
                        <input type="text"  class="form-control" name="new_product_name" id="product_name" required>
                    </div>
                    <div class="mb-3">
                        <select class="form-select" aria-label="Default select example" name="select_category_id">
                                <option disabled><fmt:message key="admin.product.select"/></option>
                                <c:forEach var="category" items="${categoryList}">
                                    <option value="${category.getId()}">${category.getName()}</option>
                                </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="product_description" class="form-label"><fmt:message key="admin.product.description"/></label>
                        <input type="text" class="form-control" name="new_product_description" id="product_description" required>
                    </div>
                    <div class="mb-3">
                        <label for="product_price" class="form-label"><fmt:message key="cart.page.row"/></label>
                        <input type="text" class="form-control" name="new_product_price"  id="product_price" required>
                    </div>
                    <div class="mb-3">
                        <label for="product_service_time" class="form-label"><fmt:message key="admin.product.time"/></label>
                        <input type="text" class="form-control" name="product_service_time"  id="product_service_time"  required>
                    </div>
                    <div class="mb-3">
                        <label for="id_photo" class="form-label"><fmt:message key="placeholder.name.photo"/></label>
                        <div class="custom-file">
                        <input type="file"  class="form-control" class="custom-file-input" name="image" id="id_photo" required>
                        </div>
                    </div>
                    ${operationSuccess}
                    ${operationFail}
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="admin.product.button"/></button>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>
    <hr>

    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <h5><fmt:message key="admin.master.title"/></h5>
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="sign_up_master">

                <div class="mb-3">
                    <label for="username_sign_up"  class="form-label"><fmt:message key="placeholder.name.username"/></label>
                    <input type="text" class="form-control" name="username" value="${requestScope.mapData.get("username")}" pattern="^[a-zA-Z0-9_]{4,16}$" id="username_sign_up" required>
                </div>
                <div class="mb-3">
                    <label for="firstname_sign_up"  class="form-label"><fmt:message key="placeholder.name.firstName"/></label>
                    <input type="text" class="form-control" name="first_name" value="${requestScope.mapData.get("first_name")}" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$" id="firstname_sign_up" required>
                </div>
                <div class="mb-3">
                    <label for="lastname_sign_up"  class="form-label"><fmt:message key="placeholder.name.lastName"/></label>
                    <input type="text" class="form-control" name="last_name" value="${requestScope.mapData.get("last_name")}" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$"  id="lastname_sign_up" required>
                </div>
                <div class="mb-3">
                    <label for="email_sign_up"  class="form-label"><fmt:message key="placeholder.name.email"/></label>
                    <input type="email" class="form-control" name="email" pattern="^(?=.{3,30}$)[^\s]+@[^\s]+\.[^\s]+$" value="${requestScope.mapData.get("email")}" id="email_sign_up" placeholder="example@example.com" required>
                </div>
                <div class="mb-3">
                    <label for="product_description" class="form-label"><fmt:message key="admin.master.description"/></label>
                    <input type="text" class="form-control" name="new_master_description" id="master_description" required>
                </div>
                <div class="mb-3">
                    <select class="form-select" aria-label="Default select example" name="select_position">
                        <option disabled><fmt:message key="admin.master.select"/></option>
                        <c:forEach var="position" items="${positionList}">
                            <option value="${position.getPosition()}">${position.getPosition()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="password_sign_up" class="form-label"><fmt:message key="placeholder.name.password"/></label>
                    <input  type="password" class="form-control" name="password" pattern="^.{4,18}$" value="${requestScope.mapData.get("password")}" id="password_sign_up" minlength="2" maxlength="30" required>
                </div>
                <div class="mb-3">
                    <label for="password_sign_up_rep" class="form-label"><fmt:message key="placeholder.name.passwordRe"/></label>
                    <input  type="password" class="form-control"  name="password_rep" pattern="^.{4,18}$" minlength="2" maxlength="30" id="password_sign_up_rep" required>
                </div>
                ${errorPasswordSingUp}
                ${masterNotAdded}
                ${masterAddSuccess}
                <button type="submit" class="btn btn-primary"><fmt:message key="admin.master.button"/></button>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
