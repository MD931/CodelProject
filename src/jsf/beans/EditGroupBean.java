package jsf.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	
	private ContactGroupServices cgs;
	ContactGroup contactGroup;
	private Long id;
	@ManagedProperty("#{msgs}")
	private ResourceBundle msgs;
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
	
	public String renameGroup() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(cgs.isExist(contactGroup.getGroupName())) {
			context.addMessage("error", new FacesMessage(msgs.getString("groupNameExisted")));
			return null;
		} else if (contactGroup.getGroupName().isEmpty()){
			context.addMessage("error", new FacesMessage(msgs.getString("errorGroupName")));
			return null;
		}else {
			int result = cgs.update(contactGroup);
			System.out.println(result);
			if(result == ResponseTools.SUCCESS) {
				context.addMessage("success", new FacesMessage(msgs.getString("updatedSucces")));
				return null;
			}else if(result == ResponseTools.VERSION_ERROR) {
				context.addMessage("error", new FacesMessage(msgs.getString("errorVersion")));
				return null;
			}else {
				context.addMessage("error", new FacesMessage(msgs.getString("errorUpdating")));
				return null;
			}
		}
	}

	public ResourceBundle getMsgs() {
		return msgs;
	}

	public void setMsgs(ResourceBundle msgs) {
		this.msgs = msgs;
	}
	
	
	
}
