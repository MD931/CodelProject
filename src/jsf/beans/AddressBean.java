package jsf.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="addressBean")
public class AddressBean implements Serializable{
	
	String street;
	String city;
	Integer zip;
	String country;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
