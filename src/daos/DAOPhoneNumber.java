package daos;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import daos.interfaces.IDAOPhoneNumber;
import entities.PhoneNumber;

public class DAOPhoneNumber extends HibernateDaoSupport implements IDAOPhoneNumber{

	public PhoneNumber read(Long id) {
		return (PhoneNumber) getHibernateTemplate().load(PhoneNumber.class, id);
	}
	
	public void create(PhoneNumber entity) {
		getHibernateTemplate().save(entity);
		
	}

	public void update(PhoneNumber entity) {
		getHibernateTemplate().update(entity);
		
	}

	public void delete(Long id) {
		PhoneNumber c = read(id);
		getHibernateTemplate().delete(c);
		
	}

}
