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
 * Servlet implementation class FakeContact
 */
public class Peupler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Peupler() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ContactServices cs = (ContactServices) context.getBean("contactServices");
		Contact c1 = (Contact) context.getBean("contact1");
		Contact c2 = (Contact) context.getBean("contact2");
		
		cs.createContact(c1);
		cs.createContact(c2);
	}

}
