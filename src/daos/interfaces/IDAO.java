package daos.interfaces;

import entities.Contact;

public interface IDAO<T>{
	public void create(T entity);
	public T read(Long id);
	public void update(T entity);
	public void delete(Long id);
	public T load(Long id); 
}
