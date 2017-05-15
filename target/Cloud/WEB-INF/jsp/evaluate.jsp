<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${ empty activityId}">
	查询失败：你还未曾创建过课程！！！！
</c:if>
<c:forEach items="${activityId}" var="list">
	活动时间：${list[1]}
	活动ID<a href="resources/jsp/studentEvaluate.jsp?id=${list[0]}">${list[0]}</a>
</c:forEach>
</body>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/evaluate.js"></script>
</html>