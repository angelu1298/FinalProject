$(function() {
 
   $(".notice_board > ul > li").on("click", function() {

      var $con = $(this).find(".board_cont");
      if ($con.is(":visible")) {
         $(".notice_board > ul > li").find(".board_cont").slideUp();
         $(".notice_board > ul > li").removeClass("active");
      } else {
         $(".notice_board > ul > li .board_cont:visible").slideUp();
         $(".notice_board > ul > li").removeClass("active");
         $(this).addClass("active");
         $con.slideDown();
      }
   })
});