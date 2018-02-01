package util;

import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;

import entities.Address;
import entities.Contact;
import entities.PhoneNumber;

/**
 * 
 * This class is used by Services classes to verify their data
 * provided from Servlets forms
 *
 */
public class FieldValidator {
	public  int createContact(ProceedingJoinPoint pjp,String firstname,String lastname,String email,Address address,
			Set<PhoneNumber> phones) throws Throwable {
		System.out.println("aspect : createContact");
		if(firstname == null || !firstname.matches("^[a-zA-Z]{2,}$")){
			return ResponseTools.FIRSTNAME_ERROR;
		}
		if(lastname == null || !lastname.matches("^[a-zA-Z]{2,}$")){
			return ResponseTools.LASTNAME_ERROR;
		}
		if(email == null || !email.matches("^[A-Za-z][A-Za-z0-9._-]+@[A-Za-z0-9-_]+\\.?[A-Za-z0-9-_]+\\.[A-Za-z]{2,6}$")){
			return ResponseTools.EMAIL_ERROR;
		}
		if(address.getStreet()== null || !address.getStreet().matches("^.{2,}$")){
			return ResponseTools.STREET_ERROR;
		}
		if(address.getCity() == null || !address.getCity().matches("^[A-Za-z ]{2,}$")){
			return ResponseTools.CITY_ERROR;
		}

		if(address.getZip() == null || !address.getZip().matches("^[1-9][0-9]{4}$")){
			return ResponseTools.ZIP_ERROR;
		}
		if(address.getCountry() == null || !address.getCountry().matches("^[A-Za-z ]{2,}$")){
			return ResponseTools.COUNTRY_ERROR;
		}
		
		for(PhoneNumber ph : phones) {
			if(ph.getPhoneKind()==null || !ph.getPhoneKind().matches("^[A-Za-z ]{2,}$")) {
				return ResponseTools.PHONE_KIND_ERROR;
			}
			if(ph.getPhoneNumber()==null || !ph.getPhoneNumber().matches("^0[0-9]{9}$")) {
				return ResponseTools.PHONE_NUMBER_ERROR;

			}
		}
		return (int)pjp.proceed();
		
	}
	
	public int createEntreprise(ProceedingJoinPoint pjp,String firstname,String lastname,
			String email,Address address,Set<PhoneNumber>phones,Integer numSiret) throws Throwable {
		System.out.println("aspect : createEntreprise");
		if(firstname == null || !firstname.matches("^[a-zA-Z]{2,}$")){
			return ResponseTools.FIRSTNAME_ERROR;
		}
		if(lastname == null || !lastname.matches("^[a-zA-Z]{2,}$")){
			return ResponseTools.LASTNAME_ERROR;
		}
		if(email == null || !email.matches("^[A-Za-z][A-Za-z0-9._-]+@[A-Za-z0-9-_]+\\.?[A-Za-z0-9-_]+\\.[A-Za-z]{2,6}$")){
			return ResponseTools.EMAIL_ERROR;
		}
		if(address.getStreet()== null || !address.getStreet().matches("^.{2,}$")){
			return ResponseTools.STREET_ERROR;
		}
		if(address.getCity() == null || !address.getCity().matches("^[A-Za-z ]{2,}$")){
			return ResponseTools.CITY_ERROR;
		}

		if(address.getZip() == null || !address.getZip().matches("^[1-9][0-9]{4}$")){
			return ResponseTools.ZIP_ERROR;
		}
		if(address.getCountry() == null || !address.getCountry().matches("^[A-Za-z ]{2,}$")){
			return ResponseTools.COUNTRY_ERROR;
		}
		
		if(numSiret== null || !numSiret.toString().matches("^[0-9]{5,}$")){
			return ResponseTools.SIRET_ERROR;
		}
		
		for(PhoneNumber ph : phones) {
			if(ph.getPhoneKind()==null || !ph.getPhoneKind().matches("^[A-Za-z ]{2,}$")) {
				return ResponseTools.PHONE_KIND_ERROR;
			}
			if(ph.getPhoneNumber()==null || !ph.getPhoneNumber().matches("^0[0-9]{9}$")) {
				return ResponseTools.PHONE_NUMBER_ERROR;

			}
		}
		return (int)pjp.proceed();
	}
	public int updateContact(ProceedingJoinPoint pjp,Contact c ,String firstName,String lastName,String email,
			String street,String city,String zip,String country) throws Throwable {
		System.out.println("aspect : updateContact++++++++++++++++++++++++++++++++++++++++++++++++++++");
		if(firstName == null || !firstName.matches("^[a-zA-Z]{2,}$")){
			return ResponseTools.FIRSTNAME_ERROR;
		}
		if(lastName == null || !lastName.matches("^[a-zA-Z]{2,}$")){
			return ResponseTools.LASTNAME_ERROR;
		}
		if(email == null || !email.matches("^[A-Za-z][A-Za-z0-9._-]+@[A-Za-z0-9-_]+\\.?[A-Za-z0-9-_]+\\.[A-Za-z]{2,6}$")){
			return ResponseTools.EMAIL_ERROR;
		}
		if(street== null || !street.matches("^.{2,}$")){
			return ResponseTools.STREET_ERROR;
		}
		if(city == null || !city.matches("^[A-Za-z ]{2,}$")){
			return ResponseTools.CITY_ERROR;
		}

		if(zip == null || !zip.matches("^[1-9][0-9]{4}$")){
			return ResponseTools.ZIP_ERROR;
		}
		if(country == null || !country.matches("^[A-Za-z ]{2,}$")){
			return ResponseTools.COUNTRY_ERROR;
		}
		for(PhoneNumber ph : c.getProfiles()) {
			System.out.println(ph.getPhoneKind()+" "+ph.getPhoneNumber());
			if(ph.getPhoneKind()==null || !ph.getPhoneKind().matches("^[A-Za-z ]{2,}$")) {
				return ResponseTools.PHONE_KIND_ERROR;
			}
			if(ph.getPhoneNumber()==null || !ph.getPhoneNumber().matches("^0[0-9]{9}$")) {
				return ResponseTools.PHONE_NUMBER_ERROR;

			}
		}
		
		return (int)pjp.proceed();
		
		
	}

}
