package entities;

import java.util.HashSet;
import java.util.Set;

public class Contact {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Address add;
	private Set<ContactGroup> books;
	private Set<PhoneNumber> profiles;
	private UserAccount user;
	private int version;
	
	public Contact(){
		super();
		books = new HashSet<ContactGroup>();
		profiles = new HashSet<PhoneNumber>();
	}
	
	public Contact(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.books = new HashSet<ContactGroup>();
		this.profiles = new HashSet<PhoneNumber>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAdd() {
		return add;
	}
	
	public void setAdd(Address add) {
		this.add = add;
	}
	
	public Set<PhoneNumber> getProfiles() {
		return profiles;
	}
	
	
	public void setProfiles(Set<PhoneNumber> profiles) {
		this.profiles = profiles;
	}
	
	public Set<ContactGroup> getBooks() {
		return books;
	}
	
	
	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}
	
	public UserAccount getUser() {
		return user;
	}
	public void setUser(UserAccount user) {
		this.user = user;
	}

}
