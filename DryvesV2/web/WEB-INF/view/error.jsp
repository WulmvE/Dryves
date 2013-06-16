<%-- 
    Document   : error.jsp
    Created on : May 28, 2013, 4:49:41 PM
    Author     : Maartje
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="col_content">
    <div class="block_six white">		
        <h2>ik wil inloggen</h2>

        <form  name="inlog" action="j_security_check" method=post><br/>
            <input type="text" name="j_username" placeholder="alias"><br/>
            <input type="password" name="j_password" placeholder="password"><br/>
            <span class="form_msg_error text_red">je inlog gegevens zijn onjuist.<br> probeer het nog eens.</span>
            
            <span class="local_menu"> 
                <a href="#" onclick="document.inlog.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>

        </form>
        
        <script type="text/javascript">
            document.inlog.j_username.focus();
        </script>
        
    </div>

    <div class="block_six white">		

        <h2>ik wil lid worden</h2>

            <form  name="no_account" action="register" method=post>
            <input type="text" name="j_username" placeholder="kies je alias"><br/>
            <span class="local_menu">               
                <a href="#" onclick="document.no_account.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>
        </form>
    </div>