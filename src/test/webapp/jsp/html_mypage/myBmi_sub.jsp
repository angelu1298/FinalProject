<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Insert title here</title>
	
	<style>
		table {width: 700px; height: 300px;}
		table td {text-align:center; border:1px solid #ccc;}
		table th {text-align:center; border:1px solid #ccc; font-weight:bold;}
		#cul {text-align:right; color:red;}
	</style>
</head>
<body>
	
	키(cm) : <input type="text"  id="input_h" name="input_h"><br><br>
	몸무게(kg) : <input type="text" id="input_w" name="input_w"><br><br>
	<input type="button" id="cal" value="BMI 계산하기"><br><br>
	BMI : <input type="text" id="bmivalue" name="bmivalue" disabled><br><br>
	
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
	
	<script>
		$("#cal").click(function() {
			
			var input_h = $("#input_h").val();
			var input_w = $("#input_w").val();
			var bmi_result = (input_w / ((input_h/100) * (input_h/100)));
			
			bmi_result = bmi_result.toFixed(1);
			
			alert(bmi_result);
			
			$("#bmivalue").val(bmi_result);
			
		});	
	</script>

</body>
</html>