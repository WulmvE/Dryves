<%-- 
    Document   : changeProfile
    Created on : 10-jun-2013, 16:32:10
    Author     : hctung
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="col_content">
    <form name="change_profile_form" method="post" action="changeProfileConfirmed">
        <div id="quick_search" class="block_six white">		
            <h2>accountgegevens</h2>
            <input class="input_alias" name="alias" type="text"  value="${dryver.alias}" placeholder="gebruikersnaam" required title="vul een alias in"/><br/>
            <input class="input_password" name="password" type="password" value="${dryver.password}" placeholder="password" required pattern=".{8,}"  title="een wachtwoord moet mininmaal 8 karakters hebben"/><br/>
            <input class="input_email"  name="email" type="email"  value="${dryver.email}" placeholder="email adres" required title="vul een geldig email adres in"/><br/>
        </div>

        <div id="" class="block_six white">		
            <h2>naam</h2>

            <input class="input_firstName" name="firstName" type="text"  value= "${dryver.firstName}" placeholder="voornaam" required title="vul je voornaam in"/><br/>
            <input class="input_adjective"  name="adjective" type="text"  value="${dryver.adjective}" placeholder="tussenvoegsel" title="vul een tussenvoegsel in"/><br/>
            <input class="input_lastName"  name="lastName" type="text"  value="${dryver.lastName}" placeholder="achternaam" required title="vul je achternaam in"/><br/>
        </div>

        <div id="" class="block_six white">		
            <h2>auto</h2>

            <input class="input_carBrand" name="carBrand" type="text"  value= "${carProfileDryver.brand}" placeholder="merk"  title="vul het automerk in"/><br/>
            <input class="input_numSeats"  name="numSeats" type="text"  value="${carProfileDryver.numSeats}" placeholder="zitplaatsen" title="vul het aantal zitplaatsen in"/><br/>

        </div>   
        <div id="" class="block_six white">		
            <h2>woonplaats</h2>

            <input class="input_city" name="city" type="text"  value= "${dryver.city}" placeholder="woonplaats" required title="vul je woonplaats in"/><br/>

            <span class="local_menu">               
                <input class="submit_reg" type="submit" value="&#xf0fb;">
            </span>
        </div>  

    </form>
    <div id="" class="block_six white">
        <h2>profielfoto</h2>
        <form name="update_avatar"action="geslaagd" method="post" enctype="multipart/form-data" >
            <!--<label for="fileName">Selecteer profielfoto</label>-->
            <div style="position:relative; display: inline-block; width:74px; height:74px;">
                <img class="avatar" src="ava/avatar000.jpg" style="position:absolute; top:0px; left:0px;"/>
                <img class="avatar" src="ava/avatar${dryver.idMember}.jpg" style="position:absolute; top:0px; left:0px;" />
            </div><br/><br/><br/>
            <input id="fileName" type="file" name="fileName" size="20" placeholder="kies een profielfoto" style="font-size:16px; border: #a0a0a0 solid 1px"/><br>
            <!--            <input type="submit" value="upload"/>-->
            <span class="local_menu">               
                <!--<a href="#" onclick="document.update_avatar_form.submit()" class="local_menu_button larger" id="button_a">&#xf115;</a>-->


                <a href="#" onclick="document.update_avatar.submit();
                        return false;" class="local_menu_button larger submit" id="button_a">&#xf0c7;</a>
            </span>
        </form>
    </div>            
</div>
