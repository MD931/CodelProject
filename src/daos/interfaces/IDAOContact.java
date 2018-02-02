package daos.interfaces;

import java.util.List;

import entities.Contact;

public interface IDAOContact extends IDAO<Contact> {
	public List<Contact> findByLastName(String lastName);
	public List<Contact> findByFirstName(String firstName);
	public List<Contact> findByEmail(String email);
	public List<Contact> getAllContacts(int firstResult, int maxResults);
	public List<Contact> findContactByName(String[] tokens);
	
}
