<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <style type="text/css">
    li{
	list-style-type:decimal;
	margin-top:50px;
	margin-left:30%;
	width:300px;
}
span{
	display:block;
	margin-top:20px;
}
    
    </style>
</head>
<body>

</body>
<script type="text/javascript" src="../easyui/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var thisURL = document.URL;
	var getval = thisURL.split('?')[1];
	var titleType = getval.split("=")[1];
	$.ajax({
		url:"../../show/getEduEvaluate",
		type : "get",	
		 data:{
			activityId:titleType,
		}, 
		success:function(data){
		console.log(data);
			if(data == null || "" == data){
			var nodespan = document.createElement('span');
				nodespan.innerText="目前没有人评教！！！";
				$("body").append(nodespan);
				return ;
			}
			var node = document.createElement('ul');
			for(var i=0; i<data.length; i++){
				if(data[i].eduEvaluate == null || "" == data[i].eduEvaluate){
					continue;
				}
				var arr = eval("("+data[i].eduEvaluate+")")[0] ;
				var nodeli = document.createElement('li');
				var nodeli1 = document.createElement('span');
				var nodeli2 = document.createElement('span');
				var nodeli3 = document.createElement('span');
				var nodeli4 = document.createElement('span');
				if(arr.hard == null ||"" == arr.hard){
					arr.hard = "未评价";
				}
				if(arr.rate == null ||"" == arr.rate){
					arr.hard = "未评价";
				}
				if(arr.advice == null ||"" == arr.advice){
					arr.hard = "未评价";
				}
				if(arr.starts == null ||"" == arr.starts){
					arr.hard = "未评价";
				}
				nodeli1.innerText="难度: "+arr.hard+"\r\n";
				nodeli2.innerText="讲课速度: "+arr.rate+"\r\n";
				nodeli3.innerText="学生建议: "+arr.advice+"\r\n";
				nodeli4.innerText="评价: "+arr.starts+"颗星\r\n";
				nodeli.append(nodeli1);
				nodeli.append(nodeli2);
				nodeli.append(nodeli3);
				nodeli.append(nodeli4);
				node.appendChild(nodeli);
			}
			$("body").append(node);
			
		},
		error:function(data){
			alert("评教出错");
		}
	}) 
})
</script>
</html>