//alert("hello");

// cross btn

const toggleSidebar = () =>{
	if($('.sidebar').is(":visible")){
		//true
		//show krna hai
		
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%");
		
	}else{
		//false
		//band krna hai
		
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20%");
		
	}
}