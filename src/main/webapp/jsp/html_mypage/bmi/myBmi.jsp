<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 




	<style>
		table {width: 700px; height: 300px; margin:20px; }
		table td {text-align:center; border:1px solid #ccc;}
		table th {text-align:center; border:1px solid #ccc; font-weight:bold;}
		#cul {text-align:right; color:red; margin-right:20px; }
		.size_bmi{ top:20%; left:20%; width:800px; height: 450px;}
		/* .modal_bg {position:fixed; width:500px;} */
	</style> 
	
	<script>
	$(function(){ 
		
		 $(".btn_close").click(function(){
			 $(".modal_bg").addClass("off");
			$(".modal_bg").animate({"opacity":"0"});
		 })
		 
	})
	</script> 

	<div class="modal_area size_bmi">
		<h1>BMI<span class="btn_close">x</span></h1>
		<div class="layer_content">
			
			<!-- bmi -->
			
			<table>
				<tr>
					<th></th>
					<th>  체질량 지수  </th>
					<th>  허리둘레(남:90cm이상, 여:85cm이상)  </th>
					<th>  허리둘레(남:90cm이하, 여:85cm이하)  </th>
				</tr>
				<tr>
					<th>저체중</th>
					<td>18.5미만</td>
					<td>낮음</td>
					<td>보통</td>
				</tr>
				<tr>
					<th>정상</th>
					<td>18.5 ~ 22.9</td>
					<td>보통</td>
					<td>증가</td>
				</tr>
				<tr>
					<th>위험체중</th>
					<td>23 ~ 24.9</td>
					<td>증가</td>
					<td>중등도</td>
				</tr>
				<tr>
					<th>1단계 비만</th>
					<td>25 ~ 29.9</td>
					<td>중등</td>
					<td>고도</td>
				</tr>
				<tr>
					<th>2단계 비만</th>
					<td>30이상</td>
					<td>고도</td>
					<td>매우 고도</td>
				</tr>
			</table>
			
			<div id="cul">
				(출처 - 비만치료지침 대한비만학회, 2009)
			</div>
			
			<!-- bmi -->
				
		</div>
			
	</div>
 