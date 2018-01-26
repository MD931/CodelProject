package services;

import daos.interfaces.IDAOPhoneNumber;

public class PhoneNumberServices {
	
	private IDAOPhoneNumber dao;
	
	public PhoneNumberServices() {}
	
	public PhoneNumberServices(IDAOPhoneNumber dao) {
		this.dao = dao;
	}
}
