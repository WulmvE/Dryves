<%-- 
    Document   : myDryves
    Created on : 1-jun-2013, 17:02:03
    Author     : hctung
--%>

<!--<div id="col_content">
    <div id="sub_menu">
    <div class="block_triple_half white">
        <h2>Mijn profiel</h2>
    </div>
        <div class="result block_triple white">
            <div>
                <img class="avatar" src="img/avatar.jpg" />
                <a href="#" class="avatar_label">${ride.idMember.alias}</a>
            </div>
            <div class="summary">
                <span class="route" >${selectedRide.startLocation} <span class="text_green"><></span> ${selectedRide.endLocation}</span><br>
                <fmt:formatDate pattern="MM/dd/yyyy" value="${selectedRide.departureDate}"/><br>
${selectedRide.seatsAvailable} ${selectedRide.seatsAvailable==1 ? "plaats" : "plaatsen"} vrij<br>
<span class="price">&euro; <fmt:formatNumber type="number" pattern="#0.00" value="${selectedRide.askingPrice}" /></span> / Plaats <br>                    
<r:rating_stars rating="${selectedRide.idMember.avgRating}"/>

</div>
</div>
<div class="result block_triple white">
    <button onclick="location.href = '#'">Meld aan</button> <button onclick="location.href = '#'">Stuur Bericht</button> <button onclick="location.href = '#'">Friend Request</button><br>
    knoppen komen hier<br>
    TODO: maak nieuwe div zonder mouseOver
</div>

<h2>ik wil meerijden</h2>
<form name="quick_search_form" method="post" action="searchresults">
    <input class="input_location" name="search_start" type="text"  placeholder="van"/><br/>
    <input class="input_location" name="search_destination" type="text"  placeholder="naar"/><br/>
    <input class="input_date"  name="search_date" type="text"  placeholder="op" /><br/>
    <a href="#" onclick="document.quick_search_form.submit()" class="button" id="button_search"><img src="img/arrow_right.png" alt="search button" /></a>
</form>
</div>
</div>

<div id="col_content">
<div id="sub_menu">
<div class="block_triple_half blue">		
<h2 style="color: white">Mijn vrienden</h2>
</div>
</div>
<div id="col_content" style="width: 456px; height: 228px; max-height: 228px; overflow: auto;">

<ul id="profile_results">

<li class="profile_result block_single white">
</li>
<li class="profile_result block_single white">
</li>
<li class="profile_result block_single white">
</li>
<li class="profile_result block_single white">
</li>
<li class="profile_result block_single white">
</li>
<li class="profile_result block_single white">
</li>

</ul>

</div>
</div>-->
<div id="col_content">

    <div class="block_six white">		
        <h2>Mijn profiel</h2>

    </div>

    <div class="block_six white">		
        <h2>Mijn vrienden</h2>

    </div>
</div>


<div id="col_content">
    <div id="sub_menu">
        <div class="block_triple_half blue">		
            <h2 style="color: white">Aangeboden ritten</h2>
        </div>
    </div>
    <div id="col_content" style="width: 456px; height: 100%; max-height: 228px; overflow: auto;">

        <ul id="profile_results">

            <li class="profile_result block_triple_half white">
                <div class="summary">
                    <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                    <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                    <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                </div>
            </li>
            <li class="profile_result block_triple_half white">
                <div class="summary">
                    <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                    <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                    <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                </div>
            </li>
            <li class="profile_result block_triple_half white">
                <div class="summary">
                    <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                    <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                    <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                </div>
            </li>
            <li class="profile_result block_triple_half white">
                <div class="summary">
                    <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                    <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                    <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                </div>
            </li>
        </ul>

    </div>
</div>

<div id="col_content">
    <div id="sub_menu">
        <div class="block_triple_half blue">		
            <h2 style="color: white">Gevraagde ritten</h2>
        </div>
    </div>
    <div id="col_content" style="width: 456px; height: 100%; max-height: 228px; overflow: auto;">

        <ul id="profile_results">

            <li class="profile_result block_triple_half white">
                <div class="summary">
                    <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                    <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                    <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                </div>
            </li>
            <li class="profile_result block_triple_half white">
                <div class="summary">
                    <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                    <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                    <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                </div>
            </li>

        </ul>

    </div>
</div>
