<%-- 
    Document   : createRide
    Created on : 26-mei-2013, 19:13:28
    Author     : Vincent
--%>

<div id="col_content">
<form name="quick_create_form" method="post" action="createRideConfirmed">
    <div id="quick_search" class="block_six white">		
        <h2>rit aanmaken</h2><br/><br/><br/><br/>
            <input class="input_location" name="create_start" type="text"  placeholder="van" value="${create_start}"/><br/>
            <input class="input_location" name="create_destination" type="text"  placeholder="naar" value="${create_end}"/><br/>
            <input class="input_date"  name="create_date" type="text"  placeholder="op" value="${create_date}" /><br/>
    </div>

    <div id="quick_create" class="block_six white">		
        <h2>details</h2><br/><br/><br/><br/>
        
            <input class="input_car" name="create_carid" type="text"  placeholder="auto" value="${create_car}"/><br/>
            <input class="input_numberofseats" name="create_num_seats" type="text"  placeholder="aantal zitplaatsen"/><br/>
            <input class="input_price"  name="create_price" type="text"  placeholder="prijs (&euro;)"/><br/>
            <a href="#" onclick="document.quick_create_form.submit()" class="button" id="button_create"><img src="img/arrow_right.png" alt="create button" /></a>
    </div>
</form>

</div>