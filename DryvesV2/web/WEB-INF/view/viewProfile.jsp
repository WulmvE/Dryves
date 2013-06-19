<%-- 
    Document   : viewProfile
    Created on : Jun 17, 2013, 11:56:22 AM
    Author     : Maartje
--%>

<%@taglib tagdir="/WEB-INF/tags" prefix="r"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<div id="col_content">

        <div id="quick_create" class="block_six white">		
        <h2>${aliasFriend}</h2>
            <img class="avatargroot" src="ava/avatar${dryverFriend.idMember}.jpg" /> <br>
            <r:rating_stars rating="${dryverFriend.avgRating}"/>
        </div>
        
        <div id="quick_search" class="block_six white">		
        <h2>dryver gegevens</h2><br>
        <p> </p><br>
        <p> </p><br>
            <table>
                <tr>
                    <td>     </td>
                    <th class="c" align="left">Jullie zijn geen Dryves-vrienden</th>
                </tr>
                <tr>
                    <td>     </td>
                    <td class="c" align="left">Daarom worden de gegevens van ${dryverFriend.alias} niet getoond.</td>
                </tr>
            </table>
         
        
    </div>
    
                
    <div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">aangeboden ritten</h2>
            </div>
        </div>
        <div class="profile_result_block">

            <ul id="results">

                <c:forEach var="rideDryverProfile" items="${rideDryverProfiles}">
                    <li class="result block_triple white">
                        <div>
                            <img class="avatar" src="ava/avatar${rideDryverProfile.idMember.idMember}.jpg" />
                            <a href="#" class="avatar_label">${rideDryverProfile.idMember.alias}</a>
                        </div>
                        <div class="summary">
                            <span class="route" >${rideDryverProfile.startLocation} <span class="text_green"><></span> ${rideDryverProfile.endLocation}</span><br>
                            <fmt:formatDate pattern="MM/dd/yyyy" value="${rideDryverProfile.departureDate}"/><br>
                            ${rideDryverProfile.seatsAvailable} ${rideDryverProfile.seatsAvailable==1 ? "plaats" : "plaatsen"}<br>
                            <span class="price">&euro; <fmt:formatNumber type="number" pattern="#0.00" value="${rideDryverProfile.askingPrice}" /></span> / Plaats <br>                    
                            <r:rating_stars rating="${rideDryverProfile.idMember.avgRating}"/>
                            <a class="button" href="<c:url value='rideDetails2?${rideDryverProfile.idRide}'/>"><img src="img/arrow_right.png" /></a><br>  
                        </div>
                    </li>
                </c:forEach>
     </ul>
       
        </div>
</div>

    
</div>
            
