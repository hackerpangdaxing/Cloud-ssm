<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">   
     <link rel="stylesheet" type="text/css" href="themes/icon.css"> 
     <link rel="stylesheet" type="text/css" href="css/regist.css"> 
<title>注册</title>
</head>
<body>
<div id="regist_content">
<p> 用户名: <input type="text"  id="name" placeholder="用户名" class="easyui-validatebox" name="name"></p> 
<p> 身份: <select >
              <option>老师</option>
              <option>学生</option>
        </select></p> 
<p> 密码: <input type="text"  id="password" placeholder="密码" class="easyui-validatebox"></p>
<p> 确认密码: <input type="text"  id="Confpassword" placeholder="确认密码" class="easyui-validatebox"></p> 
<p> 邮箱: <input type="text"  id="mail" placeholder="邮箱" class="easyui-validatebox"></p> 
</div>

<div id="btn">
        <a href="#" class="easyui-linkbutton">注册</a>
   </div>

</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="js/regist.js"></script>
</html>