package services;

import daos.DAOContact;
import daos.interfaces.IDAOContact;
import entities.Contact;

public class ContactServices {
	
	private IDAOContact dao = new DAOContact();
	
	public ContactServices() {}
	
	public ContactServices(IDAOContact dao) {
		this.dao = dao;
	}
	
	public void create(String id, String name, String phone, String email) {
		dao.create(id, name, phone, email);
	}
	
	public Contact read(Long id) {
		return dao.read(id);
	}
	
	public void update(Long id, String firstName, String lastName, String email) {
		dao.update(id, firstName, lastName, email);
	}
	
	public void delete(String id) {
		dao.delete(id);
	}
	
	public void createContact(Contact contact) {
		dao.createContact(contact);
	}
}
