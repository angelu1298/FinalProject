<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8"); %>

<div class="cal_result">
	<strong>${kcal} Kcal</strong> 
	<a href="mypage_main_weekly.brn?kcal=${kcal}">저장하기</a> 
</div>

