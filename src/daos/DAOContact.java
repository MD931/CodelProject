package daos;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import daos.interfaces.IDAOContact;
import entities.Contact;

public class DAOContact extends HibernateDaoSupport implements IDAOContact{

	public Contact read(Long id) {
		return (Contact) getHibernateTemplate().get(Contact.class, id);
	}
	
	public void create(Contact entity) {
		getHibernateTemplate().save(entity);
		
	}

	public void update(Contact entity) {
		getHibernateTemplate().merge(entity);
	}

	public void delete(Long id) {
		Contact c = read(id);
		getHibernateTemplate().delete(c);
	}

	public List<Contact> getContactsBylastName(String lastName) {
		return (List<Contact>)getHibernateTemplate().find("from Contact c where c.lastName=?",lastName);
	}

	public List<Contact> getContactsByfirstName(String firstName) {
		return (List<Contact>)getHibernateTemplate().find("from Contact c where c.firstName=?",firstName);

	}
	
	public List<Contact> getAllContacts(int firstResult, int maxResults){
		DetachedCriteria criteria = DetachedCriteria.forClass(Contact.class);
		return (List<Contact>)getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

}
