<%-- 
    Document   : searchresults
    Created on : May 20, 2013, 4:35:31 PM
    Author     : Patrick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="sub_menu">
    <div class="block_double blue"><div class="menu_icon icon_results">${aantalrides}</div><span class="menu_item">
            <c:if test="${aantalrides == 1}">
                resultaat
            </c:if>
            <c:if test="${aantalrides > 1 || aantalrides == 0}">
                resultaten
            </c:if>
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
                    ${ride.departureDate}<br>
                    ${ride.seatsAvailable} stoelen beschikbaar<br>
                    <span class="price">&euro; ${ride.askingPrice}</span> / Plaats <br>
                    <c:if test="${ride.idMember.avgRating > 0.00 && ride.idMember.avgRating <= 2.00}">
                    <span class="rating_small text_green"></span>
                    </c:if>
                    <c:if test="${ride.idMember.avgRating > 2.00 && ride.idMember.avgRating <= 4.00}">
                    <span class="rating_small text_green"></span>
                    </c:if>
                    <c:if test="${ride.idMember.avgRating > 4.00 && ride.idMember.avgRating <= 5.00}">
                    <span class="rating_small text_green"></span>
                    </c:if>
                    <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                </div>

            </li>
        </c:forEach>
    </ul>
</div>

