package jsf.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.ContactGroup;
import services.ContactGroupServices;
import util.ResponseTools;

@ManagedBean(name="listGroup")
@ViewScoped
public class ListGroup implements Serializable{
	List<ContactGroup> contactGroups;
	ContactGroup selectedGroup;
	ContactGroupServices cgs;

	@PostConstruct
	public void init(){
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		cgs = (ContactGroupServices) context.getBean("contactGroupServices");
		contactGroups = cgs.getAllContactGroup();
	}

	public void supprimerGroup() {
		if(cgs.delete(selectedGroup) == ResponseTools.SUCCESS) {
			contactGroups.remove(selectedGroup);
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erreur lors de la suppression"));
		}
	}

	public List<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(List<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

	public ContactGroup getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(ContactGroup selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public void editGroup() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(!selectedGroup.getGroupName().isEmpty()) {
			if(cgs.update(selectedGroup) == ResponseTools.SUCCESS) {
				context.addMessage(null, new FacesMessage("Suppression réussie"));
			}else {
				context.addMessage(null, new FacesMessage("Erreur lors de la suppression"));
			}
		}else {
			context.addMessage(null, new FacesMessage("Champs vide"));
			contactGroups = cgs.getAllContactGroup();
		}
	}
	
	
	
	


}
