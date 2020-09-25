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
<script type="text/javascript" src="/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/js/register.js"></script>
<body>
<form action="/login/register">
<span id="msg"></span><br>
<input type="text" class="username"  name="username"placeholder="用户名"><br><br>
<input type="email" class="email" name="email" placeholder="邮箱"><br><br>
<input type="password" class="password" name="password" placeholder="密码"><br><br>
<input type="password" class="comfirpassword" placeholder="确认密码"><br><br>
<input type="submit" value="提交" class="button"><br><br>
<a href="login.jsp">返回登陆</a>
</form>
</body>
</html>
