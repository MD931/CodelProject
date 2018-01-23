package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import services.ContactServices;
import services.UserServices;

/**
 * Servlet implementation class CreateContactServlet
 */
public class NewContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id  = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		/*
		ContactServices.create(id, name, phone, email, UserServices.read(Long.parseLong("1")));*/
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ContactServices cs = (ContactServices) context.getBean("contactServices");
		cs.create(id, name, phone, email, UserServices.read(Long.parseLong("1")));
	}

}
