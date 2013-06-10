<%-- 
    Document   : myDryves
    Created on : 1-jun-2013, 17:02:03
    Author     : hctung
--%>
<%@taglib tagdir="/WEB-INF/tags" prefix="r"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div id="col_content">

    <div class="block_six white">		
        <h2>Mijn profiel</h2>
        <div class="profile block_triple white">
            <div>
                <img class="avatar" src="ava/avatar${idMember}.jpg" />
                <a href="#" class="avatar_label">wijzig foto</a>
            </div>
            <div class="summary">
                <span class="text" style="font-weight: bold">${alias}</span><br>              
                <br>
                <span class="text" style="font-weight: bold">text</span><br>
                <r:rating_stars rating="4"/><br>
                <a href="#">ratings inzien</a>
            </div>
        </div>
    </div>


    <div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">Mijn vrienden</h2>
            </div>
        </div>


        <div class="block_triple friends" style=" overflow-x: scroll; overflow-y: hidden;">
            <ul>        

                <c:forEach var="friend" items="${friends}">
                    <li class="block_single white">
                        <div>
                            <img class="avatar" style="margin-top: 35px;" src="ava/avatar${friend.idFriend.idMember}.jpg" />
                            <a href="#" class="avatar_label">${friend.idFriend.alias}</a>
                    </li>
                </c:forEach>
<!--                <li class="block_single white">
                    <img class="avatar" style="margin-top: 35px;" src="img/avatar.jpg" />
                    <a href="#" class="avatar_label">alias</a>
                </li>
                <li class="block_single white">
                    <img class="avatar" style="margin-top: 35px;" src="img/avatar.jpg" />
                    <a href="#" class="avatar_label">alias</a>
                </li>                <li class="block_single white">
                    <img class="avatar" style="margin-top: 35px;" src="img/avatar.jpg" />
                    <a href="#" class="avatar_label">alias</a>
                </li>
                <li class="block_single white"></li>-->
            </ul>
        </div>
        <div id="sub_menu">
            <div class="block_triple_half white">		
                <h4 style="margin-top: 25px;">knoppenbalk</h4>
            </div>
        </div>
    </div>


    <div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">Aangeboden ritten</h2>
            </div>
        </div>
        <div class="profile_result_block">

            <ul id="results">

                <c:forEach var="ride" items="${rides}">
                    <li class="result block_triple white">
                        <div>
                            <img class="avatar" src="ava/avatar${idMember}.jpg" />
                            <a href="#" class="avatar_label">${ride.idMember.alias}</a>
                        </div>
                        <div class="summary">
                            <span class="route" >${ride.startLocation} <span class="text_green"><></span> ${ride.endLocation}</span><br>
                            <fmt:formatDate pattern="MM/dd/yyyy" value="${ride.departureDate}"/><br>
                            ${ride.seatsAvailable} ${ride.seatsAvailable==1 ? "plaats" : "plaatsen"}<br>
                            <span class="price">&euro; <fmt:formatNumber type="number" pattern="#0.00" value="${ride.askingPrice}" /></span> / Plaats <br>                    
                            <r:rating_stars rating="${ride.idMember.avgRating}"/>
                            <a class="button" href="<c:url value='rideDetails2?${ride.idRide}'/>"><img src="img/arrow_right.png" /></a><br>
                        </div>
                    </li>
                </c:forEach>

            </ul>
        </div>
    </div>

    <div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">Gevraagde ritten</h2>
            </div>
        </div>
        <div class="profile_result_block">

            <ul class="profile_results">

                <c:forEach var="ride" items="${rides_passenger}">
                    <li class="result block_triple white">
                        <div>
                            <img class="avatar" src="ava/avatar${ride.idMember.idMember}.jpg" />
                            <a href="#" class="avatar_label">${ride.idMember.alias}</a>
                        </div>
                        <div class="summary">
                            <span class="route" >${ride.startLocation} <span class="text_green"><></span> ${ride.endLocation}</span><br>
                            <fmt:formatDate pattern="MM/dd/yyyy" value="${ride.departureDate}"/><br>
                            ${ride.seatsAvailable} ${ride.seatsAvailable==1 ? "plaats" : "plaatsen"}<br>
                            <span class="price">&euro; <fmt:formatNumber type="number" pattern="#0.00" value="${ride.askingPrice}" /></span> / Plaats <br>                    
                            <r:rating_stars rating="${ride.idMember.avgRating}"/>
                            <a class="button" href="<c:url value='rideDetails2?${ride.idRide}'/>"><img src="img/arrow_right.png" /></a><br>
                        </div>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </div>
