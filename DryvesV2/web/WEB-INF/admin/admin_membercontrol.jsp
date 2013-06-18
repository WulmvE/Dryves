<%-- 
    Document   : admin membercontrol
    Created on : Jun 3, 2013, 1:53:17 AM
    Author     : Patrick
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="r"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
        <title>Dryves Admin - Ledenbeheer</title>

        <link rel="stylesheet" type="text/css" href="/DryvesV2/css/main.css" />
        <link rel="stylesheet" type="text/css" href="/DryvesV2/css/admin.css" />
        <link rel="stylesheet" type="text/css" href="/DryvesV2/css/jquery-ui.css" />

        <script type="text/javascript" src="/DryvesV2/js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="/DryvesV2/js/jquery-ui.min.js"></script>
    </head>

    <body>

        <div id="main">

            <div id="main_menu">
                <a id="menu_logo" class="block_double green text_white" href="statistics"><div id="logo" class="menu_icon">&#xf06c;</div><span class="menu_item">dryves admin</span></a>
                <a id="menu_stats" class="block_single blue" href="statistics"><div class="menu_icon"></div><span class="menu_item text_white">stastistieken</span></a>
                <a id="menu_settings" class="block_single blue" href="settings"><div class="menu_icon"></div><span class="menu_item text_white">instellingen</span></a>
                <a id="menu_members" class="block_single green" href="membercontrol"><div class="menu_icon"></div><span class="menu_item text_white">leden</span></a>
                <a id="menu_logout" class="block_single blue" href="logout"><div class="menu_icon text_white"></div><span class="menu_item text_white">uitloggen</span></a>		
            </div>

            <div id="col_content" >

                <div class="column_triple_flexh">
                    <div class="block_triple white settings_cont">		
                        <h2 style="margin-bottom: 10px;">Admin toevoegen</h2>
                        <form name="add_admin" action="adminDo" method="post">
                            <input type='hidden' name='task' value='addAdmin' />
                            <input class="selectstats" name="alias" type="text" placeholder="alias" />
                            <span class="local_menu">               
                                <a href="#" onclick="document.add_admin.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
                            </span>
                        </form>
                    </div>

                    <div class="block_triple white settings_cont">		
                        <h2 style="margin-bottom: 10px;">Admin verwijderen</h2>
                        <form name="remove_admin" action="adminDo" method="post">
                            <input type='hidden' name='task' value='removeAdmin' />
                            <input class="selectstats" name="alias" type="text" placeholder="alias" />
                            <span class="local_menu">               
                                <a href="#" onclick="document.remove_admin.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
                            </span>
                        </form>
                    </div>


                    <div class="block_triple_half blue">		
                        <h2 style="color: white">Huidige Admins</h2>
                    </div>

                    <ul class="member_list">        

                        <c:forEach var="admin" items="${admins}">
                            <li class="block_single white">
                                <div style="position:relative; top:10px">
                                    <img class="avatar" style="margin-top: 15px;" src="ava/avatar${admin.dryver.idMember}.jpg" />
                                    <a href="#" class="avatar_label">${admin.dryver.alias}</a>
                                </div>   
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="column_triple_flexh">
                    <div class="block_triple white settings_cont">		
                        <h2 style="margin-bottom: 10px;">Lid blokkeren</h2>
                        <form name="block_member" action="adminDo" method="post">
                            <input type='hidden' name='task' value='blockMem' />
                            <input class="selectstats" name="alias" type="text" placeholder="alias" />
                            <span class="local_menu">               
                                <a href="#" onclick="document.block_member.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
                            </span>
                        </form>
                    </div>

                    <div class="block_triple white settings_cont">		
                        <h2 style="margin-bottom: 10px;">Lid vrijgeven</h2>
                        <form name="unblock_member" action="adminDo" method="post">
                            <input type='hidden' name='task' value='unblockMem' />
                            <input class="selectstats" name="alias" type="text" placeholder="alias" />
                            <span class="local_menu">               
                                <a href="#" onclick="document.unblock_member.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
                            </span>
                        </form>
                    </div>

                    <div class="block_triple_half blue">		
                        <h2 style="color: white">Geblokkeerde leden</h2>
                    </div>

                    <ul class="member_list">        

                        <c:forEach var="blocked_member" items="${blocked_members}">
                            <li class="block_single white">
                                <div style="position:relative; top:10px">
                                    <img class="avatar" style="margin-top: 15px;" src="ava/avatar${blocked_member.idMember}.jpg" />
                                    <a href="#" class="avatar_label">${blocked_member.alias}</a>
                                </div>   
                            </li>
                        </c:forEach>
                    </ul>



                </div>

                <div id="cont_promo">al 24.145 ton co<span class="sub">2</span> bespaard...</div>
            </div>

    </body>

</html>
