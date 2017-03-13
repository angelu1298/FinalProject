<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<script>
		$(function(){
			// 모달창 닫기
			$(".btn_close").click(function(){
				 $(".modal_bg").addClass("off");
			 })
		})
	</script>
	
	<div class="modal_area google_map">
		<h1><strong></strong>의 위치정보입니다.<span class="btn_close">x</span></h1>
		<div class="layer_content">
			<div id="map" class="goomap"></div>
			<script async defer src="https://maps.google.com/maps/api/js?v=3.exp&region=KR&key=AIzaSyDSY6m6TfPnFvqWmzVWioKt3x-QCfasBbI"></script>
		</div> 
	</div>
	
	
			