package daos;

public class DAOContact {
	
	public void create(String id, String name, String phone, String email) {
		System.out.println("Create "+id+", "+name+", "+phone+", "+email);
	}
	
	public void read(String name) {
		System.out.println("Search "+name);
	}
	
	public void update(String id) {
		System.out.println("Update "+id);
	}
	
	public void delete(String id) {
		System.out.println("Delete "+id);
	}

}
