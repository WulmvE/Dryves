<%-- 
    Document   : register
    Created on : 7-jun-2013, 11:14:56
    Author     : Vincent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">

    function initStats() {
        $element = $('selectstat').first();
        $('.selectstats li:first').trigger('click');
    }

    function capitalize(string) {

        return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
    }

    function getCarData(lp) {
        
$(".form_msg_error").addClass("hidden");
        //as long there are less than 8 charaters in the associated text input, don't call getJSON.
        if (lp.length < 8) {
            return;
        }

        $.getJSON("getCar?lp=" + lp, function(data) {

            if (data.d.results.length > 0) {
                br = capitalize(data.d.results[0].Merk);
                tp = capitalize(data.d.results[0].Handelsbenaming);            
                zp = data.d.results[0].Aantalzitplaatsen ;
                
                if(zp>=1){
                    zp--;
                }

                $(input_carBrand).val(br + " " + tp).removeAttr('disabled');
                $(input_numSeats).val(zp).removeAttr('disabled');
            }
            
            //if there are no results show error message.
            else{
               
            }
        });
    }

    $(function() {
        $('#input_licensePlate').on("keyup", function(e) {
            getCarData($(e.target).val());
        });

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
            
            <input id="input_licensePlate" name="licensePlate" name="carPlate" type="text"  placeholder= "kenteken (xx-xx-xx)"/>
            <input id="input_carBrand" name="carBrand" type="text"  placeholder= "autotype"  disabled/><br/>
            <input id="input_numSeats"  name="numSeats" type="text"  placeholder="max. aantal meerijders"  disabled/><br/>

        </div>   
        <div id="quick_create" class="block_six white">		
            <h2>overige</h2>

            <input class="input_city" name="city" type="text"  placeholder= "woonplaats"/><br/>
            <input class="input_city" name="birthDate" type="text"  placeholder= "geboortedatum"/><br/>
            
            <select  name="gender">
                <option value="" disabled selected>geslacht</option>
                <option value="m">man</option>
                <option value="v">vrouw</option>
            </select>
            
            <span class="local_menu">               
                <a href="#" onclick="document.quick_create_form.submit()" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>
        </div>  
    </form>
</div>