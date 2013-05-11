<%-- 
    Document   : searchRideDetails
    Created on : 5-mei-2013, 20:33:47
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>	
        <link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
        <link type="text/css" rel="stylesheet" href="css/bootstrap_overrides.css"/>

    </head>
    <body>

        <!--  black navbar -->

        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="#">200.456 ton CO2 bespaard!</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <li class="active"><a href="#">Home</a></li>
                            <li><a href="#about">Over</a></li>
                            <li><a href="#contact">Contact</a></li>
                        </ul>
                        <form class="navbar-form pull-right">
                            <input class="span2" type="text" placeholder="Gebruikersnaam">
                            <input class="span2" type="password" placeholder="Password">
                            <button type="submit" class="btn">Inloggen</button>
                        </form>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>

        </br>
        </br>

        <!-- logo -->
        <div  id="bs_main" class="container">
            <div class="row">
                <div class="span3">
                    <div id="cont_logo"><img src="img/logo.png" class="img-rounded"/></div>
                </div>
                </br>
                </br>
                </br>
                </br>

                <!-- tabs-->
                <div class="span9">
                    <div class="tabbable"> <!-- Only required for left/right tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="/Dryves/searchRide" data-toggle="tab">Zoek een rit</a></li>
                            <li><a href="/Dryves/myRideDetails" data-toggle="tab">Nieuwe rit aanbieden</a></li>
                            <li><a href="/Dryves/myRides" data-toggle="tab">Mijn aangeboden ritten</a></li>
                            <li><a href="/Dryves/myRequestedRides" data-toggle="tab">Mijn aanvragen</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab1">
                                <!-- <p>I'm in Section 1.</p> -->
                            </div>
                            <div class="tab-pane" id="tab2">
                                <!-- <p>Howdy, I'm in Section 2.</p> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <form class= "well span12">
                <div class="span1">	
                    <p>Beginpunt:</p>
                    <p>Eindpunt:</p>
                    <p>Datum:</p>
                    <p>Tijd:</p>
                    <p>Auto:</p>
                    <p>Zitplaatsen:</p>
                    <p>Prijs</p>
                    <div class="span2">
                        <button class="btn btn-primary">Ja, ik wil meerijden</button>
                    </div>
                </div>
                <div class="span2">	
                    <p>Amsterdam centraal</p>
                    <p>Amsterdam sloterdijk</p>
                    <p>29-04-2013</p>
                    <p>19:00</p>
                    <p>BMW 1-serie</p>
                    <p>2</p>
                    <p>20€</p>
                </div>
                <div class="span1 offset1">	
                    <p>Chauffeur:</p>
                    <div class="span3">	
                        <img src="img/avatar.jpg" class="img-rounded">
                    </div>
                </div>
                <div class="span2">	
                    <p>hctung</p>
                </div>

                <div class="span4">
                    <img src="img/kaart.jpg" class="img-rounded">
                </div>
            </form>
            <script src="js/bootstrap.js"></script>
    </body>
</html>
