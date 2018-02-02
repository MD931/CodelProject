package jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	
	String firstname;
	String lastname;
	String email;
	String street;
	String city;
	String zip;
	String country;
	Long id;
	Integer numSiret;
	List<PhoneNumber> phones;
	List<ContactGroup> allGroups;
	List<Long> selectedGroupsId;
	Contact contact;
	List<PhoneNumber> profiles;
	private ContactServices cs;
	private ContactGroupServices cgs;
	private EntrepriseServices es;
	private PhoneNumberServices ps;
	
	@ManagedProperty("#{msgs}")
	private ResourceBundle msgs;
	
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
		
		
		/* INIT */
		contact = cs.read(id);
		firstname = contact.getFirstName();
		lastname = contact.getLastName();
		email = contact.getEmail();
		street = contact.getAdd().getStreet();
		city = contact.getAdd().getCity();
		zip = contact.getAdd().getZip();
		country = contact.getAdd().getCountry();
		if(contact instanceof Entreprise) {
			System.out.println("Entreprise "+((Entreprise) contact).getNumSiret());
			numSiret = ((Entreprise) contact).getNumSiret();
		}
		selectedGroupsId = new ArrayList<Long>();

		contact.getBooks().forEach(book ->{
			selectedGroupsId.add(book.getGroupId());
		});
		profiles = new ArrayList<PhoneNumber>(contact.getProfiles());
		/* INIT */
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
	



	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
		ps.delete(phone);
		profiles.remove(phone);
		contact.getProfiles().remove(phone);
	}


	public void edit() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<ContactGroup> selectedGroups = new ArrayList<>();
		for(Long id : selectedGroupsId) {
			for(ContactGroup cg : allGroups) {
				if(cg.getGroupId() == id) {
					selectedGroups.add(cg);
					break;
				}
			}
		}
		contact.setBooks(new HashSet<>(selectedGroups));
		contact.setProfiles(new HashSet<>(profiles));
		if(contact instanceof Entreprise) {
			Entreprise entreprise = (Entreprise) contact;
			if(numSiret.toString().isEmpty() || numSiret.toString().matches("^[0-9]{5,}$")) {
				context.addMessage("numSiret", new FacesMessage(msgs.getString("errorNumsiret")));
			}else {
				int result = es.update(entreprise, firstname, lastname, email, street, city, zip, country, numSiret);
				if(result == ResponseTools.SUCCESS) {
					context.addMessage("success", new FacesMessage(msgs.getString("updatedSucces")));
				}else if(result == ResponseTools.VERSION_ERROR) {
					context.addMessage("error", new FacesMessage(msgs.getString("errorVersion")));
				}else {
					context.addMessage("error", new FacesMessage(error(result)));
				}
			}
		}else {
			int result = cs.update(contact, firstname, lastname, email, street, city, zip, country);
			if(result == ResponseTools.SUCCESS) {
				context.addMessage("success", new FacesMessage(msgs.getString("updatedSucces")));
			}else if(result == ResponseTools.VERSION_ERROR) {
				context.addMessage("error", new FacesMessage(msgs.getString("errorVersion")));
			}else {
				context.addMessage("error", new FacesMessage(error(result)));
			}
		}
	}

	public void loadData() {
		System.out.println("loadData");
	}
	
	public String error(int responseError) {
		switch (responseError) {
		case ResponseTools.FIRSTNAME_ERROR:
			return msgs.getString("errorFirstName");
		case ResponseTools.LASTNAME_ERROR:
			return msgs.getString("errorLasttName");
		case ResponseTools.EMAIL_ERROR:
			return msgs.getString("errorEmail");
		case ResponseTools.STREET_ERROR:
			return msgs.getString("errorStreet");
		case ResponseTools.CITY_ERROR:
			return msgs.getString("errorCity");
		case ResponseTools.ZIP_ERROR:
			return msgs.getString("errorZip");
		case ResponseTools.COUNTRY_ERROR:
			return msgs.getString("errorCountry");
		case ResponseTools.PHONE_KIND_ERROR:
			return msgs.getString("errorKind");
		case ResponseTools.PHONE_NUMBER_ERROR:
			return msgs.getString("errorNumber");
		default:
			return msgs.getString("error");
		}
	} 
	
	public ResourceBundle getMsgs() {
		return msgs;
	}



	public void setMsgs(ResourceBundle msgs) {
		this.msgs = msgs;
	}



}
