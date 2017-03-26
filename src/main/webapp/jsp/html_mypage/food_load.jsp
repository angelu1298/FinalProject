<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:if test="${!empty cuisine}">
	<c:forEach items="${cuisine }" var="cs">
	${cs.cus_tt} &nbsp; ${cs.cus_kcal}kcal
	</c:forEach>
</c:if>

<c:if test="${!empty grocery}">
	<c:forEach items="${grocery }" var="grc">
		${grc.grc_tt} &nbsp; ${grc.grc_kcal}kcal
	</c:forEach>
</c:if>