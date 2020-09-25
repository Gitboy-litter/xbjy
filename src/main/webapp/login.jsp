<%--
  Created by IntelliJ IDEA.
  User: QT
  Date: 2020/9/22
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<body>
<span id="msg"></span><br>
    <label>用户名</label>
    <input type="text" class="username" value="admin" name="username"><br><br>
    <label>密码</label>
    <input type="password" class="password" value="admin" name="password"><br><br>
<label>验证码</label>
    <input type="text"  name="Code"><br><br>
    <img src="/img/verifyCode" onclick="clickCode()">
    <input type="button" value="提交" class="button"><br><br>
    <input type="button" value="注册" href="register.jsp"><br><br>
</body>
</html>
