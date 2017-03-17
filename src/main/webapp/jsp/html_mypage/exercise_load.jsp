<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:if test="${!empty calendarBean}">
	<c:forEach var="c" items="${calendarBean}">
		<p><strong>${c.exer_tt}</strong><span>${c.e_kcal}kcal</span></p>
	</c:forEach>
</c:if>
<c:if test="${!empty cuisine}">
	<c:forEach items="${cuisine }" var="cs">
		<p><strong>${cs.cus_tt}</strong><span>${cs.cus_kcal}kcal</span></p>
	</c:forEach>
</c:if>

<c:if test="${!empty grocery}">
	<c:forEach items="${grocery }" var="grc">
		<p><strong>${grc.grc_tt}</strong><span>${grc.grc_kcal}kcal</span></p>
	</c:forEach>
</c:if>

