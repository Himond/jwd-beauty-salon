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
    <title><fmt:message key="account.page.title"/></title>
    <mytag:image/>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://bootstraptema.ru/snippets/form/2017/jquery.payform.min.js"></script>
    <script src="https://bootstraptema.ru/snippets/form/2017/script.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid body-back center-block" style="padding-top:50px;padding-bottom:50px">
    <div class="container">
        <div class="row">
            <div class="col-sm">
            </div>
            <div class="col-sm">
                <h5><fmt:message key="account.page.title"/></h5>
                <form method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="top_up_an_account">
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label"><fmt:message key="account.page.card"/></label>
                        <input type="number" class="form-control" pattern="^[0-9]{16}$" name="card_number"  id="cardNumber" aria-describedby="number" required>
                        <div id="number" class="form-text"><fmt:message key="account.page.cardText"/></div>
                    </div>
                    <div class="mb-3">
                        <label for="replenishmentAmount" class="form-label"><fmt:message key="account.page.amount"/></label>
                        <input type="number" class="form-control" name="amount"  id="replenishmentAmount" aria-describedby="amountHelp" min="1" max="1500" required>
                        <div id="amountHelp" class="form-text"><fmt:message key="account.page.amountText"/></div>
                    </div>
                    <hr>
                    <div class="form-group" id="credit_cards">
                        <img src="https://bootstraptema.ru/snippets/form/2017/visa.jpg" id="visa">
                        <img src="https://bootstraptema.ru/snippets/form/2017/mastercard.jpg" id="mastercard">
                        <img src="https://bootstraptema.ru/snippets/form/2017/amex.jpg" id="amex">
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary"><fmt:message key="account.page.button"/></button>
                </form>
            </div>
            <div class="col-sm">
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
