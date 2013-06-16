<%-- 
    Document   : changeProfile
    Created on : 10-jun-2013, 16:32:10
    Author     : hctung
--%>


<div id="col_content">
    <form name="change_profile_form" method="post" action="changeProfileConfirmed">
        <div id="quick_search" class="block_six white">		
            <h2>accountgegevens</h2>
            <input class="input_alias" name="alias" type="text"  value="${dryver.alias}" placeholder="gebruikersnaam"/><br/>
            <input class="input_password" name="password" type="password" value="${dryver.password}" placeholder="password"/><br/>
            <input class="input_email"  name="email" type="text"  value="${dryver.email}" placeholder="email adres" /><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>naam</h2><br/><br/><br/><br/>

            <input class="input_firstName" name="firstName" type="text"  value= "${dryver.firstName}" placeholder="voornaam"/><br/>
            <input class="input_adjective"  name="adjective" type="text"  value="${dryver.adjective}" placeholder="tussenvoegsel"/><br/>
            <input class="input_lastName"  name="lastName" type="text"  value="${dryver.lastName}" placeholder="achternaam"/><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>auto</h2><br/><br/><br/><br/>

            <input class="input_carBrand" name="carBrand" type="text"  value= "${carProfileDryver.brand}" placeholder="merk"/><br/>
            <input class="input_numSeats"  name="numSeats" type="text"  value="${carProfileDryver.numSeats}" placeholder="zitplaatsen"/><br/>

        </div>   
        <div id="quick_create" class="block_six white">		
            <h2>woonplaats</h2><br/><br/><br/><br/>

            <input class="input_city" name="city" type="text"  value= "${dryver.city}" placeholder="stad"/><br/>

            <span class="local_menu">               
                <a href="#" onclick="document.change_profile_form.submit()" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>
        </div>  

    </form>

</div>
