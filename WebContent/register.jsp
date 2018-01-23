<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Register</title>
</head>
<body>

<body>

<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Sofia' rel='stylesheet' type='text/css'>

<div class='login'>
  <h2>Register</h2>
  
  <input id="username" 		name='username' 		placeholder='Username' 			type='text' >
  <input id='pw' 			name='password' 		placeholder='Password' 			type='password' >
  <input id='pwConfirm' 	name='passwordConfirm' 	placeholder='Confirm password' 	type='password' >
  
  <div class='agree'>
  		<input id='agree' name='agree' type='checkbox'>
    <label for='agree'></label>Accept rules and conditions
  </div>
  <input class='animated' type='submit' value='Register' id='register'>
  <a class='forgot' href='login.jsp'>Already have an account?</a>

  <div id='error' style="text-align : center; font-style:bold;">
  </div>
</div>
  
  	<script type="text/javascript" src="js/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/other/register.js"></script>

</body>
</body>
</html>