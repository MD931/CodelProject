package services;

import daos.DAOUser;
import entities.UserAccount;
import util.FieldValidator;

public class UserServices {
	
	final static DAOUser dao = new DAOUser();
	
	public static boolean loginUser(String usernameORemail, String password){
		return false;
	}
	
	public static boolean registerUser(String username, String password){
		if(FieldValidator.validateRegistration(username, password)){
			return DAOUser.createAccount(username, password);
		}
		return false;
	}
	
	public static UserAccount read(Long id) {
		return dao.read(id);
	}

}
