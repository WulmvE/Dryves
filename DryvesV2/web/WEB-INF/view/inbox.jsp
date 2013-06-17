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
    <a class="block_single white text_blue" href="javascript:history.back()"><div class="menu_icon"></div><span class="menu_item menu_label_blue">terug</span></a>		
    <a class="block_single blue" href="outbox"><div class="menu_icon icon_messages">&#xf093;</div><span class="menu_item menu_label_white">Verzonden</span></a>		
    <a class="block_single blue" href="write"><div class="menu_icon icon_messages">&#xf040;</div><span class="menu_item menu_label_white">Schrijven</span></a>		

    <div class="block_triple blue">
        <h2 style="color:#fff;">Inbox van: ${user.alias}</h2>
    </div>
<div id="col_content">
    
    <ul id="results">

        <li class="block_six white" style="overflow: auto">

            <c:forEach var="message" items="${messages}">
                <form name="message_form_${message.idMessage}" method="post">
                    <input type="hidden" name="idMessage" value="${message.idMessage}"/>
                    <input type="hidden" name="idSender" value="${message.idMemberSender.idMember}"/>
                    <input type="hidden" name="dateTime" value="${message.dateTime}"/>
                
                <hr>
                <span class="text_blue" style="float: left; margin-left: 5px;">Afzender: <a href="#">${message.idMemberSender.alias}</a>, 
                    datum: </span><span style="float: left; margin-left: 0.5em;"> ${message.dateTime}</span>
                    <span><a href="#" onclick="document.message_form_${message.idMessage}.submit();"><img class="view_message" src="img/arrow_right.png" alt="Toon bericht"></a></span>
                <br>
                <hr>
                </form>
            </c:forEach>
        </li>
        <li class="block_six white" id="msgBox" style="display:block;"> 
            <c:forEach var="single" items="${singleMessage}">
                <hr>
                <span style="float: left; padding-left: 5px;">Ontvangen van:</span><span id="msgTitle" class="text_blue">${single.idMemberSender.alias}</span>
                <br>
                <hr>
                <span id="msgText"class="text_blue">${single.text}</span><br>
            </c:forEach>
        </li>
    </ul>
</div>

</div>
