package jsf.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.Contact;
import services.ContactServices;

@ManagedBean(name="populate")
@RequestScoped
public class PopulateBean {
	
	
	/*
	 * 
	 */
	public void launchPopulate() {
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		ContactServices cs = (ContactServices) context.getBean("contactServices");
		Contact c1 = (Contact) context.getBean("contact1");
		Contact c2 = (Contact) context.getBean("contact2");
		cs.populate(c1);
		cs.populate(c2);
		
	}
}
