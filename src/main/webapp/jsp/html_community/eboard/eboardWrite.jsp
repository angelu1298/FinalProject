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

        $("#eboardwrite").submit();

    })

})


</script>
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">
		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		<form method="post" action="ebo_write_ok.brn" enctype="multipart/form-data" id="eboardwrite">
			<h3>운동게시판</h3>
			
			<h4>글쓰기</h4>
			
			<!--게시글-->
 			<div class="Board_write">  
				<!--제목-->
				<div class="board_tit">
					<input type="text" name="e_sj" id="s_sj" placeholder="제목을 입력해주세요."/>
				</div>
				<!--정보-->
<!-- 				<div class="Editor_Area">  -->
				 <script>
				
				$(function(){	
					
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
					
				});
				</script> 
				 <div class="Editor_area">
				 <!-- style="display:none;" -->
				 <!-- 정보 --> 
					<textarea name="e_ct" id="e_ct" rows="10" cols="766px"  class="input_box"></textarea>
				</div> 
<!-- 					<textarea name="e_ct"></textarea> -->

				<!--첨부파일-->
				 <div class="file_Area">
					<h6><strong>첨부파일</strong></h6>
<!-- 					<input type="file" name="ebo_file" value="파일추가"></h6> -->
					<script>
						$(function(){
							$('.file_Area').on('click','.btn_del', function(){
								if($('#s_fl').length==1){
									$('.addfile > li:first-child').after('<li class="pdf">' +
									 '<input type="file" name="e_fl2" id="s_fl2" class="s_fl" value="파일추가">'+
									 '<input type="button" class="btn_del"></li>');
								}else if($('#s_fl2').val()!=''){
									$('.pdf:nth-child(2)').after('<li class="pdf">' +
											 '<input type="file" name="e_fl3" id="s_fl3" class="s_fl" value="파일추가">'+
											 '<input type="button" class="btn_del"></li>');
								}else if($('#s_fl2').val()!=''){
									alert('더이상 파일을 업로드 할 수 없습니다');
								}
							$('.btn_del').each(function(index,item){
								$(this).on('click',function(){
									if($(this).parent('li')!=$('.addfile > li:first-child'))
										$(this).parent('li').remove();
								})
							})
							})
						})
					</script>
					<ul class="addfile">
						<li class="pdf"><input type="file" name="e_fl" id="s_fl" class="s_fl" value="파일추가"><input type="button" class="btn_del"></li>
					</ul>
				</div> 
				<!--//첨부파일-->
			</div>
			<!--//게시글-->
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl">
					<a href="#" onclick="javascript:history.go(-1);" class="white">취소</a>
					<a href="ebo_List.brn" class="white">목록</a>
				</div>
				<div class="fr">
					<!-- <a href="boardList.jsp" class="black">등록</a> -->
					 <input type="submit" value="등록" class="black" id="formsubmit"/>
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