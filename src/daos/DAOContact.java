package daos;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import daos.interfaces.IDAOContact;
import entities.Contact;
import entities.PhoneNumber;

public class DAOContact extends HibernateDaoSupport implements IDAOContact {

	public Contact read(Long id) {
		return (Contact) getHibernateTemplate().get(Contact.class, id);
	}

	public void create(Contact entity) {
		getHibernateTemplate().save(entity);

	}

	public void update(Contact entity) {
		getHibernateTemplate().merge(entity);
	}

	public void delete(Contact entity) {
		getHibernateTemplate().delete(entity);
	}

	public List<Contact> findByLastName(String lastName) {
		return (List<Contact>) getHibernateTemplate().find("from Contact c where c.lastName like ?",
				"%" + lastName + "%");
	}

	public List<Contact> findByFirstName(String firstName) {
		return (List<Contact>) getHibernateTemplate().find("from Contact c where c.firstName like ?",
				"%" + firstName + "%");

	}

	public List<Contact> findByEmail(String email) {
		String query = "from Contact c where c.email like :search";
		return (List<Contact>) getHibernateTemplate().findByNamedParam(query, "search", "%" + email + "%");
	}
	
	public List<Contact> getAllContacts(int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Contact.class);
		return (List<Contact>) getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

	/**
	 * La fonction prend en paramètre une liste de noms et on fait une requete de
	 * ces noms sur le firstName et le lastName
	 */
	public List<Contact> findContactByName(String[] tokens) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Contact.class);
		SimpleExpression[] res = new SimpleExpression[tokens.length * 2];
		int i = 0;
		for (String name : tokens) {
			res[i] = Restrictions.like("firstName", "%" + name + "%");
			res[i + 1] = Restrictions.like("lastName", "%" + name + "%");
			i += 2;
		}

		return (List<Contact>) getHibernateTemplate().findByCriteria(criteria.add(Restrictions.or(res)));
	}

}
