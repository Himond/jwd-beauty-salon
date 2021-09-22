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
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid body-back center-block" style="padding-top:50px;padding-bottom:50px">
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <h5>Add new Category</h5>
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="add_category">
                <div class="mb-3">
                    <label for="category" class="form-label">Add new Category</label>
                    <input type="text" class="form-control"  name="new_category_name" id="category" aria-describedby="categoryHelp">
                    <div id="categoryHelp" class="form-text">You can add a new category.</div>
                </div>
                <button type="submit" class="btn btn-primary">add category</button>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>
    <hr>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <h5>Add new Coupon</h5>
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="create_coupon">
                <div class="mb-3">
                    <label for="coupon" class="form-label">Add new coupon</label>
                    <input type="text" class="form-control" pattern="^[a-zA-Z0-9_]{6}$" name="coupon_code" id="coupon" aria-describedby="couponHelp">
                    <div id="couponHelp" class="form-text">You can add a new coupon.</div>
                </div>
                <div class="mb-3">
                    <label for="discount" class="form-label">Discount</label>
                    <input type="text" class="form-control"  name="coupon_discount" id="discount" aria-describedby="couponDiscount">
                    <div id="couponDiscount" class="form-text">Add discount for coupon.</div>
                </div>
                <div class="mb-3">
                    <label for="coupon_add" class="form-label">Coupon valid to</label>
                    <input type="date" class="form-control" name="coupon_valid_to"  id="coupon_add" required>
                </div>
                <button type="submit" class="btn btn-primary">add coupon</button>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>



    <hr>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <h5>Add new Product</h5>
            <form method="POST" action="${pageContext.request.contextPath}/controller" enctype="multipart/form-data">
                <input type="hidden" name="command" value="add_product">
                <div class="mb-3">
                    <br/>
                    <div class="mb-3">
                        <label for="product_name" class="form-label">Product name</label>
                        <input type="text"  class="form-control" name="new_product_name" pattern="^[a-zA-Z0-9_]{4,16}$" id="product_name" required>
                    </div>
                    <div class="mb-3">
                        <select class="form-select" aria-label="Default select example">
                            <option selected>Open this select menu</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="product_description" class="form-label">Description</label>
                        <input type="text" class="form-control" name="new_product_description" pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$" id="product_description" required>
                    </div>
                    <div class="mb-3">
                        <label for="product_price" class="form-label">Price</label>
                        <input type="text" class="form-control" name="new_product_price"  pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F-\s]{2,30}$"  id="product_price" required>
                    </div>
                    <div class="mb-3">
                        <label for="product_service_time" class="form-label">Service time</label>
                        <input type="text" class="form-control" name="product_service_time" pattern="^\+\d{12}$" id="product_service_time" placeholder="+375291234567" required>
                    </div>
                    <div class="mb-3">
                        <label for="id_photo" class="form-label"><fmt:message key="placeholder.name.photo"/></label>
                        <div class="custom-file">
                        <input type="file"  class="form-control" class="custom-file-input" name="product_image" id="id_photo" required>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">add category</button>
            </form>



        </div>
        <div class="col-sm"></div>
    </div>


</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
