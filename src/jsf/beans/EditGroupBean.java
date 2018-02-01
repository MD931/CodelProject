package jsf.beans;

import java.io.Serializable;
import java.util.Map;

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

@ManagedBean(name="editGroup")
@ViewScoped
public class EditGroupBean implements Serializable{
	
	String groupeName;
	private ContactGroupServices cgs;
	ContactGroup contactGroup;
	private Long id;
	
	@PostConstruct
    public void init(){
		ApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getContext());
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		id = Long.parseLong(params.get("id"));
		
		cgs = (ContactGroupServices) context.getBean("contactGroupServices");
		contactGroup = cgs.read(id);
    }

	public ContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}
	
	public void renameGroup() {
		FacesContext context = FacesContext.getCurrentInstance();
		int result = cgs.update(contactGroup);
		if(result == ResponseTools.SUCCESS) {
			context.addMessage("success", new FacesMessage("Modification effectuée avec succès"));
		}else if(result == ResponseTools.VERSION_ERROR) {
			context.addMessage("error", new FacesMessage("Version erreur"));
		}else {
			context.addMessage("error", new FacesMessage("Echec de modification"));
		}
	}
	
	
	
}
