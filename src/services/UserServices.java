package services;

import daos.DAOUser;
import entities.UserAccount;
import util.FieldValidator;

public class UserServices {
	
	final static DAOUser dao = new DAOUser();
	
	public static UserAccount loginUser(String username, String password){
		if(FieldValidator.validateLogin(username, password)) {
			return dao.connectToAccount(username, password); 
		}
		return null;
	}
	
	public static String registerUser(String username, String password,String confirmPassword){
		if(FieldValidator.validateRegistration(username, password,confirmPassword)){
			if(DAOUser.getUserByUsername(username)==null) {
				DAOUser.createAccount(username, password);
				return "success";
			} else 
				return "exists";
		}
		return "failed";
	}
	

}
