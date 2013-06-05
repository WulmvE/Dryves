<%-- 
    Document   : myDryves
    Created on : 1-jun-2013, 17:02:03
    Author     : hctung
--%>
<%@taglib tagdir="/WEB-INF/tags" prefix="r"%>


<div id="col_content">

    <div class="block_six white">		
        <h2>Mijn profiel</h2>
        <div class="profile block_triple white">
            <div>
                <img class="avatar" src="img/avatar.jpg" />
                <a href="#" class="avatar_label">wijzig foto</a>
            </div>
            <div class="summary">
                <span class="text" style="font-weight: bold">${alias}</span><br>              
                <br>
                <span class="text" style="font-weight: bold">text</span><br>
                <r:rating_stars rating="4"/><br>
                <a href="#">ratings inzien</a>
            </div>
        </div>
    </div>


    <div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">Mijn vrienden</h2>
            </div>
        </div>


        <div class="block_triple friends" style=" overflow-x: scroll; overflow-y: hidden;">
            <ul>            
                <li class="block_single white">
                    <img class="avatar" style="margin-top: 35px;" src="img/avatar.jpg" />
                    <a href="#" class="avatar_label">alias</a>
                </li>
                <li class="block_single white">
                    <img class="avatar" style="margin-top: 35px;" src="img/avatar.jpg" />
                    <a href="#" class="avatar_label">alias</a>
                </li>                <li class="block_single white">
                    <img class="avatar" style="margin-top: 35px;" src="img/avatar.jpg" />
                    <a href="#" class="avatar_label">alias</a>
                </li>
                <li class="block_single white"></li>
            </ul>
        </div>
        <div id="sub_menu">
            <div class="block_triple_half white">		
                <h4 style="margin-top: 25px;">knoppenbalk</h4>
            </div>
        </div>
    </div>


    <div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">Aangeboden ritten</h2>
            </div>
        </div>
        <div class="profile_result_block">

            <ul class="profile_results">
                <li class="profile_result block_triple_half white">
                    <div class="summary">
                        <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                        <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                        <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                    </div>
                </li>
                <li class="profile_result block_triple_half white">
                    <div class="summary">
                        <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                        <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                        <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                    </div>
                </li>
                <li class="profile_result block_triple_half white">
                    <div class="summary">
                        <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                        <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                        <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                    </div>
                </li>
                <li class="profile_result block_triple_half white">
                    <div class="summary">
                        <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                        <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                        <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                    </div>
                </li>
            </ul>

        </div>
    </div>

    <div id="col_content">
        <div id="sub_menu">
            <div class="block_triple_half blue">		
                <h2 style="color: white">Gevraagde ritten</h2>
            </div>
        </div>
        <div class="profile_result_block">

            <ul class="profile_results">

                <li class="profile_result block_triple_half white">
                    <div class="summary">
                        <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                        <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                        <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                    </div>
                </li>
                <li class="profile_result block_triple_half white">
                    <div class="summary">
                        <span class="route" >ride.startLocation<span class="text_green"><></span>ride.endLocation</span><br>
                        <span class="date_time">00/00/0000, 13:00u</span><span class="price">&euro; 99</span> / Plaats <br>                    

                        <a class="button" href="#"><img src="img/arrow_right.png" /></a><br>
                    </div>
                </li>

            </ul>

        </div>
    </div>
