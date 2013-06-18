<%-- 
    Document   : admin statistics
    Created on : Jun 3, 2013, 1:53:17 AM
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
        <title>Dryves Admin - Statistieken</title>

        <link rel="stylesheet" type="text/css" href="/DryvesV2/css/main.css" />
        <link rel="stylesheet" type="text/css" href="/DryvesV2/css/admin.css" />
        <link rel="stylesheet" type="text/css" href="/DryvesV2/css/jquery-ui.css" />

        <script type="text/javascript" src="/DryvesV2/js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="/DryvesV2/js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="/DryvesV2/js/jquery.ui.datepicker-nl.js"></script>

        <script type="text/javascript" src="/DryvesV2/js/canvasjs.min.js"></script>
        <script type="text/javascript" src="/DryvesV2/js/date.format.js"></script>

    </head>

    <body>
        <script type="text/javascript">

            function capitalize(string) {

                return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
            }

            function initStats() {
                //setting up the top5 stats        
                $('[name="type"]')[0].selectedIndex = 1;

                var today = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
                $('[name="from"]').val('01/05/2013');
                $('[name="to"]').val(today.format('dd/mm/yyyy'));

                $('.submit').trigger('click');
            }

            function getStats(senderID, targetID, visType) {

                $.getJSON("admin?", $(senderID).serialize(), function(items) {
                    //$.getJSON("admin?type=" + type, function(items) {

                    var resultslist = [];
                    var headerText = $(senderID + ' [name="type"]').find('option:selected').text();

                    if (visType === 'table') {
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
                            $('#top5_header').text(capitalize(headerText));
                            $('#top5').children().fadeTo(200, 1.0);
                        });
                    }
                    else if (visType === 'column' || visType === 'line') {

                        $.each(items, function(i, item) {
                            nugget = {label: item[0], y: item[1]},
                            resultslist[i] = nugget;
                        });

                        //fade out old results. when animation is done append new ones and fade in again.
                        $('#totals').children().fadeTo(200, 0.0, function() {
                            drawGraph(resultslist);
                            $('#header_totals').text(capitalize(headerText));
                            $('#totals').children().fadeTo(200, 1.0);
                        });
                    }
                    
                    else if (visType === 'pie') {

                        $.each(items, function(i, item) {
                            lidLeden = (item[1]>1)?"leden":"lid";
                            nugget = {y: item[1],legendText:item[0]+".",name:item[0] +": "+ item[2] +" / "+item[1] +" "+ lidLeden },
                            resultslist[i] = nugget;
                        });

                        //fade out old results. when animation is done append new ones and fade in again.
                        $('#divs').children().fadeTo(200, 0.0, function() {
                            drawPie(resultslist);
                            $('#header_divs').text(capitalize(headerText));
                            $('#divs').children().fadeTo(200, 1.0);
                        });
                    }
                    
                    
                    
                    
                }
                );
            }

            $(function() {

                initStats();

                $(function() {
                    $(".input_date").datepicker({
                        dateFormat: "dd/mm/yy",
                        regional: "nl"
                    });
                });
            }
            );</script>

        <div id="main">

            <div id="main_menu">
                <a id="menu_logo" class="block_double green text_white" href="statistics"><div id="logo" class="menu_icon">&#xf06c;</div><span class="menu_item">dryves admin</span></a>
                <a id="menu_stats" class="block_single green" href="statistics"><div class="menu_icon"></div><span class="menu_item text_white">stastistieken</span></a>
                <a id="menu_settings" class="block_single blue" href="settings"><div class="menu_icon"></div><span class="menu_item text_white">instellingen</span></a>
                <a id="menu_members" class="block_single blue" href="membercontrol"><div class="menu_icon"></div><span class="menu_item text_white">leden</span></a>
                <a id="menu_logout" class="block_single blue" href="logout"><div class="menu_icon text_white"></div><span class="menu_item text_white">uitloggen</span></a>		
            </div>

            <div id="col_content" >

                <div class="block_six white stats_cont">		
                    <h2>Top 5</h2>

                    <form id="top5_stats">
                        <input type="hidden" name="by" value="date">
                        <select name="type" id="top5_type">
                            <option value="" disabled selected>type</option>
                            <option value="topCreated">lid: meeste aangeboden ritten</option>
                            <option value="topDriven">lid: meeste meegereden ritten</option>
                            <option value="topRevenue">lid: hoogste opbrengst</option>
                            <option value="topDestination">populairste aankomstplaatsen</option>
                            <option value="topDeparture">populairste vertrekplaatsen</option>
                        </select><br/>
                        <input class="input_date selectstats" name="from" id="top5_from" type="text" value="vanaf"/>
                        <input class="input_date selectstats" name="to" id="top5_to" type="text" value="tot"/>

                        <div class="local_menu">
                            <a href="#" onclick="getStats('#top5_stats', '#top5_results', 'table');
                return false;" class="local_menu_button larger submit">&#xf0fb;</a>
                        </div>
                    </form>
                </div>

                <div id="top5" class="block_six white stats_cont">		
                    <h2 id="top5_header"></h2>
                    <table id="top5_results" class="results">                          
                    </table>                  
                </div>

                <div class="block_six white stats_cont">		
                    <h2>Totalen</h2>
                    <form id="totals_stats">

                        <select name="type" id="totals_type">
                            <option value="" disabled >type</option>
                            <option value="totalRidesNew" selected>aantal nieuwe ritten</option>
                            <option value="totalRides">totaal aantal ritten</option>
                            <option value="totalMembersNew">aantal nieuwe leden</option>
                            <option value="totalMembers">aantal leden (cumulatief)</option>
                            <option value="totalMilageNew">gereden kilometers</option>
                            <option value="totalMilage">gereden kilometers (cumulatief)</option>
                            <option value="totalRevenueNew">opbrengst</option>
                            <option value="totalRevenue">opbrengst (cumulatief)</option>
                        </select>

                        <select name="by" id="totals_by">
                            <option value="" disabled >tijdschaal</option>
                            <option value="date" selected>per dag</option>
                            <option value="yearweek">per week</option>
                            <option value="monthname">per maand</option>
                            <option value="year">per jaar</option>
                        </select><br/>

                        <input class="input_date selectstats" name="from" id="totals_from" type="text" value="vanaf"/>
                        <input class="input_date selectstats" name="to" id="totals_to" type="text" value="tot" />

                        <div class="local_menu">
                            <a href="#" onclick="getStats('#totals_stats', '#graph', 'column');
                return false;" class="local_menu_button larger submit">&#xf0fb;</a>
                        </div>
                    </form>

                </div>

                <div id="totals" class="block_six white stats_cont">
                    <h2 id="header_totals"></h2>
                    <div id="graph_container"><div id="graph"></div></div>
                </div>

                <div class="block_six white stats_cont">		
                    <h2>Verdeling</h2>
                    <form id="dist_stats">

                        <select name="type" id="dist_type">
                            <option value="" disabled >type</option>
                            <option value="distGender" selected>geslacht</option>
                            <option value="distAge">leeftijd</option>
                        </select><br/>

                        <input class="input_date selectstats" name="from" id="dist_from" type="text" value="vanaf"/>
                        <input class="input_date selectstats" name="to" id="dist_to" type="text" value="tot" />

                        <div class="local_menu">
                            <a href="#" onclick="getStats('#dist_stats', '#pie', 'pie');
                return false;" class="local_menu_button larger submit">&#xf0fb;</a>
                        </div>
                    </form>
                </div>

                <div id="divs" class="block_six white stats_cont">
                    <h2 id="header_divs"></h2>
                    <div id="pie_container"><div id="pie"></div></div>

                </div>        

            </div>

            <div id="cont_promo">al 24.145 ton co<span class="sub">2</span> bespaard...</div>
        </div>

    </body>

    <script type="text/javascript">

            function drawGraph(dataSet) {

                CanvasJS.addColorSet("dryves",
                        [
                            "#6ac3ed",
                        ]);

                var chart = new CanvasJS.Chart("graph",
                        {
                            colorSet: "dryves",
                            labelFontFamily: "segoe",
                            axisX: {
                                labelAngle: -45,
                                labelFontSize: 10,
                            },
                            axisY: {
                                labelAngle: -45,
                                labelFontSize: 10,
                                gridThickness: 1,
                                gridColor: "#d0d0d0"

                            },
                            data: [//array of dataSeries              
                                { //dataSeries object

                                    /*** Change type "column" to "bar", "area", "line" or "pie"***/
                                    type: "column",
                                    markerType: "circle", //"circle", "square", "cross", "none"


                                    markerColor: "#62b5dc",
                                    dataPoints: dataSet
                                }
                            ]
                        });

                chart.render();
            }

           function drawPie(dataSet) {

                CanvasJS.addColorSet("dryves",
                        [//colorSet Array
                            "#62b5dc",
                            "#f48049",
                            "#a6c400",
                            "#6ac3ed",
                            "#fba47a",
                            "#b5d501",                            
                            "#bdeaff"
                        ]);

                var chart = new CanvasJS.Chart("pie",
                        {
                            colorSet: "dryves",
                             
                            legend: {
                                verticalAlign: "center",
                                horizontalAlign: "left",
                                fontSize: 15,
                                fontFamily: "segoe",
                                fontColor:"#919191",
                            },
                            theme: "theme2",
                            data: [
                                {
                                    type: "pie",
                                    indexLabelFontFamily: "segoe",
                                    indexLabelFontSize: 20,
                                    startAngle: -20,
                                    showInLegend: true,
                                    toolTipContent: "{name}",
                                    dataPoints: dataSet
                                }
                            ]
                        });

                chart.render();
            }

    </script>

</html>
