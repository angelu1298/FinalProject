<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#re_form{
	padding:5px 10px 40px 10px;
	margin-top:10px;
	background-color:#eee;
	border:none;
}
span#re-title{
	color:#000;
	font-size:12pt;
	line-height:200%;
}
</style>
 
<script>
//코멘트 작성 부분
function doCreateCmt() {
      // 한줄댓글 내용이 필수이므로 검사
      if($('#fcomm_ct').val() == '') {
            alert("한줄 댓글의 내용은 필수 입력입니다.");
            $('#fcomm_ct').focus(); return;
      }
     var f_no = $('#f_no').val();
  	 var fcomm_ct = $('#fcomm_ct').val();

  	 alert("f_no"+f_no);
  	alert("fcomm_ct"+fcomm_ct);
  	
      $.ajax({
  		type: "post",
  		data : {"f_no": f_no,
  				"fcomm_ct": fcomm_ct},
  		url: "createCmt.brn",
  		success : function(data){
  			
  			 $('.commentList').load("listCmt.brn","f_no="+f_no); 
  			/* $('#cmtTarget').load("./listCmt.nhn","f_no="+f_no); */
  		
  		},
  		error: function(data, status){
  			alert('실패'+data + status);
  		}
  	})//ajax
      
}//fuction

//코멘트 삭제 부분
function doDeleteCmt() {
	var fcomm_no= $('#fcomm_no').val();
	var f_no = $('#f_no').val();
	
	
	alert("f_no"+f_no);
	alert("fcomm_no"+fcomm_no);
	
	$.ajax({
		data : {
			"f_no" : f_no,
			"fcomm_no" : fcomm_no 
		},
		type : "post", 
		url : "deleteCmt.brn",
		success : function(data) {

				alert('삭제 성공');
				
				$('.commentList').val(''); // 내용 비우기
				$('.commentList').load("listCmt.brn", "f_no=" + f_no);
				
				$('#del').hide();
				$('#mod').hide();

		},
		error : function(data, status) {
			alert('삭제 실패'+data+status);
		}
	})//ajax

}//function


//리스트 
$(function() {
//코멘트 대댓글 숨기기
	$('.fcomm_re_ct').hide();
	$('.fcomm_rep_button').hide();
	
})

//코멘트 댓글 부분
function replycmt() {
//대댓글 버튼 보이기
	$('.fcomm_re_ct').show();
	$('.fcomm_rep_button').show();
	
//코멘트 대댓글 버튼 누를때
$('.fcomm_rep_button').each(function(index, item) {
	$(this).on('click', function() {
		var fcomm_no = $('#fcomm_no').val();
		var f_no = $("#f_no").val();
		var fcomm_re_ct = $('#fcomm_re_ct').val();
		
		alert('fcomm_no'+fcomm_no);
		alert('f_no'+f_no);
		alert('fcomm_re_ct'+fcomm_re_ct);
		
		$.ajax({
			data : {
				"f_no" : f_no,
				"fcomm_no" : fcomm_no,
				"fcomm_ct" : fcomm_re_ct
			},
			type : "post",
			url : "fcommReply_ok.brn",
			success : function(data) {
				$('.commentList').load("listCmt.brn", "f_no=" + f_no);
				
				$('.fcomm_re_ct').hide();
				$('.fcomm_rep_button').hide();
			},
			error : function(data, status) {
				alert('코멘트 답글 실패.'+data+status);
			}
		})
	})
})

	
	
}
</script> 
 

		<!-- 입력 부분 -->
          
           <div id="re_form">
				<input type="hidden" class="f_no" name="f_no" id="f_no" value="${f_no}" /> 
            	<span id="re-title"> ID : ${sessionScope.mem_id}</span>
            	<span id="rep-content"> 댓글 : <textarea id="fcomm_ct" name="fcomm_ct" style="width: 50%"></textarea>
            	<input type="button" value="입력" id="createbt" onclick="doCreateCmt()"/>
            	</span>
            </div>

 
 		<!-- 리스트 부분 -->
 
 <c:if test="${!empty bean}"> 
      <div id="cmtTarget">
		 
		<ul>
		
		<c:forEach var="item" items="${bean}">
			<li>
				<p>
			<c:if test="${item.fcomm_re_lev != 0}">
				<c:forEach var="a" begin="1" end="${item.fcomm_re_lev }">
				&nbsp;&nbsp;▶
				</c:forEach>
			</c:if>
		      		<%--하나의 코멘트를 for문을 돌리기! --%>
		      		${item.fcomm_no}
		            ${item.mem_id} : ${item.fcomm_ct} 
		            [${item.fcomm_dt}]
		            <%--해당 코멘트 번호 --%>
		            <input type="hidden" value="${item.fcomm_no}" name="fcomm_no" id="fcomm_no"/>
		            
		            <c:if test="${sessionScope.mem_id == item.mem_id }">
			            <input type="button" id="del" value="삭제" onclick="doDeleteCmt()" />
		            </c:if>
		            
					<c:if test="${sessionScope.mem_id != item.mem_id }">
					<input type="button" id="reply" value="답글" onclick="replycmt()" />
					</c:if>
				</p>
				<p>
					<%--해당 코멘트의 댓글 --%>
			</li>
				<li>
					<textarea style="width: 50%" class="fcomm_re_ct" id="fcomm_re_ct" name="fcomm_re_ct"></textarea>
					<input type="button" value="입력" class="fcomm_rep_button" />
				</li>
				</p>
				
		</c:forEach>
		</ul>		
      </div>
      
      
<!-- 리스트 부분 -->

</c:if>
<c:if test="${empty bean}">
등록된 코멘트가 없습니다.
</c:if>
