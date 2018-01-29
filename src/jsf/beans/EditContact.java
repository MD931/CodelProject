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
import entities.Entreprise;
import entities.PhoneNumber;
import services.ContactGroupServices;
import services.ContactServices;
import services.EntrepriseServices;
import services.PhoneNumberServices;
import util.ResponseTools;

@ManagedBean(name="editContact")
@ViewScoped
public class EditContact implements Serializable{
	List<PhoneNumber> phones;
	List<ContactGroup> allGroups;
	List<Long> selectedGroupsId;
	Long id;
	Integer numSiret;
	Contact contact;
	List<PhoneNumber> profiles;
	private ContactServices cs;
	private ContactGroupServices cgs;
	private EntrepriseServices es;
	private PhoneNumberServices ps;
	
    @PostConstruct
    public void init(){
    	System.out.println("init");
    	phones = new ArrayList<PhoneNumber>();
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		id = Long.parseLong(params.get("id"));
		cs = (ContactServices) context.getBean("contactServices");
		cgs = (ContactGroupServices) context.getBean("contactGroupServices");
		es = (EntrepriseServices) context.getBean("entrepriseServices");
		ps = (PhoneNumberServices) context.getBean("phoneNumberServices");
		allGroups = cgs.getAllContactGroup();
		contact = cs.read(id);
		if(contact instanceof Entreprise) {
			System.out.println("Entreprise "+((Entreprise) contact).getNumSiret());
			numSiret = ((Entreprise) contact).getNumSiret();
		}
		contact.getProfiles().forEach(phone ->{
			System.out.println(phone.getPhoneKind()+" "+phone.getPhoneNumber());
		});
		
		selectedGroupsId = new ArrayList<Long>();
		
		contact.getBooks().forEach(book ->{
			selectedGroupsId.add(book.getGroupId());
		});
		
		profiles = new ArrayList<PhoneNumber>(contact.getProfiles());
		System.out.println(allGroups);
    }
    
	public List<PhoneNumber> getProfiles() {
		return profiles;
	}



	public void setProfiles(List<PhoneNumber> profiles) {
		this.profiles = profiles;
	}



	public Contact getContact() {
		return contact;
	}



	public void setContact(Contact contact) {
		this.contact = contact;
	}



	public List<ContactGroup> getAllGroups() {
		return allGroups;
	}

	public void setAllGroups(List<ContactGroup> allGroups) {
		this.allGroups = allGroups;
	}



	public List<Long> getSelectedGroupsId() {
		return selectedGroupsId;
	}

	public void setSelectedGroupsId(List<Long> selectedGroupsId) {
		this.selectedGroupsId = selectedGroupsId;
	}
	
	public Integer getNumSiret() {
		return numSiret;
	}



	public void setNumSiret(Integer numSiret) {
		this.numSiret = numSiret;
	}



	public List<PhoneNumber> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneNumber> phones) {
		this.phones = phones;
	}
	
    public void add() {
    	PhoneNumber phone = new PhoneNumber();
    	phone.setContact(contact);
    	contact.getProfiles().add(phone);
        profiles.add(phone);
    }
	
    public void remove(PhoneNumber phone) {
    	ps.delete(phone.getId());
        profiles.remove(phone);
        contact.getProfiles().remove(phone);
    }
	

	public void edit() {
		System.out.println("edit");
		FacesContext context = FacesContext.getCurrentInstance();
		if(contact instanceof Entreprise) {
			System.out.println("edit Entreprise");
			Entreprise entreprise = (Entreprise) contact;
			if(numSiret.toString().isEmpty()) {
				context.addMessage("numSiret", new FacesMessage("NumSiret required"));
			}else {
				System.out.println("edit numSiret not empty "+numSiret);
				int result = es.update(entreprise, numSiret);
				if(result == ResponseTools.SUCCESS) {
					context.addMessage("success", new FacesMessage("Modification effectuée avec succès"));
				}else if(result == ResponseTools.VERSION_ERROR) {
					context.addMessage("error", new FacesMessage("Version erreur"));
				}else {
					context.addMessage("error", new FacesMessage("Echec de modification"));
				}
			}
		}
	}
	
	
	
	
}