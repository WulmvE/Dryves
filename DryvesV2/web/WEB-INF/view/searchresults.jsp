<%-- 
    Document   : searchresults
    Created on : May 20, 2013, 4:35:31 PM
    Author     : Patrick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves - Zoekresultaat</title>

        <link rel="stylesheet" type="text/css" href="css/main.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />

        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    </head>

    <body>

        <script type="text/javascript">
            $(function() {
                $(".view_details img").click(function() {
                    var position = $(this).parents("#result").position();
                    $(this).parents("#results").toggleClass("dimmed");
                    $('body').append('<div class="overlay"/>');
                });

                $(".sort_option").click(function() {
                    var current = $(this);
                    if (!current.hasClass("active_up") && !current.hasClass("active_down")) {
                        current.addClass("active_up").siblings().removeClass("active_up active_down");
                    }
                    else {
                        current.toggleClass("active_up active_down");
                    }
                });
            });




        </script>

        <div id="main">

            <div id="main_menu">
                <a id="logo" class="block_double green" href="#" ><span><img src="img/logo.png" alt="Dryves logo"/></span></a>
                <a class="block_single blue" href="#"><div class="menu_icon"></div><span class="menu_item">over dryves</span></a>
                <a class="block_single blue" href="#"><div class="menu_icon"></div><span class="menu_item">contact</span></a>
                <a class="block_single blue" href="#"><div class="menu_icon"></div><span class="menu_item">hoe werkt 't</span></a>
                <a class="block_single blue" href="#"><div class="menu_icon"></div><span class="menu_item">mijn <span>dryves</span></span></a>		
            </div>-->

            <div id="sub_menu">
                <div class="block_double blue"><div class="menu_icon icon_results">50</div><span class="menu_item">resultaten</span></div>

                <a class="block_single white" href="#"><div class="menu_icon"></div><span class="menu_item menu_label_blue">opnieuw</span></a>

                <div class="block_double white">
                    <div class="menu_icon">			
                        <ul class="sort_options">
                            <li class="sort_option active_up">tijdsdtip</li>
                            <li class="sort_option">gebruiker</li>
                            <li class="sort_option">rating</li>
                        </ul>			
                    </div>
                    <span class="menu_item menu_label_blue">sorteren op</span>
                </div>

                <a class="block_single white" href="#"><div class="menu_icon"></div><span class="menu_item menu_label_blue">terug</span></a>		
            </div>

            <div id="col_content">

                <ul id="results">
                    <c:forEach var="ride" items="${rides}">
                        <li class="result block_triple white">

                            <div>
                                <img class="avatar" src="img/avatar.jpg" />
                                <a href="#" class="avatar_label">${ride.idRide}</a>
                            </div>

                            <div class="summary">
                                <span class="route" >${ride.startLocation} <span class="text_green"><></span> ${ride.endLocation}</span><br>
                                ${ride.departureDate}<br>
                                ${ride.seatsAvailable} stoelen beschikbaar<br>
                                <span class="price">&euro; ${ride.askingPrice}</span> / Plaats <br>
                                <span class="rating_small text_green"></span>
                                <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                            </div>

                        </li>
                    </c:forEach>
                    <!--
                                        <li class="result block_triple white">
                    
                                            <div>
                                                <img class="avatar" src="img/avatar.jpg" />
                                                <a href="#" class="avatar_label">blake1987</a>
                                            </div>
                    
                                            <div class="summary">
                                                <span class="route" >Amsterdam <span class="text_green"><></span> Tilburg2</span><br>
                                                31/05/2013<br>
                                                1 plaats vrij<br>
                                                <span class="price">€10</span> / Plaats <br>
                                                <span class="rating_small text_orange"></span>
                                                <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                                            </div>
                    
                                        </li>
                    
                                        <li class="result block_triple white">
                    
                                            <div>
                                                <img class="avatar" src="img/avatar.jpg" />
                                                <a href="#" class="avatar_label">blake1987</a>
                                            </div>
                    
                                            <div class="summary">
                                                <span class="route" >Amsterdam <span class="text_green"><></span> Tilburg3</span><br>
                                                31/05/2013<br>
                                                1 plaats vrij<br>
                                                <span class="price">€10</span> / Plaats <br>
                                                <span class="rating_small text_orange"></span>
                                                <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                                            </div>
                    
                                        </li>
                    
                                        <li class="result block_triple white">
                    
                                            <div>
                                                <img class="avatar" src="img/avatar.jpg" />
                                                <a href="#" class="avatar_label">blake1987</a>
                    
                                            </div>
                    
                                            <div class="summary">
                                                <span class="route" >Amsterdam <span class="text_green"><></span> Tilburg4</span><br>
                                                31/05/2013<br>
                                                1 plaats vrij<br>
                                                <span class="price">€10</span> / Plaats <br>
                                                <span class="rating_small text_green"></span>
                                                <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                                            </div>
                    
                                        </li>
                    
                                        <li class="result block_triple white">
                    
                                            <div>
                                                <img class="avatar" src="img/avatar.jpg" />
                                                <a href="#" class="avatar_label">blake1987</a>
                                            </div>
                    
                                            <div class="summary">
                                                <span class="route" >Amsterdam <span class="text_green"><></span> Tilburg</span><br>
                                                31/05/2013<br>
                                                1 plaats vrij<br>
                                                <span class="price">€10</span> / Plaats <br>
                                                <span class="rating_small text_red"></span>
                                                <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                                            </div>
                    
                                        </li>
                    
                                        <li class="result block_triple white">
                    
                                            <div>
                                                <img class="avatar" src="img/avatar.jpg" />
                                                <a href="#" class="avatar_label">blake1987</a>
                                            </div>
                    
                                            <div class="summary">
                                                <span class="route" >Amsterdam <span class="text_green"><></span> Tilburg</span><br>
                                                31/05/2013<br>
                                                1 plaats vrij<br>
                                                <span class="price">€10</span> / Plaats <br>
                                                <span class="rating_small text_grey"></span>
                                                <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                                            </div>
                    
                                        </li>
                    
                                        <li class="result block_triple white">
                    
                                            <div>
                                                <img class="avatar" src="img/avatar.jpg" />
                                                <a href="#" class="avatar_label">blake1987</a>
                                            </div>
                    
                                            <div class="summary">
                                                <span class="route" >Amsterdam <span class="text_green"><></span> Tilburg</span><br>
                                                31/05/2013<br>
                                                1 plaats vrij<br>
                                                <span class="price">€10</span> / Plaats <br>
                                                <span class="rating_small text_green"></span>
                                                <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                                            </div>
                    
                                        </li>
                    
                                        <li class="result block_triple white">
                    
                                            <div>
                                                <img class="avatar" src="img/avatar.jpg" />
                                                <a href="#" class="avatar_label">blake1987</a>
                                            </div>
                    
                                            <div class="summary">
                                                <span class="route" >Amsterdam <span class="text_green"><></span> Tilburg</span><br>
                                                31/05/2013<br>
                                                1 plaats vrij<br>
                                                <span class="price">€10</span> / Plaats <br>
                                                <span class="rating_small text_red"></span>
                                                <a class="view_details" href="#"><img src="img/arrow_right.png" /></a><br>
                                            </div>
                    
                                        </li>-->
                </ul>
            </div>

<!--            <div id="cont_promo">al 24.145 ton co<span class="sub">2</span> bespaart...</div>

        </div>
    </body>
</html>-->
