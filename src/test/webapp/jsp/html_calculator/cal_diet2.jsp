<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

	<%@ include file="../inc/subHeader.jsp" %>
	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">
	
		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../inc/myinfo.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
					
			<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			
			<style>
			.cal_diet{ width:100%; height:auto; display:inline-block; border:1px solid #00f;}
				.group{ border:1px solid #f00; width:100%; background-attachment:fixed; }
				.cal_diet .result{border:1;}
				.cal_diet  .w{
				float:right;
					 background-image:url('resources/img/cal/diet_background.png');
					  background-repeat:no-repeat;
					   background-position:center center ;
						text-align: center;
						border:1px solid #000;  width:500px; height:500px; display:block;
					 }
						.cal_diet  p {position: absolute; top: 20%; margin:0 auto; }
				.cal_diet  .goal_weight{
					
					font-size:40pt;
					text-align: center;
					background-color:black;
					opacity: 0.28;
					color:white;
					width:100px; height:50px; line-height:50px; 
					}
			.cal_diet 	#w1{
					 
					font-size:30pt;
					}
			 .cal_diet 	.result{position:relative; top:-500px; left:-130%} 
			</style>
			
			<div class="cal_diet">
			<script src="./resources/js/jquery-ui.min.js"></script>
			<script>
			$(function() {
				//유효성검사 
			
			
				$(".goal_weight").keyup(function() {
					if($('.goal_weight').val() > 150){
						alert('병원가세요');
						$('.goal_weight').val('');
					}/* else if($('.goal_weight').val() < 36){
						alert('최저몸무게는 36kg입니다');
						$('.goal_weight').val('');
					}   */
				});
				
				
				function setImageRadio(){ 
					document.getElementById('man').src= 
					(document.getElementsByName('gender')[0].checked==true)?"resources/img/cal/man-click-running.png":"resources/img/cal/man-running.png"; 
			
					document.getElementById('woman').src= 
					(document.getElementsByName('gender')[1].checked==true)?"resources/img/cal/girl-click-running.png":"resources/img/cal/girl-running.png"; 
			
					};
				
				$("#picker").datepicker();
				
				$('.cal_diet_button').each(function(index, item) {
					$(this).on('click', function() {
						var now_weight = $('.now_weight').val();
						var goal_weight = $('.goal_weight').val();
						var goalDay = $('.goalDay').val();
			// 			var select_per = Integer.parseInt($('.per:checked').val());
						var select_per = $('.per:checked').val();
						alert(select_per);
						$.ajax({
							data : {
								"now_weight" : now_weight,
								"goal_weight" : goal_weight,
								"goalDay" : goalDay,
								"select_per" : select_per
							
							},
							type : "post",
							url : "calculator_diet_ok.brn",
							success : function(data) {
								$('.result').load('cal_diet_result.brn');
									
							},
							error : function(data, status) {
								alert('결과 출력 실패');
							}
						})
					})
				});
				
			 
			});
			</script>
			<!-- 목표 체중&nbsp; -->
			<div class="w">
				<p>
					<input type="text" name="goal_weight" class="goal_weight" />
					<span id="w1">Kg</span>
				</p>
				
			<div class="group">
			
			<!-- 	 	키 &nbsp;<input type="text" name="height" size="5px"/> <br/> -->
				 	<!--  -->
			
					<input type="radio" name="gender" value="남"  > 
					<img src="resources/img/cal/man-running.png" id="man"
						onClick="document.getElementsByName('gender')[0].checked=true; setImageRadio();" 
						onmouseover="this.src='resources/img/cal/man-click-running.png'"
						onmouseout="this.src='resources/img/cal/man-running.png'"> 
			
					<input type="radio" name="gender" value="여" >
					<img src="resources/img/cal/girl-running.png" id="woman"
						onClick="document.getElementsByName('gender')[1].checked=true; setImageRadio();"
						onmouseover="this.src='resources/img/cal/girl-click-running.png'"
						onmouseout="this.src='resources/img/cal/girl-running.png'"> <br/>
					<!--  -->
					현재 체중&nbsp;<input type="text" size="5px" name="now_weight" class="now_weight" /> <br/>
				
			<!-- 		목표 체중&nbsp;<input type="text" id="w" size="5px" name="goal_weight" class="goal_weight" style="width:100px; height:25px; line-height:25px; background-image:url('resources/img/cal/scalebox.PNG')"/> -->
				<br/>
					목표날짜&nbsp;<input type="text" size="5px" name="goalDay" class="goalDay" />
					
					<input type="text" id="picker" />
			
				  <ul>
				  		<li><input type="radio" class="per" name="select_per" id="select_per1" value="100" /><label for="select_per1">운동 100%</label></li>
				  		<li><input type="radio" class="per" name="select_per" id="select_per2" value="80" /><label for="select_per2">운동 80%, 식사 20%</label></li>
				  		<li><input type="radio" class="per" name="select_per" id="select_per3" value="60" /><label for="select_per3">운동 60%, 식사 40%</label></li>
				  		<li><input type="radio" class="per" name="select_per" id="select_per4" value="50" /><label for="select_per4">운동 50%, 식사 50%</label></li>
				  		<li><input type="radio" class="per" name="select_per" id="select_per5" value="40" /><label for="select_per5">운동 40%, 식사 60%</label></li>
				  		<li><input type="radio" class="per" name="select_per" id="select_per6" value="20" /><label for="select_per6">운동 20%, 식사 80%</label></li>
				 </ul>
					 	<div class="result">
				<%-- 		<%@ include file="cal_diet_result.jsp" %> --%>
				
					{dietbean.reduceKg} kg을 {dietbean.goalDay} 일 안에 감량하려면 <br/>
					매일 섭취하는 칼로리에서 - {dietbean.reduceKcal}kcal 줄여나가야함 <br/>
					운동{dietbean.exec_percent}% 식사 {dietbean.meal_percent}% <br/>
					운동 : - {dietbean.reduceExec}kcal <br/>
					식사 : 하루 {dietbean.reduceMeal}kcal 먹기 <br/>
					운동으로 - {dietbean.reduceExec}kcal을 소모하려면 <br/>
						db데이터~
					
					</div>
			  	<button class="cal_diet_button">결과보기</button> 
			</div>
			</div>
			
			<!-- 	$(function() {
				$('.cal_diet_button').each(function(index, item) {
					$(this).on('click', function() {
						var now_weight = $('.now_weight').val();
						var goal_weight = $('.goal_weight').val();
						var goalDay = $('.goalDay').val();
						var select_per = $('.per').val();
						$.ajax({
							data : {
								"now_weight" : now_weight,
								"goal_weight" : goal_weight,
								"goalDay" : goalDay,
								"select_per" : select_per
							
							},
							type : "post",
							url : "calculator_diet_ok.brn",
							success : function(data) {
								var item =$.param(data);
								alert(item);
									$('.result').load('cal_diet_result.brn',item);
									
							},
							error : function(data, status) {
								alert('결과 출력 실패');
							}
						})
					})
				});
			}); -->
			
			<!-- $(function() {
				$('.cal_diet_button').each(function(index, item) {
					$(this).on('click', function() {
						var now_weight = $('.now_weight').val();
						var goal_weight = $('.goal_weight').val();
						var goalDay = $('.goalDay').val();
						var select_per = $('.per').val();
						$.ajax({
							data : {
								"now_weight" : now_weight,
								"goal_weight" : goal_weight,
								"goalDay" : goalDay,
								"select_per" : select_per
							
							},
							type : "post",
							url : "calculator_diet_ok.brn",
							success : function(data) {
								var item =$.param(data);
								alert(item);
									$('.result').load('cal_diet_result.brn',item);
									
							},
							error : function(data, status) {
								alert('결과 출력 실패');
							}
						})
					})
				});
			}); -->
				
		
		
		
		
		
		
		</div>
		
		</div>		
		<!-- 서브컨텐츠 영역 END -->
	
	</section>
	<!-- // container End -->
	
	<%@ include file="../inc/footer.jsp" %>