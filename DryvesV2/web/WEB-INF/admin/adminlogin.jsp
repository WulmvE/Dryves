<%-- 
    Document   : adminpanel
    Created on : Jun 3, 2013, 1:53:17 AM
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
        <title>Dryves - Admin</title>

        <link rel="stylesheet" type="text/css" href="css/main.css" />
        <link rel="stylesheet" type="text/css" href="css/admin.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />

        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>

    </head>

    <body>

        <div id="main" style="width:458px;">

            <div id="main_menu">
                <a class="block_triple green text_white" href="#"><div id="logo" class="menu_icon">&#xf06c;</div><span class="menu_item">dryves admin</span></a>
                		
            </div>

            <div id="col_content">

                <div id="loginBox" class="block_six white">		
                    <h2>Log in</h2>
                    <form name="login_form" method="post" action="j_security_check">
                        <input type="text" size="20" name="j_username" placeholder="username"><br/>
                        <input type="password" size="20" name="j_password" placeholder="password"><br/>
                        <a href="#" onclick="document.login_form.submit()" class="button" id="button_login"><img src="img/arrow_right.png" alt="login button" /></a>

                    </form>

                </div>

                <div id="cont_promo">al 24.145 ton co<span class="sub">2</span> bespaard...</div>
            </div>

    </body>

</html>
