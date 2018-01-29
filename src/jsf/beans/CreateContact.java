package jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.Address;
import entities.ContactGroup;
import entities.PhoneNumber;
import services.ContactGroupServices;
import services.ContactServices;
import services.EntrepriseServices;

@ManagedBean(name="createContact")
@ViewScoped
public class CreateContact implements Serializable{
	String firstname;
	String lastname;
	String email;
	String street;
	String city;
	String zip;
	String country;
	Integer numSiret;
	List<PhoneNumber> phones;
	List<ContactGroup> allGroups;
	List<Long> selectedGroupsId;
	private ContactServices cs;
	private ContactGroupServices cgs;
	private EntrepriseServices es;
	
    @PostConstruct
    public void init(){
    	System.out.println("refresh");
    	phones = new ArrayList<PhoneNumber>();
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		cs = (ContactServices) context.getBean("contactServices");
		cgs = (ContactGroupServices) context.getBean("contactGroupServices");
		es = (EntrepriseServices) context.getBean("entrepriseServices");
		allGroups = cgs.getAllContactGroup();
		System.out.println(allGroups);
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



	public List<PhoneNumber> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneNumber> phones) {
		this.phones = phones;
	}
	
    public void add() {
        phones.add(new PhoneNumber());
    }
	
    public void remove(PhoneNumber phone) {
        phones.remove(phone);
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

	public void create() {

		FacesContext context = FacesContext.getCurrentInstance();
		boolean error = false;
		if(firstname.isEmpty()) {
			context.addMessage("fname", new FacesMessage("Champ first obligatoire !"));
			error = true;
		}
		if(lastname.isEmpty()) {
			error = true;
			context.addMessage("lname", new FacesMessage("champ last obligatoire !"));
		}
		if(email.isEmpty() || !email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
			error = true;
			context.addMessage("email", new FacesMessage("champ email obligatoire !"));
		}
		if(street.isEmpty()) {
			error = true;
			context.addMessage("street", new FacesMessage("champ street obligatoire !"));
		}
		if(city.isEmpty()) {
			error = true;
			context.addMessage("city", new FacesMessage("champ city obligatoire !"));

		}
		if(country.isEmpty()) {
			error = true;
			context.addMessage("country", new FacesMessage("champ country obligatoire !"));

		}
		if(!zip.matches("^[0-9]{5}$")) {
			error = true;
			context.addMessage("zip", new FacesMessage("code postal invalide !"));

		}
		if(!error) {
			List<ContactGroup> selectedGroups = new ArrayList<>();
			for(Long id : selectedGroupsId) {
				for(ContactGroup cg : allGroups) {
					if(cg.getGroupId() == id) {
						selectedGroups.add(cg);
						break;
					}
				}
			}
			Address address = new Address(street,city,zip, country);
			
			if(numSiret != null) {
				es.create(firstname, lastname, email, address, new HashSet<PhoneNumber>(phones)
						, new HashSet<>(selectedGroups), numSiret);
			
			} else {
				cs.create(firstname, lastname, email, address, new HashSet<PhoneNumber>(phones)
						, new HashSet<>(selectedGroups));
			} 
		}
		
	}
	
	
	
	
}
