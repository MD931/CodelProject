package services;

import daos.DAOContact;
import daos.interfaces.IDAOContact;
import entities.Contact;
import entities.UserAccount;

public class ContactServices {
	
	private IDAOContact dao = new DAOContact();
	
	public ContactServices() {}
	
	public ContactServices(IDAOContact dao) {
		this.dao = dao;
	}
	
	public void create(String id, String name, String phone, String email, UserAccount user) {
		dao.create(id, name, phone, email, user);
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
}
