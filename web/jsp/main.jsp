<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="shortcut icon" href="../static/core/img/ico.png" type="image/png">
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>Welcome</h3>
<hr/>
${user.getFirstName()}, hello ${role}!
<hr/>
<form name="LoginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="log_out"/>

    <br/>
    <input type="submit" value="Logout"/>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
