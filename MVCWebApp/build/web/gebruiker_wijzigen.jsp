<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <title>32Learn</title>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript" src="base.js"></script>
    </head>
    <body>
        <div id="page">
            <div id="maindiv">
                <div id="topdiv">
                    <img src="logo.png" id="logo" alt="logo">
                    <div id="languages">
                        <img id="languageselector" src="landen/English.png" alt="English" title="English">
                        <div id="flags">
                            <img src="landen/Nederlands.png" alt="Nederlands" title="Nederlands">
                            <img src="landen/English.png" alt="English" title="English">
                        </div>
                    </div>
                    <div id="login">
                        <table>
                            <tr>
                                <td>Username:</td><td><input type="text" name="32learnusername" id="32learnusername" value="Username" class="logininput"></td>
                            </tr>
                            <tr>
                                <td>Password:</td><td><input type="password" name="32learnpassword" id="32learnpassword" value="Password" class="logininput"></td>
                            </tr>
                            <tr>
                                <td></td><td><input type="submit" value="Login" id="loginbutton"></td>
                            </tr>
                        </table>
                    </div>
                    <div id="searchbar">
                        <div id="links">
                        </div>
                        <div id="searchcontrols">
                            <input type="text" id="searchfield" name="search" value="Enter your search terms here">
                            <select>
                                <option>All areas of interest</option>
                            </select>
                            <select>
                                <option>All educations</option>
                            </select>
                            <input type="submit" value="Search!" id="searchbutton">
                        </div>
                    </div>
                </div>
                <div id="container">
                    <div id="newsdiv">
                        <div class="newsitem">
                            29-10-2011:<br>Site layout attempt 3
                        </div>
                        <div class="newsitem">
                            2-10-2011:<br>Site layout attempt 2
                        </div>
                        <div class="newsitem">
                            23-9-2011:<br>Example newsitem 3
                        </div>
                        <div class="newsitem">
                            22-9-2011:<br>Example newsitem 2
                        </div>
                        <div class="newsitem">
                            21-9-2011:<br>Example newsitem 1
                        </div>
                        <div class="newsitem">
                            20-9-2011:<br>Site layout attempt 1
                        </div>
                    </div>
                    <div id="comingsoon">
                        <p style="font-weight:bold;text-align:center;">
                            To be expected
                        </p>
                    </div>
                    <div id="centerdiv">
                        <div id="payment">
                            <p style="font-weight:bold; font-size:large;"> 
                                Payment overview
                            </p>
                            <div id="basket">
                                <span style="font-weight:bold;">Shopping basket</span>
                                <table>
                                    <c:forEach var="tempGebruiker" items="${coursesInBasket}">
                                        <tr>
                                            <td>
                                                <div class="basketobjectname">
                                                    Calculus I
                                                </div>
                                            </td>
                                            <td>
                                                <div class="basketobjectprice">
                                                    â?¬15.00
                                                </div>
                                            </td>
                                            <td>
                                                <div class="basketobjectremove">
                                                    <a href="">Remove</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    <tr>
                                        <td>
                                            <div id="baskettotal">
                                                Total: â?¬27.00
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="submit" value="Checkout">
                                        </td>
                                    </tr>                                    
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="sitemapholder">
                    <div id="sitemapcontent">
                        <p>
                            Sitemap:
                        </p>
                        <a href="#">Contact</a>
                        <a href="#">Courses</a>
                        <a href="#">Register</a>
                        <a href="#">Login</a>
                        <a href="#">Teachers</a>
                    </div>
                </div>
                <div id="footerdiv">
                    &copy; 3TwoLearn 2011.
                </div>
            </div>
        </div>
    </body>
</html>