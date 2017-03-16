
function find_check(){
	if($('#srch_txt').val().trim()==''){
		alert('검색할 값을 입력해주세요');
	}
}

function delete_check(){
	if (confirm("정말 삭제하시겠습니까??") == true){
		var s_no = $("#s_no").val();
		var page = $("#page").val();
		$.ajax({
			data:{"s_no" : s_no,
					"page" : page },
			type : "post",
			url : "sboarddelete_ok.brn",
			success : function(data){
				if(data==1)
					alert('게시물이 삭제되었습니다');
				else
					alert('게시물 삭제에 실패했습니다'+data);
			},
			error : function(data,status){
				alert('게시물 삭제에 실패했습니다.');
			}
		})
	}else{   //취소
		
	}
}
function s_scrap_ok(){
	var s_no = $('#s_no').val();
	$.ajax({
		type : "post",
		data : {"s_no" : s_no},
		url : "sboardscrap.brn",
		success : function(data){
			var check = Number(data);
			if(check==1){
				alert('스크랩 완료!!');
			}else{
				alert('이미스크랩한 게시물 입니다.');
			}
		},
		error : function(data, status){
			alert('게시물 스크랩에 실패하셨습니다'+status+data);
		}
	})
}


function scomm_write(){

	if( $('#scomm_ct').val().trim()==''){
		alert('코멘트를 입력하세요');
		return false;
	}
	var s_no = $('#s_no').val();
	var scomm_ct = $('#scomm_ct').val();
	$.ajax({
		type: "post",
		data : {"s_no": s_no,
				"scomm_ct": scomm_ct},
		url: "scommWrite_ok.brn",
		success : function(data){
			if(data==1){
			$('.commentList').empty();
			$('.commentList').load("./scommList.brn",'s_no='+s_no);
			}else{
				alert('실패!!'+data);
			}
		},
		error: function(data, status){
			alert('실패 ㅠㅠ '+data + status);
		},
		 headers : {"cache-control": "no-cache","pragma": "no-cache"}
	})
}