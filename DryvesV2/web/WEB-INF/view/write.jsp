<%-- 
    Document   : Messages
    Created on : May 25, 2013, 4:35:31 PM
    Author     : Willem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="col_content">
    <div id="sub_menu">
        <a class="block_single white" href="javascript:history.back()"><div class="menu_icon"></div><span class="menu_item menu_label_blue">terug</span></a>		
        <a class="block_single blue" href="inbox"><div class="menu_icon icon_messages">&#xf003;</div><span class="menu_item menu_label_white">Ontvangen</span></a>		
        <a class="block_single blue" href="outbox"><div class="menu_icon icon_messages">&#xf093;</div><span class="menu_item menu_label_white">Verzonden</span></a>		
    </div>

    <div class="block_triple blue">
        <h2 style="color: #fff;">Verzenden aan: ${idReciever.alias}</h2>
        <br>
    </div>

    <div class="block_triple friends" style=" overflow-x: scroll; overflow-y: hidden;">
        <ul>        
            <c:forEach var="friend" items="${friends}">
                <li class="block_single white">
                    <form name="friendselecter_${friend.idFriend.idMember}" method="post">
                        <input type="hidden" name="idReciever" value="${friend.idFriend.idMember}">
                        <a href="#" class="avatar_label" onclick="document.friendselecter_${friend.idFriend.idMember}.submit();">       
                            <div style="cursor: pointer;">
                                <img class="avatar" style="margin-top: 15px;" src="ava/avatar${friend.idFriend.idMember}.jpg" />
                                <br>${friend.idFriend.alias}
                            </div>
                        </a>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>

        <div class="block_six white" id="writeDiv" style="display: block;">
        <form name="msgForm" method="post" action="send">
            <input type="hidden" name="idMemberReciever" value="${idReciever.idMember}">
            <input type="hidden" name="dateTime" value="11-06-2013">
            <textarea name="msg" style="margin-top: 15px; width: 90%; height: 200px;">Typ hier uw bericht...</textarea>
            <input type="submit" onclick="document.msgForm.submit();">
        </form>
    </div>

</div>
