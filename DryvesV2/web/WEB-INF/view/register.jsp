<%-- 
    Document   : register
    Created on : 7-jun-2013, 11:14:56
    Author     : Vincent
--%>

<script type="text/javascript">

    function initStats() {
        $element = $('selectstat').first();
        $('.selectstats li:first').trigger('click');
    }
    
    function capitalize(string){
        
        return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
    }

    function getCarData(lp) {
        
        //as long there are less than 8 charaters in the associated text input, don't call getJSON.
        if(lp.length<8){
            return;
        }
        
        $.getJSON("getCar?lp=" + lp, function(data) {

            if (typeof data.d.results[0] !== undefined) {
                br = capitalize(data.d.results[0].Merk);
                tp = capitalize(data.d.results[0].Handelsbenaming);
                zp = parseInt(data.d.results[0].Aantalzitplaatsen)-1;

                $(input_carBrand).val(br + " " + tp).removeAttr('disabled');
                $(input_numSeats).val(zp).removeAttr('disabled');
            }
        });
    }

    $(function() {
        $('#input_licensePlate').bindWithDelay("keyup", function(e) {
            getCarData($(e.target).val());
        }, 100);
        
//        $('#input_licensePlate').change(function(e) {
//            getCarData($(e.target).val());
//        });
    });

</script>

<div id="col_content">
    <form name="quick_create_form" method="post" action="registerConfirmed">
        <div id="quick_search" class="block_six white">		
            <h2>accountgegevens</h2>
            <input class="input_alias" name="alias" type="text"  value="${tempAlias}" placeholder="alias"/><br/>
            <input class="input_password" name="password" type="text"  placeholder= "wachtwoord"/><br/>
            <input class="input_email"  name="email" type="text"  placeholder="emailadres" /><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>naam</h2>

            <input class="input_firstName" name="firstName" type="text"  placeholder= "voornaam"/><br/>
            <input class="input_adjective"  name="adjective" type="text"  placeholder="tussenvoegsel" /><br/>
            <input class="input_lastName"  name="lastName" type="text"  placeholder="achternaam" /><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>auto</h2>
            <input id="input_licensePlate" name="licensePlate" name="carPlate" type="text"  placeholder= "kenteken"/><br/>
            <input id="input_carBrand" name="carBrand" type="text"  placeholder= "autotype"  disabled/><br/>
            <input id="input_numSeats"  name="numSeats" type="text"  placeholder="max. aantal meerijders"  disabled/><br/>

        </div>   
        <div id="quick_create" class="block_six white">		
            <h2>woonplaats</h2>

            <input class="input_city" name="city" type="text"  placeholder= "city"/><br/>

            <a href="#" onclick="document.quick_create_form.submit()" class="button" id="button_create"><img src="img/arrow_right.png" alt="create button" /></a>
        </div>  

    </form>

</div>