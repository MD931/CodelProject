package daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Contact;
import util.HibernateUtil;

public class DAOContact {
	
	public void create(String id, String name, String phone, String email) {
		System.out.println("Create "+id+", "+name+", "+phone+", "+email);
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		Contact c = new Contact(name,phone, email);
		Transaction tx = s.getTransaction();
		if(!tx.isActive()) tx = s.beginTransaction();
		s.save(c);
		tx.commit();
		s.close();
	}
	
	public void read(String name) {
		System.out.println("Search "+name);
	}
	
	public void update(String id) {
		System.out.println("Update "+id);
	}
	
	public void delete(String id) {
		System.out.println("Delete "+id);
	}

}
