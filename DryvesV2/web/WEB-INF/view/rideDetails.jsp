<%-- 
    Document   : rideDetails
    Created on : 27-mei-2013, 18:47:53
    Author     : hctung
--%>

<%@page import="entity.Car"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="r"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
    var directionDisplay;
    var directionsService = new google.maps.DirectionsService();
    function initialize() {
        var latlng = new google.maps.LatLng(55.836858, 4.699463);
        directionsDisplay = new google.maps.DirectionsRenderer();
        var myOptions = {
            zoom: 14,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            mapTypeControl: false
        };
        var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
        directionsDisplay.setMap(map);
    }
    function calcRoute() {
        var start = "${selectedRide.startLocation}";
        var end = "${selectedRide.endLocation}";
        var request = {
            origin: start,
            destination: end,
            travelMode: google.maps.DirectionsTravelMode.DRIVING
        };
        directionsService.route(request, function(response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
                document.getElementById('distance').innerHTML += Math.round(Number(response.routes[0].legs[0].distance.value /1000)) + " km";
            } else {
                if (status == 'ZERO_RESULTS') {
                    alert('No route could be found between the origin and destination.');
                } else if (status == 'UNKNOWN_ERROR') {
                    alert('A directions request could not be processed due to a server error. The request may succeed if you try again.');
                } else if (status == 'REQUEST_DENIED') {
                    alert('This webpage is not allowed to use the directions service.');
                } else if (status == 'OVER_QUERY_LIMIT') {
                    alert('The webpage has gone over the requests limit in too short a period of time.');
                } else if (status == 'NOT_FOUND') {
                    alert('At least one of the origin, destination, or waypoints could not be geocoded.');
                } else if (status == 'INVALID_REQUEST') {
                    alert('The DirectionsRequest provided was invalid.');
                } else {
                    alert("There was an unknown error in your request. Requeststatus: \n\n" + status);
                }
            }
        });
    }

    function go() {
        initialize();
        calcRoute();
    }
    window.onload = go;
</script>

<div id="col_content">

    <div class="block_six white">
        <div class="result block_triple white">
            <div>
                <img class="avatar" src="ava/avatar${selectedRide.idMember.idMember}.jpg" />
                <a href="#" class="avatar_label">${selectedRide.idMember.alias}</a>
            </div>
            <div class="summary">
                <span class="route" >${selectedRide.startLocation} <span class="text_green"><></span> ${selectedRide.endLocation}</span><br>
                <span class="route">   <div class ="route" id="distance">Afstand: </div></span><br>
                <fmt:formatDate pattern="MM/dd/yyyy" value="${selectedRide.departureDate}"/><br>
                ${selectedRide.seatsAvailable} ${selectedRide.seatsAvailable==1 ? "plaats" : "plaatsen"}<br>
                <span class="price">&euro; <fmt:formatNumber type="number" pattern="#0.00" value="${selectedRide.askingPrice}" /></span> / Plaats <br>                    
                <r:rating_stars rating="${selectedRide.idMember.avgRating}"/>

            </div>
        </div>
        <div class="result block_triple white">
            
          
            <form action="<c:url value='rideDetails?${selectedRide.idRide}'/>" method="post">   
                  <input type="hidden"
                       name="requestRide"
                       value="${selectedRide.idRide}">
                <input type="hidden"
                       name="requestNegotiation"
                       value="1">  
                <input type="submit"
                       name="submit"
                       value="aanmelden">
            </form>



            <button onclick="location.href = '#'">Meld aan</button> <button onclick="location.href = '#'">Stuur Bericht</button> <button onclick="location.href = '#'">Friend Request</button><br>
            Passagiers van deze rit: <br>
            <c:forEach var="negotiation" items="${selectedRide.negotiationList}">
                <c:if test="${negotiation.acceptedDriver == 1 && negotiation.acceptedPassenger == 1}">
                    Confirmed: ${negotiation.dryver.alias}<br>
                </c:if>
                <c:if test="${negotiation.acceptedDriver != 1 || negotiation.acceptedPassenger != 1}">
                    Pending: ${negotiation.dryver.alias}<br>
                </c:if>
            </c:forEach>
        </div>
    </div>

    <div class="block_six white">		
        <div id="map_canvas" style="width:100%; height:100%"></div>
    </div>

</div>
