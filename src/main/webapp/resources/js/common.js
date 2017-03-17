function resizeWin(){
	$(".log_container").css("height", $(window).innerHeight() ) ;	
	$(".log_container").css("width", $(window).innerWidth() ) ;
	$(".subContent").css("min-height", $(window).innerHeight() - 250 ) ;	
}

function scrollFunc(){
	
	// 헤더 보여지기
	if($(window).scrollTop() <= 120){
		$("header").stop().animate({top: '0px'}, 50);	
	} 
	// 헤더 숨기기
	if($(window).scrollTop() > 120 || $(window).scrollTop() >= $(window).innerHeight() ){
		$("header").stop().animate({top: '-80px'}, 50);	
	}
 
}

$(function() { 
	  
	resizeWin(); 
	
	$("nav.snb > ul > li").click(function() {
		$(this).children("ul").css("display", "block");
	});
	
	$("nav.snb > ul > li").mouseleave(function() {
		$(this).children("ul").css("display", "none");
	});

	// 맨 위로 로딩
	$(".topbtn").click(function(){ 	
		$('html,body').animate({scrollTop: 0}, 500);
	});  
});

// 윈도우의 스크롤이 작동할 때마다 , 헤더와 푸터를 확실하게 작동해주어야 함.
$(window).scroll(function(e){   
	resizeWin();
	scrollFunc();
}); 

// 윈도우가 리사이징 될 때, 섹션의 사이즈를 다시 윈도우 사이즈에 맞춰주어야함
$(window).resize(function(e) {
	resizeWin();
}); 

