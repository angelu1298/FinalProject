<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 두번째~ -->
<script>
	$(function(){
		$('#inputdata').hide();		
		
		$('.exerName').click(function(){
			$('#inputdata').show();		
			var erc_nm = $(this).val();
			
			$('#time_send').click(function(){
				var time = $("#time").val();
				var erc_nm = $("input[name='exername']:checked").val();
				alert("erc_nm? = " + erc_nm);
				var gender = $("input[name='gender']:checked").val();
				var weight = $("#weight").val();
				
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

	시간 : <input type="text" name="time" id="time" />
	성별 : <input type="radio" name="gender" id="gender" value="1"/> 남
		  <input type="radio" name="gender" id="gender" value="2"/> 여
		  
	몸무게 : <input type="text" name="weight" id="weight" value="${membean.mem_w }" /> 
	<input type="button" id="time_send" value="결과보기"/>

</div>

<div id="select_result"></div>