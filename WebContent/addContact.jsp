<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create</title>
</head>
<body>
<form action="./newContact" method="POST">
	Name :<input type="text" name="name" /><br />
	Phone :<input type="text" name="phone" /><br />
	Email : <input type="text" name="email" /><br />
	
	Street : <input type="text" name="street" /><br />
	City : <input type="text" name="city" /><br />
	Zip : <input type="text" name="zip" /><br />
	Country : <input type="text" name="country" /><br />
	
	<input type="submit" value="Create" />
</form>
</body>
</html>