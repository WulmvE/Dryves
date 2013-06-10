<%-- 
    Document   : Messages
    Created on : May 25, 2013, 4:35:31 PM
    Author     : Willem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/messagescript.js"></script>
<!DOCTYPE html>

<div id="col_content">
    <div id="sub_menu">
        <a class="block_single white" href="javascript:history.back()"><div class="menu_icon"></div><span class="menu_item menu_label_blue">terug</span></a>		
        <a class="block_single blue" href="inbox"><div class="menu_icon icon_messages">&#xf003;</div><span class="menu_item menu_label_white">Ontvangen</span></a>		
        <a class="block_single blue" href="outbox"><div class="menu_icon icon_messages">&#xf093;</div><span class="menu_item menu_label_white">Verzonden</span></a>		

        <div class="block_triple blue">

        </div>
        <div class="block_triple friends" style=" overflow-x: scroll; overflow-y: hidden;">
            <ul>        
                <c:forEach var="friend" items="${friends}">
                    <li class="block_single white">
                        <form name="friendselecter_${friend.idFriend.idMember}" method="post">
                            <input type="hidden" name="idMemberReciever" value="${friend.idFriend.idMember}">
                            <a href="#" class="avatar_label" onclick="openwrite();
                                    document.friendselecter_${friend.idFriend.idMember}.submit()">       
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

        <li class="block_six white" id="toHideDiv"></li>
        <li class="block_six white" id="writeDiv" style="display:none;">
            <form name="msgForm" method="post">
                <input type="hidden" name="idMemberReciever" value="${idReciever}">
                <input type="hidden" name="dateTime" value="07-06-2013">
                <h2 style="text-align: right;">Verzenden aan: ${idReciever.alias}</h2>
                <br>
                <textarea name="msg" rows="5" col="20">Typ hier uw bericht...</textarea>
                <input type="submit" onclick="document.msgForm.submit();">
            </form>
        </li>
        </ul>
    </div>

</div>
