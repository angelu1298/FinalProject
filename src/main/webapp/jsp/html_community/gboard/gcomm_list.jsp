<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script>

$(function(){
	
	// 코멘트
	$('.gcomm_re_ct_area').hide();
	$('.reply').each(function(index, item) {
		$(this).on('click', function() {
			$('.gcomm_re_ct_area').css("display","none");
			$(this).parents('li').find('.gcomm_re_ct_area').css("display",""); 
		})
	})
		
	$('.deletecomm').each(function(index, item) {
		$(this).on('click', function() {
			if (confirm("정말 삭제하시겠습니까??") == true) {
				var gcomm_no = $(this).parents('li').find('.gcomm_no').val();
				var gbbs_num = $("#gbbs_num").val();
				$.ajax({
					data : {
						"gbbs_num" : gbbs_num,
						"gcomm_no" : gcomm_no
					},
					type : "post",
					url : "gcomm_delete_ok.brn",
					success : function(data) {
						if (data == 1) {
						alert('코멘트가 삭제되었습니다');
							$('.commentList').empty();
							$('.commentList').load("gcomm_list.brn", 'gbbs_num=' + gbbs_num);
							$('.gcomm_re_ct_area').css("display","none");
						}
						else{
							alert('코멘트 삭제에 실패했습니다');
						}
							
					},
					error : function(data, status) {
						alert('코멘트 삭제에 실패하였습니다.');
					}, complete : function(){
						location.reload();
					}
				})
			} else { //취소
			}
		})
	})
	
	/* 코멘트!! */
	$('.gcomm_rep_button').each(function(index, item) {
		
		$(this).on('click', function() {
	
			var gcomm_no = $(this).parents('li').find('.gcomm_no').val();
			var gbbs_num = $("#gbbs_num").val();
			var gcomm_re_ct = $(this).prev('.gcomm_re_ct').val();
			
			$.ajax({
				data : {
					"gbbs_num" : gbbs_num,
					"gcomm_no" : gcomm_no,
					"gcomm_re_ct" : gcomm_re_ct
				},  
				type : "post",
				url : "gcomm_reply_ok.brn",
				success : function(data) {
					$('.commentList').html("");
					$('.commentList').load("gcomm_list.brn",'num='+gbbs_num);
				},
				error : function(data, status) {
					alert('코멘트 답글 실패.');
				} , complete : function(){
					location.reload();
				}
			})
		})
	})
	
	
	$(".gcomm_rep_buttonL").click(function(){
		
		if( $('#gcomm_ct').val().trim()==''){
			alert('코멘트를 입력하세요');
			return false;
		}
		var gbbs_num = $('#gbbs_num').val();
		var gcomm_ct = $('#gcomm_ct').val();
		
		$.ajax({
			type: "post",
			data : {
				"gbbs_num": gbbs_num,
				"gcomm_ct": gcomm_ct
			},
			url: "gcomm_write_ok.brn",
			success : function(data){
				if(data==1){
					$('.sntList').load("gcomm_list.brn",'gbbs_num='+gbbs_num);
				} else{
					alert('실패!!'+data);
				}
			},
			error: function(data, status){
				alert('실패 ㅠㅠ '+data + status);
			}, complete : function(){
				location.reload();
			}
		})
	})
	/* change */
})

</script>
<!-- 코멘트 부분 -->
<h3>코멘트 남기기</h3>
<c:if test="${!empty beanlist}">
	<ul>
		<c:forEach var="comm" items="${beanlist}">
			<li>
				<input type="hidden" class="gcomm_no" name="gcomm_no" id="gcomm_no" value="${comm.gcomm_no}" />
				<!-- 답글인 경우 -->
				<c:if test="${comm.gcomm_re_lev != 0}"> 
					<p class="ic_reply">
						<c:forEach var="k" begin="1" end="${comm.gcomm_re_lev}">
						<strong>reply</strong>
						</c:forEach>
						<c:set var="num" value="${comm.gcomm_re_lev}" />
					</p>
				</c:if>		

				<div class="content st0${num}">
					<p class="fl">
						<!-- 작성자  -->
						<span>${comm.mem_id } </span>
						<!-- 작성 날짜 -->
						<span> ${comm.gcomm_dt }</span>
					</p>
					<!-- 코멘트 내용 -->
					<span>${comm.gcomm_ct }</span>
				</div>

				<p class="sbtnarea">
					<c:if test="${sessionScope.mem_id == comm.mem_id }">
						<span class="deletecomm">삭제</span>
					</c:if>
					<c:if test="${sessionScope.mem_id !=comm.mem_id }">
						<span></span>
					</c:if>
					<span class="reply">답글</span>
				</p>

				<div class="gcomm_re_ct_area" style="display:none">
					<h5>댓글을 작성해주세요</h5>
					<textarea cols="100%" rows="10%" class="gcomm_re_ct" id="gcomm_re_ct"></textarea>
					<button class="gcomm_rep_button">댓글남기기</button>
				</div>
			</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${empty beanlist }">
	<p class="nolist">등록된 코멘트가 없습니다.</p>
</c:if>

<div class="gcomm_ct_area comm_ct_area">
	<textarea cols="70" rows="20" id="gcomm_ct"></textarea>
	<button class="gcomm_rep_buttonL comm_rep_buttonL" >코멘트남기기</button>
</div>