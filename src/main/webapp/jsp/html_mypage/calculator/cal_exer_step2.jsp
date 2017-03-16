<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 두번째~ -->
<script src="./resources/js/jquery.min.js"></script>
<script>
	$(function(){
		$('#inputdata').hide();		
// 		$('#gender').hide();		
// 		$('#weight').hide();	
		
// 		$('#time_send').hide();		
		
		$('.exerName').click(function(){
			$('#inputdata').show();		
// 			$('#gender').show();		
// 			$('#weight').show();	
			
// 			$('#time_send').show();	
			var erc_nm = $(this).val();
// 			alert("됨? " +erc_nm);
			
			$('#time_send').click(function(){
				var time = $("#time").val();
// 				var gender = $("#gender").val();
				
				var erc_nm = $("input[name='exername']:checked").val();
				alert("erc_nm? = " + erc_nm);
				var gender = $("input[name='gender']:checked").val();
				var weight = $("#weight").val();
				
// 				alert("여긴? " +erc_nm);
// 				alert("시간,성별,몸무게 = " + time + gender + weight);
				
				$.ajax({
					type : "post",
					url : "cal_exer_step2.brn",
					data : {
						"erc_nm" : erc_nm,
						"time" : time,
						"gender" : gender,
						"weight" : weight
					},
					success : function(data){
						alert(data);
						$('#select_result').empty();
						$("#select_result").append(data);
						
					},
					error : function(data, status) {
						alert('결과 출력 실패');
					}
				});//ajax end
			}); //click end
			
		});
		
		
	});
</script>
<c:forEach var="name" items="${erc_nm}">
	<input type="radio" class="exerName" name="exername" value="${name.erc_nm}" />${name.erc_nm}

</c:forEach>
<div id="inputdata">
<br/>
시간 : <input type="text" name="time" id="time" /> <br/>
성별 : <input type="radio" name="gender" id="gender" value="1"/> 남
	  <input type="radio" name="gender" id="gender" value="2"/> 여<br/>
몸무게 : <input type="text" name="weight" id="weight" /> 

<input type="button" id="time_send" value="결과보기"/>
</div>

<div id="select_result"></div>