package services;

import daos.DAOUser;
import util.FieldValidator;

public class UserServices {

	public static boolean loginUser(String usernameORemail, String password){
		return false;
	}
	
	public static boolean registerUser(String username, String password){
		if(FieldValidator.validateRegistration(username, password)){
			return DAOUser.createAccount(username, password);
		}
		return false;
	}

}
