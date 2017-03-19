<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../inc/boardHeader.jsp"%>
<script>
$(document).ready(function(){
   $('.ebo_scrap').click(function(){
      var e_no = $('#e_no').val();
      $.ajax({
         type : "post",
         data : {"e_no" : e_no},
         url : "eboardscrap.brn",
         success : function(data){
            var check = Number(data);
            if(check==1){
               alert('스크랩 완료!!');
            }else{
               alert('이미스크랩한 게시물 입니다.');
            }
            location.href="eboardView.brn?num=" + e_no + "&state=cont";
            
         },
         error : function(data, status){
            alert('게시물 스크랩에 실패하셨습니다'+status+data);
         },
         headers : {"cache-control": "no-cache","pragma": "no-cache"}
      });
   });
   
});
</script>
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

   <!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->   
   <%@ include file="../../inc/leftMenu05.jsp" %>
      
   <!-- 서브컨텐츠 영역 START -->
   <div class="subContent">
      
      <h3>운동게시판</h3>
         
      <h4>상세보기</h4>
      
      <!--게시글-->
      <div class="Board_view">
         <input type="hidden" name="e_no" id="e_no" value="${ebobean.e_no }" />
         <!--LIKE-->
         <div class="likeArea">
            <ul> 
               <li><input type="button" value="스크랩" class="ebo_scrap btn_scrap"/><span>스크랩하기</span></li>
            </ul>
         </div>
         <!--//LIKE-->
         
         <!--제목-->
         <div class="board_tit">
            ${ebobean.e_sj}
         </div>
         <!--정보-->
         <div class="board_info">
            <dl class="dateinfo">
               <dt>작성일</dt>
               <dd>${ebobean.e_dt}</dd>
            </dl>
            <dl class="clickinfo">
               <dt>조회수</dt>
               <dd>${ebobean.e_rc}</dd>
            </dl>
            <dl class="likeinfo">
               <dt>추천수</dt>
               <dd>${ebobean.e_lk}</dd>
            </dl>
            <dl class="writerinfo">
               <dt>작성자</dt>
               <dd>${ebobean.mem_id}</dd>
            </dl>
         </div>
         <div class="Post_Area nobor">
            <div>
               ${e_ct}
            </div>
            <!--//내용영역-->
         </div>
            <!--첨부파일-->
            <div class="file_Area">
               <ul class="addfile">
                  <li class="pdf"><a href="./resources/upload${ebobean.e_fl}" target="_blank">
                  ${ebobean.e_fl}</a></li>
               </ul>
            </div>
            <!--//첨부파일-->
         </div>
         <!--//게시글-->
         <!--버튼영역-->
      <div class="btnB_area">
         <div class="fl">
         <c:if test="${sessionScope.mem_id == mem_id}">
             <input type="button" value="삭제" class="white"
               onclick="location='ebo_delete_ok.brn?num=${ebobean.e_no}&page=${page}'" />
             <input type="button" value="수정" class="white"
               onclick="location='eboardView.brn?num=${ebobean.e_no}&page=${page}&state=edit'" />
         </c:if>
         </div>
         <div class="fr">
             <input type="button" value="목록" class="black"
               onclick="location='ebo_list.brn?page=${page}'" />
         </div>
      </div>
      <!-- //버튼영역 -->
         <!-- 이전글, 다음글 -->
         <ul class="nextprev_box">
            <li>
               <dl class="next_box">
                  <dt class="prne"><span class="noti_txt01">다음글</span></dt>
                  <dd class="subject"><a href="eboardView.brn?num=${ebobean.e_no+1}&page=${page}&state=cont">${beannext.e_sj }</a></dd>
                  <dd class="prne_date">${beannext.e_dt}</dd>
               </dl>
            </li>
            <li>
               <dl class="prev_box">
                  <dt class="prne"><span class="noti_txt01">이전글</span></dt>
                  <dd class="subject"><a href="eboardView.brn?num=${ebobean.e_no-1}&page=${page}&state=cont">${beanpre.e_sj }</a></dd>
                  <dd class="prne_date">${beanpre.e_dt}</dd>
               </dl>
            </li>
         </ul>
         <!-- //이전글, 다음글 -->
      <div class="commentList">
         <%@ include file="eboardComm.jsp" %>
      </div>
      <!--//게시글영역-->
   </div>
   <!-- 서브컨텐츠 영역 END -->
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>