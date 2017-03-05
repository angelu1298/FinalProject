$(function(){
	$('#checkall').on('click',function(){
		if($('#checkall').is(':checked')==true){
		$('#agree01').prop('checked',true);
		$('#agree02').prop('checked',true);
		}else{
			$('#agree01').prop('checked',false);
			$('#agree02').prop('checked',false);
			
		}
	})
	$('#agree01').on('click',function(){
		if($('#agree01').is(':checked')==true){
			if($('#agree02').is(':checked')==true){
			$('#checkall').prop('checked',true);
			}
		}else{
			$('#checkall').prop('checked',false);
		}
	})
	$('#agree02').on('click',function(){
		if($('#agree02').is(':checked')==true){
			if($('#agree01').is(':checked')==true){
				$('#checkall').prop('checked',true);
			}
		}else{
			$('#checkall').prop('checked',false);
		}
	})
	$('#agreement').on('submit',function(e){
		if($('#agree01').is(':checked')==false){
			alert('고유 식별번호 수집에 동의해주세요');
			return false;
		}
		if($('#agree02').is(':checked')==false){
			alert('개인정보 수집에 동의해주세요');
			return false;
		}
		return true;
	})
	$('#idcheck').on('click', function(){
		//모달로
	})
	
})