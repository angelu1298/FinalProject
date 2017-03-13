<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<script>
	$(function(){
	
		 $(".btn_submit").click(function(){
			 $(".modal_bg").addClass("off");
			 $("#delok").submit();
		 })
	
	})
	</script> 

	<div class="modal_area size_small">
		<h1>삭제확인<span class="btn_close">x</span></h1>
		<div class="layer_content">
			<h2 class="mt50">현재 게시물을 삭제하시겠습니까?</h2>
			<form method="post" action="gbbs_delete_ok.brn" class="style_corfrim" id="delok">
			
				<input type="hidden" name="num" value="${gbbsbean.gbbs_num}" />
				<input type="hidden" name="page" value="${page}" />
	 
	 			<p class="modal_btn_area">
					<input type="submit" value="삭제" class="btn_submit" />
					<input type="reset" value="취소" class="btn_close" />
				</p>
				
			</form>
			
		</div> 
	</div>