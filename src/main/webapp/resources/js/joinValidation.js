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
/*	$('#idcheck').on('click', function(){
		//모달로
		
	})*/
	$('#joinformThum').submit(function(){
		if($('#wannbe').val()==''){
			$('#thumbnail').val('워너비사진은 필수 입력값입니다.');
			return false;
		}
	})
	
	$('#joinform').on('submit', function(e){
		if($.trim($('#inputid').val())==''){
			$('#inputid').css({'color':'red'});
			$('#inputid').val('아이디를 입력하세요');
			return false;
		}
		if($.trim($('#inputpw').val())==''){
			$('#inputpw').css({'color':'red'});
			$('#inputpw').val('비밀번호를 입력하세요');
			return false;
		}
		if($.trim($('#inputpw').val())!=$.trim($('#checkpw').val())){
			$('#checkpw').css({'color':'red'});
			$('#checkpw').val('비밀번호가 일치하지 않습니다');
			return false;
		}
		if($.trim($('#inputname').val())==''){
			$('#inputname').css({'color':'red'});
			$('#inputname').val('이름을 입력하세요');
			return false;
		}
		if($('#gender_m').is(':checked')==false && $('#gender_f').is(':checked')==false){
			$('#gender_f_label').after('<span class="tip_info2" id="gendercheck"><font color="red" >성별을 선택해주세요.</font></span>')
			return false;
		}
		if($.trim($('#birthyear').val())==''){
			alert('생년을 입력하세요');
			return false;
		}
		if($.trim($('#birthmonth').val())==''){
			alert('월을 입력하세요');
			return false;
		}
		if($.trim($('#birthday').val())==''){
			alert('일을 입력하세요');
			return false;
		}
		if($.trim($('#middleph').val())=='' || $.trim($('#lastph').val())==''){
			$('#middleph').css({'color':'red'});
			$('#middleph').val('필수 입력');
			$('#lastph').css({'color':'red'});
			$('#lastph').val('값 입니다');
			return false;
		}
		if($.trim($('#email').val())==''){
			$('#email').css({'color':'red'});
			$('#email').val('이메일을 입력하세요');
			return false;
		}
		if($.trim($('#domain').val())==''){
			$('#domain').css({'color':'red'});
			$('#domain').val('도메인을 입력하세요');
			return false;
		}
		if($.trim($('#height').val())==''){
			$('#height').css({'color':'red'});
			$('#height').val('키를 입력하세요');
			return false;
		}
		if($.trim($('#weight').val())==''){
			$('#weight').css({'color':'red'});
			$('#weight').val('몸무게를 입력하세요');
			return false;
		}
		
		
	})
	$('#sel').change(function(){
		if($('#sel').val()==""){
			$('#domain').attr('disabled',true);
			$('#domain').val('');
			$('#domain').focus();
		}else{
			$('#domain').val($('#sel').val());
			$('#domain').prop('readonly',true);
		}
	})
	$('#checkpw').on('blur',function(){
		if(($.trim($('#inputpw').val()))!=($.trim($('#checkpw').val()))){
			$('#checkpw').css({'color':'red'});
			$('#checkpw').val('비밀번호가 일치하지 않습니다.');
		}
	})
	$('#middleph').on('blur',function(){
		if(!$.isNumeric($('#middleph').val())){
			$('#middleph').css({'color':'red'});
			$('#middleph').val('숫자로만 ');
			$('#lastph').css({'color':'red'});
			$('#lastph').val(' 입력하세요');
		}
		if($('#middleph').val().length>=5){
			$('#middleph').val($('#middleph').val().substr(0,4));
		}
	})
	$('#lastph').on('blur',function(){
		if(!$.isNumeric($('#lastph').val())){
			$('#middleph').css({'color':'red'});
			$('#middleph').val('숫자로만 ');
			$('#lastph').css({'color':'red'});
			$('#lastph').val(' 입력하세요');
		}
		if($('#lastph').val().length>=5){
			$('#lastph').val($('#lastph').val().substr(0,4));
		}
	})
	
	$('.w200').focus(function(){
		$(this).css({'color':'black'});
		$(this).val('');
	})
	$('.w100').focus(function(){
		$(this).css({'color':'black'});
		$(this).val('');
	})
	$('.w400').focus(function(){
		$(this).css({'color':'black'});
		$(this).val('');
	})
	$('.gender').focus(function(){
		$('#gendercheck').remove();
	})
	
	$('#wannabe').change(function(){
		$('#joinformThum').submit();
	})
	$('#finishjoin').on('click',function(){
		$('#joinformThum').attr('action','./JoinComplete.brn');
		$('#joinformThum').submit();
	})
})
  function zipcode_find() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $('#zipcode').val( data.zonecode); //5자리 새우편번호 사용
               $('#address').val(fullAddr);

                // 커서를 상세주소 필드로 이동한다.
               $('#detailaddr').focus();
            }
        }).open();
    }
