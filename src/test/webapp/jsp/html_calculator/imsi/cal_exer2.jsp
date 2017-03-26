<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="./resources/js/jquery.min.js"></script>
<script>
$(function(){
	$(".erc_ty").on('click', function(){
		var erc_ty = $('.erc_ty:checked').val();
		alert(erc_ty);
		$.ajax({
			type : "post",
			url : "calculator_exercise_ok.brn",
			data : {
				"erc_ty" : erc_ty
			},
			success : function(data){
			var inputdata = $('.inputdata').val();
				alert('오는감'+inputdata);
				$('.result').load('cal_exercise_result.brn', 'erc_nm' + inputdata);
					
			},
			error : function(data, status) {
				alert('결과 출력 실패');
			}
		})
	});
	
	$('.exer_type').on('click', function(){
		
	});
});
</script>
<input type="hidden" class="inputdata" value="${erc_nm }"/>
<h4>운동 계산기</h4>

<input type="radio" class="erc_ty" name="exer_type" value="걷기/달리기" /> 걷기/달리기
<input type="radio" class="erc_ty" name="exer_type" value="구기운동" /> 구기운동
<input type="radio" class="erc_ty" name="exer_type" value="댄스" />댄스
<input type="radio" class="erc_ty" name="exer_type" value="생활체육" /> 생활체육
<input type="radio" class="erc_ty" name="exer_type" value="수중운동" /> 수중운동
<input type="radio" class="erc_ty" name="exer_type" value="기타" /> 기타<br/>

 <div class="result"></div>


<div class="input_kcal"></div>Kcal <br/>
<button class="result_button">결과</button>

<div class="result_time"></div>분 동안 운동해야 합니다.

				        		<!-- 	/*  "<tr>"
									  + "<td>" + this.id + "</td>"
									  + "<td>" + this.name + "</td>"
									  + "<td align='right'>" + this.price + "</td>"
									  + "<td>" + this.description + "</td>" + "</tr>"); */ -->
