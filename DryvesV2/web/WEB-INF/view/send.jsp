<%-- 
    Document   : Messages
    Created on : May 25, 2013, 4:35:31 PM
    Author     : Willem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/messagescript.js"></script>
<!DOCTYPE html>

<div id="sub_menu">
    <a class="block_single white" href="javascript:history.back()"><div class="menu_icon"></div><span class="menu_item menu_label_blue">terug</span></a>		
    <a class="block_single blue" href="outbox"><div class="menu_icon icon_messages">&#xf093;</div><span class="menu_item menu_label_white">Verzonden</span></a>		
    <a class="block_single blue" href="write"><div class="menu_icon icon_messages">&#xf040;</div><span class="menu_item menu_label_white">Schrijven</span></a>		

    <div class="block_triple blue">
<!--        <h2 style="color: #fff;">${user.alias}</h2>-->
        <h3 style="color: #fff;">Je bericht is succesvol verzonden!</h3>
    </div>
    <div id="col_content">

        <ul id="results">

            <li class="block_six white" style="overflow: auto">
                ${message.text}
            </li>
            <li class="block_six white" id="msgBox" style="display:block;"> 
            </li>
        </ul>
    </div>

</div>
