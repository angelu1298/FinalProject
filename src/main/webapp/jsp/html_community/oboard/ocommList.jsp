<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		$('.ocomm_re_ct').hide();
		$('.ocomm_rep_button').hide();
		$('.reply').each(function(index, item) {
			$(this).on('click', function() {
				$('.ocomm_re_ct').hide();
				$('.ocomm_rep_button').hide();
				$(this).parents('li').find('.ocomm_re_ct').show();
				$(this).parents('li').find('.ocomm_rep_button').show();
			})
		})
		$('.deletecomm').each(function(index, item) {
			$(this).on('click', function() {
				if (confirm("정말 삭제하시겠습니까??") == true) {
					var ocomm_no = $(this).parents('li').find('.ocomm_no').val();
					var o_no = $("#o_no").val();
					$.ajax({
						data : {
							"o_no" : o_no,
							"ocomm_no" : ocomm_no
						},
						type : "post",
						url : "ocommdelete_ok.brn",
						success : function(data) {
							if (data == 1) {
								alert('코멘트가 삭제되었습니다');
								$('.commentList').empty();
								$('.commentList').load("ocommList.brn", 'o_no=' + o_no);
								$('.ocomm_re_ct').hide();
								$('.ocomm_rep_button').hide();
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
		$('.ocomm_rep_button').each(function(index, item) {
			$(this).on('click', function() {
				var ocomm_no = $(this).parents('li').find('.ocomm_no').val();
				var o_no = $("#o_no").val();
				var ocomm_re_ct = $(this).parents('li').find('.ocomm_re_ct').val();
				$.ajax({
					data : {
						"o_no" : o_no,
						"ocomm_no" : ocomm_no,
						"ocomm_ct" : ocomm_re_ct
					},
					type : "post",
					url : "ocommReply_ok.brn",
					success : function(data) {
						$('.commentList').load("ocommList.brn",'o_no='+o_no);
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
		<c:forEach var="ocomm" items="${beanlist }">
			<li>
			<input type="hidden" class="ocomm_no" name="ocomm_no"
					value="${ocomm.ocomm_no }" />
				<!-- 답글인 경우 -->
				<c:if test="${ocomm.ocomm_re_lev != 0}"> 
					<p class="ic_reply">
						<c:forEach var="k" begin="1" end="${ocomm.ocomm_re_lev}">
							<strong>reply</strong>					
						</c:forEach>
					</p>
				</c:if>			
					<span>${ocomm.mem_id } </span>
					<!-- 작성자  -->
					<span> ${ocomm.ocomm_dt }</span>
					<!-- 작성 날짜 -->
					<span>${ocomm.ocomm_ct }</span>
					<!-- 코멘트 내용 -->
				</p>
				<p class="sbtnarea">
					<c:if test="${sessionScope.mem_id == ocomm.mem_id }">
						<span class="deletecomm">[삭제]</span>
					</c:if>
					<c:if test="${sessionScope.mem_id !=ocomm.mem_id }">
						<span></span>
					</c:if>
					<span class="reply">[답글]</span>
				</p>
			
				<div class="comm_re_ct_area">
					<h5>댓글을 작성해주세요</h5>
					<textarea cols="100%" rows="10%" class="ocomm_re_ct"></textarea>
					<button class="ocomm_rep_button" >댓글남기기</button>
				</div>
				
			</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${empty beanlist }">
	<span>등록된 코멘트가 없습니다.</span>
</c:if>
<div class="comm_ct_area">
	<textarea cols="70" rows="20" id="ocomm_ct"></textarea>
	<button onclick="ocomm_write()" class="comm_rep_buttonL">코멘트남기기</button>
</div>	
	<script>
	
		function ocomm_write(){

			alert("!!!!!!!!!!!!!!!!!");			
			if( $('#ocomm_ct').val()==''){
				alert('코멘트를 입력하세요');
				return false;
			}
			var o_no = $('#o_no').val();
			var ocomm_ct = $('#ocomm_ct').val();
			$.ajax({
				type: "post",
				data : {"o_no": o_no,
						"ocomm_ct": ocomm_ct},
				url: "ocommWrite_ok.brn",
				success : function(data){
					alert(data);
					if(data==1){
					$('.commentList').empty();
					$('.commentList').load("./ocommList.brn",'o_no='+o_no);
					}else{
						alert('실패!!'+data);
					}
				},
				error: function(data, status){
					alert('실패 ㅠㅠ '+data + status);
				},
				 headers : {"cache-control": "no-cache","pragma": "no-cache"}
			});
		};
	
	</script>
