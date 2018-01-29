package services;

import daos.interfaces.IDAOPhoneNumber;
import entities.PhoneNumber;

public class PhoneNumberServices {
	
	private IDAOPhoneNumber dao;
	
	public PhoneNumberServices() {}
	
	public PhoneNumberServices(IDAOPhoneNumber dao) {
		this.dao = dao;
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}
}
