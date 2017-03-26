<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/subHeader.jsp"%>
<script src="./resources/js/jquery.min.js"></script>
<script type="text/javascript" src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<script>
$(function(){

    //전역변수

    var obj = [];               

    //스마트에디터 프레임생성

    nhn.husky.EZCreator.createInIFrame({

        oAppRef: obj,

        elPlaceHolder: "e_ct",

        sSkinURI: "./resources/editor/SmartEditor2Skin.html", 
        
        htParams : {

            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)

            bUseToolbar : true,             

            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)

            bUseVerticalResizer : true,     

            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)

            bUseModeChanger : true, 

        }

    });

    //전송버튼

    $("#formsubmit").click(function(){

        //id가 smarteditor인 textarea에 에디터에서 대입

        obj.getById["e_ct"].exec("UPDATE_CONTENTS_FIELD", []);

        //폼 submit

        $("#eboardedit").submit();

    })

})


</script>
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		<form method="post" action="ebo_edit_ok.brn" enctype="multipart/form-data" id="eboardedit">
			<input type="hidden" name="num" value="${ebobean.e_no}" />
 			<input type="hidden" name="page" value="${page}" />
			
			<h3>운동게시판</h3>
			
			<!--게시글-->
			<div class="Board_write"> 
				<!--제목-->
				<div class="board_tit">
					<input type="text" name="e_sj" value="${ebobean.e_sj}" />
					
				</div>
				
				<!--정보-->
				<div class="Editor_Area"> 
					<textarea name="e_ct" id="e_ct" rows="10" cols="766px"  class="input_box">${ebobean.e_ct}</textarea>
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<h6><strong>첨부파일</strong>
					<input type="file" name="e_fl" id="s_fl" value="파일추가"></h6>
					<ul class="addfile">
						<li class="pdf"><a href="#">${ebobean.e_fl }</a><input type="button" class="btn_del"></li>
					</ul>
				</div>
				<!--//첨부파일-->
			</div>
			<!--//게시글-->
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl">
					<input type="reset" class="white"/>
					<a href="ebo_list.brn" class="white">목록</a>
				</div>
				<div class="fr">
					<!-- <a href="boardList.jsp" class="black">등록</a> -->
					 <input type="submit" value="수정" class="black" id="formsubmit" />
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