package services;

import org.hibernate.StaleObjectStateException;

import daos.interfaces.IDAOContact;
import entities.Address;
import entities.Contact;
import util.ResponseTools;

public class ContactServices {
	
	private IDAOContact dao;
	
	public ContactServices() {}
	
	public ContactServices(IDAOContact dao) {
		this.dao = dao;
	}
	
	public void create(String name, String phone, String email, Address address) {
		Contact c = new Contact(name, phone, email);
		c.setAdd(address);
		dao.create(c);
	}
	
	public Contact read(Long id) {
		return dao.read(id);
	}
	
	public int update(Contact c, Long id, String firstName, String lastName, String email
			, String street, String city, String zip, String country) {
		try {
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setEmail(email);
			c.getAdd().setStreet(street);
			c.getAdd().setCity(city);
			c.getAdd().setZip(zip);
			c.getAdd().setCountry(country);
			dao.update(c);
			return ResponseTools.SUCCESS;
		} catch (StaleObjectStateException e) {
			return ResponseTools.VERSION_ERROR;
		} catch (Exception e) {
			return ResponseTools.MAIN_ERROR;
		}
	}
	
	public int delete(Long id) {
		try{
			dao.delete(id);
			return ResponseTools.SUCCESS;
		}catch(IllegalArgumentException e) {
			return ResponseTools.NOT_EXIST;
		}catch (Exception e) {
			return ResponseTools.MAIN_ERROR;
		}
	}
	
	
}
