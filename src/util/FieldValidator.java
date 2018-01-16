package util;

/**
 * 
 * This class is used by Services classes to verify their data
 * provided from Servlets forms
 *
 */
public class FieldValidator {
	
	public static boolean validateLogin(String username,String password){
		if("".equals(password) || "".equals(username)) 
			return false;
		return true;
	} 
	
	public static boolean validateRegistration(String username,String password){
		if("".equals(password) || "".equals(username)) 
			return false;
		return true;
	}

}
