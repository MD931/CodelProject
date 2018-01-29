package jsf.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="login")
public class LoginAccess implements Serializable {
	private String login, password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String controlLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(login.isEmpty() || password.isEmpty()) {
			context.addMessage(null, new FacesMessage("Un des champs est vide"));
			return null;
		}else if(login.equals(password)) {
			return ("main");
		}else {
			context.addMessage(null, new FacesMessage("Mauvais login"));
			return null;
		}
	}
}
