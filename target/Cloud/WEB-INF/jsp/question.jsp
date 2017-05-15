<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/question.css">
<title>Insert title here</title>
</head>
<body>

<c:if test="${empty question}">
<div id="error">
		查询失败：你还没有上传过题目!!!!!
		</div>
	</c:if>
	<c:if test="${!empty question}">
		<div id="question"></div>
	</c:if>
	<%-- <c:forEach items="${question}" var="arr">
		<div id="question">
			<c:out value="${arr.questionTitle}"></c:out>
			</br> 正确率：
			<c:out value="${arr.accuracy}"></c:out>
		</div>
	</c:forEach>  --%>
	<c:if test="${! empty question}">
<div id="footer">	
	<a>第[${pageNum}]页</a>
  	<a href="${pageContext.request.contextPath}/chooseMsg">首页</a>&nbsp;&nbsp;
  	<a href="${pageContext.request.contextPath}/chooseMsg?pageNum=${pageNum-1==0?1:pageNum-1}">上一页</a>&nbsp;&nbsp;
  	<a href="${pageContext.request.contextPath}/chooseMsg?pageNum=${pageNum+1>total?total:pageNum+1}">下一页</a>&nbsp;&nbsp;
  </div>	
  </c:if>
</body>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var a = ${question};
	 for(var i=0; i<a.length; i++) {
		var node = document.createElement('span');
		node.innerText ="题目"+a[i].questionTitle+"\r\n正确率"+a[i].accuracy;
		node.id="nodeText";
		$("#question").append(node);
	 }
	/*  for(var i=0; i<myEscapedJSONString.length; i++) {
		 var s = myEscapedJSONString[i].accuracy;
		 console.log(s)
		 if("" == s){
			 s = "0.00%";
		 }
		$("#question").append(s);
		$("#question").append(myEscapedJSONString[i].questionTitle);
	 }  */
})


	  

</script>
</html>