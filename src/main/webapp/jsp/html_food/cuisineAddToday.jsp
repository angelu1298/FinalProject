<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<script>
	$(function(){
		
		 $(".btn_submit").click(function(){
			 $("#delok").submit();
		 })
		 $(".btn_close").click(function(){
			 $(".modal_bg").addClass("off");
		 })
		 
	})
	</script> 

	<div class="modal_area size_small">
		<h1>오늘의 섭취 목록에 추가<span class="btn_close">x</span></h1>
		<div class="layer_content">
		
			<h2 class="mt50"><strong class="txtred">&lt; <%=request.getParameter("cus_tt")%> &gt;</strong>을(를)<br/>오늘 식단에 추가하시겠습니까?</h2>
			<form method="get" action="cuisineAddTodayOk.brn" class="style_corfrim" id="delok">
				<input type="hidden" name="cus_no" value="<%=request.getParameter("cus_no")%>" class="cus_no" />
				<input type="hidden" name="cus_cal" value="<%=request.getParameter("cus_cal")%>" class="cus_cal" />
				<input type="hidden" name="quantity" value="<%=request.getParameter("quantity")%>" class="quantity" />
	 			<p class="modal_btn_area">
					<input type="submit" value="추가하기" class="btn_submit" />
					<input type="reset" value="취소" class="btn_close" />
				</p>
			</form>
			
		</div> 
	</div>