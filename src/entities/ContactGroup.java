package entities;

import java.util.HashSet;
import java.util.Set;

public class ContactGroup {
	private Integer groupId;
	private String groupName;
	private Set<Contact> contacts;
	
	public ContactGroup() {
		super();
		this.contacts = new HashSet<Contact>();
	}
	public ContactGroup(String groupName) {
		super();
		this.groupName = groupName;
		this.contacts = new HashSet<Contact>();
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Set<Contact> getContacts(){
		return contacts;
	}
	
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
}
