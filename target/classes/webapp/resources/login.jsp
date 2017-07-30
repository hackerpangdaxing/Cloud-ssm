<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<button id="clickEvent">提交答案</button>
</body>
<script type="text/javascript" src=""></script>
<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
<script type="text/javascript">
	$("#clickEvent").click(function(){
		$.ajax({
			method: "POST",
		    url: "/Cloud/ClassActivity/submitAnswer",
		    headers: {'Content-type': 'application/json;charset=UTF-8'},
		    data: JSON.stringify( (
		    		{     
		    			activityId:5676951,
		    			userId:5,
		    			answerResults:[{qbld:1200000001,isRight:0},{qbld:1200000002,isRight:1}]
		    			} )),
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
