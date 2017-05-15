<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/easyui/css/evaluate.css"> 
<title>Insert title here</title>
</head>
<body>
<c:if test="${ empty activityId}">
	查询失败：你还未曾创建过课程！！！！
</c:if>
<c:if test="${!empty activityId}">
<div class="content">
<div class="header">教学评价</div>
<div class="activity">
<table>
<tr><td>开课时间</td><td>课程评价</td></tr>
<c:forEach items="${activityId}" var="list">
<tr class="activity_li">
	<td class="activity_li_1">${list[1]}</td>
	 <td  class="activity_li_2"><a href="resources/jsp/studentEvaluate.jsp?id=${list[0]}">课程编号为${list[0]}的评价</a></td >
</tr>
</c:forEach>
</table>
</div>
</div>
</c:if>
</body>
<script type="text/javascript" src="resources/easyui/js/jquery.min.js"></script>
</html>