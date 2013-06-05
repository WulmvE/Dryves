<%-- 
    Document   : adminpanel
    Created on : Jun 3, 2013, 1:53:17 AM
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
        <title>Dryves - Admin</title>

        <link rel="stylesheet" type="text/css" href="css/main.css" />
        <link rel="stylesheet" type="text/css" href="css/admin.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />

        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/flotr2.min.js"></script>

    </head>

    <body>
        <script type="text/javascript">

            function initStats() {
                $element = $('selectstat').first();
                $('.selectstats li:first').trigger('click');
            }

            function getStats(type, element) {

                $element = $(element);

                $.getJSON("Admin?type=" + type, function(items) {

                    var resultslist = [];
                    $.each(items, function(i, item) {

                        var listItem = '<tr class="result"><td class="text_blue">' + (i + 1) + '</td><td class="item_name">' + item[0] + '</td><td>' + item[1] + '</td></tr>';
                        resultslist[i] = listItem;

                    });

                    //fade out old results. when animation is done append new ones and fade in again.
                    $('#top5').children().fadeTo(200, 0.0, function() {
                        //remove old tr's
                        $('.results .result').remove();
                        //append tr's to table
                        $(resultslist.join('')).appendTo(".results");
                        $('#top5_header').text($element.text());
                        $element.addClass('active');
                        $element.siblings().removeClass('active');
                        $('#top5').children().fadeTo(200, 1.0);
                    });

                });
            }
            



            $(function() {

                initStats();

                $("#menu_settings").on("click", function(event) {
                    $('#col_content').hide(200, function(){
                        
                        $('.stats_cont').hide();
                        $('.settings_cont').show();
                        $('#col_content').show(200);
                        $('#menu_stats').removeClass('green').addClass('blue');
                        $('#menu_settings').removeClass('blue').addClass('green');
                        
                    });                    
                    
                });
            });
        </script>

        <div id="main">

            <div id="main_menu">
                <a class="block_double green text_white" href="#"><div id="logo" class="menu_icon">&#xf06c;</div><span class="menu_item">dryves admin</span></a>
                <a id="menu_stats" class="block_single green" href="#"><div class="menu_icon"></div><span class="menu_item text_white">stastistieken</span></a>
                <a id="menu_settings" class="block_single blue" href="#"><div class="menu_icon"></div><span class="menu_item text_white">instellingen</span></a>
                <a class="block_single blue" href="#"><div class="menu_icon"></div><span class="menu_item text_white">leden</span></a>
                <a class="block_single blue" href="#"><div class="menu_icon text_white"></div><span class="menu_item text_white">uitloggen</span></a>		
            </div>

            <div id="col_content" >
                
                <!--statistieken-->

                <div class="block_six white stats_cont">		
                    <h2>Top 5</h2>
                    <ul class="selectstats">
                        <li id="starter" class="selectstat" onclick="getStats('mostCreated', this)" ><span>Lid: Meeste ritten aangeboden</span></li>
                        <li class="selectstat" onclick="getStats('mostDriven', this)"><span>Lid: Meeste ritten meegereden</span></li>
                        <li class="selectstat" onclick="getStats('highestGrossing', this)"><span>Lid: Hoogste opbrengst</span></li>
                        <li class="selectstat" onclick="getStats('mostDeparture', this)"><span>Populairste vertrekplaatsen</span></li>
                        <li class="selectstat" onclick="getStats('mostDestination', this)"><span>Populairste aankomstplaatsen</span></li>
                    </ul>
                </div>

                <div id="top5" class="block_six white stats_cont">		
                    <h2 id="top5_header"></h2>
                    <table class="results">                          
                    </table>
                </div>

                <div class="block_six white stats_cont">		
                    <h2>Totalen</h2>
                    <ul class="selectstats">
                        <li class="selectstat" ><span class="active">Aantal leden</span></li>
                        <li class="selectstat" ><span>Aantal ritten</span></li>
                        <li class="selectstat" ><span>Opbrengst</span></li>
                    </ul>
                </div>

                <div class="block_six white stats_cont">
                    <h2>Aantal Leden</h2>
                    <div id="graph_container"><div id="graph"></div></div>

                </div>

                <div class="block_six white stats_cont">		
                    <h2>Verdeling</h2>
                    <ul class="selectstats">
                        <li class="selectstat" ><span class="active">Leeftijd</span></li>
                        <li class="selectstat" ><span>Geslacht</span></li>                       
                    </ul>
                </div>

                <div class="block_six white stats_cont">
                    <h2>Leeftijd</h2>
                    <div id="pie_container"><div id="pie"></div></div>

                </div>


                <!--instellingen-->

                <div class="block_six white settings_cont">		
                    <h2>Zakelijk</h2>
                    <ul class="selectstats">
                        <li class="selectstat active" ><span class="active">Percentage Ritprijs</span></li>
                    </ul>
                </div>

                <div class="block_six white settings_cont">
                    <h2>Percentage Ritprijs</h2>
                    <input type="text" value="10" style="width:40px; margin-top: 50px;"/> <span style="font-size: 22px">%</span>
                    <a class="button" href="<c:url value='rideDetails?${ride.idRide}'/>"><img src="img/arrow_right.png" /></a><br>
                </div>

            </div>

            <div id="cont_promo">al 24.145 ton co<span class="sub">2</span> bespaard...</div>
        </div>

    </body>

    <script type="text/javascript">

            (function() {
                var container = document.getElementById('graph'), graph;
                var data = [[2009, 9146], [2008, 9484], [2007, 10129], [2006, 10225]];
                graph = Flotr.draw(container, [data], {
                    bars: {
                        show: true,
                        horizontal: false,
                        shadowSize: 0,
                        barWidth: 0.5
                    },
                    mouse: {
                        track: true,
                        relative: true
                    },
                    xaxis: {
                        noTicks: 4,
                        ticks: [
                            [0, "2011-Q3"],
                            [1, "2011-Q4"],
                            [2, "2012-Q1"],
                            [3, "2012-Q2"]
                        ]
                    },
                    yaxis: {
                        min: 0,
                        autoscaleMargin: 1
                    },
                    grid: {
                        color: '#a0a0a0'}
                });
            })();



            (function basic_pie(container) {

                var
                        d1 = [
                    [0, 4]
                ],
                        d2 = [
                    [0, 5]
                ],
                        d3 = [
                    [0, 1.03]
                ],
                        d4 = [
                    [0, 1.5]
                ],
                        graph;

                graph = Flotr.draw(container, [{
                        data: d1,
                        label: '18-25'
                    }, {
                        data: d2,
                        label: '26-40'
                    }, {
                        data: d3,
                        label: '41-55',
                        pie: {
                            explode: 0
                        }
                    }, {
                        data: d4,
                        label: '56-75'
                    }], {
                    shadowSize: 0,
                    HtmlText: false,
                    grid: {
                        verticalLines: false,
                        horizontalLines: false,
                        color: '#a0a0a0'
                    },
                    xaxis: {
                        showLabels: false
                    },
                    yaxis: {
                        showLabels: false
                    },
                    pie: {
                        show: true,
                        explode: 0
                    },
                    mouse: {
                        track: false
                    },
                    legend: {
                        position: 'se',
                        backgroundColor: '#ffffff'
                    }
                });
            })(document.getElementById("pie"));






    </script>


</html>
