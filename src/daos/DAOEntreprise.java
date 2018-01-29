package daos;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import daos.interfaces.IDAOEntreprise;
import entities.Contact;
import entities.Entreprise;

public class DAOEntreprise extends HibernateDaoSupport implements IDAOEntreprise {

	public void create(Entreprise entity) {
		getHibernateTemplate().save(entity);
	}

	public Entreprise read(Long id) {
		return (Entreprise) getHibernateTemplate().get(Entreprise.class, id);
	}

	public void update(Entreprise entity) {
		getHibernateTemplate().merge(entity);
		
	}

	public void delete(Entreprise entreprise) {
		getHibernateTemplate().delete(entreprise);
	}

}
