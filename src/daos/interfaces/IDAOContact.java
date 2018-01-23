package daos.interfaces;

import entities.Contact;

public interface IDAOContact {
	public void create(String id, String name, String phone, String email);
	public Contact read(Long id);
	public void update(Long id, String firstName, String lastName, String email);
	public void delete(String id);
	public void createContact(Contact contact);
}
