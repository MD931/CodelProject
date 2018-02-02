package jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import entities.Address;
import entities.ContactGroup;
import entities.PhoneNumber;
import services.ContactGroupServices;
import services.ContactServices;
import services.EntrepriseServices;
import util.ResponseTools;

@ManagedBean(name = "createContact")
@ViewScoped
public class CreateContact implements Serializable {
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

	@ManagedProperty("#{msgs}")
	private ResourceBundle msgs;

	@PostConstruct
	public void init() {
		phones = new ArrayList<PhoneNumber>();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(
				(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
		cs = (ContactServices) context.getBean("contactServices");
		cgs = (ContactGroupServices) context.getBean("contactGroupServices");
		es = (EntrepriseServices) context.getBean("entrepriseServices");
		allGroups = cgs.getAllContactGroup();
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
		List<ContactGroup> selectedGroups = new ArrayList<>();
		for (Long id : selectedGroupsId) {
			for (ContactGroup cg : allGroups) {
				if (cg.getGroupId() == id) {
					selectedGroups.add(cg);
					break;
				}
			}
		}
		Address address = new Address(street, city, zip, country);
		int res;
		if (numSiret != null) {
			 res= es.create(firstname, lastname, email, address, new HashSet<PhoneNumber>(phones),
					new HashSet<>(selectedGroups), numSiret);
		} else {
			res = cs.create(firstname, lastname, email, address, new HashSet<PhoneNumber>(phones),
					new HashSet<>(selectedGroups));
		}
		if(res==ResponseTools.SUCCESS) {
			context.addMessage(null, new FacesMessage(msgs.getString("createContact")));
			reset();
		} else {
			context.addMessage(null, new FacesMessage(error(res)));
		}

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
		case ResponseTools.SIRET_ERROR:
			return msgs.getString("errorNumsiret");
		default:
			return msgs.getString("error");
		}
	}
	
	public void reset() {
		this.setFirstname("");
		this.setLastname("");
		this.setEmail("");
		this.setZip("");
		this.setCity("");
		this.setStreet("");
		this.setCountry("");
		numSiret = null;
		phones = new ArrayList<>();
	}

	public ResourceBundle getMsgs() {
		return msgs;
	}

	public void setMsgs(ResourceBundle msgs) {
		this.msgs = msgs;
	}

}
