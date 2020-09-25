<%--
  Created by IntelliJ IDEA.
  User: QT
  Date: 2020/9/23
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .dh li{
        color: white;
        float: left;
        list-style-type: none;
    }
    .ms li{
        color: black;
        list-style-type: none;
        float: right;
    }
</style>
<script src="/js/usermanger.js"></script>
<body>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/left.jsp" %>
<div id="right">
   <form action="/user/userlist">
       <input type="text" name="name" placeholder="根据姓名查询">
       <input type="submit" value="查询" class="button btn-small">
   </form>
    <button><a href="/html/user/UserInsert.jsp">添加</a></button >
    <button><a href="/user/findUserFocus">我的关注</a></button >
    <table class="table tab-content">
        <tr>
            <td>序号</td>
            <td>用户名</td>
            <td>邮箱</td>
            <td>真实姓名</td>
            <td>年龄</td>
            <td>性别</td>
            <td>部门</td>
            <td>登陆时间</td>
            <td>详细信息</td>
            <td>加关注</td>
            <td>修改</td>
            <td>删除</td>
        </tr>
        <c:forEach var="list" items="${page.data}" varStatus="st">
            <tr>
                <td >${list.id}</td>
                <td>${list.username}</td>
                <td>${list.email}</td>
                <td>${list.realName}</td>
                <td>${list.age}</td>
                <td><c:if test="${list.gender==1}">男</c:if>
                    <c:if test="${list.gender==0}">女</c:if>
                </td>
                <td>${list.deptName}</td>
                <td><fmt:parseDate value="${list.loginTime}" var="datetime"/>
                    <fmt:formatDate value="${datetime}" pattern="yyyy 年 MM 月 dd 日 HH 时 mm 分
                                    ss 秒"/></td>
                <td><a href="/user/selectUserById?id=${list.id}">详细信息</a></td>
                <td><input class="checked" value="${list.id}" type="checkbox"  <c:forEach items="${userfoucs}" var="foucs">
                    <c:if test="${foucs.userFocusId==list.id}">checked=""</c:if>
                </c:forEach>></td>
                <td><a href="/user/update?id=${list.id}">修改</a></td>
                <td><a href="/user/delete?id=${list.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    <ul class="dh">
        <li><a href="/user/userlist?pageCurrent=1&name=${name}">首页</a></li>
        <li><a href="/user/userlist?pageCurrent=${page.pageCurren-1}&name=${name}">上一页</a></li>
        <li><a href="/user/userlist?pageCurrent=${page.pageCurren+1}&name=${name}">下一页</a></li>
        <li><a href="/user/userlist?pageCurrent=${page.pageTotal}&name=${name}">尾页</a></li>
    </ul>
    <ul class="ms">
    <li>当前${page.pageCurren}页&nbsp</li>
    <li>共${page.pageTotal}页&nbsp</li>
    <li>每页有${page.pageSize}条&nbsp</li>
    <li>共${page.pagecount}条&nbsp</li>
    </ul>
</div>
</body>
</html>
