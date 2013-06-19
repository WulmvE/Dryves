<%-- 
    Document   : rating_stars
    Created on : May 31, 2013, 5:28:19 PM
    Author     : Patrick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag description="translates numerical rating to unicode characters (fontsawesome stars)" pageEncoding="UTF-8"%>

<%@attribute name="rating"%>

<%-- 
fontawesome stars
 icon-star (&#xf005;)
 icon-star-empty (&#xf006;)
 icon-star-half-full (&#xf123;)
--%>

<c:if test="${rating < 0.00}"><span class="rating_small text_grey" data-score="${rating}">&#xf006;&#xf006;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating >= 0.00 && rating<= 0.25}"><span class="rating_small text_red" data-score="${rating}">&#xf006;&#xf006;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 0.25 && rating<= 0.75}"><span class="rating_small text_red" data-score="${rating}">&#xf123;&#xf006;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 0.75 && rating<= 1.25}"><span class="rating_small text_red" data-score="${rating}">&#xf005;&#xf006;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 1.25 && rating<= 1.75}"><span class="rating_small text_red" data-score="${rating}">&#xf005;&#xf123;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 1.75 && rating<= 2.25}"><span class="rating_small text_orange" data-score="${rating}">&#xf005;&#xf005;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 2.25 && rating<= 2.75}"><span class="rating_small text_orange" data-score="${rating}">&#xf005;&#xf005;&#xf123;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 2.75 && rating<= 3.25}"><span class="rating_small text_orange" data-score="${rating}">&#xf005;&#xf005;&#xf005;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 3.25 && rating<= 3.75}"><span class="rating_small text_green" data-score="${rating}">&#xf005;&#xf005;&#xf005;&#xf123;&#xf006;</span></c:if>
<c:if test="${rating > 3.75 && rating<= 4.25}"><span class="rating_small text_green" data-score="${rating}">&#xf005;&#xf005;&#xf005;&#xf005;&#xf006;</span></c:if>
<c:if test="${rating > 4.25 && rating<= 4.75}"><span class="rating_small text_green" data-score="${rating}">&#xf005;&#xf005;&#xf005;&#xf005;&#xf123;</span></c:if>
<c:if test="${rating > 4.75 && rating<= 5.00}"><span class="rating_small text_green" data-score="${rating}">&#xf005;&#xf005;&#xf005;&#xf005;&#xf005;</span></c:if>