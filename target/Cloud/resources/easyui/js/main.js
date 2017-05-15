/* 
* @Author: Marte
* @Date:   2017-03-26 14:55:42
* @Last Modified by:   Marte
* @Last Modified time: 2017-04-26 13:21:53
*/

$(function(){
	
		
		$("#signOut").click(function(){
			window.location.href="index.html"
		})
	
        $('#west-tree').tree({
            data : tree,
            state:"open",    
            onClick : function(node){
                treeClick(node);
             }
        })
        $("#center-tabs").tabs('add',{
                 title:'首页',  
                 content:'<iframe src="1.html" frameborder=0> </iframe>',    
                closable:true,    
        })

        function treeClick(node){
            var id = node.id;
         if(id==1 || id==2 || id==3){
            $('#west-tree').tree(node.state == 'closed' ? 'expand' : 'collapse', node.target); 
                 node.state = node.state == 'closed' ? 'closed' : 'open';  
            }else{
                 var text = node.text;
                 var url = node.attributes.url;
                 addTab(text,url);
        
            }
        }



/*新增内容-----------------------------------*/
  function addTab(title, href){  
      var tt = $('#center-tabs');  
    if (tt.tabs('exists', 0)){//如果tab已经存在,则选中并刷新该tab          
        tt.tabs('close', title);  
        
       
    }  
        if (href){  
            var content = '<iframe scrolling="yes" frameborder="0"   src="'+href+'" style="width:100%;height:100%;"></iframe>';  
        } else {  
            var content = '未实现';  
        }  
        tt.tabs('add',{  
            title:title,  
            closable:true, 
           content:content,  
        });  
    
}  

});


  var tree=[
             {
                id : 1,
                text : '教师管理',
                state : 'closed',
                children : [
                        {
                             id : 11,
                             text : '教学评价',
                              attributes:{    
                                    "url":"../../evaluat",    
                                },
                        },
                         {
                             id : 12,
                             text : '做题信息',
                              attributes:{    
                                    "url":"../../chooseMsg",    
                                },
                        },
                         {
                             id : 13,
                             text : '上传题目',
                              attributes:{    
                                    "url":"../../editFile",    
                                },
                        }
                    ]
            }
  ]    
