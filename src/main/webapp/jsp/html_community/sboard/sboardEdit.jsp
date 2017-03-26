<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/boardHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/boardHeader.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
			<h3>후기게시판</h3>
			
			<h4>보기</h4>
		
		<form action="sboardedit_ok.brn" method="post" encType="multipart/form-data">		
			<!--게시글-->
				<div class="Board_write">
				<!--제목-->
				<div class="board_tit">
					<input type="text" id="s_sj" name="s_sj" value="${sbean.s_sj }" />
					<input type="hidden" id="s_no" name="s_no" value="${sbean.s_no }"/>
					<input type="hidden" id="page" name="page" value="${page }"/>
					<input type="hidden" id="limit" name="limit" value="${limit }"/>
				</div>
					<script>
				
				$(function(){	
					var oEditors = [];
					
					// 추가 글꼴 목록
					//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
					
					nhn.husky.EZCreator.createInIFrame({
						oAppRef: oEditors,
						elPlaceHolder: "s_ct",
						sSkinURI: "./resources/editor/SmartEditor2Skin.html",	
						htParams : {
							bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
							//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
							fOnBeforeUnload : function(){
								//alert("완료!");
							}
						}, //boolean
						fOnAppLoad : function(){
							//예제 코드
							//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
						},
						fCreator: "createSEditor2"
					});
					
					$('#formsubmit').on('click',function(){
						oEditors.getById["s_ct"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
						
						if($('#s_sj').val().trim()==''){
							$('#s_sj').focus();
							alert('제목을 입력해주세요');
						}
						if($('#s_ct').val().trim()==''){
							$('#s_ct').focus();
							alert('내용을 입력해주세요');
						}
						if($('#s_sj').val().trim()!='' && $('#s_ct').val().trim()!='')
							$('#sboardwrite').submit();
						
						
					})
 
					
					$('.file_Area').on('click', ".btn_add", function(){ 
					    var fileIndex = $("#fileview li input[type=file]").length;      
					    $("#fileview").append(
					            "<li>"
					            + "<input type=\"file\" name=\"s_fl["+ fileIndex +"]\" />"
					            + "<input class=\"btn_add\" type=\"button\" value=\"파일추가\" />"
					            + "<input class=\"btn_del\" type=\"button\" value=\"파일삭제\" />"
					            + '</li>'
					     );
					});    
					
					$(document).on('click', ".btn_del", function(){ 
						    var fileIndex = $("#fileview li input[type=file]").length;
					    $(this).parent("li").remove();
					});
				})
				</script>
				
				<!--정보-->
				<div class="board_info">
					<dl class="dateinfo">
						<dt>작성일</dt>
						<dd>${sbean.s_dt }</dd>
					</dl>
					<dl class="clickinfo">
						<dt>조회수</dt>
						<dd>${sbean.s_rc }</dd>
					</dl>
					<dl class="likeinfo">
						<dt>추천수</dt>
						<dd>${sbean.s_lk }</dd>
					</dl>
					<dl class="writerinfo">
						<dt>작성자</dt>
						<dd>${sbean.mem_id }</dd>
					</dl>
				</div>
				<div class="Post_Area nobor">
					<!---->
					<div class="Editor_area">
					<textarea name="s_ct" id="s_ct" rows="10" cols="766px"
						style="display: none;">${sbean.s_ct }</textarea>
					</div>
					<!--//내용영역-->
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<h6><strong>첨부파일</strong><input type="file" name="s_fl" id="s_fl" value="파일추가"></h6>
					<ul class="addfile">
						<li class="pdf"><a href="#">${sbean.s_fl }</a><input type="button" class="btn_del"></li>
					</ul>
				</div>
				<!--//첨부파일-->
				<!--버튼영역-->
				<div class="btnB_area">
					<div class="fl">
						<a href="./sboardList.brn?page=${page }" class="white">취소</a>
						<a href="./sboardList.brn?page=${page }" class="white">목록</a>
					</div>
					<div class="fr">
						<input type="submit" class="black"  value="수정"/>
					</div>
				</div>
				<!--//버튼영역--> 
				</div>
			</form>
			<!--//게시글-->
		</div>
		<!--//게시글영역-->
	<!-- 서브컨텐츠 영역 END -->
</section>
<!-- // container End -->

<%@ include file="/jsp/inc/footer.jsp"%>
