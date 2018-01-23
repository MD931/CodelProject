package daos.interfaces;

import entities.Contact;
import entities.UserAccount;

public interface IDAOContact {
	public void create(String id, String name, String phone, String email, UserAccount user);
	public Contact read(Long id);
	public void update(Long id, String firstName, String lastName, String email);
	public void delete(String id);
}
