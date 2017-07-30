<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>

<script>
	var port = "2475693";
    var socket_url = "ws://localhost:8080/Cloud/hello.do?activityid="+port;
    var  websocket ;
   $(function(){
       //连接websocket服务器
       $("#connect").click(function(){
     // alert(socket_url)
           websocket = new WebSocket(socket_url); 
           websocket.onopen = function (evt) { onOpen(evt) }; 
           websocket.onclose = function (evt) { onClose(evt) }; 
           websocket.onmessage = function (evt) { onMessage(evt) }; 
           websocket.onerror = function (evt) { onError(evt) }; 
       });

       //注册发送消息按钮事件
       $("#send").click(function(){
           var who = $("input[name='who']:checked").val();//获取消息接收方类别
           var content = $.trim($("#content").val());//获取消息内容
           var msg = "{";
           var message = {
        		   state:'join',
        		   activityId:port,
        		   message:content,
        		   sendTo:'',
        		   data:'',
        		   username:''
        		   };
           if(""==content){
               alert("输入消息内容！");
               return;
           }
           if("server"==who){
               message.state = "server";
               $("#msg").after("<p>你对服务器说:"+content+"</p>");
           }else if("broadcast"==who){
                message.state = "broadcast";
               $("#msg").after("<p>你对大家说:"+content+"</p>");
           }else if("join"==who){
              message.state = "join";
               $("#msg").after("<p>你对大家说:"+content+"</p>");
           }
           message.message = content;
           sendd(message);
       });
   });
   
    //连接成功websocket服务器时执行
       function onOpen(evt) { 
           $("#msg").text("成功连接到websocket服务器......");
           $("#send").removeAttr("disabled");//发送信息按钮可用
           $("#connect").attr("disabled","disabled");//连接服务器按钮不可用
           sendd({
        	   state:'join',
        	   activityId:port,
        	   message:'',
        	   sendTo:'',
        	   data:'',
        	   username:''
        	   })
           }
    
    //连接关闭时执行
    function onClose(evt) { 
       console.log("连接关闭。。。。"); 
    } 
    //服务器有推送消息过来时执行
    function onMessage(obj) {
    	var data = JSON.parse(obj.data);
    	if(data.message != null && data.message != ""){
           $("#msg").after("<p>"+data.message+"</p>");
    	}
        console.log(data);
    } 
    //有错误信息时执行
    function onError(evt) { 
       console.log('Error occured: ' + evt.data); 
    }
    //向服务器发送消息,此方式实际生产中考虑消息加密传递
    function sendd(msg){
        websocket.send(JSON.stringify(msg));
    }
    //刷新在线用户列表
    function refresh_line_users(data){
    	var obj = JSON.parse(data.data);	
    	console.info("=========="+data)
        $("#userlist").empty();
        for(var i=0;i<obj.data.length;i++){
            $("#userlist").append("<option value='"+$.trim(obj.data[i])+"'>"+$.trim(obj.data[i])+"</option>");
        }
        $("#userlist").append("<option value='all'>全部</option>");
    }
</script>
</head>
<body>
<h1>普通请求方式</h1>

        响应提示区域：<div id="msg" style="color:red"></div>
        <hr>
        <br>
        消息内容：
        <input type="text" id="content" name="content">
        <br><br>
         在线列表:
         <select id="userlist">
         </select>
        <br><br>
        我要发消息给：<input type="radio" id="server" name="who" value="server" checked="checked">服务器识别消息
        <input type="radio" id="one" name="who" value="broadcast">个人
        <input type="radio" id="all" name="who" value="join">广播
        <input type="radio" id="out" name="who" value="out">退出
        <br><br>
        <input type="button" id="connect" value="连接websocket服务器">
        <input type="button" id="send" value="发送" disabled="disabled">
</body>
</html>