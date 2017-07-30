/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-04-27 21:24:43
 * @version $Id$
 */
$(function(){


  $('#name').validatebox({
        required : true,
        validType : 'phone',
        missingMessage : '用户名不能为空',
    })

  $('#password').validatebox({
        required : true,
        validType : 'pass',
        missingMessage : '密码不能为空',
    })
 $('#Confpassword').validatebox({
        required : true,
        validType : 'pass',
        missingMessage : '密码不能为空',
    })
  $('#mail').validatebox({
        required : true,
        validType : 'mail',
        missingMessage : '邮箱不能为空',
    })
$('#btn a').click(function() {
	  var type =  $('input:radio[name="type"]:checked').val();
     if(! $('#name').validatebox('isValid')){
            $('#name').focus();
        }else if(type == undefined){
        	$('#type').focus();
        } else  if(! $('#password').validatebox('isValid')){
            $('#password').focus();
        }else  if(! $('#Confpassword').validatebox('isValid')){
            $('#Confpassword').focus();
        } else  if(! $('#mail').validatebox('isValid')){
            $('#mail').focus();
        }else{
            var pass1 = $("#password").val();
            var pass2 = $("#Confpassword").val();
            if(pass1 == pass2){
                        $.ajax({
                            url : "../../regist",
                            type : "post",
                            data :{
                                 name : $("#name").val(),
                                 type : type,
                                 password : $("#password").val(),
                                 mail : $("#mail").val(),
                            },
                            success : function(data){
                            	if ("success" == data) {
                        			alert("注册成功，去邮箱激活后使用账户");
                        			  top.location='index.html';
                        		} else if ("defeat" == data) {
                        			alert("注册失败，请重新注册");
                        			 top.location='index.html';
                        		} else if ("exist" == data) {
                        			alert("该账户已存在，请换个账户");
                        			top.location='index.html';
                        		}
                            },
                            error : function(data){
                                alert("error");
                            }
                        })
                    

            }
        }
});







//验证手机号 密码 邮箱
         $.extend($.fn.validatebox.defaults.rules, {
              phone: {
                   validator: function (value) {
                         return/0?(13|14|15|17|18)[0-9]{9}/i.test($.trim(value)); 
        　　　　　　　}, 
　　　　　　　message: '联系电话格式错误.' } }); 

          $.extend($.fn.validatebox.defaults.rules, {
              pass: {
                   validator: function (value) {
                        return/^(\w){6,20}$/i.test($.trim(value));　}, 
　　　　　　　message: '密码为6~20位字母和数字.' } }); 


          $.extend($.fn.validatebox.defaults.rules, {
              mail: {
                   validator: function (value) {
                        return/\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/i.test($.trim(value));　}, 
　　　　　　　message: '邮箱格式错误.' } }); 
  })