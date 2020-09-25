<%--
  Created by IntelliJ IDEA.
  User: QT
  Date: 2020/9/23
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    $(function () {
        $.ajax({
            url: "/user/sdept",
            data: "",
            type: "post",
            dataType: "json",
            success: function (data) {
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '" >' + data[i].name + '</option>'
                }
                $("#dept").append(html)
            },
            error: function () {
                alert(1)
            }
        })
    })


</script>
<body>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/left.jsp" %>
<div id="right">
    <form action="/user/insertUser">
        <%--<input type="hidden" class="id" name="id" placeholder="id">--%>
        用户名<input type="text" name="username" placeholder="用户名"><br><br>
        邮箱<input type="email" name="email" placeholder="邮箱"><br><br>
        真实姓名 <input type="text" name="realName" placeholder="真实姓名"><br><br>
        年龄<input type="text" name="age" placeholder="年龄"><br><br>
        手机号<input type="text" name="phone" placeholder="手机号"><br><br>
        注册时间<input type="date" name="registerTime" placeholder="注册时间"><br><br>
        性别<select name="gender">
        <option value="1">男</option>
        <option value="0">女</option>
    </select>
        部门名<select name="deptId" id="dept">
    </select>
        <input type="submit" value="提交" class="button"><br><br>
        <a href="/user/userlist">返回</a>
    </form>
</div>
</body>
</html>
