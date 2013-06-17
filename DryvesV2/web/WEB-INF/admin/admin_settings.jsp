<%-- 
    Document   : admin settings
    Created on : Jun 3, 2013, 1:53:17 AM
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
        <title>Dryves Admin - Instellingen</title>

        <link rel="stylesheet" type="text/css" href="css/main.css" />
        <link rel="stylesheet" type="text/css" href="css/admin.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />

        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    </head>

    <body>
        
        <div id="main">

            <div id="main_menu">
                <a id="menu_logo" class="block_double green text_white" href="statistics"><div id="logo" class="menu_icon">&#xf06c;</div><span class="menu_item">dryves admin</span></a>
                <a id="menu_stats" class="block_single blue" href="statistics"><div class="menu_icon"></div><span class="menu_item text_white">stastistieken</span></a>
                <a id="menu_settings" class="block_single green" href="settings"><div class="menu_icon"></div><span class="menu_item text_white">instellingen</span></a>
                <a id="menu_members" class="block_single blue" href="membercontrol"><div class="menu_icon"></div><span class="menu_item text_white">leden</span></a>
                <a id="menu_logout" class="block_single blue" href="logout"><div class="menu_icon text_white"></div><span class="menu_item text_white">uitloggen</span></a>		
            </div>

            <div id="col_content" >

                <div class="block_six white settings_cont">
                    <h2>Afdrachtpercentage instellen</h2>

                    <form name="modify_perc" action="adminDo" method="post">
                        <input type='hidden' name='task' value='setPerc' />
                        <input type="text" name="perc" value="10" style="width:40px; margin-top: 50px;"/> <span style="font-size: 22px">% van ritprijs</span>
                        <span class="local_menu">               
                            <a href="#" onclick="document.modify_perc.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
                        </span>
                </div>
                
                <div class="block_six white settings_cont">
                    <h2>Oude afdrachtpercentages </h2>
                    <ul class="overview">
                        <li>henk</li>
                        <li>jan </li>
                        <li>piet</li>
                       
                    </ul>
                </div>

            <div id="cont_promo">al 24.145 ton co<span class="sub">2</span> bespaard...</div>
        </div>

    </body>
    
</html>
