<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/subHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <script src="./resources/js/jquery.js"></script>
<script src="./resources/js/list.js"></script> -->
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

      <!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->   
         <!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->
   <%@ include file="../../inc/leftMenu05.jsp"%>

      <input type="hidden" name="o_no" id="o_no" value="${obsbean.o_no}" />
      <input type="hidden" name="page" id="page" value="${page }" />      
      <!-- 서브컨텐츠 영역 START -->
      <div class="subContent">
      
         <h3>자유게시판</h3>
         ${sessionScope.mem_id}
         <h4>보기</h4>
      
         <!--게시글-->
         <div class="Board_view">
            <input type="hidden" name="o_no" value="${obsbean.o_no}" />
            <!--LIKE-->
            <div class="likeArea">
               <ul> 
                  <li><a href="#" class="btn_scrap" title="스크랩" ><span>스크랩하기</span></a></li>
               </ul>
            </div>
      
   
               
                  
            <!--제목-->
            <div class="board_tit">
               ${obsbean.o_sj}
            </div>
            <!--정보-->
            <div class="board_info">
               <dl class="dateinfo">
                  <dt>작성일</dt>
                  <dd>${obsbean.o_dt}</dd>
               </dl>
               <dl class="clickinfo">
                  <dt>조회수</dt>
                  <dd>${obsbean.o_rc}</dd>
               </dl>
               <dl class="likeinfo">
                  <dt>추천수</dt>
                  <dd>${obsbean.o_lk}</dd>
               </dl>
               <dl class="writerinfo">
                  <dt>작성자</dt>
                  <dd>${obsbean.mem_id}</dd>
               </dl>
               
               
            </div>
            <div class="Post_Area nobor">
               <!---->
               <div>
                  ${obsbean.o_ct}
               </div>
               <!--//내용영역-->
            </div>
            
            <!--첨부파일-->
            <div class="file_Area">
               <ul class="addfile">
                  <li class="pdf">
                     <c:if test="${obsbean.o_fl !=null}">
                        <a href="./resources/upload${obsbean.o_fl}" target="_blank">${obsbean.o_fl }</a>
                     </c:if>
                     <c:if test="${obsbean.o_fl ==null}">
                        첨부파일이 없습니다
                     </c:if>
                     </li>
               </ul>
            </div>
            
            
            <!--//첨부파일-->
         </div>
         <!--//게시글-->
         <!--버튼영역-->
         <div class="btnB_area">
            <div class="fl">
               <!-- 
                  <a href="/obs/obs_delete" class="white">삭제</a>
                  <a href="boardWrite.jsp" class="white">수정</a>
                -->
              <c:if test="${sessionScope.mem_id eq obsbean.mem_id}">   
                  <input type="button" value="수정" class="input_button"
                     onclick="location='obs_view.brn?num=${obsbean.o_no}&page=${page}&state=mod'" />
                <input type="button" value="삭제" class="input_button"
                     onclick="location='obs_delete.brn?num=${obsbean.o_no}&page=${page}&state=del'" />
               </c:if>            
            </div>
            <div class="fr">
               <a href="obs_list.brn" class="black">목록</a>
            </div>
            
         </div>
<!-- 좋아요폼 -->

         <!--//버튼영역-->
         <!-- 이전글, 다음글 -->
         <ul class="nextprev_box">
            <li>
               <dl class="next_box">
                  <dt class="prne"><span class="noti_txt01">다음글</span></dt>
                  <dd class="subject">
                     <c:if test="${obsbean_p.o_sj == null}">
                        다음 글이 없습니다
                     </c:if>
                     <c:if test="${obsbean_p.o_sj != null}">
                        <a href="obs_view.brn?num=${obsbean.o_no+1}&page=${page}&state=cont">${obsbean_p.o_sj }</a>
                     </c:if>
                  </dd>
                  <dd class="prne_date"></dd>
               </dl>
            </li>
            <li>
               <dl class="prev_box">
                  <dt class="prne"><span class="noti_txt01">이전글</span></dt>
                  <dd class="subject">
                     <c:if test="${obsbean_m.o_sj == null}">
                        이전 글이 없습니다
                     </c:if>
                     <c:if test="${obsbean_m.o_sj != null}">
                        <a href="obs_view.brn?num=${obsbean.o_no-1}&page=${page}&state=cont">${obsbean_m.o_sj }</a>      
                     </c:if>
                  </dd>
                  <dd class="prne_date"></dd>
               </dl>
            </li>
         </ul>
         <!-- //이전글, 다음글 -->
         <div class="commentList">
         <%@include file="ocommList.jsp" %>
      </div>
      </div>
      <!--//게시글영역-->
   <!-- 서브컨텐츠 영역 END -->
</section>
<!-- // container End -->
<script type="text/javaScript">
   $(document).ready(function(){
   
      $(".btn_scrap").click(function(){
         var o_no = $('#o_no').val();
         $.ajax({
            type : "post",
            data : {"o_no" : o_no},
            url : "obs_scrap.brn",
            success : function(data){
               var check = Number(data);
               if(check==1){
                  alert('스크랩 완료!!');
                  
               }else{
                  alert('이미스크랩한 게시물 입니다.');
               }
               
               location.href="obs_view.brn?num=" + o_no + "&state=cont";
            },
            error : function(data, status){
               alert('게시물 스크랩에 실패하셨습니다'+status+data);
            },
             headers : {"cache-control": "no-cache","pragma": "no-cache"}
         });
   });
   });
</script>

<%@ include file="../../inc/footer.jsp"%>