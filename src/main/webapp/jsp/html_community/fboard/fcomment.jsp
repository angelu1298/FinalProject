<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function(){
	$('.fcomm_re_ct').hide();
	$('.fcomm_rep_button').hide();
	$('.fcreply').each(function(index,item){  //답변 버튼을 누를때
		$(this).on('click',function(){
			$('.fcomm_re_ct').hide();
			$('.fcomm_rep_button').hide();
			$(this).parents('li').find('.fcomm_re_ct').show();
			$(this).parents('li').find('.fcomm_rep_button').show();
		})
	})
	$('.fcdelete').each(function(index,item){
		$(this).on('click',function(){
			if(confirm("삭제하시겠습니까?") == true){
				var fcomm_no = $(this).parents('li').find('.fcomm_no').val();
				var f_no = $("#f_no").val();
				$.ajax({
					data:{"f_no":f_no,"fcomm_no":fcomm_no},
					type:"post",
					url:"deleteCmt.brn",
					success:function(data){
							alert('코멘트가 삭제되었습니다.');
							$('.commentList').empty();
							$('.commentList').load("listCmt.brn",'f_no='+f_no);
							$('.fcomm_re_ct').hide();
							$('.fcomm_rep_button').hide();
						
					},
					error:function(data,status){
						alert('코멘트 삭제에 실패하였습니다.');
					}
				})
			}
		})
	})
	
	
	//코멘트 댓글버튼 누를때
	$('.fcomm_rep_button').each(function(index,item){
		$(this).on('click',function(){
			var fcomm_no=$(this).parents('li').find('.fcomm_no').val();
			var f_no =$("#f_no").val();
			var fcomm_re_ct =$(this).parents('li').find('.fcomm_re_ct').val();
			$.ajax({
				data:{"f_no":f_no,"fcomm_no":fcomm_no,"fcomm_ct":fcomm_re_ct},
				type:"post",
				url:"fcommReply_ok.brn",
				success:function(data){
					$('.commentList').load("listCmt.brn",'f_no='+f_no);
				},
				error:function(data,status){
					alert('코멘트 답글 실패');
				}
			})
		})
	})
	
})

function fcomm_write(){
	if($('#fcomm_ct').val().trim() == ''){
		alert('코멘트를 입력하세요');
		return false;
	}
	var f_no=$('#f_no').val();
	var fcomm_ct=$('#fcomm_ct').val();
	$.ajax({
		type:"post",
		data:{"f_no":f_no,"fcomm_ct":fcomm_ct},
		url:"createCmt.brn",
		success:function(data){

				$('.commentList').empty();
				$('.commentList').load("listCmt.brn",'f_no='+f_no);

		},
		error:function(data,status){
			alert('실패'+data+status);
		},
		headers:{"cache-control": "no-cache","pragma": "no-cache"}
	})
	
}
</script>


<!-- 코멘트 부분 -->
<h3>코멘트 남기기</h3>
<c:if test="${!empty bean}">
	<ul>
		<c:forEach var="comm" items="${bean}">
			<li>
			<input type="hidden" class="fcomm_no" name="fcomm_no"
					value="${comm.fcomm_no }" />
				<p>
				<!-- 답글인 경우 -->
				<c:if test="${comm.fcomm_re_lev != 0}"> 
					<c:forEach var="k" begin="1" end="${comm.fcomm_re_lev}">
						&nbsp;&nbsp;	
					</c:forEach>	
				</c:if>			
					<img src="./resources/images/AnswerLine.gif">
					<span>${comm.mem_id } </span>
					<!-- 작성자  -->
					<span> ${comm.fcomm_dt }</span>
					<!-- 작성 날짜 -->
					<span>${comm.fcomm_ct }</span>
					<!-- 코멘트 내용 -->
				</p>
				<p>
					<c:if test="${sessionScope.mem_id == comm.mem_id }">
						<span class="fcdelete">[삭제]</span>
					</c:if>
					<c:if test="${sessionScope.mem_id !=comm.mem_id }">
						<span></span>
					</c:if>
					<span class="fcreply">[답글]</span>
				</p>
				<p>
					<textarea cols="100%" rows="10%" class="fcomm_re_ct"></textarea>
					<button class="fcomm_rep_button" >댓글남기기</button>
				</p>
			</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${empty bean}">
	<span>등록된 코멘트가 없습니다.</span>
</c:if>
	<textarea cols="70" rows="20" id="fcomm_ct"></textarea>
	<button onclick="fcomm_write()" >코멘트남기기</button>
