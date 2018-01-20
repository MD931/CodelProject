package daos;

import org.hibernate.Session;

import entities.Contact;
import entities.UserAccount;
import util.HibernateUtil;

public class DAOUser {

	public static boolean createAccount(String username,String password){
	UserAccount userAccount = new UserAccount();
	//Make userAccount persistent ( persist it )
	userAccount.setUsername(username);
	userAccount.setPassword(password);
	userAccount.toString();
	//commit transaction
		return true;
	}
	
	public UserAccount read(Long id) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!s.getTransaction().isActive()) s.beginTransaction();
		UserAccount user=(UserAccount) s.load(UserAccount.class,
				id);
		return user;
	}
	
	public static boolean connectToAccount(String username,String password){
		System.out.println("DAO USER");
		return true;
	}
}
