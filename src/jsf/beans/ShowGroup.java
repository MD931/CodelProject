package jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
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
import entities.ContactGroup;
import services.ContactGroupServices;
import util.ResponseTools;

@ManagedBean(name="showGroup")
@ViewScoped
public class ShowGroup implements Serializable{

	private long id;
	ContactGroup group;
	List<Contact> contacts;
	private ContactGroupServices cgs;
	
	Contact selectedContact;

	@PostConstruct
	public void init()
	{
		System.out.println("INIT Show Group");
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();


		id = Long.parseLong(params.get("id"));

		cgs = (ContactGroupServices) context.getBean("contactGroupServices");
		group = cgs.read(id);
		contacts = new ArrayList<>(group.getContacts());
	}

	public ContactGroup getGroup() {
		return group;
	}

	public void setGroup(ContactGroup group) {
		this.group = group;
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

	public void deleteContactFromGroup() {
		FacesContext context = FacesContext.getCurrentInstance();
		group.removeContact(selectedContact);
		if(cgs.update(group) == ResponseTools.SUCCESS) {
			contacts.remove(selectedContact);
			context.addMessage(null, new FacesMessage("supprimé avec succès"));
		}else {
			context.addMessage(null, new FacesMessage("echec"));
		}
		
	}
	
}
