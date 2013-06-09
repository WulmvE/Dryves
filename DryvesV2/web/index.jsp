<%-- 
    Document   : index
    Created on : 17-mei-2013, 14:46:35
    Author     : hctung
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="col_content">

    <div id="quick_search" class="block_six white">		
        <h2>ik wil meerijden</h2>
        <!--        <form name="quick_search_form" method="post" action="searchresults">
                    <input class="input_location" name="search_start" type="text"  placeholder="van"/><br/>
                    <input class="input_location" name="search_destination" type="text"  placeholder="naar"/><br/>
                    <input class="input_date"  name="search_date" type="text"  placeholder="op" /><br/>
                    <a href="#" onclick="document.quick_search_form.submit()" class="button" id="button_search"><img src="img/arrow_right.png" alt="search button" /></a>
                </form>-->
        <form name="quick_search_form" method="post" action="searchresults">
            <input class="input_location" name="search_start" type="text"  placeholder="van"/><br/>
            <input class="input_location" name="search_destination" type="text"  placeholder="naar"/><br/>
            <input class="input_date"  name="search_date" type="text"  placeholder="op" /><br/>
            <span class="local_menu">               
                <a href="#" onclick="document.quick_search_form.submit()" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>
        </form>
    </div>

    <div id="quick_create" class="block_six white">		
        <h2>ik zoek meerijders</h2>
        <form name="quick_create_form" method="post" action="createRide">
            <input class="input_location" name="create_start" type="text"  placeholder="van"/><br/>
            <input class="input_location" name="create_destination" type="text"  placeholder="naar"/><br/>
            <input class="input_date"  name="create_date" type="text"  placeholder="op"/><br/>

            <span class="local_menu">               
                <a href="#" onclick="document.quick_create_form.submit()" class="local_menu_button larger submit" id="button_create">&#xf0fb;</a>
            </span>
        </form>
    </div>

</div>