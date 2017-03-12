
$(function() {
	$(".log_container").css("height", $(window).innerHeight() ) ;	
	$(".log_container").css("width", $(window).innerWidth() ) ; 
});
 
// 윈도우의 스크롤이 작동할 때마다
$(window).scroll(function(e){   
  
}); 

// 윈도우가 리사이징 될 때, 섹션의 사이즈를 다시 윈도우 사이즈에 맞춰주어야함
$(window).resize(function(e) { 
	$(".log_container").css("height", $(window).innerHeight() ) ;	
	$(".log_container").css("width", $(window).innerWidth() ) ; 
});
