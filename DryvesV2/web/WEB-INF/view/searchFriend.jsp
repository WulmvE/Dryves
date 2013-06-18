<%-- 
    Document   : searchFriend
    Created on : 14-jun-2013, 13:51:47
    Author     : hctung
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="col_content">

    <div id="search_friend" class="block_six white">		
        <h2>ik zoek een vriend</h2>
        <form name="search_friend_form" method="post">
            <input class="input_first_name" name="search_friend_first_name" type="text"  placeholder="voornaam"/><br/>
            <input class="input_last_name" name="search_friend_last_name" type="text"  placeholder="achternaam"/><br/>

            <input class="input_email"  name="search_friend_email" type="text"  placeholder="email" /><br/>
            <span class="local_menu">               
                <a href="#" onclick="document.search_friend_form.submit()" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>
        </form>
    </div>

    <div id="result_search_friend" class="block_six white">

        <c:forEach var="dryver" items="${dryvers}">
            <li class="result block_triple white">
                <div> 
                    <img class="avatar" src="ava/avatar${dryver.idMember}.jpg" />
                    <a href="#" class="avatar_label">${dryver.alias}</a>

                </div>
                <a class="button" title="Doe friend request" href="<c:url value='requestFriend?requestDryver=${dryver.idMember}'/>"><img src="img/arrow_right.png" /></a>



            </li>
        </c:forEach>

        <c:forEach var="friend" items="${alreadyFriends}">
            <li class="result block_triple white">
                <div> 
                    <img class="avatar" src="ava/avatar${friend.idMember}.jpg" />
                    <a href="#" class="avatar_label">${friend.alias}</a>

                </div>
                <c:forEach var="friendCheck" items="${friend.friendList}" varStatus="loop">
                    <c:choose>
                        <c:when test="${friendCheck.idFriend.idMember == idMember}">
                            <c:if test="${friendCheck.status == true}">
                                <br>
                                <br>
                                <h1 style="color: #66ccff"><span>Vrienden</span></h1>
                            </c:if>
                            <c:if test="${friendCheck.status == false}">
                                <br>
                                <br>
                                <h1 style="color: #66ccff"><span>In aanvraag</span></h1>
                            </c:if>
                        </c:when>
                    </c:choose>

                </c:forEach>


            </li>
        </c:forEach>

    </div>

</div>
