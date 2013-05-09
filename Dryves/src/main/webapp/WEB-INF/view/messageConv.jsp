<%-- 
    Document   : myRequestedRides
    Created on : 5-mei-2013, 20:46:18
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>

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
        <div class="container">
            <div class="row">
                <div class="span3">
                    <img src="img/dryves.jpg" class="img-rounded">
                </div>
                </br>
                </br>
                </br>
                </br>

                <!-- tabs-->
                <div class="span9">
                    <div class="tabbable"> <!-- Only required for left/right tabs -->
                        <ul class="nav nav-tabs">
                            <li><a href="#tab1" data-toggle="tab">Zoek een rit</a></li>
                            <li><a href="#tab2" data-toggle="tab">Nieuwe rit aanbieden</a></li>
                            <li><a href="#tab1" data-toggle="tab">Mijn aangeboden ritten</a></li>
                            <li><a href="#tab1" data-toggle="tab">Mijn aanvragen</a></li>
                            <li><a href="#tab1" data-toggle="tab">Mijn berichten</a></li>
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
                
                <div class="conv_recieved">Hier komt dan de conversatie...</div>

                <div class="conv_sent">En de reactie daarop...<br>
                                       welke over meerdere regels gaat...</div>

                <div class="conv_recieved">Hier komt dan de conversatie...<br>ook<br>al<br>zijn<br>dit<br>er<br>veel</div>

                <div class="conv_sent">En de reactie daarop...</div>
                
            </form>
            <script src="js/bootstrap.js"></script>
        </div>
    </body>
</html>
