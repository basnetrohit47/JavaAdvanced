package fr.epita.basnet.Services;

import java.util.List;

/**
 * Interface for Multiple DAOs
 * @author Basnet Rohit
 * @param <T>
 */
public interface Dao <T> {

public void write(T instance);
public void update(T instance);
public void delete(T instance);
public List<T>listall();
public List<T>searchBy(String addrs);
	
}
