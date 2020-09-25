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
                var deptid = $(".deptid").val();
                // console.log(data)
                // var html='<option value="-1">请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    if (deptid == data[i].id) {
                        html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>'
                    } else {
                        html += '<option value="' + data[i].id + '" >' + data[i].name + '</option>'
                    }
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
    <form>
        ID<input type="text" name="id" disabled value="${User.id}"><br><br>
        用户名<input type="text" name="username" disabled value="${User.username}"><br><br>
        邮箱<input type="email" name="email" disabled value="${User.email}"><br><br>
        真实姓名 <input type="text" name="realName" disabled value="${User.realName}"><br><br>
        年龄<input type="text" name="age" disabled value="${User.age}"><br><br>
        详细信息<input type="text" name="desc" disabled value="${User.desc}"><br><br>
        手机号<input type="text" name="phone" disabled value="${User.phone}"><br><br>
        性别<input type="text" disabled name="gender" disabled value="${User.gender}"><br><br>
        部门名<input type="text" disabled value="${User.deptName}" name="deptName" ><br><br>
        部门ID<input type="text" disabled name="deptid" value="${User.deptId}"><br><br>
        <%--<a href="/user/userlist">返回</a>--%>
    </form>
</div>
</body>
</html>
