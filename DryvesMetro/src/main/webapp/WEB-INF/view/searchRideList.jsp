<%-- 
    Document   : resTest
    Created on : 11-mei-2013, 13:47:26
    Author     : Willem van Ess
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet"  media="screen and (min-width: 721px)" href="css/desktopMetro.css" type="text/css"/>
        <link rel="stylesheet"  media="screen and (min-width: 320px) and (max-width: 720px)" href="css/mobileMetro.css" type="text/css"/>
        <link rel="stylesheet"  media="screen and (max-width: 319px)" href="css/metro.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>

        <title>JSP Page</title>

        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>

        <script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places&language=nl"></script>
        <script type="text/javascript" src="js/jquery.geocomplete.min.js"></script>

    </head>
    <body>

        <script type="text/javascript">
            $(function() {
                $(".input_location").geocomplete({
                    types: ['(cities)'],
                    country: 'nl'
                });
            });
        </script>

        <div class="container">

            <div id="logoHolder"><img src="img/dryves.jpg" class="logo"/></div>

            <div id="smallTile"><img src="img/car_icon.jpg" class="icon"/></div>
            <div id="smallTile"><img src="img/message_icon.png" class="icon"/></div>
            <div id="smallTile"><img src="img/login_icon.png" class="icon"/></div>

            <br>

            <div id="smallTile"><img src="img/about_icon.png" class="icon"/></div>
            <div id="smallTile"><img src="img/contact_icon.jpg" class="icon"/></div>
            <div id="smallTile"><br>200.456 ton CO2 bespaard!</div>

            <br>

            <div id="bigTile">
                <div class="tileHeader">Inbox</div>
                <hr>
                <table>    
                    <thead>
                        <tr>
                            <th>Eindpunt</th>
                            <th>Datum</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Amsterdam</td>
                            <td><a href="#">12-06-2013</a></td>
                        </tr>
                        <tr>
                            <td>Die ene collega</td>
                            <td><a href="#">Hey! Jij ook hier?</a></td>
                        </tr>
                        <tr>
                            <td>Die andere collegaaaaaaaaaaaaaaaaaaaaaa</td>
                            <td><a href="#">Rij je mee?</a></td>
                        </tr>
                        <tr>
                            <td>DatabaseMember04</td>
                            <td><a href="#">DatabaseSubject04</a></td>
                        </tr>
                        <tr>
                            <td>DatabaseMember05</td>
                            <td><a href="#">DatabaseSubject05</a></td>
                        </tr>
                    </tbody>
                </table>              
            </div>
            <div id="bigTile">
<div class="span4">	
                    <p>Beginpunt:</p>
                    <p>Eindpunt:</p>
                    <p>Datum:</p>
                    <p>Tijd:</p>
                    <p>Auto:</p>
                    <p>Zitplaatsen:</p>
                    <p>Prijs</p>
                    <div class="span4">
                        <button class="btn btn-primary">Ja, ik wil meerijden</button>
                    </div>
                </div>
                <div class="span4">	
                    <p>Amsterdam centraal</p>
                    <p>Amsterdam sloterdijk</p>
                    <p>29-04-2013</p>
                    <p>19:00</p>
                    <p>BMW 1-serie</p>
                    <p>2</p>
                    <p>20€</p>
                </div>
                <div class="span4 offset4">	
                    <p>Chauffeur:</p>
                    <div class="span4">	
                        <img src="img/avatar.jpg" class="img-rounded">
                    </div>
                </div>
                <div class="span4">	
                    <p>hctung</p>
                </div>

                <div class="span4">
                    <img src="img/kaart.jpg"/>
                </div>                
            </div>

            <br>

            <div id="halfTile">
                ...Hier dan de invoer voor nieuwe berichten...
            </div>

        </div>
        <script src="js/bootstrap.js"></script>
    </body>
</html>

