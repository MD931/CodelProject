package services;

import java.util.List;
import java.util.Set;

import org.hibernate.StaleObjectStateException;

import daos.interfaces.IDAOContact;
import entities.Address;
import entities.Contact;
import entities.ContactGroup;
import entities.PhoneNumber;
import util.ResponseTools;

public class ContactServices {

	private IDAOContact dao;

	public ContactServices() {
	}

	public ContactServices(IDAOContact dao) {
		this.dao = dao;
	}

	public int create(String firstname, String lastname, String email, Address address, Set<PhoneNumber> phones,
			Set<ContactGroup> books) {
		Contact c = new Contact(firstname, lastname, email);
		c.setAdd(address);
		c.setProfiles(phones);
		c.setBooks(books);
		phones.forEach(phone -> {
			phone.setContact(c);
		});
		books.forEach(book -> {
			book.addContact(c);
		});
		dao.create(c);
		return ResponseTools.SUCCESS;
	}

	public Contact read(Long id) {
		return dao.read(id);
	}

	public int update(Contact c, String firstName, String lastName, String email, String street, String city,
			String zip, String country) {
		try {
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setEmail(email);
			c.getAdd().setStreet(street);
			c.getAdd().setCity(city);
			c.getAdd().setZip(zip);
			c.getAdd().setCountry(country);
			dao.update(c);
			return ResponseTools.SUCCESS;
		} catch (StaleObjectStateException e) {
			return ResponseTools.VERSION_ERROR;
		}
	}

	public int delete(Contact entity) {
		try {
			dao.delete(entity);
			return ResponseTools.SUCCESS;
		} catch (IllegalArgumentException e) {
			return ResponseTools.NOT_EXIST;
		} catch (Exception e) {
			return ResponseTools.MAIN_ERROR;
		}
	}

	public int populate(Contact entity) {
		try {
			dao.create(entity);
			return ResponseTools.SUCCESS;
		} catch (StaleObjectStateException e) {
			return ResponseTools.VERSION_ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseTools.MAIN_ERROR;
		}
	}

	public List<Contact> getAllContacts(int firstResult, int maxResults) {
		return dao.getAllContacts(firstResult, maxResults);
	}

	public List<Contact> searchContacts(String fullName) {
		String[] tokens = fullName.trim().split(" ");
		return dao.findContactByName(tokens);
	}

	public List<Contact> searchByField(String search, String field) {
		if (search.length() > 0) {
			switch (field) {
			case "lastname":
				return dao.findByLastName(search.trim());
			case "firstname":
				return dao.findByFirstName(search.trim());
			case "email":
				return dao.findByEmail(search.trim());
			default:
				return searchContacts(search.trim());
			}
		}
		return null;
	}

}
