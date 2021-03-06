package services;

import java.util.Set;

import org.hibernate.StaleObjectStateException;

import daos.interfaces.IDAOEntreprise;
import entities.Address;
import entities.Contact;
import entities.ContactGroup;
import entities.Entreprise;
import entities.PhoneNumber;
import util.ResponseTools;

public class EntrepriseServices {
	private IDAOEntreprise dao;
	
	public EntrepriseServices() {}
	
	public EntrepriseServices(IDAOEntreprise dao) {
		this.dao = dao;
	}
	
	public int create(String firstname, String lastname, String email, Address address, 
			Set<PhoneNumber> phones, Set<ContactGroup> books, Integer numSiret) {
		Entreprise e = new Entreprise(firstname, lastname, email, numSiret);
		e.setAdd(address);
		e.setProfiles(phones);
		e.setBooks(books);
		phones.forEach(phone ->{
			phone.setContact(e);
		});
		books.forEach(book ->{
			book.addContact(e);
		});
		dao.create(e);
		return ResponseTools.SUCCESS;

	}
	
	public Entreprise read(Long id) {
		return null;
	}
	
	public int update(Entreprise entreprise, String firstName, String lastName, String email
			,String street, String city, String zip, String country, Integer numSiret) {
		try {
			entreprise.setFirstName(firstName);
			entreprise.setLastName(lastName);
			entreprise.setEmail(email);
			entreprise.getAdd().setStreet(street);
			entreprise.getAdd().setCity(city);
			entreprise.getAdd().setZip(zip);
			entreprise.getAdd().setCountry(country);
			entreprise.setNumSiret(numSiret);
			dao.update(entreprise);
			return ResponseTools.SUCCESS;
		} catch (StaleObjectStateException e) {
			return ResponseTools.VERSION_ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseTools.MAIN_ERROR;
		}
	}
	
	public int delete(Entreprise entity) {
		try{
			dao.delete(entity);
			return ResponseTools.SUCCESS;
		}catch(IllegalArgumentException e) {
			return ResponseTools.NOT_EXIST;
		}catch (Exception e) {
			return ResponseTools.MAIN_ERROR;
		}
	}
}
