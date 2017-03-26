<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.board.gboard.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<%@ include file="../../inc/boardHeader.jsp"%>
 
	<script type="text/javascript">
		
		$(function(){
			
			// 코멘트: 대댓을 보이지않게 
			$('.gcomm_re_ct_area').css("display","none");
			
			/* ##### 이미지출력 큰이미지  ##### */
			// 이미지뷰
			var filelists = "${gbbsbean.gbbs_file}";
			// 이미지들을 저장하면서 DB에 저장시켰던 값을 불러온다.
			filelists = filelists.split(';');
			// ;파일명;파일명;파일명;의 형태로 저장되므로, 첫번째 배열을 생략해준다
			for(var i=1; i<filelists.length; i++){
				var currslttxt = "<li><img src=\"" 
								+ "${pageContext.request.contextPath}/resources/upload/" + filelists[i] + "\"" + " alt=\""+ i + "\" />"
								+ "</li>";
				$("#thumMove").html(function(i,rslttxt){
					return  currslttxt + rslttxt;
				});

				// 썸네일
				$(".thumnail > ul").html(function(i,rslttxt){
					return  currslttxt.replace('/gbbs','/smgbbs') + rslttxt;
				});
			}
			

			/* ##### 이미지출력  썸네일쪽 ##### */
			// 이미지사이즈
			$("#thumMove").css("width", $(".thumSet").innerWidth() * filelists.length) ;
			$("#thumMove > li").css("width", $(".thumSet").innerWidth() ) ;
			$("#thumMovethumb").css("width", $(".thumSet").innerWidth() * filelists.length) ;
			
			// 삭제버튼 모달창으로 페이지 load
			$("#cont_del_btn").click(function(){
				$(".modal_bg").removeClass("off");
				$(".modal_bg").animate({"opacity":"1"});
				//삭제페이지를 load
				$(".modal_bg").load("gbbs_cont.brn?num=${gbbsbean.gbbs_num}&page=${page}&state=del");
			}) 
			
			// 갤러리 이미지 섬네일이미지 클릭 시, 해당 이미지로 animate			
			$(".thumnail ul li").click(function(){
				$(this).siblings().removeClass("focus");
				$(this).addClass("focus");
				var i = $(this).index();
				var currpos = Number($("#thumMove").css("left").replace("px",""));	
				var moveleft = - $(".thumSet").innerWidth() * i ; // left가 <<-- 방향으로 움직여야 하기때문에 음수로 방향을 이동한다.
				$("#thumMove").animate({"left":moveleft},100);	
			}); 
			
			// 썸네일 이전 이미지 보기
			$(".prevPageBtnthumb").click(function(){
				// 현재 움직이는 ul의 left쪽 위치를 구해서, 숫자형태로 바꿔줌.
				var currpos = Number($("#thumMovethumb").css("left").replace("px",""));	
				// 섬네일 하나의 사이즈
				var onesize =  Number($(".thumnail").css("width").replace("px",""))+30;	
				// 현재 위치에서 한칸식 이동
				var moveleft = currpos + onesize;
				// left위치가 0이 아니라면! : 0이면 첫번째 이미지가  보여지고 있기 때문에  빈공간이 보여질 수 있음.
				if( currpos != 0 && currpos >= onesize ){
					$("#thumMovethumb").animate({"left":moveleft},100);	
				} else if( currpos < onesize ){
					$("#thumMovethumb").animate({"left":"0"},100);	
				}
			})
			
			// 다음 이미지 보기
			$(".nextPageBtnthumb").click(function(){
				// 현재 움직이는 ul의 left쪽 위치를 구해서, 숫자형태로 바꿔줌.
				var currpos = Number($("#thumMovethumb").css("left").replace("px",""));	
				var ulsize =  Number($(".thumnail").css("width").replace("px",""));
				var onesize =  Number($(".thumnail ul li").css("width").replace("px",""))+30;	
				var moveright = currpos - ulsize;

				var thumsetwidth = $(".thumnail").innerWidth() ;
				var maxidx = $("#thumMovethumb li").length; 
				var maxsize = (Number($("#thumMovethumb li").css("width").replace("px",""))+30)*maxidx; 
				
				// left위치가 이미지갯수*갤러리한칸보다 많으면 이동하지않고 처음페이지로 넘어간다.
				if( !((-1) * currpos > ulsize - onesize) && maxsize > thumsetwidth ){	
					$("#thumMovethumb").animate({"left":moveright},100);
				}
				
			})
			
			// 이전 이미지 보기
			$(".prevPageBtn").click(function(){
				
				// 현재 움직이는 ul의 left쪽 위치를 구해서, 숫자형태로 바꿔줌.
				var currpos = Number($("#thumMove").css("left").replace("px",""));	
				// 현위치 + 이동할위치(리스트 하나의 사이즈)
				var moveleft = currpos + $(".thumSet").innerWidth();
				var i = Math.floor( (-1)* moveleft / $(".thumSet").innerWidth() ) + 1;
				// left위치가 0이 아니라면! : 0이면 첫번째 이미지가  보여지고 있기 때문에  빈공간이 보여질 수 있음.
				if( currpos != 0 ){
					$("#thumMove").animate({"left":moveleft},100);	
				}

				// 해당이미지의 썸네일에 포커스 추가
				$(".thumnail ul li").removeClass("focus");
				$(".thumnail ul li:nth-child("+i+")").addClass("focus");
				
			})
			
			// 다음 이미지 보기
			$(".nextPageBtn").click(function(){
				
				// 현재 움직이는 ul의 left쪽 위치를 구해서, 숫자형태로 바꿔줌.
				var currpos = Number($("#thumMove").css("left").replace("px",""));	
				// 현위치 - 이동할위치(리스트 하나의 사이즈)
				var moveright = currpos - $(".thumSet").innerWidth() ;
				var i = Math.floor( (-1)* moveright / $(".thumSet").innerWidth() ) + 1;
				
				// 전체 이미지 갯수(오른쪽으로 무한대 이동하는 것을 방지)
				var maxidx = $("#thumMove li").length;
				
				// left위치가 이미지갯수*갤러리한칸보다 많으면 이동하지않고 처음페이지로 넘어간다.
				if( (-1)*currpos < $(".thumSet li").innerWidth() * (maxidx-1) ){
					$("#thumMove").animate({"left":moveright},100);	
				} else {
					$("#thumMove").animate({"left":"0"},100);
				}
				
				// 해당이미지의 썸네일에 포커스 추가
				$(".thumnail ul li").removeClass("focus");
				$(".thumnail ul li:nth-child("+i+")").addClass("focus");
				
			})
			
		
		});
		

		$(window).resize(function(e) {
			
			resizeWin();

			// 이미지사이즈
			// 이미지뷰
			var filelists = "${gbbsbean.gbbs_file}";
			// 이미지들을 저장하면서 DB에 저장시켰던 값을 불러온다.
			filelists = filelists.split(';');

			/* ##### 이미지출력  썸네일쪽 ##### */
			// 이미지사이즈
			$("#thumMove").css("width", $(".thumSet").innerWidth() * filelists.length) ;
			$("#thumMove > li").css("width", $(".thumSet").innerWidth() ) ;
			$("#thumMovethumb").css("width", $(".thumSet").innerWidth() * filelists.length) ;
			
		}); 
		 
		
		</script> 

	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp" %>
		
		<!-- 텐츠 영역 START -->
		<div class="subContent"> 
		
			<h3>갤러리</h3>
			<h4>상세보기</h4>
		
			<!-- 전체리스트 -->
			<!-- 게시글 -->
			<div class="Board_view">
			
				<!--LIKE-->	
				 <c:if test="${!empty sessionScope.mem_id }">
			         <div class="likeArea">
			            <ul> 
			            <li>
							<a href="gbbs_like_ok.brn?num=${gbbsbean.gbbs_num}&page=${page}&usernum=${sessionScope.usernum}" class="btn_scrap" title="스크랩">
								<span>스크랩하기</span>
							</a>
						</li>
			            </ul>
			         </div>
         		</c:if>
				<!--//LIKE--> 
				
				<!--제목-->
				<div class="board_tit">
					${gbbsbean.gbbs_subject}
				</div>
				
				<!--정보-->
				<div class="board_info">
					
					<dl class="dateinfo">
						<dt>작성일</dt>
						<dd><fmt:formatDate value="${gbbsbean.gbbs_date}" pattern="yyyy.MM.dd" /></dd>
					</dl>
					<dl class="clickinfo">
						<dt>조회수</dt>
						<dd>${gbbsbean.gbbs_readcount}</dd>
					</dl>
					<dl class="likeinfo">
						<dt>추천수</dt>
						<dd>${gbbsbean.gbbs_like}</dd>
					</dl>
					<dl class="writerinfo">
						<dt>작성자</dt>
						<dd>${gbbsbean.gbbs_userid}</dd>
					</dl>
				</div>
				
				<!-- 이미지박스 -->
				<div class="gallbox">
					<div class="thumSet gall_view">
						<ul id="thumMove" class="thumMove">
							
						</ul>
					</div>
					<input type="button" class="prevPageBtn" value="이전">
					<input type="button" class="nextPageBtn" value="다음">
				</div>
				
				<!-- 썸네일 -->
				<div class="thumnailbox">
					<div class="thumnail">
						<ul id="thumMovethumb" class="thumMove">
							
						</ul>
					</div>
					<input type="button" class="prevPageBtnthumb" value="이전">
					<input type="button" class="nextPageBtnthumb" value="다음">
				</div>
				
				<div class="Post_Area nobor">
					<!--내용영역-->
					<div>
						${gbbsbean.gbbs_content}
					</div>
					<!--//내용영역-->
				</div> 
			</div>
			<!--//게시글-->
			
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl"> 
				<c:if test="${sessionScope.mem_id == gbbsbean.gbbs_userid}">
						<a class="white" id="cont_del_btn" href="javascript:;">삭제</a>
						<a class="white" href="gbbs_cont.brn?num=${gbbsbean.gbbs_num}&page=${page}&state=edit">수정</a>
				</c:if>
				</div>
				<div class="fr">
					<a class="black" href="gbbs_gall.brn?page=${page}">목록</a>
				</div>
			</div>
			<!--//버튼영역-->
			
			<!-- 이전글, 다음글 -->
			<ul class="nextprev_box">
				<li>
					<c:if test="${param.rownum>1}">
					<dl class="next_box">	
						<dt class="prne"><span class="noti_txt01">다음글</span></dt>
						<dd class="subject"><a href="gbbs_cont.brn?num=${gbbsbean_next.gbbs_num}&rownum=${param.rownum-1}&page=${page}&state=cont&listcount=<%=request.getParameter("listcount")%>">${gbbsbean_next.gbbs_subject}</a></dd>
						<dd class="prne_date"><fmt:formatDate value="${gbbsbean_next.gbbs_date}" pattern="yyyy.MM.dd" /></dd>
					</dl>
					</c:if>
					<c:if test="${param.rownum==1}">
						<p class="nolist">다음글이 없습니다.</p>
					</c:if>
				</li>
				<li>
					<c:if test="${param.rownum+1<=param.listcount}">
					<dl class="prev_box">
						<dt class="prne"><span class="noti_txt01">이전글</span></dt>
						<dd class="subject"><a href="gbbs_cont.brn?num=${gbbsbean_prev.gbbs_num}&rownum=${param.rownum+1}&page=${page}&state=cont&listcount=<%=request.getParameter("listcount")%>">${gbbsbean_prev.gbbs_subject}</a></dd>
						<dd class="prne_date"><fmt:formatDate value="${gbbsbean_prev.gbbs_date}" pattern="yyyy.MM.dd" /></dd>
					</dl>
					</c:if>
					<c:if test="${param.rownum==param.listcount}">
						<p class="nolist">이전글이 없습니다.</p>
					</c:if>
				</li>
			</ul>
			<!-- //이전글, 다음글 -->
			
			<input type="hidden" name="gbbs_num" id="gbbs_num" value="${gbbsbean.gbbs_num}" />
			<input type="hidden" name="page" id="page" value="${page}" />
				
			<!--댓글 -->
			<div class="commentList">
				<%@include file="gcomm_list.jsp" %>
			</div>
			<!--//댓글 -->
			
		</div>
		<!--//게시글영역-->
	<!-- 서브컨텐츠 영역 END -->
	</div>
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>		
 
</body>
</html>