<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../inc/boardHeader.jsp"%>
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<section class="sub_container">

	<%@ include file="/jsp/inc/leftMenu05.jsp"%>
	<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->

	<!-- 서브컨텐츠 영역 START -->
	<div class="subContent">
		<h3>후기게시판</h3>

		<h4>글쓰기</h4>

		<form action="sboardwrite_ok.brn" method="post"
			enctype="multipart/form-data" id="sboardwrite">
			<!--게시글-->
			<div class="Board_write">
				<!--제목-->
				<div class="board_tit">
					<input type="text" name="s_sj" id="s_sj" placeholder="제목을 입력해주세요." />
				</div>
				<!--정보-->
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
				<div class="Editor_area">
					<textarea name="s_ct" id="s_ct" rows="10" cols="766px"
						style="display: none;"></textarea>
				</div>
				<!--첨부파일-->
				<div class="file_Area">
						<h6>	
							<strong>첨부파일</strong>
						</h6>
						<ul id="fileview">
							<li>
								<input name="s_fl[0]" type="file" />
								<input class="btn_add" type="button" value="파일추가" />
							</li>
						</ul>
					</div>
				<!--//첨부파일-->
			</div>
			<!--//게시글-->
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl">
					<a href="#" onclick="javascript:history.go(-1);" class="white">취소</a>
					<a href="sboardList.brn" class="white">목록</a>
				</div>
				<div class="fr">
					<a href="#" class="black" id="formsubmit">등록</a>
				</div>
			</div>
			<!--//버튼영역-->
		</form>
	</div>

	<!--//게시글영역-->
	<!-- 서브컨텐츠 영역 END -->
	</div>
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>
