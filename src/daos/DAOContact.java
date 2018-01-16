package daos;

import org.hibernate.Session;

import entities.Address;
import entities.Contact;
import util.HibernateUtil;

public class DAOContact {
	
	public void create(String id, String name, String phone, String email) {
		System.out.println("Create "+id+", "+name+", "+phone+", "+email);
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Contact c = new Contact(name,phone, email);
		Address a = new Address("a","a","a","a");
		c.setAdd(a);
		s.persist(c);
		s.getTransaction().commit();
	}
	
	public void read(Long id) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Contact createdContact=(Contact) s.load(Contact.class,
				id);
		System.out.println("Search "+id+" "+createdContact.getFirstName());
	}
	
	public void update(String id) {
		System.out.println("Update "+id);
	}
	
	public void delete(String id) {
		System.out.println("Delete "+id);
	}

}
