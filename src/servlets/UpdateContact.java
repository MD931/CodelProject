package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.Address;
import entities.Contact;
import services.ContactServices;

/**
 * Servlet implementation class UpdateContactServlet
 */
public class UpdateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id  = request.getParameter("id");
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ContactServices cs = (ContactServices) context.getBean("contactServices");
		Contact c = cs.read(Long.parseLong(id));
		request.setAttribute("contact", c);
		System.out.println(c.getFirstName()+" "+c.getLastName()+" "+c.getEmail()+" "+c.getAdd().getStreet());
		getServletContext().getRequestDispatcher("/updateContact.jsp").forward(request, response);
		//ContactServices.update(Long.parseLong(id), name, phone, email);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id  = request.getParameter("id");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ContactServices cs = (ContactServices) context.getBean("contactServices");
		Address add = new Address(street, city, zip, country);
		cs.update(Long.parseLong(id), firstname, lastname, email, street, city, zip, country);
		
		//ContactServices.update(Long.parseLong(id), name, phone, email);
	}

}
