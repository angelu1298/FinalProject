<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script>
		$(function(){
			// 모달창 닫기
			$(".btn_close").click(function(){
				 $(".modal_bg").addClass("off");
				 $(".modal_bg").animate({"opacity":"0"});
			 })
		})
	</script>
	<div class="modal_area alertpage">
		<h1>알려드립니다<span class="btn_close">x</span></h1>
		<div class="layer_content">
			<p class="message"></p>
 			<p class="modal_btn_area">
				<input type="reset" value="확인" class="btn_close" />
			</p>
		</div> 
	</div>