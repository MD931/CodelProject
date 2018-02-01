package daos.interfaces;

import java.util.List;

import entities.Contact;

public interface IDAOContact extends IDAO<Contact> {
	public List<Contact> getContactsBylastName(String lastName);
	public List<Contact> getContactsByfirstName(String firstName);
	public List<Contact> getAllContacts(int firstResult, int maxResults);
	public List<Contact> findContactByName(String[] tokens);
}
