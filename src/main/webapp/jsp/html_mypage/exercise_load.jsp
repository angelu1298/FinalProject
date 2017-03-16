<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:if test="${calendarBean != null}">
	<c:forEach var="c" items="${calendarBean}">
		${c.exer_tt} &nbsp; ${c.e_kcal}kcal <br/>
	</c:forEach>
</c:if>


