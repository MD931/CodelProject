package daos;

import entities.UserAccount;

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
	
	public static boolean connectToAccount(String username,String password){
		System.out.println("DAO USER");
		return true;
	}
}
