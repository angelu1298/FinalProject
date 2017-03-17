<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../inc/subHeader.jsp"%>
<!-- 임시 아이디 세션 생성 -->
<%-- <% session.setAttribute("id","testId"); %> --%>
<script src="./resources/js/jquery.min.js"></script>
<script src="./resources/js/list_eboard.js"></script>
<script>
   $("#viewcount").val("${limit}").prop("selected", true);
</script>
    
   <!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
   <section class="sub_container">

      <!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->   
      <%@ include file="../../inc/leftMenu05.jsp" %>
      
      <!-- 서브컨텐츠 영역 START -->
      <div class="subContent">
         <h3>운동게시판</h3>
         
         <h4>목록</h4>
      
         <!-- form -->
         <form action="eboardView.jsp"  method="post">
         <p class="srch_result">
            <span class="rs_txt"><strong>${listcount }</strong>건이 검색되었습니다.</span>
         </p>
         <!--테이블-->
         <div class="common_list_box">
            <table cellpadding="0" cellspacing="0" border="1" summary="운동게시판입니다. 제목,글쓴이,등록일,조회수,추천수를 확인할 수 있고, 등록일 기준으로 sorting 됩니다.">
               <caption>운동게시판</caption>
               <colgroup>
                  <col width="50px">
                  <col width="100%"> 
                  <col width="100px">
                  <col width="100px">
                  <col width="50px">
                  <col width="50px">
               </colgroup>
               <thead>
                  <tr>
                     <th>No</th>
                     <th>제목</th>
                     <th>글쓴이</th>
                     <th>작성일</th>
                     <th>조회</th> 
                     <th>추천</th> 
                  </tr>
                  </thead>
            <tbody>
               <!-- 반복문 시작 -->
                  <!--등록된 게시물이 없는경우-->
                  <c:if test="${listcount == 0}">
                  <tr>
                        <td class="nolist" colspan="6">등록된 게시물이 없습니다.</td>
                  </tr>
                  </c:if>
                  <!--등록된 게시물이 있는 경우-->
                  <c:if test="${listcount != 0}">
                  <c:forEach var="e" items="${ebolist}">
                     <tr>
                        <td><!-- No -->
                           ${e.e_no}
                        </td>
                        <td class="tit"><!-- 제목 출력 부분 -->   
                           <a href="eboardView.brn?num=${e.e_no}&page=${page}&state=cont">
                              ${e.e_sj}
                              <c:if test="${!empty e.e_fl }">
                              	<span class="fileExist">파일 있음</span>
                              </c:if>
                           </a>
                        </td>
                        <td><!-- 글쓴이 -->
                           <div>${e.mem_id}</div>
                        </td>
                        <td><!-- 작성일 -->
                           <div>${e.e_dt}</div>
                        </td>
                        <td><!-- 조회 -->
                           <div>${e.e_rc}</div>
                        </td>
                        <td><!-- 추천 -->
                           <div>${e.e_lk}</div>
                        </td>
                     </tr>
                     </c:forEach>
                     </c:if>
            </tbody>
            </table>
            </div>
            <!--테이블-->
            
            <!--페이징 -->
            <div class="paginate">
            <p>
               <a href="ebo_list.brn?page=1" class="pre" title="맨앞">&lt;&lt;</a>
               <c:if test="${page <=1}">
                  <a href="#none" class="pre" title="이전페이지">&lt;</a>
               </c:if>
               <c:if test="${page > 1}">
                  <a href="ebo_list.brn?page=${page-1}&limit=${limit}" class="pre" title="이전페이지">&lt;</a>
               </c:if>
               <!-- 페이지가 1,2,3 이 있음 현재페이지가 2 라고 치면 
                  1은 2랑 다르니까 1에는 링크가 걸리고 2는 2랑 같으니까 링크가 안걸려야함 현재페이지니까 -->
               <c:forEach var="a" begin="${startpage}" end="${endpage}">
                  <c:if test="${a == page }">
                     <strong>${a}</strong>
                  </c:if>
                  <c:if test="${a != page }">
                     <a href="ebo_list.brn?page=${a}&limit=${limit}" >${a}</a>
                  </c:if>
               </c:forEach>
               <!--  -->
               <a href="ebo_list.brn?page=${maxpage}" class="pre" title="맨뒤">&gt;&gt;</a>
               <c:if test="${page >= maxpage }">
                  <a href="#none" class="next" title="다음페이지">&gt;</a>
               </c:if>
               <c:if test="${page < maxpage }">
                  <a href="ebo_list.brn?page=${page+1}&limit=${limit}" class="next" title="다음페이지">&gt;</a>
               </c:if>   
            </p>
               
            </div>
            <!--//페이징 -->   
             <div class="btnB_area">
               <div class="fr">
                  <a href="./eboardWrite.brn" class="black">글쓰기</a>
               </div>
            </div>
            
         </form>      
         <!-- //form -->
            
            <!--검색영역-->
            <!--한줄-->
             <form method="get" action="ebo_find_ok.brn"
                onsubmit="return find_check()">
            <div class="borad_srch" id="bbsfind">
               <span class="fl ml20">
                     <select id="viewcount fl" name="limit">
                        <option value="20">20개보기</option>
                        <option value="50">50개보기</option>
                        <option value="100">100개보기</option>
                     </select> 
               </span>
            <p class="col">
               <label for="srch_sel01" class="sc_txt">검색영역</label>
               <select class="w180 mr10" id="srch_sel01" name="find_field">
                  <option value="ebo_tot">전체</option>
                  <option value="ebo_id">작성자</option>
                      <option value="ebo_subject">글제목</option>
                      <option value="ebo_content">글내용</option>
               </select>
               <label for="srch_txt" class="dnone"></label><input type="text" name="find_name" id="srch_txt" class="w280 mr10" value=""/>
               <input type="submit" class="input_button" value="검색" />
            </p>
            <!--한줄-->
         </div>
         <!-- //form -->
            </form>
         <!--//검색영역-->
         
      </div>
      <!-- 서브컨텐츠 영역 END -->
   </section>
   <!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>