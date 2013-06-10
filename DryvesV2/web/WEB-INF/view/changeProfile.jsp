<%-- 
    Document   : changeProfile
    Created on : 10-jun-2013, 16:32:10
    Author     : hctung
--%>


<div id="col_content">
    <form name="change_profile_form" method="post" action="changeProfileConfirmed">
        <div id="quick_search" class="block_six white">		
            <h2>accountgegevens</h2>
            <input class="input_alias" name="alias" type="text"  placeholder="${profileDryver.alias}"/><br/>
            <input class="input_password" name="password" type="password"  placeholder= "password"/><br/>
            <input class="input_email"  name="email" type="text"  placeholder="${profileDryver.email}" /><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>naam</h2><br/><br/><br/><br/>

            <input class="input_firstName" name="firstName" type="text"  placeholder= "${profileDryver.firstName}"/><br/>
            <input class="input_adjective"  name="adjective" type="text"  placeholder="${profileDryver.adjective}" /><br/>
            <input class="input_lastName"  name="lastName" type="text"  placeholder="${profileDryver.lastName}" /><br/>
        </div>

        <div id="quick_create" class="block_six white">		
            <h2>auto</h2><br/><br/><br/><br/>

            <input class="input_carBrand" name="carBrand" type="text"  placeholder= "${carProfileDryver.brand}"/><br/>
            <input class="input_numSeats"  name="numSeats" type="text"  placeholder="${carProfileDryver.numSeats}" /><br/>
            
        </div>   
        <div id="quick_create" class="block_six white">		
            <h2>woonplaats</h2><br/><br/><br/><br/>

            <input class="input_city" name="city" type="text"  placeholder= "${profileDryver.city}"/><br/>
 
            <a href="#" onclick="document.change_profile_form.submit()" class="button" id="button_create"><img src="img/arrow_right.png" alt="create button" /></a>
        </div>  

    </form>

</div>
