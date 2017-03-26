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
<p>
	<c:forEach var="name" items="${erc_nm}">
		<label>
			<input type="radio" class="exerName" name="exername" value="${name.erc_nm}" />
			${name.erc_nm}
		</label>
	</c:forEach>
</p>

<div id="inputdata">

	<p><strong>시간</strong> <input type="text" name="time" id="time" /></p>
	<p><strong>성별</strong>
			<label><input type="radio" name="gender" id="gender" value="1"/>
 			<img src="resources/img/cal/man.png"/></label>
		  <label><input type="radio" name="gender" id="gender" value="2"/>
			<img src="resources/img/cal/woman.png" /></label>
                        
	</p>	  
	<p><strong>몸무게</strong><input type="text" name="weight" id="weight" value="${membean.mem_w }" /> </p>
	<p >
	<input type="button" id="time_send" value="결과보기"/>
	</p>
</div>

<div id="select_result"></div>