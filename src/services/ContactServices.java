package services;

import daos.DAOContact;
import entities.Contact;
import entities.UserAccount;

public class ContactServices {
	
	final static DAOContact dao = new DAOContact();
	
	public static void create(String id, String name, String phone, String email, UserAccount user) {
		dao.create(id, name, phone, email, user);
	}
	
	public static Contact read(Long id) {
		return dao.read(id);
	}
	
	public static void update(Long id, String firstName, String lastName, String email) {
		dao.update(id, firstName, lastName, email);
	}
	
	public static void delete(String id) {
		dao.delete(id);
	}
}
