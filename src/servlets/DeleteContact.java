package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import services.ContactServices;

/**
 * Servlet implementation class DeleteContactServlet
 */
public class DeleteContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id  = request.getParameter("id");
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ContactServices cs = (ContactServices) context.getBean("contactServices");
		cs.delete(Long.parseLong(id));
	}

}
