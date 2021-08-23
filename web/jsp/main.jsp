<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.08.2021
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h3>Welcome</h3>
<hr/>
${User}, hello!
<hr/>
<form name="LoginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="logout"/>

    <br/>
    <input type="submit" value="Logout"/>
</form>

</body>
</html>
