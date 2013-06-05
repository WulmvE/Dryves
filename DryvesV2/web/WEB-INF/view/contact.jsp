<%-- 
    Document   : index
    Created on : 17-mei-2013, 14:46:35
    Author     : Maartje
--%>
<body>
<div id="col_content">

    <div class="block_six white">		
        <h2>contact</h2><br/>
        <p> </p><br/>
        <p> </p><br/>
     
        <p class="c" align="left" padding="20px">Wij luisteren graag naar onze klanten en 
        willen graag weten, hoe zij onze dienst ervaren. Mocht je vragen, opmerkingen hebben of wil 
        misbruik rapporteren, dan kun je contact met ons opnemen middels de mail.</p><br/>
        <p class="c" align="left" padding="20px">Vermeld daarbij tevens de alias
        en het betreffende ritnummer indien van toepassing.</p>
           
    </div>

    <div class="block_six white">		
        <!--<h2.contact>Stuur een mail</h2><br/>-->
        <h2 class="blauw"> stuur een mail</h2><br>
        <p> </p></br>
        
        <form name="mail" action="MAILTO:Dryves@geldverdienen.com" method="post" enctype="text/plain">
            <input type="text" name="name" value="jouw naam" placeholder="jouw naam"><br>
            <input type="text" name="mail" value="jouw email"><br>
            <input type="text" name="comment" value="jouw bericht" placeholder="jouw bericht"><br><br>
            <a href="#" onclick="document.mail.submit()" class="button" id="button_search"><img src="img/arrow_right.png" alt="search button" /></a>
    
<!--            <input type="submit" value="Send">-->
        </form>

    </div>
    
</div>
</body>