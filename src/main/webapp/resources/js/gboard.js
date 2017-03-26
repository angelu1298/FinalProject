function find_check(){
	
	if($.trim($(".find_field").val())==''){
		var msg = "검색할 항목을 선택해주세요";
		messageFunc(msg);
		return false;
	}
	if($.trim($('.find_name').val())==''){
		var msg = "검색할 값을 입력해주세요";
		messageFunc(msg);
		return false;
	}
}

function messageFunc(msg){
	
	$(".modal_bg").load("alert_page.brn");
	$(".layer_content .message").html( "<p>" + msg + "</p>");
	
	$(".modal_bg").removeClass("off");
	$(".modal_bg").animate({"opacity":"1"});
	
}

function previewFile(obj) {
	
	var node = obj
	var preview = node.nextSibling;
	var file    = obj.files[0];
	var reader  = new FileReader();
	
	reader.onloadend = function () {
	    preview.src = reader.result;
	}

  if (file) {
    reader.readAsDataURL(file);
  } else {
    preview.src = "";
  }  

}

$(function(){
	 
	// 빈칸 체크
	$('#gboardwritebtn').on('click',function(e){

		var fileIndex = $("#fileview li").length;
		var flag = false;
		
		if(fileIndex==0){
			var msg = "이미지가 최소 1개 이상 첨부되어야 합니다.";
			messageFunc(msg);
		}
		$("#fileview li input").each(function(idx,item){
			var a = $(this).val(); 
			if(a==""){
				var msg = "이미지가 최소 1개 이상 첨부되어야 합니다.";
				messageFunc(msg);
			} else {
				flag = true;
			}
		})
		 
		if(flag==true){ 
			
			if($('#gbbs_subject').val().trim()==''){ 
				var msg = "제목을 입력해주세요.";
				messageFunc(msg);
			} else if($('#gbbs_content').val().trim()==''){
				var msg = "내용을 입력해주세요.";
				messageFunc(msg);
			} else if ($('#gbbs_subject').val().trim()!='' && $('#gbbs_content').val().trim()!=''){
				// 조건충족 -> 
				$('#gboardwrite').submit();
			} 
			
		} else {
			// 이미지가 등록이 안되었으면?
			e.preventDefault();
		}
	})
 
	$(".btn_add").click(function(){

		var filenum = $("#fileview li:last-child > input[type=file]").length;
		var filechck = $("#fileview li:last-child > input[type=file]").val();
		if(filechck!='' || filenum==0 ){
			$("#fileview p").css({"display":"none"});
		    var fileIndex = $("#fileview li input[type=file]").length;      
		    $("#fileview").append(
		            "<li>"
		            + "<input type=\"file\" name=\"gbbs_file["+ fileIndex +"]\" class=\"getfile\" accept=\"image/*\"  onchange=\"previewFile(this)\"/>"
		            + "<img class=\"preview\" />"
		            + "<input class=\"btn_del\" type=\"button\" value=\"파일삭제\" />"
		            + '</li>');
		} else {
			var msg = "비어있는 리스트부터 추가해주세요";
			messageFunc(msg);
		}
	});    

	// 현재 항목 삭제
	$(document).on('click', ".btn_del", function(){  
	    $(this).parent("li").remove();
	});  
	
})


