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

//searh result

const search = () =>{

 //console.log("search...");
 
 let query = $("#search-input").val();

  if(query == '')
   {
	$(".search-result").hide();
   }else{
	   console.log(query);
   
	    //sending reuqest to server
     let url =`http://localhost:9999/search/${query}`;
	 
	 fetch(url).then(response =>{
		 return response.json();
	 }).then(data =>{
		 console.log(data);

         let text = `<div class='list-group'>`;

             data.forEach((contact) => {
				 text += `<a href='/user/${contact.cID}/contact' class='list-group-item list-group-item-action'> ${contact.cName} </a>`
			 });

		 text += `</div>`;

		 $(".search-result").html(text);
		 $(".search-result").show();
	 })


	   $(".search-result").show();
   }

}