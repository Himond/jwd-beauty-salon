<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title><fmt:message key="dashboard.page.title"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>


<c:if test="${user.role() == 'CLIENT'}">
    <div class="container-fluid body-back  mx-auto" style="padding-top:50px;padding-bottom:50px">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm">
                </div>
                <div class="col-8">
                    <center><h4><fmt:message key="dashboard.page.header"/></h4></center>
                    <center><h4><i><fmt:message key="dashboard.page.greet"/></i></h4></center>
                    <hr>
                    <div class="card mb-3 center-block" style="max-width: 1000px;">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <c:choose>
                                    <c:when test="${empty user.photo()}">
                                        <img src="${pageContext.request.contextPath}/static/core/img/account.png" class="card-img " alt="...">
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <div class="card-header"><h6 class="card-title" style=" font-size: 20px;"><fmt:message key="dashboard.page.cardHeader"/></h6></div>
                                    <li><i class="card-text"><fmt:message key="dashboard.page.name"/> ${user.firstName()}</i></li>
                                    <li><i class="card-text"><fmt:message key="dashboard.page.surname"/> ${user.lastName()}</i></li>
                                    <li><i class="card-text"><fmt:message key="dashboard.page.email"/> ${user.email()}</i></li>
                                    <li><i class="card-text"><fmt:message key="dashboard.page.phone"/> ${user.phone()}</i></li>
                                    <li><i class="card-text"><fmt:message key="dashboard.page.data"/> ${user.dateOfBirth().toString()}</i></li>
                                        ${editPassword}
                                    <div class="card-footer">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="go_to_edit_password_page_command">
                                                <button type="submit" class="btn btn-outline-dark"><fmt:message key="dashboard.page.editPassword"/></button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="go_to_edit_profile_page_command">
                                                <input type="hidden" name="first_name" value="${user.firstName()}">
                                                <input type="hidden" name="last_name" value="${user.lastName()}">
                                                <input type="hidden" name="email" value="${user.email()}">
                                                <input type="hidden" name="phone" value="${user.phone()}">
                                                <input type="hidden" name="date_of_birth" value="${user.dateOfBirth().toString()}">
                                                <button type="submit" class="btn btn-outline-dark"><fmt:message key="dashboard.page.editProfile"/></button>
                                            </form>
                                        </div>
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
    </div>
</c:if>

<c:if test="${user.role() == 'MASTER'}">
    <div class="container-fluid  bg-master"  style="padding-top:50px;padding-bottom:50px">
        <center><h5 style="font-size: 35px;"><b><fmt:message key="dashboard.page.master.title"/></b></h5></center>
        <hr>
        <div class="row">
            <div class="col-sm">
                <center> <figure class="snip1376">
                    <c:choose>
                        <c:when test="${empty user.photo()}">
                            <img src="${pageContext.request.contextPath}/static/core/img/teacher_main.jpg" alt="..." />
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                    <figcaption class="text-center">
                        <h3>${user.firstName()} ${user.lastName()}</h3>
                        <span><b>${user.position()}</b></span>
                        <hr>
                        <i>${user.description()} </i>
                        <hr>
                        <i> ${user.email()}</i>
                        <div class="icons"><a href="#"><i class="fab fa-instagram"></i></a>
                            <a href="#"><i class="fab fa-vk"></i></a>
                            <a href="#"> <i class="fab fa-twitter"></i></a>
                        </div>
                    </figcaption>
                </figure></center>
            </div>
        </div>
    </div>
</c:if>








<jsp:include page="footer.jsp"/>
</body>
</html>
