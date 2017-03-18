<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
		<script type="text/javascript">
			
			var ercname;
				
			$(function(){
				
				$(".ercnmBtn").on("click", function(){
					
					$(".ercnmBtn").removeClass("on");
					$(this).addClass("on");
					
					var kcalinput =  "<span>감량하려는 칼로리를 입력해주세요.</span>";
						kcalinput += "<input type='text' id='kcalinput'>";
					var kcalbutton = "<input type='button' id='kcalbutton' value='OK''>";
					
					$("#kcalbox_area").html(kcalinput+kcalbutton);
					var ercnmBtnId = $(this).attr('id');
					ercname = ercnmBtnId.split('_')[1];

					$("#kcalbox_area input[type=button]").on("click", function(){
						
						var muValue = $("#mugae").val();
						var erc_sx	= ${erc_sx};
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
							} 
						})
			
					})
					
				});
				
			})
		</script>
	
	<p class="btn_list">
		<c:forEach var="exer" items="${exerciselist }">
			<input type="button" value="${exer.erc_nm}" id="ercnmBtn_${exer.erc_nm}" class="ercnmBtn">
		</c:forEach>
	</p>
	<div class="rsltbox">
		<p>
			<span id="kcalbox_area"></span>
			<span id="kcalbutton_area"></span>
		</p>
		<span id="timevalue"></span>
	</div>