package jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.Contact;
import entities.ContactGroup;
import entities.Entreprise;
import entities.PhoneNumber;
import services.ContactServices;
import util.ResponseTools;

@ManagedBean(name="showContact")
@ApplicationScoped
public class ShowContact implements Serializable {
	
	Long id;
	Contact contact;
	Integer numSiret;
	List<PhoneNumber> profiles;
	List<ContactGroup> groups;
	private ContactServices cs;

	@PostConstruct
	public void init()
	{
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		id = Long.parseLong(params.get("id"));

		cs = (ContactServices) context.getBean("contactServices");
		
		contact = cs.read(id);
		if(contact instanceof Entreprise) {
			System.out.println("Entreprise "+((Entreprise) contact).getNumSiret());
			numSiret = ((Entreprise) contact).getNumSiret();
		}
		if(!contact.getProfiles().isEmpty()) {
			profiles = new ArrayList<PhoneNumber>(contact.getProfiles());
		}else {
			profiles = new ArrayList<>();
		}
		if(!contact.getBooks().isEmpty()) {
			groups = new ArrayList<ContactGroup>(contact.getBooks());	
		}else {
			groups = new ArrayList<>();
		}
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Integer getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(Integer numSiret) {
		this.numSiret = numSiret;
	}

	public List<PhoneNumber> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<PhoneNumber> profiles) {
		this.profiles = profiles;
	}

	public List<ContactGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ContactGroup> groups) {
		this.groups = groups;
	}
	
	public String supprimerContact() {
		System.out.println("AZUL");
		FacesContext context = FacesContext.getCurrentInstance();
		if(cs.delete(contact) == ResponseTools.SUCCESS) {
			
			return ("main?faces-redirect=true");
			
		}else {
			return null;
		}
		
	}

}
