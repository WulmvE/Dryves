<%-- 
    Document   : loginuser
    Created on : May 28, 2013, 4:49:16 PM
    Author     : Maartje
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="col_content">
    <div class="block_six white">		
        <h2 class="blauw">login</h2></br>
        <p> </p></br>
       
        <form  name="inlog" action="j_security_check" method=post><br/>
            <input type="text" name="j_username" placeholder="alias"><br/>
            <input type="password" name="j_password" placeholder="password"><br/>
            <a href="#" onclick="document.inlog.submit()" class="button" id="button_search"><img src="img/arrow_right.png" alt="search button" /></a>

        </form>
    </div>


        <div class="block_six white">		

            <h2 class="blauw">nog geen alias?</h2></br>
            <p> </p></br>

            <form  name="no_account" action="newuser" method=post>
                <input type="text" name="j_username" placeholder="username"><br/>
                <a href="#" onclick="document.no_account.submit()" class="button" id="button_search"><img src="img/arrow_right.png" alt="search button" /></a>
            </form>
        </div>


<!--<<body>
  <p>Login to access secure pages:</p>
  <form method="post" action="j_security_check">
    <panelGrid columns="2">
      <outputLabel for="j_username" value="Username" />
      <input type="text" name="j_username" />
 
      <outputLabel for="j_password" value="Password" />
      <input type="password" name="j_password" />
 
      <outputText value="" />
      <panelGrid columns="2">
        <input type="submit" name="submit" value="Login" />
        <button outcome="index" value="Cancel" />
      </panelGrid>
    </panelGrid>
  </form>
<</body>-->