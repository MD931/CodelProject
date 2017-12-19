package services;

import daos.DAOContact;

public class ContactServices {
	
	final static DAOContact dao = new DAOContact();
	
	public static void create(String id, String name, String phone, String email) {
		dao.create(id, name, phone, email);
	}
	
	public static void read(String name) {
		dao.read(name);
	}
	
	public static void update(String id) {
		dao.update(id);
	}
	
	public static void delete(String id) {
		dao.delete(id);
	}
}
