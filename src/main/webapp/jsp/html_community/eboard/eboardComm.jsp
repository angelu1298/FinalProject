<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="./resources/js/jquery.min.js"></script>
<script>
function ecomm_write(){

	if( $('#ecomm_ct').val().trim()==''){
		alert('코멘트를 입력하세요');
		return false;
	}
	var e_no = $('#e_no').val();
	var ecomm_ct = $('#ecomm_ct').val();
	$.ajax({
		type: "post",
		data : {"e_no": e_no,
				"ecomm_ct": ecomm_ct},
		url: "ebo_comm.brn",
		success : function(data){
			if(data==1){
			$('.commentList').empty();
			$('.commentList').load("ecommList.brn",'e_no='+e_no);
			}else{
				alert('실패!!'+data);
			}
		},
		error: function(data, status){
			alert('실패 ㅠㅠ '+data + status);
		},
		 headers : {"cache-control": "no-cache","pragma": "no-cache"}
	})
}

 	 $(document).ready(function(){
 		$('.ecomm_re_ct_area').hide();
		$('.reply').each(function(index, item) {
			$(this).on('click', function() {
				$('.ecomm_re_ct_area').hide();
				$(this).parents('li').find('.ecomm_re_ct_area').show();
			})
		})
		
		$('.deletecomm').each(function(index, item) {
			$(this).on('click', function() {
				if (confirm("정말 삭제하시겠습니까??") == true) {
					var ecomm_no = $(this).parents('li').find('.ecomm_no').val();
					var e_no = $("#e_no").val();
					$.ajax({
						data : {
							"e_no" : e_no,
							"ecomm_no" : ecomm_no
						},
						type : "post",
						url : "ebo_reply_del.brn",
						success : function(data) {
							if (data == 1) {
								alert('코멘트가 삭제되었습니다');
								$('.commentList').empty();
								$('.commentList').load("ecommList.brn", 'e_no=' + e_no);
								$('.ecomm_re_ct').hide();
								$('.ecomm_rep_button').hide();
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
		$('.ecomm_rep_button').each(function(index, item) {
			$(this).on('click', function() {
				var ecomm_no = $(this).parents('li').find('.ecomm_no').val();
				var e_no = $("#e_no").val();
				var ecomm_re_ct = $(this).parents('li').find('.ecomm_re_ct').val();
				$.ajax({
					data : {
						"e_no" : e_no,
						"ecomm_no" : ecomm_no,
						"ecomm_ct" : ecomm_re_ct
					},
					type : "post",
					url : "ebo_reply.brn",
					success : function(data) {
						$('.commentList').load("ecommList.brn",'e_no='+e_no);
					},
					error : function(data, status) {
						alert('코멘트 답글 실패.');
					}
				})
			})
		})
	})
</script>
<h3>코멘트 남기기</h3>
<c:if test="${!empty beanlist }">
	<ul>
		<c:forEach var="co" items="${beanlist}">
			<li>
			<input type="hidden" class="ecomm_no" name="ecomm_no" value="${co.ecomm_no }" />
					<!-- 답글인 경우 -->
					<c:if test="${co.ecomm_re_lev != 0}">
						<p class="ic_reply">
							<c:forEach var="k" begin="1" end="${co.ecomm_re_lev}">
								<strong>reply</strong>	
							</c:forEach>
						</p>
					</c:if>	
					<c:set var="num" value="${co.ecomm_re_lev}" />
						<div class="content st0${num}">
							<p class="fl">
								<span>${co.mem_id } </span>
								<!-- 작성자  -->
								<span> ${co.ecomm_dt }</span>
								<!-- 작성 날짜 -->
							</p>
							<!-- 코멘트 내용 -->
							<span>${co.ecomm_ct }</span>
						</div>
				<p class="sbtnarea">
					<c:if test="${sessionScope.mem_id == co.mem_id }">
						<span class="deletecomm">삭제</span>
					</c:if>
					<c:if test="${sessionScope.mem_id !=co.mem_id }">
						<span></span>
					</c:if>
					<span class="reply">답글</span>
				</p>
				<div class="ecomm_re_ct_area comm_re_ct_area">
					<h5>댓글을 작성해주세요</h5>
					<textarea cols="100%" rows="10%" class="ecomm_re_ct comm_re_ct" id="ecomm_re_ct"></textarea>
					<button class="ecomm_rep_button comm_rep_button">댓글남기기</button>
				</div>
			</li>
		</c:forEach>
	</ul>
</c:if>	
<c:if test="${empty beanlist }">
	<p class="nolist">등록된 코멘트가 없습니다.</p>
</c:if>

<div class="ecomm_ct_area comm_re_ct_area">
	<textarea cols="70" rows="20" id="ecomm_ct"></textarea>
	<button onclick="ecomm_write()" class="ecomm_rep_buttonL comm_re_ct_area">코멘트남기기</button>
</div>	

