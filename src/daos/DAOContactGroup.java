package daos;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import daos.interfaces.IDAOContactGroup;
import entities.ContactGroup;

public class DAOContactGroup extends HibernateDaoSupport implements IDAOContactGroup {

	public ContactGroup read(Long id) {
		return (ContactGroup) getHibernateTemplate().load(ContactGroup.class, id);
	}
	
	public void create(ContactGroup entity) {
		getHibernateTemplate().save(entity);
		
	}

	public void update(ContactGroup entity) {
		getHibernateTemplate().update(entity);
		
	}

	public void delete(Long id) {
		ContactGroup c = read(id);
		getHibernateTemplate().delete(c);
	}

}
