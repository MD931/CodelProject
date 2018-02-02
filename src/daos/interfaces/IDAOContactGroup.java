package daos.interfaces;

import java.util.List;

import entities.ContactGroup;

public interface IDAOContactGroup extends IDAO<ContactGroup>{
	public List<ContactGroup> getAllContactGroup();
	public boolean isExist(String groupName);
}
