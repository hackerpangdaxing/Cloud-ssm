<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<input type="text"   id="name">	
<input type="text" id="password">	
<button id="clickEvent">登录</button>
</body>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript">
	$("#clickEvent").click(function(){
		$.ajax({
			url:"sign",
			type : "get",
			data:{
				name : $("#name").val(),
				password : $("#password").val(),
			},			
			success:function(data){
				alert(data);
			},
			error:function(data){
				console.log(data);
			}
		}) 
	})
</script>
</html>
