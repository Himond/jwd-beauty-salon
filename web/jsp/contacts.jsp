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
    <title><fmt:message key="cart.contacts.title"/></title>
    <mytag:image/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid bg-back-contact"  style="padding-top:50px;padding-bottom:50px">
    <a name="contact"></a>

    <div class="row">
        <div class="col-6">

            <center><h5 style="font-size: 35px;"><b><fmt:message key="cart.contacts.main"/></b></h5></center>
            <hr>
            <ul style="font-size: 25px;">
                <b><fmt:message key="cart.contacts.address"/></b>
                <li style="list-style-type: none;">
                    <b><fmt:message key="cart.contacts.row"/></b>
                </li>

            </ul>
            <ul style="font-size: 25px;">
                <b><fmt:message key="dashboard.page.email"/></b>
                <li style="list-style-type: none;">
                    <b><a href="mailto:backstage@mail.ru" style="color: black;">backstage@mail.ru</a></b>
                </li>

            </ul>
            <ul style="font-size: 25px;">
                <b><fmt:message key="cart.contacts.phones"/></b>
                <li style="list-style-type: none;">
                    <a href="tel:+8(029)6304919"><i class="fas fa-phone" style="color: black;" ><b class="text-black"> +375 (29) 630-49-19</b></i></a>
                </li>
                <li style="list-style-type: none;">
                    <a href="tel:+8(044)5866555"><i class="fas fa-phone" style="color:black;" ><b class="text-black"> +375 (44) 586-65-55</b></i></a>
                </li>

            </ul>
            <ul style="font-size: 25px;">
                <b><fmt:message key="cart.contacts.time"/></b>
                <li style="list-style-type: none;">
                    <b><fmt:message key="cart.contacts.fromToTime"/></b>
                </li>

            </ul>
        </div>
        <div class="col-6">
            <center><div style="position:relative;overflow:hidden;"><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d587.7457402826986!2d30.335296388159698!3d53.89650678899013!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46d051efaef42975%3A0x6dc0704b0eb36b02!2zS29tc29tb2wnc2theWEgMywg0JzQvtCz0LjQu9GR0LI!5e0!3m2!1sru!2sby!4v1584015891280!5m2!1sru!2sby" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe></div>
            </center>
        </div>

    </div>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
