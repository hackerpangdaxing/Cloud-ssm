/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-04-27 20:37:36
 * @version $Id$
 */

function login()
{
    var diag = new Dialog();
    diag.Width = 400;
    diag.Height = 180;
    diag.Title="登录";
    diag.URL = "login.html";
    diag.show();
}
function regist()
{
    var diag = new Dialog();
    diag.Width = 400;
    diag.Height = 280;
    diag.Title="注册";
    diag.URL = "regist.html";
    diag.show();
}