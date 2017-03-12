
	 /*서브페이지 카테고리*/
	$(function(){ 

		/*시도단위*/
		var sido = Array ( "서울특별시", "인천광역시", "대전광역시", "광주광역시", "대구광역시", "울산광역시", "부산광역시",
				      "경기도",  "강원도",  "충청북도",  "충청남도", "전라북도",  "전라남도",  "경상북도",  "경상남도",  "제주도");
		
		/*시도를 셀레이어에 for문으로 출력*/
		for(var i = 0; i<sido.length;i++){
			$(".selbox > p ").next(".sido").append("<input type=\"radio\" id=\"sido_0" + (i+1) 
					+ "\" name=\"sido\" value=\"" + sido[i] + "\" />"
					+ "<label for=\"sido_0" + (i+1) + "\">" + sido[i] + "</label>");
		}
		 
		$(".selbox > p ").click(function() {
			/* 다른 레이어를 숨겨줌 display none*/
			$(".sellayer").css("display", "none");
	
			/* 자신의 레이어면 display block!*/
			$(this).siblings(".sellayer").css("display", "block");
			/* 셀선택값을 마우스가 떠나면 display none*/
			$(".sellayer").mouseleave(function(e) {
				$(this).css("display", "none");
			});
			
			/* 시도가 선택되지 않을 경우, 군구를 선택할 수 없음 */
			if($(this).attr("id") == "gungu_txt" && $("#sido_txt").val() == "" ){ 
			 	return false;
			}
			
		});
		// 시,도 선택시
		
		/*change|시작*/
		
		$("input:radio[name='sido']").change(function() {
		 
			$(this).parent().css("display", "none");
			var sido = $("input:radio[name='sido']:checked");
			var sidoval = sido.val();
			$("#sido_txt").attr("value", sidoval);
			$("#gungu_txt").attr("value","");
			var id = sido.attr("id");
			var num = id.substr(6,7);
			
			$(".mapArea > .fr > div > a").each(function() {
				if($(this).html() == sidoval){

					console.log($(this).html());
					$(this).parent().attr("class",$(this).attr('class').substring(4,$(this).attr('class').length).trim());
					$(this).siblings().removeClass("on");
					$(this).addClass("on");
				
				}
			});
			
			
			var regionArray = Array();
			var regionNone = Array ( "시,군,구 선택" );
			var regionSeoul = Array("강남구",  "강동구",  "강북구",
					    "강서구",  "관악구",  "광진구",  "구로구",  "금천구",
					    "노원구",  "도봉구",  "동대문구",  "동작구",  "마포구",
					    "서대문구",  "서초구",  "성동구",  "성북구",  "송파구",
					    "양천구",  "영등포구",  "용산구",  "은평구",  "종로구",
					    "중구",   "중랑구");
			 var regionIncheon = Array ("계양구",  "남구",   "남동구",
					    "동구",   "부평구",  "서구",   "연수구",  "중구",
					    "강화군",  "옹진군");
			 var regionDaejeon = Array ("대덕구",  "동구",   "서구",
					    "유성구",  "중구");
			 var regionGwangju = Array ("광산구",  "남구",   "동구", 
					    "북구",   "서구");
			 var regionDaegu = Array ("남구",   "달서구",  "동구",
					    "북구",   "서구",   "수성구",  "중구",   "달성군");
			 var regionUlsan = Array ("남구",   "동구",   "북구",
					    "중구",   "울주군");
			 var regionBusan = Array ("강서구",  "금정구",  "남구",
					    "동구",   "동래구",  "부산진구",  "북구",   "사상구",
					    "사하구",  "서구",   "수영구",  "연제구",  "영도구",
					    "중구",   "해운대구",  "기장군");
			 var regionGyeonggi = Array ("고양시",  "과천시",  "광명시",
					    "광주시",  "구리시",  "군포시",  "김포시",  "남양주시",
					    "동두천시",  "부천시",  "성남시",  "수원시",  "시흥시",
					    "안산시",  "안성시",  "안양시",  "양주시",  "오산시",
					    "용인시",  "의왕시",  "의정부시",  "이천시",  "파주시",
					    "평택시",  "포천시",  "하남시",  "화성시",  "가평군",
					    "양평군",  "여주군",  "연천군");
			 var regionGangwon = Array ("강릉시",  "동해시",  "삼척시",
					    "속초시",  "원주시",  "춘천시",  "태백시",  "고성군",
					    "양구군",  "양양군",  "영월군",  "인제군",  "정선군",
					    "철원군",  "평창군",  "홍천군",  "화천군",  "횡성군");
			 var regionChungbuk = Array ("제천시",  "청주시",  "충주시",
					    "괴산군",  "단양군",  "보은군",  "영동군",  "옥천군",
					    "음성군",  "증평군",  "진천군",  "청원군");
			 var regionChungnam = Array ("계룡시",  "공주시",  "논산시",
					    "보령시",  "서산시",  "아산시",  "천안시",  "금산군",
					    "당진군",  "부여군",  "서천군",  "연기군",  "예산군",
					    "청양군",  "태안군",  "홍성군");
			 var regionJeonbuk = Array ("군산시",  "김제시",  "남원시",
					    "익산시",  "전주시",  "정읍시",  "고창군",  "무주군",
					    "부안군",  "순창군",  "완주군",  "임실군",  "장수군",  "진안군");
			 var regionJeonnam = Array ("광양시",  "나주시",  "목포시",
					    "순천시",  "여수시",  "강진군",  "고흥군",  "곡성군",
					    "구례군",  "담양군",  "무안군",  "보성군",  "신안군",
					    "영광군",  "영암군",  "완도군",  "장성군",  "장흥군",
					    "진도군",  "함평군",  "해남군",  "화순군");
			 var regionGyeongbuk = Array ("경산시",  "경주시",  "구미시",
					    "김천시",  "문경시",  "상주시",  "안동시",  "영주시",
					    "영천시",  "포항시",  "고령군",  "군위군",  "봉화군",
					    "성주군",  "영덕군",  "영양군",  "예천군",  "울릉군",
					    "울진군",  "의성군",  "청도군",  "청송군",  "칠곡군");
			 var regionGyeongnam = Array ("거제시",  "김해시",  "마산시",
					    "밀양시",  "사천시",  "양산시",  "진주시",  "진해시",
					    "창원시",  "통영시",  "거창군",  "고성군",  "남해군",
					    "산청군",  "의령군",  "창녕군",  "하동군",  "함안군",
					    "함양군",  "합천군");
			 var regionJeju = Array ("서귀포시",  "제주시",  "남제주군",  "북제주군");
			
			 regionArray[0] = regionNone;
			 regionArray[1] = regionSeoul;
			 regionArray[2] = regionIncheon;
			 regionArray[3] = regionDaejeon;
			 regionArray[4] = regionGwangju;
			 regionArray[5] = regionDaegu;
			 regionArray[6] = regionUlsan;
			 regionArray[7] = regionBusan;
			 regionArray[8] = regionGyeonggi;
			 regionArray[9] = regionGangwon;
			 regionArray[10] = regionChungbuk;
			 regionArray[11] = regionChungnam;
			 regionArray[12] = regionJeonbuk;
			 regionArray[13] = regionJeonnam;
			 regionArray[14] = regionGyeongbuk;
			 regionArray[15] = regionGyeongnam;
			 regionArray[16] = regionJeju;
			
			/*군구 단위를 레이어에 for문으로 출력*/
			$(".selbox > p ").next(".gungu").html("");
			for(var i = 0; i<regionArray[num].length;i++){
				if(i==0){
					$(".selbox > p ").next(".gungu").append("<input type=\"radio\" id=\"gungu_0"+(i+1)+ "\" name=\"gungu\" value=\"\" />"
					+"<label for=\"gungu_0"+(i+1)+"\">"+regionArray[num][i]+"</label>");
				}else{
					$(".selbox > p ").next(".gungu").append("<input type=\"radio\" id=\"gungu_0"+(i+1)+ "\" name=\"gungu\" value=\""+regionArray[num][i]+"\" />"
					+"<label for=\"gungu_0"+(i+1)+"\">"+regionArray[num][i]+"</label>");
				}
			}
			
			
			// 군,구 선택시
			$('input:radio[name=gungu]').change(function() {
				$(this).parent().css("display", "none") ;
				var gunguval = $('input:radio[name=gungu]:checked').val();
				$("#gungu_txt").attr("value", gunguval);
			});
			
			
		});
		/*change|끝*/
  		
		// 지도의 피커를 클릭했을 때,
	  	$(".mapArea > .fr > div > a").click(function() {

			$(".mapArea > .fr > div > a").removeClass("on");// 전체지도 피커 제거
			var _local_clk = $(this);
			_local_clk.addClass("on");// 선택한 부분에 피커 추가
				
			// 시도영역의 모든 값에서 지도값을 찾아낸다.
			//$(".sellayer.sido").children("input[type=radio]").each(function(){
			$("input:radio[name='sido']").each(function(){
				
				if( $(this).val().trim() == _local_clk.html().trim() ){
					console.log($(this).val());
					// value값과 지도의 표시 값이 같을경우, 라디오 버튼 체크!
					$("#sido_txt").attr("value",$(this).val().trim());
					$("#gungu_txt").attr("value","");
					$(this).prop("checked", true);
					// class값 subString으로 클래스의 길이값 까지 받아서 지도 바꿈
					var _local_clk_length = _local_clk.attr("class").length; // 클래스길이
					// 서브스트링으로 클래스에 넣어줄 값(지역명) 구하기
					var _local_clk_map  = _local_clk.attr("class").substring(4,_local_clk_length); 
					console.log(_local_clk_map);
					// 서브스트링으로 구한 값을 이용, 지도의 class를 지역명으로 변경!
					$(".mapArea > .fr > div").attr("class", _local_clk_map);
					
					$(this).trigger("change");
				}
			}); 
			
	  	});

		 
	});
