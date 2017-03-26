<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/subHeader.jsp"%>
<script type="text/javascript" src="./resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<script>
$(function(){

    //전역변수

    var obj = [];               

    //스마트에디터 프레임생성

    nhn.husky.EZCreator.createInIFrame({

        oAppRef: obj,

        elPlaceHolder: "o_ct",

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

        obj.getById["o_ct"].exec("UPDATE_CONTENTS_FIELD", []);

        //폼 submit

        $("#frm").submit();

    });
    
    $(".btn_del").click(function() {
    	$( ".file_name" ).remove();
    });

})


</script>
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">


		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
	<form action="obs_write_ok.brn" id="frm" method="post" enctype="multipart/form-data"> 
			<h3>자유게시판</h3>
			
			<h4>글쓰기</h4>
			<!--게시글-->
			<div class="Board_write"> 
				<!--제목-->
				<div class="board_tit">
					<input type="text" name="o_sj" placeholder="제목을 입력해주세요."/>
				</div>
				<!--정보-->
				<div class="Editor_Area"> 
					<textarea name="o_ct" id="o_ct" rows="10" cols="766px"  class="input_box" ></textarea>
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<h6><strong>첨부파일</strong></h6>

					<ul class="addfile">
						<li class="pdf">
							<a href="#" class="file_name">${obsbean.o_fl }</a>
							<input type="file" name="o_fl" id="o_fl" class="o_fl" value="파일추가">
							<input type="button" class="btn_del">
						</li>
					</ul>
					
					
				</div>
				<!--//첨부파일-->
			</div>
			<!--//게시글-->
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl">
					<a href="obs_list.brn" class="white">취소</a>
					<a href="obs_list.brn" class="white">목록</a>
				</div>
				<div class="fr">
					<input type="submit" value="등록" class="black" id="save"/>
					<!-- <a href="obs_write_ok.brn" class="black">등록</a>  -->			<!--  수정 	-->
				</div>
			</div>
			<!--//버튼영역--> 
	</form>
		</div>
		<!--//게시글영역-->
	<!-- 서브컨텐츠 영역 END -->
	<!-- </div> -->
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>