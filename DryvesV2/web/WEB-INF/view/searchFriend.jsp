<%-- 
    Document   : searchFriend
    Created on : 14-jun-2013, 13:51:47
    Author     : hctung
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    ${queryDryver}
                    ${status}
                    
                <c:choose>                
                    <c:when test="${status == 1}">
                        <br>
                        <br>
                        <h1 style="color: #66ccff"><span>Vrienden</span></h1>
                    </c:when>
                    <c:otherwise>
                        <br>
                        <br>
                        <h1 style="color: #66ccff"><span>Geen vrienden</span></h1>        
                    </c:otherwise>
                </c:choose>


            </li>
        </c:forEach>

    </div>

</div>
