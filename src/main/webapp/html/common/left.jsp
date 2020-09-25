<%--
  Created by IntelliJ IDEA.
  User: QT
  Date: 2020/9/22
  Time: 15:15
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
            url:"/menu/menuListAll",
            data:"",
            dataType:"json",
            type:"get",
            success:function (data) {
                var parent = data.Parent;
                var son = data.Son;
                var  html="";
                for (var i = 0; i <parent.length ; i++) {
                    html+=parent[i].name+'<ul>'
                    for (var j = 0; j <son.length ; j++) {
                        if (parseInt(son[j].parentId)===parent[i].id) {
                            html+=' <li><a href="'+son[j].url+'">'+son[j].name+'</a></li>'
                        }
                    }
                    html+='</ul>'
                }
                $("#left").append(html)
            },
            error:function () {
                alert(1)
            }
        })
})
</script>
<body>
<div id="left">
</div>
</body>
</html>
