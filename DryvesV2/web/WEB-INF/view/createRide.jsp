<%-- 
    Document   : createRide
    Created on : 26-mei-2013, 19:13:28
    Author     : Vincent
--%>

<div id="col_content">
    <form name="quick_create_form" method="post" action="createRideConfirmed">
        <div id="quick_search" class="block_six white">		
            <h2>rit aanmaken</h2><br/><br/><br/><br/>
            <input class="input_location" name="create_start" type="text"  placeholder="van" value="${create_start}" required title="vul de startlocatie in"/><br/>
            <input class="input_location" name="create_destination" type="text"  placeholder="naar" value="${create_end}" required title="vul de eindbestemming in"/><br/>
            <input class="input_date"  name="create_date" type="text"  placeholder="op" value="${create_date}" required pattern="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d" title="vul de vertrekdatum in"/><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>details</h2><br/><br/><br/><br/>

            <input class="input_car" name="create_carid" type="text"  placeholder="auto" value="${create_car}" required title="vul het automerk in"/><br/>
            <input class="input_numberofseats" name="create_num_seats" type="text"  placeholder="aantal zitplaatsen" required title="vul het aantal zitplaatsen in" pattern="[-+]?[0-9]*[.,]?[0-9]+"/><br/>
            <input class="input_price"  name="create_price" type="text"  placeholder="prijs (&euro;)" required title="vul de prijs per zitplaats in" pattern="[-+]?[0-9]*[.,]?[0-9]+"/><br/>
            <span class="local_menu">               
                <input class="submit_reg" type="submit" value="&#xf0fb;">
            </span>
        </div>
    </form>

</div>