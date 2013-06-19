<%-- 
    Document   : viewFriendProfile
    Created on : Jun 17, 2013, 11:55:51 AM
    Author     : Maartje
--%>
<%@taglib tagdir="/WEB-INF/tags" prefix="r"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<div id="col_content">

        <div id="quick_create" class="block_six white">		
        <h2>${dryverFriend.alias}</h2>
            <img class="avatargroot" src="ava/avatar${dryverFriend.idMember}.jpg" /> <br>
            <r:rating_stars rating="${dryverFriend.avgRating}"/>
        </div>
    
    <div id="quick_search" class="block_six white">		
        <h2>dryver gegevens</h2><br>
        <p> </p><br>
        <p> </p><br>
            <table>
                <tr>
                    <td>  </td>
                    <th class="c" align="left">naam</th>
                    <td>  </td>
                    <td class="c" align="left">${dryverFriend.firstName} ${dryverFriend.adjective} ${dryverFriend.lastName} </td>
                </tr>
                <tr>
                    <td>  </td>
                    <th class="c" align="left">woonplaats</th>
                    <td>  </td>
                    <td class="c" align="left">${dryverFriend.city}</td>
                </tr>
                <tr>
                    <td>  </td>
                    <th class="c" align="left">email</th>
                    <td>  </td>
                    <td class="c" align="left">${dryverFriend.email} </td>
                </tr>
                <tr>
                    <td>  </td>
                    <th class="c" align="left">geboortedag</th>
                    <td>  </td>
                    <td class="c" align="left">${birthDate}</td>
                </tr>
            </table><br> 
                
    </div>
            
<div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">aangeboden ritten</h2>
            </div>
        </div>
        <div class="profile_result_block">

            <ul id="results">

                <c:forEach var="rideDryverFriend" items="${rideDryverFriends}">
                    <li class="result block_triple white">
                        <div>
                            <img class="avatar" src="ava/avatar${rideDryverFriend.idMember.idMember}.jpg" />
                            <a href="#" class="avatar_label">${rideDryverFriend.idMember.alias}</a>
                        </div>
                        <div class="summary">
                            <span class="route" >${rideDryverFriend.startLocation} <span class="text_green"><></span> ${rideDryverFriend.endLocation}</span><br>
                            <fmt:formatDate pattern="MM/dd/yyyy" value="${rideDryverFriend.departureDate}"/><br>
                            ${rideDryverFriend.seatsAvailable} ${rideDryverFriend.seatsAvailable==1 ? "plaats" : "plaatsen"}<br>
                            <span class="price">&euro; <fmt:formatNumber type="number" pattern="#0.00" value="${rideDryverFriend.askingPrice}" /></span> / Plaats <br>                    
                            <r:rating_stars rating="${rideDryverFriend.idMember.avgRating}"/>
                            <a class="button" href="<c:url value='rideDetails2?${rideDryverFriend.idRide}'/>"><img src="img/arrow_right.png" /></a><br>  
                        </div>
                    </li>
                </c:forEach>
     </ul>
       
        </div>
</div>

<div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">auto's</h2>
            </div>
        </div>
        <div class="profile_result_block">

            <ul id="results">

                <c:forEach var="carDryverFriend" items="${carDryverFriends}">
                    <li class="result block_triple white">
                        <div>
                            <img class="avatar" src="car/car${carDryverFriend.idCar}.jpg" />
                        </div>
                        <div class="summary">
                            <span class="route" >${carDryverFriend.brand} </span> <br>
                            <br>
                            <span> ${carDryverFriend.numSeats} plaatsen </span> <br>
                        </div>
                    </li>
                </c:forEach>
     </ul>
       
        </div>
</div>
