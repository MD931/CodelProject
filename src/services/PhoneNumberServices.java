package services;

import daos.interfaces.IDAOPhoneNumber;
import entities.PhoneNumber;

public class PhoneNumberServices {
	
	private IDAOPhoneNumber dao;
	
	public PhoneNumberServices() {}
	
	public PhoneNumberServices(IDAOPhoneNumber dao) {
		this.dao = dao;
	}
	
	
	public PhoneNumber read(Long id) {
		return dao.read(id);
	}
	
	public void create(PhoneNumber entity) {
		dao.create(entity);
	}
	
	public void update(PhoneNumber entity) {
		dao.update(entity);
	}
	
	public void delete(PhoneNumber entity) {
		dao.delete(entity);
	}
}
