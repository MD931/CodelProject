$(document).ready(function(){
	$("#register").click(function(){
		var username = $("#username").val();
		var password = $("#pw").val();
		var passwordConfirm = $("#pwConfirm").val();
		var agree = document.getElementById("agree").checked;
		if(password != passwordConfirm){
            $('#error').html("password must be similar with confirm password !");
            $('#error').css("color","red");
            $('#error').css("padding","15px");
		} else if(username ==""){
			$('#error').html("Please check your username !");
            $('#error').css("color","red");
            $('#error').css("padding","15px");
		}else if(password ==""){
			$('#error').html("Please check your password !");
            $('#error').css("color","red");
            $('#error').css("padding","15px");
		}else if(!agree){
			$('#error').html(" Please confirm your agree !");
            $('#error').css("color","red");
            $('#error').css("padding","15px");
		 }else {
			// Checking for blank fields.
			$.ajax({
			    url : "register",
			    method : "post",
			    data : {
			        username : username,
			        password : password,
			        confrimPassword : passwordConfirm
			    },
			    success : function(results){
			        if(results == "success" ){
			        	/*$('#error').html("Congratulation !");
			            $('#error').css("color","green");
			            $('#error').css("padding","15px");*/
			        	window.location.replace("login.jsp");
			        } else if(results == "failed"){
			            $('#error').html("Please check your informations !");
			            $('#error').css("color","red");
			            $('#error').css("padding","15px");
			        } else if(results == "exists"){
			            $('#error').html("Username already exists  !");
			            $('#error').css("color","red");
			            $('#error').css("padding","15px");
			        } else {
			        	$('#error').html("Some Exception has occured !");
			            $('#error').css("color","red");
			            $('#error').css("padding","15px");
			        }
			    }
			});
		}
		
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