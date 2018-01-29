package jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.Contact;
import services.ContactServices;
import util.ResponseTools;

@ManagedBean(name="main")
public class Main implements Serializable{
	List<Contact> contacts;
	Contact selectedContact;
	ContactServices cs;

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void supprimerContact() {
		if(cs.delete(selectedContact) == ResponseTools.SUCCESS) {
			contacts.remove(selectedContact);
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erreur lors de la suppression"));
		}
		
	}
	
    @PostConstruct
    public void init(){
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		cs = (ContactServices) context.getBean("contactServices");
		contacts = cs.getAllContacts(0, 10);
    }

}
