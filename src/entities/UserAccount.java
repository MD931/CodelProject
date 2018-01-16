package entities;


/**
 * This entity presents the persistent data of a User account 
 *
 */
public class UserAccount {
	
	private Long idUser;
	private String username;
	private String password;
	
	
	
	public UserAccount(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public UserAccount(){
		
	}
	
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", password=" + password + " ]";
	}
	
	
	
	

}
