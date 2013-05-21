<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 17-mei-2013, 14:46:35
    Author     : hctung
--%>

<div id="col_content">

    <div id="quick_search" class="block_six white">		
        <h2>Testpagina: SearchRideServlet forward request naar test.jsp geslaagd </h2>


        <!--Onderstaande was een directe query uit de database middels jstl 
        via references > resource ref. in web.xml, is ook uitgecomment, zie web.xml -->      
    </div>
        <div id="quick_search" class="block_six white">		

        <!-- via SearchRideServlet, init method, dryverFacade instantie
        application scoped attribute met alle data uit members tabel 
        genaamd dryvers, hieronder alleen (get)Alias aangeroepen-->  
        <table>
            <c:forEach var="dryver" items="${dryvers}">
                <tr>                 
                    <td>
                    ${dryver.alias}
                    </td>
                </tr>
            </c:forEach>
            <c:forEach var="ride" items="${rides}">
                <tr>                 
                    <td>
                    ${ride.idRide}
                    </td>
                </tr>
            </c:forEach>

        </table>

        </table>
    </div>
</div>