<%-- 
    Document   : myDryves
    Created on : May 27, 2013, 3:02:55 PM
    Author     : Maartje
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<div id="col_content">
    
    <div class="block_six white">	
        <p><a href="<c:url value='logout'/>">log out</a></p><br>
        <p> </p><br>
        ${mijn_alias} <br>
        
        <c:forEach var="member" items="${members}">
            <ul>
                <li>
                    ${member.idMember}
                </li>
            </ul>
        </c:forEach>
     
    </div>    
    
    <div class="block_six white">
        <h2 class="blauw">
            mijn gegevens
        </h2> <br>
        <p> </p><br>
        <c:forEach var="member" items="${members}">
    
        <table float="left">
            <tr><th>
                  naam  
            </th>    
            <td>
                  ${member.firstName} ${member.adjective} ${member.lastName}
            </td></tr>
            <tr><th>
                  plaats 
            </th>    
            <td>
                  ${member.city}
            </td></tr>
            <tr><th>
                  email
            </th>    
            <td>
                  ${member.email}
            </td></tr>
             <tr><th>
               auto
            </th>    
            <td>
                
            </td></tr>
        </table><br>
            </c:forEach>   
            <%--     <c:forEach var="car" items="${cars}">
                      <c:if test="${car.idMember == 103}">
                          <ul>
                              <li>${car.brand}</li>
                          </ul>
                      </c:if>
             </c:forEach>    --%>   
        <p> </p><br>
        <p> </p><br>
        <div float="under">
               ${httpistest}<br>
               Previous Page:  ${mijnreferer}<br>
               ${en}
        </div>
        
    </div>
  </div>

    
    
        
