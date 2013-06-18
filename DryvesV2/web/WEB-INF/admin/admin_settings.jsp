<%-- 
    Document   : admin settings
    Created on : Jun 3, 2013, 1:53:17 AM
    Author     : Patrick
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
        <title>Dryves Admin - Instellingen</title>

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
                <a id="menu_settings" class="block_single green" href="settings"><div class="menu_icon"></div><span class="menu_item text_white">instellingen</span></a>
                <a id="menu_members" class="block_single blue" href="membercontrol"><div class="menu_icon"></div><span class="menu_item text_white">leden</span></a>
                <a id="menu_logout" class="block_single blue" href="logout"><div class="menu_icon text_white"></div><span class="menu_item text_white">uitloggen</span></a>		
            </div>

            <div id="col_content" >

                <div class="block_six white settings_cont">
                    <h2>Afdrachtpercentage instellen</h2>

                    <form name="modify_perc" action="adminDo" method="post">
                        <input type='hidden' name='task' value='setPerc' />
                        <input type="text" name="perc" value="${current}" style="width:50px; margin-top: 50px;"/> <span style="font-size: 22px">% van de ritprijs</span>
                        <span class="local_menu">               
                            <a href="#" onclick="document.modify_perc.submit();" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
                        </span>
                </div>

                <div class="block_six white settings_cont">
                    <h2>Overzicht Percentages </h2>
                    <div style="overflow-y: auto; height: 200px;">
                        <table class="results">                          

                            <c:forEach var="percentage" items="${percentages}">
                                <tr>
                                    <td class=""> <fmt:formatDate pattern="MM/dd/yyyy" value="${percentage.dateFrom}"/></td>
                                    <td class=""> ${percentage.size} %</td>
                                </tr>
                            </c:forEach>
                        </table> 
                    </div>
                </div>

                <div id="cont_promo">al 24.145 ton co<span class="sub">2</span> bespaard...</div>
            </div>

    </body>

</html>
