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
            <div style="position:relative; display: inline-block; width:77px; height:77px;">
                <img class="avatar" src="ava/avatar000.jpg" style="position:absolute; top:0px; left:0px;"/>
                <img class="avatar" src="ava/avatar${profileDryver.idMember}.jpg" style="position:absolute; top:0px; left:0px;" />
            </div>
            <div class="summary">
                <span class="text" style="font-weight: bold">${profileDryver.alias}</span><br>              
                <br>
                <!--                <span class="text" style="font-weight: bold; color: red;">Welke statistieken moeten hier nog meer komen?</span><br>-->
                <span>gemiddelde rating:</span><br>
                <r:rating_stars rating="${profileDryver.avgRating}"/><br> 
                <br>
                <a href="changeProfile" class="avatar_label">wijzig profiel</a>
            </div>
        </div>
    </div>


    <div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">
                <ul>
                    <li class="menu_icon_half blue" style="width: 380px; font-family: segoe">
                        <h2 style="color: white">Mijn vrienden</h2>
                    </li>
                    <li class="menu_icon_half blue" style="float: end">
                        <a href="searchFriend"><div class="menu_icon_half icon_messages">&#xf002;
                                <span class="menu_item_half menu_label_white">Zoeken</span>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>


        <div class="block_triple friends white" style=" overflow-x: scroll; overflow-y: hidden;">
            <ul>        

                <c:forEach var="friend" items="${friends}">
                    <li class="block_single white">
                        <div>
                            <img class="avatar" style="margin-top: 15px;" src="ava/avatar${friend.idFriend.idMember}.jpg" />
                            <a href="#" class="avatar_label">${friend.idFriend.alias}</a>
                            <c:if test="${friend.status == true}">
                                <span style="color: green">Vrienden</span>
                            </c:if>
                            <c:if test="${profileDryver.idMember == friend.idRequester}">
                                <span>Pending</span>   
                            </c:if>
                            <c:if test="${profileDryver.idMember != friend.idRequester}">
                                KNOP
                            </c:if>   
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
        <div id="sub_menu" style="margin: 0;padding: 0;">
            <div class="block_triple_half white">		
                <ul>
                    <li class="menu_icon_half blue">
                        <a href="inbox"><div class="menu_icon_half icon_messages">&#xf003;
                                <span class="menu_item_half menu_label_white">Inbox</span>
                            </div>
                        </a>
                    </li>
                    <li class="menu_icon_half blue">
                        <a href="outbox"><div class="menu_icon_half icon_messages">&#xf093;
                                <span class="menu_item_half menu_label_white">Outbox</span>
                            </div>
                        </a>
                    </li>
                    <li class="menu_icon_half blue">
                        <a href="write"><div class="menu_icon_half icon_messages">&#xf040;
                                <span class="menu_item_half menu_label_white">Schrijf</span>
                            </div>
                        </a>
                    </li>
                </ul>
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
                                <c:forEach var="negotiation" items="${ride.negotiationList}" varStatus="loop">
                                    <c:if test="${negotiation.negotiationPK.idMember == profileDryver.idMember}">
                                        <c:if test="${negotiation.ratingdone == false}">
                                        Geef rating: <a href="<c:url value='giveRating?idRide=${negotiation.negotiationPK.idRide}&score=1'/>">*</a>
                                        <a href="<c:url value='giveRating?idRide=${negotiation.negotiationPK.idRide}&score=2'/>">**</a>
                                        <a href="<c:url value='giveRating?idRide=${negotiation.negotiationPK.idRide}&score=3'/>">***</a>
                                        <a href="<c:url value='giveRating?idRide=${negotiation.negotiationPK.idRide}&score=4'/>">****</a>
                                        <a href="<c:url value='giveRating?idRide=${negotiation.negotiationPK.idRide}&score=5'/>">*****</a>
                                    </c:if>
                                    <c:if test="${negotiation.ratingdone == true}">
                                        rating al gegeven
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </div>
