<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/subHeader.jsp"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/fat/resources/js/jquery.min.js"></script>
<script src="./resources/js/fboard.js"></script>
<script type="text/javascript" src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<script>
$(function(){

    //전역변수

    var obj = [];               

    //스마트에디터 프레임생성

    nhn.husky.EZCreator.createInIFrame({

        oAppRef: obj,

        elPlaceHolder: "f_ct",

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

    $("#save").click(function(){

        //id가 smarteditor인 textarea에 에디터에서 대입

        obj.getById["f_ct"].exec("UPDATE_CONTENTS_FIELD", []);

        //폼 submit

        $("#frm").submit();

    })

})


</script>


<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp"%>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
			<h3>식품게시판</h3>
			
			<h4>글 수정</h4>
			<form method="post" action="bbs_edit_ok.brn" 
			   enctype="multipart/form-data">
			 <input type="hidden" name="f_no" value="${bbsbean.f_no}" />
 			 <input type="hidden" name="page" value="${page}" />
			
     			 
			<!--게시글-->
			<div class="Board_write"> 

			<input type="hidden" name="mem_id" id="mem_id" size="14" class="input_box" />
				<!--제목-->
				<div class="board_tit">
					<input type="text" placeholder="제목을 입력해주세요." id="f_sj" name="f_sj"/>
				</div>
				<!--정보-->
				<div class="Editor_Area" id="bbs_content"> 
				 <textarea name="f_ct" id="f_ct" rows="8" cols="50"
      				class="input_box"></textarea>
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<h6><strong>첨부파일</strong><input type="file" value="파일추가" name="f_fl"></h6>
					<ul class="addfile">
						
					</ul>
				</div>
				<!--//첨부파일-->
			</div>
			<!--//게시글-->
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl">
					<a href="bbs_list.brn" class="white">취소</a>
					<a href="bbs_list.brn" class="white">목록</a>
				</div>
				<div class="fr">
				
					<a href="#" class="black" id="save" onclick="return check()" >등록</a> 
				</div>
			</div>
			<!--//버튼영역--> 
		</form>
		</div>
		<!--//게시글영역-->
	<!-- 서브컨텐츠 영역 END -->
	
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>
