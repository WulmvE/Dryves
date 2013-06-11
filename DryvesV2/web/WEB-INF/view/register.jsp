<%-- 
    Document   : register
    Created on : 7-jun-2013, 11:14:56
    Author     : Vincent
--%>


<div id="col_content">
    <form name="quick_create_form" method="post" action="registerConfirmed">
        <div id="quick_search" class="block_six white">		
            <h2>accountgegevens</h2>
            <input class="input_alias" name="alias" type="text"  value="${tempAlias}" placeholder="alias"/><br/>
            <input class="input_password" name="password" type="text"  placeholder= "wachtwoord"/><br/>
            <input class="input_email"  name="email" type="text"  placeholder="emailadres" /><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>naam</h2><br/><br/><br/><br/>

            <input class="input_firstName" name="firstName" type="text"  placeholder= "voornaam"/><br/>
            <input class="input_adjective"  name="adjective" type="text"  placeholder="tussenvoegsel" /><br/>
            <input class="input_lastName"  name="lastName" type="text"  placeholder="achternaam" /><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>auto</h2><br/><br/><br/><br/>

            <input class="input_carBrand" name="carBrand" type="text"  placeholder= "autotype"/><br/>
            <input class="input_numSeats"  name="numSeats" type="text"  placeholder="max. aantal meerijders" /><br/>
            
        </div>   
        <div id="quick_create" class="block_six white">		
            <h2>woonplaats</h2><br/><br/><br/><br/>

            <input class="input_city" name="city" type="text"  placeholder= "city"/><br/>
 
            <a href="#" onclick="document.quick_create_form.submit()" class="button" id="button_create"><img src="img/arrow_right.png" alt="create button" /></a>
        </div>  

    </form>

</div>