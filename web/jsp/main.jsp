<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>Welcome</h3>
<hr/>
${user.getUserName()}, hello ${role}!
<hr/>
<form name="LoginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="log_out"/>

    <br/>
    <input type="submit" value="Logout"/>
</form>

</body>
</html>
