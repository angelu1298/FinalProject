<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.*"%>
<script src="./resources/js/jquery.js"></script>
<script src="./resources/js/list.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<% System.out.println("result_nm2 들어왔당"); %>
	

	<!-- erc_nm, erc_sx, erc_minw, erc_maxw, erc_ten, erc_hun -->
	
	<c:forEach var="exer" items="${exerciselist }">
	
		<input type="text" value="${exer.erc_nm}" id="ercnm_${exer.erc_nm}"><br>
<%-- 		
		<input type="text" value="${exer.erc_sx}" id="ercnm_${exer.erc_sx}">&nbsp;&nbsp;
		<input type="text" value="${exer.erc_minw}" id="ercnm_${exer.erc_minw}">&nbsp;&nbsp;
		<input type="text" value="${exer.erc_maxw}" id="ercnm_${exer.erc_maxw}">&nbsp;&nbsp;
 --%>
		<input type="hidden" id="erc_ten" value="${exer.erc_ten}" />
		<input type="hidden" id="erc_hun" value="${exer.erc_hun}" />
		<input type="hidden" id="exe_kcal" value="${exe_kcal}" />

 		
		<c:if test="${exe_kcal <100}">
			<div class=	"ten_cal">약 </div>		
			<script>
				$(function(){
					var erc_ten = $('#erc_ten').val();
					var exe_kcal = $('#exe_kcal').val();
					var result = (erc_ten / 10 * exe_kcal);
					$('.ten_cal').append(result); 
					$('.ten_cal').append(" 분의 운동이 필요합니다"); 
				});
			
			</script>
		</c:if>
		
		<c:if test="${exe_kcal >=100}">
 			<div class=	"hun_cal">약 </div> 	
			<script>
				$(function(){
					var erc_hun = $('#erc_hun').val();
					var exe_kcal = $('#exe_kcal').val();
					var result = (erc_hun / 100 * exe_kcal);
					$('.hun_cal').append(result); 
					$('.hun_cal').append(" 분의 운동이 필요합니다");
				});
			
			</script>
 		</c:if>
		<br>
		<br>
	</c:forEach>
