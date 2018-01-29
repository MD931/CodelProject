package jsf.beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.PhoneNumber;
import services.ContactGroupServices;
import services.ContactServices;

@ManagedBean(name="createGroup")
public class CreateGroup {
	
	String groupName;
	private ContactGroupServices cgs;

	
    @PostConstruct
    public void init(){
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		cgs = (ContactGroupServices) context.getBean("contactGroupServices");
    }
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String create() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(groupName.isEmpty()) {
			context.addMessage("ngroup", new FacesMessage("Group name required"));
			return null;
		}
		cgs.create(groupName);
		return null;
	}
}
