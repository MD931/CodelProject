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
	Id :<input type="text" name="id" /><br />
	Name :<input type="text" name="name" /><br />
	Phone :<input type="text" name="phone" /><br />
	Email : <input type="text" name="email" /><br />
	<input type="submit" value="Create" />
</form>
</body>
</html>