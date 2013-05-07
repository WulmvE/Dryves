<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>RollenSysteem</title>
        <link href="/MVCWebApp/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h2>Rollen</h2>
        <c:choose>
            <c:when test="${aantalRollen != 0}">
                <!-- Wanneer er gebruikers opgeslagen zijn, worden ze hier getoond -->
                <table class="rollen">
                    <tr>
                        <td>
                            <strong>Naam</strong>
                        </td>
                        
                        <td></td>
                    </tr>
                    <c:forEach var="tempRol" items="${rollenUitSessie}">
                        <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                        <tr>
                            <td>
                                ${tempRol.name}
                            </td>
                            
                            
                            <td>
                                <a href="rollen/wijzig?id=${tempRol.roleNumber}">Wijzig</a> |
                                <a href="javascript:if(confirm('Weet u het zeker dat u deze rol wil verwijderen?'))
                                   window.location='rollen/verwijder?id=${tempRol.roleNumber}';">Verwijder</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <!-- Als er geen gebruikers zijn, wordt deze melding getoond -->
                Er zijn geen rollen gevonden.
            </c:otherwise>
        </c:choose>
        <p>
            <a href="rollen/nieuw">Maak nieuwe rol aan</a>
        </p>
        
    </body>
</html>
