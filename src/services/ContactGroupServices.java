package services;

import java.util.List;

import org.hibernate.StaleObjectStateException;

import daos.interfaces.IDAOContactGroup;
import entities.Contact;
import entities.ContactGroup;
import util.ResponseTools;

public class ContactGroupServices {
	
	private IDAOContactGroup dao;
	
	public ContactGroupServices() {}
	
	public ContactGroupServices(IDAOContactGroup dao) {
		this.dao = dao;
	}
	
	public void create(String groupName) {
		ContactGroup cg= new ContactGroup(groupName);
		dao.create(cg);
	}
	
	public List<ContactGroup> getAllContactGroup(){
		return dao.getAllContactGroup();
	}
	public int update(ContactGroup cg) {
		try {
			dao.update(cg);
			return ResponseTools.SUCCESS;
		} catch (StaleObjectStateException e) {
			return ResponseTools.VERSION_ERROR;
		} catch (Exception e) {
			return ResponseTools.MAIN_ERROR;
		}
	}
	public int delete(ContactGroup entity) {
		try{
			dao.delete(entity);
			return ResponseTools.SUCCESS;
		}catch(IllegalArgumentException e) {
			return ResponseTools.NOT_EXIST;
		}catch (Exception e) {
			return ResponseTools.MAIN_ERROR;
		}
	}
}
