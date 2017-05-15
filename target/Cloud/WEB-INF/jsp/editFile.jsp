<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        
        <script src="resources/js/jquery.js" type="text/javascript" charset="utf-8"></script>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" charset="utf-8" />
    </head>
    <body>
        <div id="wrapper_login">
            <div id="menu">
                <div id="left"></div>
                <div id="right"></div>
                <h2>上传课件</h2>
                <div class="clear"></div>       
            </div>
            <div id="desc">
                <div class="body">
                    <div class="col w10 last bottomlast">
                       <form id="content_file" action="file" method="post" enctype="multipart/form-data">
                            <p>
                                <label for="username">课程类型:</label>
                            
                                  <tr>
            
                                     <td value="" size="40" class="text">
                                         <select name="selectName" id="salary" >
                                            <option>--请选择课程类型--</option>
                                            <option>C</option>
                                            <option>JAVA</option>
                                            <option>H5</option>
                                            <option>数据结构</option>
                                            <option>C#</option>
                                            <option>Android</option>
                                            <option>软件开发</option>
                                            <option>数学算法</option>
                                            <option>其他</option>
                                        </select>
                                      </td>
                                    </tr>
                                <br />
                            </p>
                            <p>
                                 <tr >
                                    <label> 测试单元:</label>
                                     <td>
                                         <input size="40" class="text" onblur="if(!(/^[0-9]{2}$/.test(this.value))){alert('只能输入两位的数字');this.value='01';}"
                                                  id="chapternum" placeholder="数字"  name="chapternum"/>
                                     </td>
                                </tr>
                            </p>
                            <p>
                                 <tr >
                                    <label> 测试小节:</label>
                                     <td>
                                         <input size="40" class="text" onblur="if(!(/^[0-9]{2}$/.test(this.value))){alert('只能输入两位的数字');this.value='01';}"
                                    id="sectionnum" placeholder="数字"  name="sectionnum" />
                                     </td>
                                </tr>
                            </p>
                            <p>
                                 
                            <tr>
                                <td bgcolor="#F1F9FB"> <label>上传题目:</label></td> 
                                 <td>  <label><input type="file" name="file" id="file"></label></td>
                            </tr>
                            </p>

                            <p class="last">
                            <input type="submit" value="开始上传课件" class="label"/>
                                
                            <input type="button" onclick="window.location.href='http://cloud-1253431508.costj.myqcloud.com/myImg/JAVA.png'" value="下载课件模板" class="label"/>
                            </p>

                        
                        </form>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
                <div id="body_footer">
                    <div id="bottom_left"><div id="bottom_right"></div></div>
                </div>
            </div>      
        </div>
    </body>
</html>