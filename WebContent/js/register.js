$(document).ready(function(){
	$("#register").click(function(){
		var username = $("#username").val();
		var password = $("#pw").val();
		var passwordConfirm = $("#pwConfirm").val();
		// Checking for blank fields.
		$.ajax({
		    url : "register",
		    method : "post",
		    data : {
		        username : username,
		        password : password
		    },
		    success : function(results){
		        if(results != null && results != ""){
		        	$('#error').html("Congratulation !");
		            $('#error').css("color","green");
		            $('#error').css("padding","15px");
		        }else{
		            $('#error').html("Some exception occurred! Please try again");
		            $('#error').css("color","red");
		            $('#error').css("padding","15px");
		        }
		    }
		});
	});
});

/*$.ajax({
    url : "loginRequest",
    method : get,
    data : {
        username : username,
        password : password
    },
    success : function(results){
        if(results != null && results != ""){
            showMessage(results);
            $('#messageDiv').css("display","block");
        }else{
            $('#messageDiv').css("display","none");
            $('#messageDiv').html("");
            alert("Some exception occurred! Please try again.");
        }
    }
});
});

//function to display message to the user
function showMessage(results){
if(results == 'SUCCESS'){
    $('#messageDiv').html("<font color='green'>You are successfully logged in. </font>")
}else if(results == 'FAILURE'){
    $('#messageDiv').html("Username or password incorrect")
}
}*/