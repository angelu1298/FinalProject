<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	${dietbean.reduceKg} kg을 ${dietbean.goalDay} 일 안에 감량하려면 <br/>
	매일 섭취하는 칼로리에서 - ${dietbean.reduceKcal}kcal 줄여나가야함 <br/>
	운동 ${dietbean.exec_percent}% 식사 ${dietbean.meal_percent}% <br/>
	운동 : - ${dietbean.reduceExec}kcal <br/>
	식사 : 하루 ${dietbean.reduceMeal}kcal 먹기 <br/>
	운동으로 - ${dietbean.reduceExec}kcal을 소모하려면 <br/>
		db데이터~
	
