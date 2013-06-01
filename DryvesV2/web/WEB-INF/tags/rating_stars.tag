<%-- 
    Document   : rating_stars
    Created on : May 31, 2013, 5:28:19 PM
    Author     : Patrick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag description="translates numerical rating to unicode characters (fontsawesome stars)" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="rating"%>

<%-- any content can be specified here e.g.: --%>

<!--
fontawesome stars
 icon-star (&#xf005;)
 icon-star-empty (&#xf006;)
 icon-star-half-full (&#xf123;)-->

<c:if test="${rating == null}"><span class="rating_small text_grey">&#xf006;&#xf006;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating >= 0.00 && rating<= 0.50}"><span class="rating_small text_red">&#xf006;&#xf006;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 0.50 && rating<= 1.00}"><span class="rating_small text_red">&#xf123;&#xf006;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 1.00 && rating<= 1.50}"><span class="rating_small text_red">&#xf005;&#xf006;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 1.50 && rating<= 2.00}"><span class="rating_small text_red">&#xf005;&#xf123;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 2.00 && rating<= 2.50}"><span class="rating_small text_orange">&#xf005;&#xf005;&#xf006;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 2.50 && rating<= 3.00}"><span class="rating_small text_orange">&#xf005;&#xf005;&#xf123;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 3.00 && rating<= 3.50}"><span class="rating_small text_orange">&#xf005;&#xf005;&#xf005;&#xf006;&#xf006;</span></c:if>
<c:if test="${rating > 3.50 && rating<= 4.00}"><span class="rating_small text_green">&#xf005;&#xf005;&#xf005;&#xf123;&#xf006;</span></c:if>
<c:if test="${rating > 4.00 && rating<= 4.50}"><span class="rating_small text_green">&#xf005;&#xf005;&#xf005;&#xf005;&#xf006;</span></c:if>
<c:if test="${rating > 4.50 && rating<= 4.90}"><span class="rating_small text_green">&#xf005;&#xf005;&#xf005;&#xf005;&#xf123;</span></c:if>
<c:if test="${rating > 4.90 && rating<= 5.00}"><span class="rating_small text_green">&#xf005;&#xf005;&#xf005;&#xf005;&#xf005;</span></c:if>

