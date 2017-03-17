<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.*"%>
<script src="./resources/js/jquery.js"></script>
<script src="./resources/js/list.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
	System.out.println("result_nm 들어왔당");
--%>
	
	
	<p class="btn_list">
		<c:forEach var="exer" items="${exerciselist }">
			<input type="button" value="${exer.erc_nm}" id="ercnmBtn_${exer.erc_nm}" class="ercnmBtn">
		</c:forEach>
	</p>
	
	<div id="kcalbox"></div>
	<div id="kcalbutton"></div>
	
	<div id="timevalue"></div>
	
	<script type="text/javascript">
		
		var ercname;
			
		$(".ercnmBtn").click(function() {
				
				var kcalinput = "<br><input type='text' id='kcalinput'></div><br><br>";
				var kcalbutton = "<input type='button' id='kcalbutton' value='kcal''></div><br><br>";
				
				$("#kcalbox").html(kcalinput);
				$("#kcalbutton").html(kcalbutton);
				
				var ercnmBtnId = $(this).attr('id');
				ercname = ercnmBtnId.split('_')[1];
		});
		
		$("#kcalbutton").click(function() {
			var muValue = $("#mugae").val();
			
			var erc_sx	= ${erc_sx };
			
			var exe_kcal = $("#kcalinput").val();
			
			$.ajax({
				type : "post",
				data :  {
							"exe_kcal" : exe_kcal,
							"exe_name" : ercname,
							"erc_sx" : erc_sx,
							"erc_w" : muValue,
						},
				url : "bringtime.brn",
				success : function(data) {
					$("#timevalue").html(data);
				},
			});	
			
			
		});
		
	</script>