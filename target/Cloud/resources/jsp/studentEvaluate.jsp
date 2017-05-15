<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript">
	var thisURL = document.URL;
	var getval = thisURL.split('?')[1];
	var titleType = getval.split("=")[1];
	$.ajax({
		url:"https://www.pangdaxing.cn/Cloud/show/getEduEvaluate",
		type : "get",	
		data:{
			activityId:titleType,
		},
		success:function(data){
			console.log(data);
		},
		error:function(data){
			console.log(data);
		}
	}) 
	var v = JSON.stringify( (
    		{   activityId:22,
                userId: 55,
                answerResults:"[{\"qbId\" : 1100000001, \"isRight\" : 1},{ \"qbId\": 1100000002, \"isRight\": 0} ]",
                startTime: '2017-04-25 00:00:00',
                endTime: '2017-04-25 00:00:00',
                rightTimes: 1,
                errorTimes: 9,
                accuracy: '10%',
                eduEvaluate: 'success',		    			
			} )),
</script>
</html>