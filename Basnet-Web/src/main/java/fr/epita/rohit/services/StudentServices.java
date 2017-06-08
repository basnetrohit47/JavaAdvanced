package fr.epita.rohit.services;

import java.util.List;

import fr.epita.rohit.model.Student;

/**
 * Services Interface
 * @author basnet
 *
 */
public interface StudentServices {
	public List<Student>list();
	public boolean delete(Student student);
	public boolean saveOrUpdate(Student student);
	
}
