<%-- 
    Document   : Messages
    Created on : May 25, 2013, 4:35:31 PM
    Author     : Willem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="col_content">

    <ul id="results">

        <li class="block_six white" style="overflow: auto">

            <c:forEach var="message" items="${messages}">
                <form name="message_form_${message.idMessage}" method="POST">
                    <input type="hidden" name="idMessage" value="${message.idMessage}"/>
                    <input type="hidden" name="idSender" value="${message.idMemberSender}"/>
                    <input type="hidden" name="dateTime" value="${message.dateTime}"/>
                </form>
                <hr>
                <span class="text_blue" style="float: left; margin-left: 5px;">Afzender: <a href="${message.idMemberSender}">${message.idMemberSender.alias}</a>, 
                    datum: </span><span style="float: left; margin-left: 0.5em;"> ${message.dateTime}</span>
                <span><a href="#" onclick="document.message_form_${message.idMessage}.submit();"><img class="view_message" src="img/arrow_right.png" alt="Toon bericht"></a></span>
                <br>
                <hr>
            </c:forEach>
        </li>
        <li class="block_six white">
            <c:forEach var="single" items="${singleMessage}">
                <span style="float: left; padding-left: 5px;">Ontvangen van:</span><span id="msgTitle" class="text_blue">${single.idMemberSender.alias}</span>
                <br>
                <hr>
                <span id="msgText"class="text_blue">${single.text}</span><br>
            </c:forEach>
            <c:forEach var="sent" items="${sent}">
                <span style="float: right; padding-left: 5px;">Verzonden door u:</span>
                <br>
                <hr>
                <span id="msgText"class="text_blue">${sent.text}</span><br>
            </c:forEach>
        </li>
    </ul>
</div>
<div id="sub_menu">
    <a class="block_single white" href="javascript:history.back()"><div class="menu_icon"></div><span class="menu_item menu_label_blue">terug</span></a>		
    <a class="block_single blue" href="#"><div class="menu_icon icon_messages">&#xf093;</div><span class="menu_item menu_label_white">Verzonden</span></a>		
    <a class="block_single blue" href="#"><div class="menu_icon icon_messages">&#xf040;</div><span class="menu_item menu_label_white">Schrijven</span></a>		

    <div class="block_triple blue">
<!--        <div style="height: 71%; margin-top: 15px;">
            <form name="message_send" method="POST">
                <textarea name="typed" rows="4" cols="29" style="width: 70%; font-size: 14px;"></textarea>
                <input type="submit" style="width: 25%; color: #444; font-size:18px; margin-left: 1%; position: relative; top: -35px;">
            </form>
        </div>
        <span class="menu_item">Reageer</span>-->
    </div>
</div>
