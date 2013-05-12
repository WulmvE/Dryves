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
                            <th>Afzender</th>
                            <th>Onderwerp</th>
                            <!--                            <th>Datum</th>-->
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Dryves</td>
                            <td><a href="#">Welkom bij Dryves</a></td>
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
                
                <div id="recieved">Hier komt dan de conversatie...</div>

                <div id="sent">En de reactie daarop...<br>
                    welke over meerdere regels gaat...</div>

                <div id="recieved">Hier komt dan de conversatie...<br>ook<br>al<br>zijn<br>dit<br>er<br>veel</div>

                <div id="sent">En de reactie daarop...</div>

                <div id="recieved">En de reactie daarop...</div>

                <div id="sent">En de reactie daarop...</div>

                <div id="recieved">En de reactie daarop...</div>
            </div>

            <br>

            <div id="halfTile">
                ...Hier dan de invoer voor nieuwe berichten...
            </div>

        </div>
        <script src="js/bootstrap.js"></script>
    </body>
</html>

