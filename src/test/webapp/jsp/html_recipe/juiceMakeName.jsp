<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

	<script>
	
		$(function(){
			
			// 모달창 닫기
			$(document).on('keyup', function(evt){ 
				$(".modal_bg").addClass("off");
				$(".modal_bg").animate({ opacity: '0' }, 500);
			})
			$(".btn_close").click(function(){
				$(".modal_bg").addClass("off");
				$(".modal_bg").animate({ opacity: '0' }, 500);
			})
			 
			// 제출하기!
			$(".btn_submit").click(function(){
				 var rcptt  = $(".rcp_tt").val();
				 var rcpno  = $(".rcp_no").val();
				 var rcpcal = $(".rcp_cal").val();
				 var qry =  "<input type=\"hidden\" id=\"rcp_tt\"  name=\"rcp_tt\"  value=\""+rcptt+"\"  />";
				 	 qry += "<input type=\"hidden\" id=\"rcp_no\"  name=\"rcp_no\"  value=\""+rcpno+"\"  />";
				 	 qry += "<input type=\"hidden\" id=\"rcp_cal\" name=\"rcp_cal\" value=\""+rcpcal+"\" />";
				 $(".titlebox").html(qry);
				 $("#recipeform").submit();
			 })
			 
		})
		
	</script>
	
	<div class="modal_area alertpage">
		<h1>레시피 저장하기<span class="btn_close">x</span></h1>
		<div class="layer_content">
			<h2 class="mt50">주스의 이름을 정해주세요</h2>
			<div class="rsltRecipe">
			 	<p>
					<input type="text" placeholder="내 주스 레시피의 이름을 정해주세요" class="rcp_tt">		
			 	</p>
			</div>
 			<p class="modal_btn_area">
				<input type="button" value="취소"    class="btn_w btn_close"   />
				<input type="button" value="저장하기" class="btn_b btn_submit"  />
			</p>
		</div> 
	</div>