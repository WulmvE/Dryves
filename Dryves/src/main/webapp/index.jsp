<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

	<!--This is a comment-->

	<title>Dryves - Home</title>

	<link rel="stylesheet" type="text/css" href="css/main.css" />

	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places&language=nl"></script>
	<script type="text/javascript" src="js/jquery.geocomplete.min.js"></script>

</head>

<body>

<script type="text/javascript">
$(function(){
$(".input_location").geocomplete({
types: ['(cities)'],
country: 'nl'
});
});
</script>

<div id="cont_top">

	<ul id="top_menu">
		<li class="menu_item"><a href="#">hoe werkt 't</a></li>
		<li class="menu_item"><a href="#">FAQ</a></li>
		<li class="menu_item"><a href="#">contact</a></li>
		<li class="menu_item"><a href="/Dryves/myRequestedRides">mijn <span class="arial">DRYVES</span></a></li>
	</ul>

</div>

<div id="main">

	<div id="col_logo">
		<div id="cont_logo"><a href="index.html"><img src="img/logo.png"/></a></div>
		<div id="tagline">carpoolen. maar dan beter.</div>
	</div>

	<div id="col_content">
		<div id="cont_search">
		<h2>Ik wil meerijden</h2>
		<input class="input_location" type="text"  placeholder="van"/><br/>
		<input class="input_location" type="text"  placeholder="naar"/><br/>
		<input class="input_date" type="text"  placeholder="op"/><br/>
		<a  href="results.html" class="button" id="button_search">zoek</a>
		</div>
		<div id="cont_create">
		<h2>Ik zoek meerijders</h2>
		<input class="input_location" type="text"  placeholder="van"/><br/>
		<input class="input_location" type="text"  placeholder="naar"/><br/>
		<input class="input_date" type="text"  placeholder="op"/><br/>
		<a  href="#" class="button" id="button_search">verder</a>
		</div>
		<div id="cont_promo"></div>
	</div>
</div>

</body>

</html>
