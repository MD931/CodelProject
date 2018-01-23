<%@page import="entities.PhoneNumber"%>
<%@page import="entities.Contact"%>
<%@page import="entities.UserAccount"%>
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
if(request.getParameter("id")==null){
	out.print("<b><font color=\"red\">Unknown id...</font></b><br><br>");
}
Long id = Long.parseLong(request.getParameter("id"));
Contact contact = new Contact(); //ContactServices.read(id);
%>
<form action="./updateContact?id=<%=contact.getId()%>" method="POST">
	Id :<input type="text" name="id" /><br />
	Firstname :<input type="text" name="firstName" value="<%=contact.getFirstName()%>"/><br />
	Lastname :<input type="text" name="phone" value="<%=contact.getLastName()%>"/><br />
	Email : <input type="text" name="email" value="<%=contact.getEmail()%>"/><br />
	<input type="submit" value="Update" />
</form>
</body>
</html>