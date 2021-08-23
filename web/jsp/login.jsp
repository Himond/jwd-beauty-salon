<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.08.2021
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="LoginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="sign_in_command">
    Login:<br/>
    <input type="text" name="username" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>

    <input type="submit" value="LogIn"/>
</form>

</body>
</html>
