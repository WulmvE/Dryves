<%-- 
    Document   : searchresults
    Created on : May 20, 2013, 4:35:31 PM
    Author     : Patrick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="r"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="sub_menu">
    <div class="block_double blue"><div class="menu_icon icon_results">${aantalrides}</div><span class="menu_item">
            ${aantalrides==1 ? "resultaat" : "resultaten"}
        </span></div>
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

        <c:forEach var="ride" items="${rides}">
            <li class="result block_triple white">
                <div>
                    <img class="avatar" src="img/avatar.jpg" />
                    <a href="#" class="avatar_label">${ride.idMember.alias}</a>
                </div>
                <div class="summary">
                    <span class="route" >${ride.startLocation} <span class="text_green"><></span> ${ride.endLocation}</span><br>
                    <fmt:formatDate pattern="MM/dd/yyyy" value="${ride.departureDate}"/><br>
                    ${ride.seatsAvailable} ${ride.seatsAvailable==1 ? "plaats" : "plaatsen"} vrij<br>
                    <span class="price">&euro; <fmt:formatNumber type="number" pattern="#0.00" value="${ride.askingPrice}" /></span> / Plaats <br>                    
                    <r:rating_stars rating="${ride.idMember.avgRating}"/>
                    <a class="button" href="<c:url value='rideDetails?${ride.idRide}'/>"><img src="img/arrow_right.png" /></a><br>
                </div>
            </li>
        </c:forEach>

    </ul>
</div>

