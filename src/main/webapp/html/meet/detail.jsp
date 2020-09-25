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
<body>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/left.jsp" %>
<div id="right">
    <c:if test="${meetingmap.flag!=1}">
        <c:if test="${meetingmap.flag==2}">
            <button ><a href="/meeting/deleteJoinMeeting?id=${meeting.id}">退出会议</a></button>
        </c:if>
        <c:if test="${meetingmap.flag==3}">
            <button ><a href="/meeting/joinMeeting?id=${meeting.id}">参加会议</a></button>
        </c:if>
    </c:if>
    <c:if test="${meetingmap.flag==1}">
        <button disabled>不需要参加会议</button>
    </c:if>
    <br><br>
        标题:${meeting.title}<br><br>
        部门名:${meeting.deptName}<br><br>
        应到:${meetingmap.should}<br><br>
        实到:${meetingmap.arrival}<br><br>
        未到:${meetingmap.should-meetingmap.arrival}<br><br>
        开始时间:${meeting.startTime}<br><br>
        结束时间:${meeting.endTime}<br><br>
        会议内容<textarea disabled>${meeting.content}</textarea><br><br>
        <a href="/meeting/list">返回</a>
    </form>
</div>
</body>
</html>
