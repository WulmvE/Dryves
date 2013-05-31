<%-- 
    Document   : login
    Created on : 29-mei-2013, 20:07:14
    Author     : hctung
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="col_content">

    <div id="loginBox" class="block_six white">		
        <h2>Login</h2>
        <form style="clear: left" name="login_form" method="post" action="j_security_check">

            <input type="text" size="20" name="j_username" placeholder="username"><br/>
            <input type="password" size="20" name="j_password" placeholder="password"><br/>
            <a href="#" onclick="document.login_form.submit()" class="button" id="button_login"><img src="img/arrow_right.png" alt="login button" /></a>

        </form>

    </div>

    <div id="loginCreate" class="block_six white">		
        <h2>Create account</h2>
        <form style="clear: left" name="create_login_form" method="post" action="#">

            <input type="text" size="20" name="#" placeholder="username"><br/>
            <input type="password" size="20" name="#" placeholder="password"><br/>
            <input type="email" name="#" placeholder="email"><br/>
            <a href="#" onclick="document.create_login_form.submit()" class="button" id="button_login"><img src="img/arrow_right.png" alt="create account button" /></a>

        </form>
    </div>

</div>