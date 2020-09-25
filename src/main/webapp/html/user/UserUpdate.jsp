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
                url:"/user/sdept",
                data:"",
                type:"post",
                dataType:"json",
                success:function (data) {
                    var html="";
                    var deptid = $(".deptid").val();
                    // console.log(data)
                    // var html='<option value="-1">请选择</option>';
                    for (var i = 0; i <data.length ; i++) {
                        if (deptid==data[i].id) {
                        html+='<option value="'+data[i].id+'" selected>'+data[i].name+'</option>'
                        }else {
                        html+='<option value="'+data[i].id+'" >'+data[i].name+'</option>'
                        }
                    }
                    $("#dept").append(html)
                },
                error:function () {
                    alert(1)
                }
            })
    })


    
</script>
<body>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/left.jsp" %>
<div id="right">
    <form action="/user/updateuser">
        <span id="msg"></span><br>
       <input type="hidden" class="id" name="id" value="${updateuser.id}">
       <input type="hidden" class="deptid" name="deptid" value="${updateuser.deptId}">
        用户名<input type="text" class="username" name="username" value="${updateuser.username}"><br><br>
        邮箱<input type="email" class="email" name="email" value="${updateuser.email}"><br><br>
        真实姓名 <input type="text" name="realName" value="${updateuser.realName}"><br><br>
        年龄<input type="text" class="age" name="age" value="${updateuser.age}"><br><br>
        性别<select name="gender" >
            <option value="1" >男</option>
            <option value="0">女</option>
        </select>
        部门名<select name="deptName" id="dept">
        </select>
        <input type="submit" value="提交" class="button"><br><br>
        <a href="/user/userlist">返回</a>
    </form>
</div>
</body>
</html>
