package daos;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import daos.interfaces.IDAOContactGroup;
import entities.Contact;
import entities.ContactGroup;

public class DAOContactGroup extends HibernateDaoSupport implements IDAOContactGroup {

	public ContactGroup read(Long id) {
		return (ContactGroup) getHibernateTemplate().load(ContactGroup.class, id);
	}
	
	public void create(ContactGroup entity) {
		getHibernateTemplate().save(entity);
		
	}

	public void update(ContactGroup entity) {
		getHibernateTemplate().merge(entity);
		
	}

	public void delete(ContactGroup entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public boolean isExist(String groupName) {
		String query = "from ContactGroup cg where cg.groupName like :search";
		List<ContactGroup> contacsGroup = (List<ContactGroup>) getHibernateTemplate().findByNamedParam(query, "search",groupName );
		if(contacsGroup==null || contacsGroup.size() == 1)
			return true;
		return false;
	}

	@Override
	public List<ContactGroup> getAllContactGroup() {
		return (List<ContactGroup>)getHibernateTemplate().find("from ContactGroup");
	}
}
