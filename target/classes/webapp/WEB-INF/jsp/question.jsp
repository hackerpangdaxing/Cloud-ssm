<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/easyui/css/question.css">
<title>Insert title here</title>
</head>
<body>

<c:if test="${empty question}">
		查询失败：你还没有上传过题目!!!!!
	</c:if>
	<c:if test="${!empty question}">
		<div id="content">
			<ul id="question"></ul>
		</div>
<div id="footer">	
	<a>第[${pageNum}]页</a>
  	<a href="${pageContext.request.contextPath}/chooseMsg">首页</a>&nbsp;&nbsp;
  	<a href="${pageContext.request.contextPath}/chooseMsg?pageNum=${pageNum-1==0?1:pageNum-1}">上一页</a>&nbsp;&nbsp;
  	<a href="${pageContext.request.contextPath}/chooseMsg?pageNum=${pageNum+1>total?total:pageNum+1}">下一页</a>&nbsp;&nbsp;
  </div>	
  </c:if>
</body>
<script type="text/javascript" src="resources/easyui/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var a = ${question};
	 for(var i=0; i<a.length; i++) {
		var node = document.createElement('li');
		var span1 =  document.createElement('span');
		var span2 =  document.createElement('span');
		var accuracy = a[i].accuracy;
		if("" == accuracy || accuracy == null){
			accuracy = "无人答过此题";
		}
		span1.innerText="题目:\r\n"+a[i].questionTitle+"\r\n";
		span1.id="node_span1";
		span2.innerText="正确率: "+accuracy;
		span2.id="node_span2";
		node.appendChild(span1);
		node.appendChild(span2);
		node.id="nodeText";
		$("#question").append(node);
	 }
})


	  

</script>
</html>