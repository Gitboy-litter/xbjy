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
                html+='<option value="-1" >请选择</option>'
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '" >' + data[i].name + '</option>'
                }
                $("#dept").append(html)
            }
        })
        $("#deptName").val(1)
    })
    $(function () {
        $("#dept").change(function () {
            $.ajax({
                url: "/user/findUserByDeptId",
                data: {deptid: $("#dept").val()},
                type: "post",
                dataType: "json",
                success: function (data) {
                    $("#person").empty();
                    var html = "";
                    for (var i = 0; i < data.length; i++) {
                        html += ' <input  name="makeUsers" type="checkbox" value="'+data[i].id+'">' + data[i].realName;
                    }
                    $("#person").append(html)
                }
            })
        })
    })

</script>
<body>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/left.jsp" %>
<div id="right">
    <form action="/meeting/addmeeting">
        <%--<input type="hidden" class="id" name="id" placeholder="id">--%>
        标题<input type="text" name="title" ><br><br>
        部门名<select name="deptId" id="dept">
    </select><br><br>
        发送人
        <div id="person">
        </div>
        <br><br>
        开始时间<input type="datetime-local" name="startTime" ><br><br>
        结束时间<input type="datetime-local" name="endTime" ><br><br>
        会议内容<textarea name="content"></textarea>
        <input type="submit" value="提交" class="button"><br><br>
        <a href="/meeting/list">返回</a>
    </form>
</div>
</body>
</html>
