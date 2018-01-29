package daos;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import daos.interfaces.IDAOEntreprise;
import entities.Entreprise;

public class DAOEntreprise extends HibernateDaoSupport implements IDAOEntreprise {

	public void create(Entreprise entity) {
		getHibernateTemplate().save(entity);
	}

	public Entreprise read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Entreprise entity) {
		getHibernateTemplate().merge(entity);
		
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
