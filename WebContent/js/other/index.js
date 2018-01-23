$(document).ready(function(){
	$("#login").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		// Checking for blank fields.
		if( username =='' || password ==''){
			return;
		} else {
			$.post("login",{ username: username, password:password},
					function(data) {
						if(data =="false"){
							$('#alert').html("Username or password incorrect");
                            $('#alert').css("display","block");
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