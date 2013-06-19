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
    <div class="block_double blue text_white"><div class="menu_icon icon_results">${aantalrides}</div><span class="menu_item">
            ${aantalrides==1 ? "resultaat" : "resultaten"}
        </span></div>
    <a class="block_single white text_blue" href="index.jsp"><div class="menu_icon"></div><span class="menu_item menu_label_blue">opnieuw</span></a>

    <div class="block_double white text_blue">
        <div class="menu_icon">			
            <ul class="sort_options" style="min-width:70px;">
                <li data-sort_type="byDate" class="sort_option active_up">datum</li>
                <li data-sort_type="byPrice" class="sort_option">prijs</li>
                <li data-sort_type="byRating" class="sort_option">rating</li>
            </ul>			
        </div>
        <span class="menu_item menu_label_blue">sorteren op</span>
    </div>

    <a class="block_single white text_blue" href="#"><div class="menu_icon" style="font-size:60px; height: 110px; padding: 10px 5px 0px 0px;">&#xf053;</div><span class="menu_item menu_label_blue">terug</span></a>		
</div>

<div id="col_content">
    <ul id="results">

        <c:forEach var="ride" items="${rides}">
            <li class="result block_triple white">
                <div style="width:80px">
                    <img class="avatar" src="ava/avatar${ride.idMember.idMember}.jpg" />
                    <a href="#" class="avatar_label">${ride.idMember.alias}</a>
                </div>
                <div class="summary">
                    <span class="route" >${ride.startLocation} <span class="text_green"><></span> ${ride.endLocation}</span><br>
                    <span class="date" data-timestamp="${ride.departureDate.time}"><fmt:formatDate pattern="dd/MM/yyyy" value="${ride.departureDate}"/></span><br>
                    ${ride.seatsAvailable} ${ride.seatsAvailable==1 ? "plaats" : "plaatsen"} vrij<br>
                    <span class="price">&euro; <fmt:formatNumber type="number" pattern="#0.00" value="${ride.askingPrice}" /></span> / Plaats <br>                    
                    <r:rating_stars rating="${ride.idMember.avgRating}"/>                
                </div>

                <span class="local_menu">                    
                    <a href="<c:url value='rideDetails?${ride.idRide}'/>" onclick="" class="local_menu_button larger" title="ritdetails">&#xf0fb;</a>	
                </span>
            </li>
        </c:forEach>

    </ul>
    
    
    
</div>

