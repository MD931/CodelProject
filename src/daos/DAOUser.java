package daos;

import org.hibernate.Session;

import entities.Contact;
import entities.UserAccount;
import util.HibernateUtil;

public class DAOUser {

	public static void createAccount(String username,String password){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) 
			s.beginTransaction();
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		s.persist(userAccount);
		s.getTransaction().commit();
	}
	
	public static UserAccount read(Long id) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) s.beginTransaction();
		UserAccount user=(UserAccount) s.load(UserAccount.class,
				id);
		return user;
	}
	
	public static UserAccount getUserByUsername(String username) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) s.beginTransaction();
		UserAccount user = (UserAccount)
			    s.createQuery("from UserAccount where username = :username")
			           .setString("username", username)
			           .uniqueResult();
		return user;
	}
	
	public static UserAccount connectToAccount(String username,String password){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) s.beginTransaction();
		UserAccount user = (UserAccount)
			    s.createQuery("from UserAccount where username = :username and password = :password")
			           .setString("username", username)
			           .setString("password", password)
			           .uniqueResult();
		return user;
	}
	
	public static UserAccount updateAccount(String username,String password) {
		return null;
	}
}
