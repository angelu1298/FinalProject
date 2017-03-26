<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			
	<%@ include file="../inc/subHeader.jsp" %>
	<script src="./resources/js/jquery-ui.min.js"></script>
	<style>
	.cal_diet{ position:relative; width:100%; height:auto; display:inline-block; border:1px solid #00f; height:500px;}
	.group{ border:1px solid #f00; width:100%; background-attachment:fixed; position:relative; }
	.group > ul{ margin-top:30%; border:1px solid #00f; display:inline-block }
	.group > ul > li{ clear:both; float:left; text-align:left;}
	.cal_diet .result{border:1;}
	.cal_diet  .w{ position:absolute; top:0; right:0; 	width:500px; height:500px; display:block;
					 background-image:url('resources/img/cal/diet_background.png');
					  background-repeat:no-repeat;
					   background-position:center center ;
						text-align: center;
				}
	.cal_diet  p {position: absolute; top: 20%; left:0; width:100%; margin:0 auto; }
	.cal_diet  .goal_weight{
					font-size:40pt;
					text-align: center;
					background-color:black;
					opacity: 0.28;
					color:white;
					width:100px; height:50px; line-height:50px; 
	}
	.cal_diet 	#w1{ font-size:30pt; }
	.cal_diet 	.result{ float:left; width:100%; padding-right:500px;} 
	</style>
	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">
	
		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../inc/myinfo.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
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
			<div class="cal_diet">
		
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
							
				<div class="w">
				
					<!-- 체중계 첫번쨰 -->
					<p>
						<input type="text" name="goal_weight" class="goal_weight" />
						<span id="w1">Kg</span>
					</p>
				
					<!-- 체중계 두번쨰 -->
					<div class="group">
						 
						 <ul>
						 	<li>
								<input type="radio" name="gender" value="남"  > 
								<img src="resources/img/cal/man-running.png" id="man"
									onClick="document.getElementsByName('gender')[0].checked=true; setImageRadio();" 
									onmouseover="this.src='resources/img/cal/man-click-running.png'"
									onmouseout="this.src='resources/img/cal/man-running.png'"> 
								<input type="radio" name="gender" value="여" >
								<img src="resources/img/cal/girl-running.png" id="woman"
									onClick="document.getElementsByName('gender')[1].checked=true; setImageRadio();"
									onmouseover="this.src='resources/img/cal/girl-click-running.png'"
									onmouseout="this.src='resources/img/cal/girl-running.png'"> 
							</li>
								현재 체중&nbsp;<input type="text" size="5px" name="now_weight" class="now_weight" />
							<li>
								목표날짜&nbsp;<input type="text" size="5px" name="goalDay" class="goalDay" />
							</li>
						
							<li>
								<input type="text" id="picker" />
							</li>
					
							<li>
							  	<ol>
							  		<li><input type="radio" class="per" name="select_per" id="select_per1" value="100" /><label for="select_per1">운동 100%</label></li>
							  		<li><input type="radio" class="per" name="select_per" id="select_per2" value="80" /><label for="select_per2">운동 80%, 식사 20%</label></li>
							  		<li><input type="radio" class="per" name="select_per" id="select_per3" value="60" /><label for="select_per3">운동 60%, 식사 40%</label></li>
							  		<li><input type="radio" class="per" name="select_per" id="select_per4" value="50" /><label for="select_per4">운동 50%, 식사 50%</label></li>
							  		<li><input type="radio" class="per" name="select_per" id="select_per5" value="40" /><label for="select_per5">운동 40%, 식사 60%</label></li>
							  		<li><input type="radio" class="per" name="select_per" id="select_per6" value="20" /><label for="select_per6">운동 20%, 식사 80%</label></li>
								 </ol>
							 	<p>목표날짜&nbsp;<input type="text" size="5px" name="goalDay" class="goalDay" /></p>
							</li>
							
						</ul>
					  	<button class="cal_diet_button">결과보기</button> 
					</div>
			</div> 
				
		
		
		
		
		
		
		</div>
		
		</div>		
		<!-- 서브컨텐츠 영역 END -->
	
	</section>
	<!-- // container End -->
	
	<%@ include file="../inc/footer.jsp" %>