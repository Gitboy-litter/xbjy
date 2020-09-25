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
    .dh li {
        color: white;
        float: left;
        list-style-type: none;
    }

    .ms li {
        color: black;
        list-style-type: none;
        float: right;
    }
</style>
<body>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/left.jsp" %>
<div id="right">
    <form action="/meeting/list">
        <input type="text" name="titleName" placeholder="根据姓名查询">
        <input type="submit" value="查询" class="button btn-small">
    </form>
    <button><a href="/html/meet/meetingadd.jsp">添加</a></button>
    <table class="table tab-content">
        <tr>
            <td>会议标题</td>
            <td>部门名称</td>
            <td>会议状态</td>
            <td>会议内容</td>
            <td>发布时间</td>
            <td>开始时间</td>
            <td>结束时间</td>
        </tr>
        <c:forEach var="list" items="${page.data}" varStatus="st">
            <tr>
                <td><a href="/meeting/detail?id=${list.id}">${list.title}</a></td>
                <td>${list.deptName}</td>
                <td>
                    <c:choose><c:when test="${list.status=='0'}">未开始</c:when>
                        <c:when test="${list.status=='1'}">进行中</c:when>
                        <c:when test="${list.status=='2'}">结束了</c:when>
                    </c:choose></td>
                <td>${list.content}</td>
                <td>${list.publishDate}</td>
                <td>${list.startTime}</td>
                <td>${list.endTime}</td>
            </tr>
        </c:forEach>
    </table>
    <ul class="dh">
        <li><a href="/meeting/list?pageCurrent=1&titleName=${titlename}">首页</a></li>
        <li><a href="/meeting/list?pageCurrent=${page.pageCurren-1}&titleName=${titlename}">上一页</a></li>
        <li><a href="/meeting/list?pageCurrent=${page.pageCurren+1}&titleName=${titlename}">下一页</a></li>
        <li><a href="/meeting/list?pageCurrent=${page.pageTotal}&titleName=${titlename}">尾页</a></li>
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
