<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		$('.scomm_re_ct_area').hide();
		$('.reply').each(function(index, item) {
			$(this).on('click', function() {
				$('.scomm_re_ct_area').hide();
				$(this).parents('li').find('.scomm_re_ct_area').show();
			})
		})
		$('.deletecomm').each(function(index, item) {
			$(this).on('click', function() {
				if (confirm("정말 삭제하시겠습니까??") == true) {
					var scomm_no = $(this).parents('li').find('.scomm_no').val();
					var s_no = $("#s_no").val();
					$.ajax({
						data : {
							"s_no" : s_no,
							"scomm_no" : scomm_no
						},
						type : "post",
						url : "scommdelete_ok.brn",
						success : function(data) {
							if (data == 1) {
								alert('코멘트가 삭제되었습니다');
								$('.commentList').empty();
								$('.commentList').load("scommList.brn", 's_no=' + s_no);
								$('.scomm_re_ct').hide();
								$('.scomm_rep_button').hide();
							}
							else
								alert('코멘트 삭제에 실패했습니다');
						},
						error : function(data, status) {
							alert('코멘트 삭제에 실패하였습니다.');
						}
					})
				} else { //취소
				}
			})

		})
		$('.scomm_rep_button').each(function(index, item) {
			$(this).on('click', function() {
				var scomm_no = $(this).parents('li').find('.scomm_no').val();
				var s_no = $("#s_no").val();
				var scomm_re_ct = $(this).parents('li').find('.scomm_re_ct').val();
				$.ajax({
					data : {
						"s_no" : s_no,
						"scomm_no" : scomm_no,
						"scomm_ct" : scomm_re_ct
					},
					type : "post",
					url : "scommReply_ok.brn",
					success : function(data) {
						$('.commentList').load("scommList.brn",'s_no='+s_no);
					},
					error : function(data, status) {
						alert('코멘트 답글 실패.');
					}
				})
			})
		})
	})
	
</script>
<!-- 코멘트 부분 -->
<h3>코멘트 남기기</h3>
<c:if test="${!empty beanlist }">
	<ul>
		<c:forEach var="comm" items="${beanlist }">
			<li>
			<input type="hidden" class="scomm_no" name="scomm_no" value="${comm.scomm_no }" />
				<!-- 답글인 경우 -->
				<c:if test="${comm.scomm_re_lev != 0}"> 
					<p class="ic_reply">
						<c:forEach var="k" begin="1" end="${comm.scomm_re_lev}">
							<strong>reply</strong>		
						</c:forEach>
					</p>
				</c:if>	
				<c:set var="num" value="${comm.scomm_re_lev}" />
					<div class="content st0${num}">
						<p class="fl">
							<span>${comm.mem_id } </span>
							<!-- 작성자  -->
							<span> ${comm.scomm_dt }</span>
							<!-- 작성 날짜 -->
						</p>
						<!-- 코멘트 내용 -->
						<span>${comm.scomm_ct }</span>
					</div>
				<p class="sbtnarea">
					<c:if test="${sessionScope.mem_id == comm.mem_id }">
						<span class="deletecomm">[삭제]</span>
					</c:if>
					<c:if test="${sessionScope.mem_id !=comm.mem_id }">
						<span></span>
					</c:if>
					<span class="reply">[답글]</span>
				</p>
				<div class="scomm_re_ct_area comm_re_ct_area">
					<h5>댓글을 작성해주세요</h5>
					<textarea cols="100%" rows="10%" class="scomm_re_ct comm_re_ct" id="scomm_re_ct"></textarea>
					<button class="scomm_rep_button comm_rep_button">댓글남기기</button>
				</div>
			</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${empty beanlist }">
	<p class="nolist">등록된 코멘트가 없습니다.</p>
</c:if>

<div class="scomm_ct_area comm_re_ct_area">
	<textarea cols="70" rows="20" id="scomm_ct"></textarea>
	<button onclick="scomm_write()" class="scomm_rep_buttonL comm_re_ct_area">코멘트남기기</button>
</div>
