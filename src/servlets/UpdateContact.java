package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
		if(c != null) {
			request.getSession(true).setAttribute("contact", c);
			System.out.println(c.getFirstName()+" "+c.getLastName()+" "+c.getEmail()+" "+c.getAdd().getStreet());
			getServletContext().getRequestDispatcher("/updateContact.jsp").forward(request, response);
		}else {
			System.out.println("ABOUCHE");
		}
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
		Contact c = (Contact) request.getSession().getAttribute("contact");
		int result = cs.update(c, Long.parseLong(id), firstname, lastname, email, street, city, zip, country);
		System.out.println("RESULT == "+result);
	}

}
