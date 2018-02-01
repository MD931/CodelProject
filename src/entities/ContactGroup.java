package entities;

import java.util.HashSet;
import java.util.Set;

public class ContactGroup {
	private Long groupId;
	private String groupName;
	private Set<Contact> contacts;
	private int version;
	
	public ContactGroup() {
		super();
		this.contacts = new HashSet<Contact>();
	}
	public ContactGroup(String groupName) {
		super();
		this.groupName = groupName;
		this.contacts = new HashSet<Contact>();
	}
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
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
	
	public void addContact(Contact contact) {
		contacts.add(contact);
	}
	
	public void removeContact(Contact contact){
		contacts.remove(contact);
		if(contact.getBooks().contains(this)){
			contact.getBooks().remove(this);
		}
	}
}
