package services;

import daos.interfaces.IDAOContact;
import entities.Address;
import entities.Contact;

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
	public Contact load(Long id) {
		return dao.load(id);

	}
	
	public void update(Long id, String firstName, String lastName, String email
			, String street, String city, String zip, String country) {
		Contact c = load(id);
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEmail(email);
		c.getAdd().setStreet(street);
		c.getAdd().setCity(city);
		c.getAdd().setZip(zip);
		c.getAdd().setCountry(country);
		dao.update(c);
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}
	
	
}
