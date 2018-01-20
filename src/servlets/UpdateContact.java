package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ContactServices;

/**
 * Servlet implementation class UpdateContactServlet
 */
public class UpdateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id  = request.getParameter("id");
		String name = request.getParameter("firstName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		ContactServices.update(Long.parseLong(id), name, phone, email);
	}

}
