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
		<h1>삭제확인<span class="btn_close">x</span></h1>
		<div class="layer_content">
		
			<h2 class="mt50"><strong class="txtred">&lt; <%=request.getParameter("rcp_tt")%> &gt;</strong>을(를)<br/>삭제하시겠습니까</h2>
			<form method="get" action="recipeDeleteOk.brn" class="style_corfrim" id="delok">
				<input type="hidden" name="rcp_no" value="<%=request.getParameter("rcp_no")%>" class="rcp_no" />
	 			<p class="modal_btn_area">
					<input type="submit" value="삭제" class="btn_submit" />
					<input type="reset" value="취소" class="btn_close" />
				</p>
			</form>
			
		</div> 
	</div>