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
                zp = data.d.results[0].Aantalzitplaatsen;

                if (zp >= 1) {
                    zp--;
                }

                $(input_carBrand).val(br + " " + tp).removeAttr('disabled');
                $(input_numSeats).val(zp).removeAttr('disabled');
            }

            //if there are no results show error message.
            else {

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
    <form name="register_form" id="register_form" method="post" action="registerConfirmed">
        <div  class="block_six white">		
            <h2>accountgegevens</h2>
            <input class="input_alias" name="alias" type="text"  value="${param.alias}" placeholder="alias" required title="vul een alias in"/><br/>
            <input class="input_password" name="password" type="password"  placeholder= "wachtwoord" required pattern=".{8,}"  title="een wachtwoord moet mininmaal 8 karakters hebben"/><br/>
            <input class="input_email"  name="email" type="email" placeholder="emailadres" required title="vul een geldig email adres in"/><br/>
        </div>

        <div  class="block_six white">		
            <h2>naam</h2>

            <input class="input_firstName" name="firstName" type="text"  placeholder= "voornaam" required title="vul je voornaam in"/><br/>
            <input class="input_adjective"  name="adjective" type="text"  placeholder="tussenvoegsel"  title="vul een tussenvoegsel in"/><br/>
            <input class="input_lastName"  name="lastName" type="text"  placeholder="achternaam" required title="vul je achternaam in"/><br/>
        </div>

        <div  class="block_six white">		
            <h2>auto</h2>

            <input id="input_licensePlate" name="licensePlate" name="carPlate" type="text"  placeholder= "kenteken (xx-xx-xx)" title="vul je kenteken in. dryves haalt de info voor je op"/>
            <input id="input_carBrand" name="carBrand" type="text"  placeholder= "autotype"  disabled/><br/>
            <input id="input_numSeats"  name="numSeats" type="text"  placeholder="max. aantal meerijders"  disabled/><br/>
        </div>   
        <div class="block_six white">		
            <h2>overige</h2>

            <input class="input_city" name="city" type="text"  placeholder= "woonplaats" required title="vul je woonplaats in"/><br/>
            <input class="input_city" name="birthDate" type="text"  placeholder= "geboortedatum" required pattern="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d" title="vul je geboortedatum in (mm-dd-jjjj)"/><br/>

            <select  name="gender" required  title="vul je geslacht in">
                <option value="" disabled selected>geslacht</option>
                <option value="m">man</option>
                <option value="v">vrouw</option>
            </select>

            <span class="local_menu">               
                <input class="submit_reg" type="submit" value="&#xf0fb;">
            </span>
        </div>  
    </form>
<!--    <div id="quick_create" class="block_six white">		
        <form action="geslaagd" method="post" enctype="multipart/form-data">
            <label for="fileName">Selecteer profielfoto</label>
            <input id="fileName" type="file" name="fileName" size="30"/><br>
            <input type="submit" value="Upload"/>
        </form>
    </div>-->
</div>