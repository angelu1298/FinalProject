<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:if test="${!empty calendarBean}">
		<p><strong>${calendarBean.exer_tt}</strong><span>${calendarBean.e_kcal}kcal</span></p>
</c:if>

<c:if test="${!empty cuisine}">
		<p><strong>${cuisine.cus_tt}</strong><span>${cuisine.cus_kcal}kcal</span></p>
</c:if>

<c:if test="${!empty grocery}">
		<p><strong>${grocery.grc_tt}</strong><span>${grocery.grc_kcal}kcal</span></p>
</c:if>

