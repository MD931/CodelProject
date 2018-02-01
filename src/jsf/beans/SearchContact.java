package jsf.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.Contact;
import services.ContactServices;
import util.ResponseTools;

@ManagedBean(name="searchContact")
@ViewScoped
public class SearchContact implements Serializable{

	String search;
	List<Contact> contacts;
	Contact selectedContact;
	private ContactServices cs;

	@PostConstruct
	public void init() {
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());

		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		search = params.get("search");
		System.out.println("------ "+search);

		/* INIT */
		cs = (ContactServices) context.getBean("contactServices");
		if(search != null) {
			contacts =cs.searchContacts(search);
		}
		/* INIT */
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void supprimerContact() {
		if(cs.delete(selectedContact) == ResponseTools.SUCCESS) {
			contacts.remove(selectedContact);
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erreur lors de la suppression"));
		}

	}

}
