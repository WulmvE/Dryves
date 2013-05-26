<%-- 
    Document   : Messages
    Created on : May 25, 2013, 4:35:31 PM
    Author     : Willem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="sub_menu">
    <div class="block_double blue"><div class="menu_icon icon_results"></div><span class="menu_item"></span></div>
    <a class="block_single white" href="index.jsp"><div class="menu_icon"></div><span class="menu_item menu_label_blue">opnieuw</span></a>

    <div class="block_double white">
        <div class="menu_icon">			
            <ul class="sort_options">
                <li class="sort_option active_up">tijdsdtip</li>
                <li class="sort_option">gebruiker</li>
                <li class="sort_option">rating</li>
            </ul>			
        </div>
        <span class="menu_item menu_label_blue">sorteren op</span>
    </div>

    <a class="block_single white" href="#"><div class="menu_icon"></div><span class="menu_item menu_label_blue">terug</span></a>		
</div>

<div id="col_content">

    <ul id="results">
        
            <li class="block_six white" style="overflow: auto">
                <form name="message_form" method="POST">
                <c:forEach var="message" items="${messages}">  
                    <input type="hidden" name="idMessage" value="${message.idMessage}"/>
<!--                    <input type="hidden" name="idSender" value="${message.idMemberSender}"/>
                    <input type="hidden" name="dateTime" value="${message.dateTime}"/>-->
                    <hr>
                    <span class="text_blue" style="float: left; margin-left: 5px;">Afzender: <a href="#">${message.idMemberSender.alias}</a>, 
                        datum: </span><span style="float: left; margin-left: 0.5em;"> ${message.dateTime}</span>
                    <span><a href="#" onclick="document.message_form.submit();"><img class="view_message" src="img/arrow_right.png"></a></span>
                    <br>
                    <hr>
            </c:forEach>
                </form>
            </li>
            <li class="block_six blue">
                <c:forEach var="single" items="${singleMessage}">
                <span class="text_white">${single.text}</span>
                </c:forEach>
            </li>
    </ul>
</div>