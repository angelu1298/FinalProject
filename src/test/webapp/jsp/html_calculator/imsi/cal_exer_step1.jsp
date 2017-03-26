<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 처음~ -->
<script src="./resources/js/jquery.min.js"></script>
 <script>
$(function(){
		$(".type").click(function(){
			var erc_ty = $(this).val();
			alert(erc_ty);
			// 	$("#운동타입_기타").on('click', function(){
			// 		var erc_ty = $('#운동타입_기타').val();
			$.ajax({
				type : "post",
				url : "cal_exer_ok.brn",
				data : {
					"erc_ty" : erc_ty
				},
				success : function(data){
					alert(data);
					$("#step2").empty();
					 $("#step2").append(data);
					
				},
				error : function(data, status) {
					alert('결과 출력 실패');
				}
			});// ajax

		});
});
</script> 
<h4>운동 계산기</h4>
<c:forEach var="a" items="${erc_ty}" >
	<input type="button" class="type" name="${a.erc_ty}" value="${a.erc_ty}" />
</c:forEach>

<%-- class="운동타입 ${a.erc_ty}" --%>

<div class="step2" id="step2"></div>

<!--  


<div class="input_kcal"></div>Kcal <br/>
<button class="result_button">결과</button>

<div class="result_time"></div>분 동안 운동해야 합니다.

/* $('.exer_type').on('click', function(){
		/* $(".운동타입").on('click', function(){
		$.each(function(index, item){
			var a = $(this).val();
			
		}) */
  				/* $.each(data, function() { //반복문
 					
				 $("#step2").append( //<table>태그 뒤에 추가
						"<input type='button' class='운동이름_'"+ 
						+ "name=" + this.erc_nm + "value=" + this.erc_nm +"/>"
					); 
  				});  */
 -->
				        	
