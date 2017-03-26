<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:forward page="goweekly.brn">
	<jsp:param value="${y}" name="y"/>
	<jsp:param value="${m}" name="m"/>
	<jsp:param value="${d}" name="d"/>
	<jsp:param value="${calendarList}" name="calendarList"/>
</jsp:forward>
    