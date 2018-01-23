package daos;

import org.hibernate.Session;

import daos.interfaces.IDAOContact;
import entities.Address;
import entities.Contact;
import util.HibernateUtil;

public class DAOContact /*extends HibernateDaoSupport*/  implements IDAOContact{
	
	public void create(String id, String name, String phone, String email) {
		System.out.println("Create "+id+", "+name+", "+phone+", "+email);
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) s.beginTransaction();
		Contact c = new Contact(name,phone, email);
		Address a = new Address("a","a","a","a");
		c.setAdd(a);
		s.persist(c);
		s.getTransaction().commit();
	}
	
	public Contact read(Long id) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) s.beginTransaction();
		Contact createdContact=(Contact) s.load(Contact.class,
				id);
		
		System.out.println("Search "+id+" "+createdContact.getFirstName());
		return createdContact;
	}
	
	public void update(Long id, String firstName, String lastName, String email) {
		System.out.println("Update "+id);
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) s.beginTransaction();
		Contact createdContact=(Contact) s.load(Contact.class,
				id);
		createdContact.setFirstName(firstName);
		createdContact.setLastName(lastName);
		createdContact.setEmail(email);
		s.getTransaction().commit();
	}
	
	public void delete(String id) {
		System.out.println("Delete "+id);
	}
	
	public void createContact(Contact contact) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) s.beginTransaction();
		s.persist(contact);
		s.getTransaction().commit();
	}

}
