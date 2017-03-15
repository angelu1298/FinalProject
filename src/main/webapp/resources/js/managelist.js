
   function find_check(){
			   if($.trim($("#find_name").val())==""){
				   alert("검색이름을 입력하세요!");
				   $("#find_name").val("").focus();
				   return false;
			   }
		   };

    $(document).ready(function(){
    	 
    	$("#viewcount").change(function(){
    	    var scount=$("#viewcount").val();
           $.ajax({
    	       type:"POST",    
    	       url:"memlist.brn",    
    	       data: {"limit" : scount}, 
    	       cache: false,
    	       headers : {"cache-control" : "no-cache", "pragma" : "no-cache"},
    	       success : function(data){    	        
    	    	  $("body").html(data);
    	       },
		       error:function(){
			        alert("data error");
			   }
    	      })//ajax end
    	     });//change end
    	
    	    });//function end