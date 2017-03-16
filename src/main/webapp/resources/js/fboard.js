
  function check(){
	  if($.trim($("#f_sj").val())==""){
		  alert("글제목을 입력하세요!");
		  $("#f_sj").val("").focus();
		  return false;
	  }
	  if($.trim($("#f_ct").val())==""){
		  alert("글내용을 입력하세요!");
		  $("#f_ct").val("").focus();
		  return false;
	  }
	  $('form').submit();
  }
  
  function delete_check(){
		if (confirm("정말 삭제하시겠습니까??") == true){
			var f_no = $("#f_no").val();
			var page = $("#page").val();
			$.ajax({
				data:{"f_no" : f_no,
						"page" : page },
				type : "post",
				url : "bbs_delete_ok.brn",
				success : function(data){
						alert('게시물이 삭제되었습니다');
				}
			})
		}
	}
  
  
  function scrap_ok(){
		var f_no = $('#f_no').val();
		$.ajax({
			type : "post",
			data : {"f_no" : f_no},
			url : "fboardscrap.brn",
			success : function(data){
				var check = Number(data);
					alert('스크랩 완료!!');
				
			},
			error : function(data, status){
				alert('게시물 스크랩에 실패하셨습니다'+status+data);
			},
			 headers : {"cache-control": "no-cache","pragma": "no-cache"}
		})
	}

  
  function fcomm_write(){

	 
		if( $('#fcomm_ct').val().trim()==''){
			alert('코멘트를 입력하세요');
			return false;
		}
		var f_no = $('#f_no').val();
		var fcomm_ct = $('#fcomm_ct').val();
		$.ajax({
			type: "post",
			data : {"f_no": f_no,
					"fcomm_ct": fcomm_ct},
			url: "writeReplyAjax.brn",
			success : function(data){
				if(data==1){
				$('.commentList').empty();
				$('.commentList').load("listReplyAjax.brn",'f_no='+f_no);
				}else{
					alert('실패!!'+data);
				}
			},
			error: function(data, status){
				alert('실패 ㅠㅠ '+data + status);
			}
		})
	}
  
 
  
