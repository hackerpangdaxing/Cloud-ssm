/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-03-26 13:14:28
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


    /*点击登录*/
    $('#btn a').click(function() {
        if(! $('#name').validatebox('isValid')){
            $('#name').focus();
        }else  if(! $('#password').validatebox('isValid')){
            $('#password').focus();
        }else {
               $.ajax({
                url:"../../sign",
                type : "get",
                data:{
                    name : $("#name").val(),
                    password : $("#password").val(),
                },          
                success:function(data){
                   if('0' == data){
                        alert("用户未激活");
                   }else if('error' == data){
                        alert("用户名或者密码错误");
                   }else if("teacher" == eval("("+data+")").type){
                	   top.location='main.html';
                   }else{
                	   alert("没有权限！！！！");
                	 top.location='index.html';
                   }
                },
                error:function(data){
                    console.log(data);
                }
            }) 
        }
    });


/*自定义格式验证格式*/
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


   
})

