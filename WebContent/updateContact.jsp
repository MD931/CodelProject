<%@page import="entities.PhoneNumber"%>
<%@page import="entities.Contact"%>
<%@page import="entities.ContactGroup"%>
<%@page import="services.ContactServices"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
<%
Contact contact = (Contact) request.getAttribute("contact");
%>
<form action="updateContact?id=<%=contact.getId()%>" method="POST">
	Firstname :<input type="text" name="firstname" value="<%=contact.getFirstName()%>"/><br />
	Lastname :<input type="text" name="lastname" value="<%=contact.getLastName()%>"/><br />
	Email : <input type="text" name="email" value="<%=contact.getEmail()%>"/><br />
	Street : <input type="text" name="street" value="<%=contact.getAdd().getStreet()%>"/><br />
	City : <input type="text" name="city" value="<%=contact.getAdd().getCity()%>"/><br />
	Zip : <input type="text" name="zip" value="<%=contact.getAdd().getZip()%>"/><br />
	Country : <input type="text" name="country" value="<%=contact.getAdd().getCountry()%>"/><br />
	<input type="submit" value="Update" />
</form>
</body>
</html>