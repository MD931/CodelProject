package services;

import daos.interfaces.IDAOContactGroup;

public class ContactGroupServices {
	
	private IDAOContactGroup dao;
	
	public ContactGroupServices() {}
	
	public ContactGroupServices(IDAOContactGroup dao) {
		this.dao = dao;
	}
}
