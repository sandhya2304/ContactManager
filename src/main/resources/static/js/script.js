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

/// first req to server to create order

const paymentStart = () =>{
	console.log("payment started....");
	
	var amount = $("#payment_field").val();
	console.log(amount)
	if(amount == '' || amount == null)
	{
		alert("amount is required!!!");
		return;
	}

  // we will use ajax to send request to server
  // to create order

  $.ajax({
	  url:'/user/create_order',
	  data: JSON.stringify({amount:amount,info:'order_request'}),
	  contentType: 'application/json',
	  type: 'POST',
	  dataType:'json',
	  
	  success: function(response){
		  if(response.status == "created"){
			  let options = {
					  key : 'rzp_test_jey4AM3vtkd3ub',
					  amount : response.amount,
					  currency : 'INR',
					  name : 'Smart Conatact Mgr ',
					  description : 'Donation',
					  image: 'data:image/jpeg;base64,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',
					  order_id : response.id,
					  handler: function(response){
						  console.log(response.razorpay_payment_id)
						    console.log(response.razorpay_order_id)
						      console.log(response.razorpay_signature)
						      console.log('payment hogya')
					  },
					  prefill: {
					        name: "",
					        email: "",
					        contact: ""
					    },
					    notes: {
					        address: " Corporate Office"
					    },
					    theme: {
					        color: "#3399cc"
					    }
			  };
			  
			  var rzp1 = new Razorpay(options);
			  
			  rzp1.on('payment.failed', function (response){
			        alert(response.error.code);
			        alert(response.error.description);
			        alert(response.error.source);
			        alert(response.error.step);
			        alert(response.error.reason);
			        alert(response.error.metadata.order_id);
			        alert(response.error.metadata.payment_id);
			        alert('payment failed!!');
			});
			  
			  rzp1.open();
			  
			  
		  }
		  
	  }
          console.log(response);
	  },
	  error:function(error){
		  console.log(error);
	  }
  });

}






