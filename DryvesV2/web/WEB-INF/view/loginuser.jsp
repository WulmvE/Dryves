<%-- 
    Document   : loginuser
    Created on : May 28, 2013, 4:49:16 PM
    Author     : Maartje
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="col_content">
    <div class="block_six white">		
        <h2>login</h2>

        <form  name="inlog" action="j_security_check" method=post><br/>
            <input type="text" name="j_username" placeholder="alias"><br/>
            <input type="password" name="j_password" placeholder="password"><br/>
            <span class="local_menu"> 
                <a href="#" onclick="document.inlog.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>

        </form><script type="text/javascript">
            document.inlog.j_username.focus();
        </script>
        
    </div>

    <div class="block_six white">		

        <h2>nog geen alias?</h2>

            <form  name="no_account" action="register" method=post>
            <input type="text" name="j_username" placeholder="username"><br/>
            <span class="local_menu">               
                <a href="#" onclick="document.no_account.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>
        </form>
    </div>
