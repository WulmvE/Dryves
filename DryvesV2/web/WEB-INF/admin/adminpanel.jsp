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

            $(function() {

                $.getJSON("Admin", function(items) {

                    //remove old tr's
                    $('.results .result').remove();
                    var resultslist = [];
                    $.each(items, function(i, item) {

                        var listItem = '<tr class="result"><td class="text_blue">' + (i + 1) + '</td><td class="item_name">' + item.place + '</td><td>' + item.value + '</td></tr>'
                        //adds items from the previous saved state to their parent lists.			
                        resultslist[i] = listItem;

                    });
                    //append tr's to table
                    $(resultslist.join('')).appendTo(".results");
                });
            });
        </script>

        <div id="main">

            <div id="main_menu">
                <a class="block_double green text_white" href="#"><div id="logo" class="menu_icon">&#xf06c;</div><span class="menu_item">dryves admin</span></a>
                <a class="block_single green" href="#"><div class="menu_icon"></div><span class="menu_item text_white">stastistieken</span></a>
                <a class="block_single blue" href="#"><div class="menu_icon"></div><span class="menu_item text_white">instellingen</span></a>
                <a class="block_single blue" href="#"><div class="menu_icon"></div><span class="menu_item text_white">leden</span></a>
                <a class="block_single blue" href="#"><div class="menu_icon text_white"></div><span class="menu_item text_white">uitloggen</span></a>		
            </div>

            <div id="col_content">

                <div class="block_six white">		
                    <h2>Top 5</h2>
                    <ul class="selectstats">
                        <li class="selectstat" ><span>Leden: Meeste aangeboden ritten</span></li>
                        <li class="selectstat" ><span>Leden: Meeste meegereden ritten</span></li>
                        <li class="selectstat" ><span>Leden: Hoogste opbrengst</span></li>
                        <li class="selectstat active" ><span>Populairste vertrekplaatsen</span></li>
                        <li class="selectstat" ><span>Populairste aankomstplaatsen</span></li>
                    </ul>
                </div>

                <div class="block_six white">		
                    <h2>Vertrekplaatsen</h2>
                    <table class="results">
                        <tr class="result"><td class="text_blue">1</td><td>Amsterdam</td><td>357</td></tr>
                        <tr class="result"><td class="text_blue">2</td><td>Rotterdam</td><td>281</td></tr>     
                    </table>
                </div>

                <div class="block_six white">		
                    <h2>Totalen</h2>
                    <ul class="selectstats">
                        <li class="selectstat active" ><span class="active">Aantal leden</span></li>
                        <li class="selectstat" ><span>Aantal ritten</span></li>
                        <li class="selectstat" ><span>Opbrengst</span></li>
                    </ul>
                </div>

                <div class="block_six white">
                    <h2>Aantal Leden</h2>
                    <div id="graph_container"><div id="graph"></div></div>

                </div>
                
                <div class="block_six white">		
                    <h2>Verdeling</h2>
                    <ul class="selectstats">
                        <li class="selectstat active" ><span class="active">Leeftijd</span></li>
                        <li class="selectstat" ><span>Geslacht</span></li>                       
                    </ul>
                </div>

                <div class="block_six white">
                    <h2>Leeftijd</h2>
                    <div id="pie_container"><div id="pie"></div></div>

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
    shadowSize:0,
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
