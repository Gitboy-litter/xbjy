<%--
  Created by IntelliJ IDEA.
  User: QT
  Date: 2020/9/22
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    #head {
        height: 10%;
        border: 1px solid deepskyblue;
    }

    #left {
        margin-top: 5px;
        margin-right: 2px;
        width: 10%;
        height: 88%;
        border: 1px solid deepskyblue;
        float: left;
    }

    #right {
        margin-top: 5px;
        width: 88%;
        height: 88%;
        border: 1px solid deepskyblue;
        float: left;
    }
</style>
<script>
    $(function () {

        $("#detail-img").click(function () {
            // 点击图片时触发文件表单控件
            $("#picFile").click();
        });

        $("#picFile").change(function () {
            // 构造文件上传form
            var formData = new FormData();
            formData.append("iconFile", document.getElementById("picFile").files[0]);
            // 上传图片
            $.ajax({
                url: "/img/upload",
                processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
                contentType: false,       //不对请求头做处理
                data: formData,
                type: "post",
                dataType: "json",
                async: true,
                success: function (data) {
                    if (data.code == '200') {
                        //成功
                        $("#detail-img").attr("src", "/img/headimg?img="+data.pic);
                    } else {
                        alert(data.msg);
                    }
                },
                error:function () {
                    alert(1)
                }
            });

        });
    });
</script>
<body>
<div id="head">
    <%--<img src="/img/headimg?img=${sessionScope.get("sessionLogin").pic}">--%>

    <img id="detail-img" src="/img/headimg?img=${sessionScope.get("sessionLogin").pic}" height="80px" width="80px">

        <input type="file" id="picFile" style="display: none;">
</div>
</body>
</html>
