package fr.epita.rohit.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.rohit.dao.StudentDao;
import fr.epita.rohit.model.Student;


/**
 * 
 * @author basnet
 *
 */

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	
	@Autowired
	SessionFactory session;
	
	/**
	 * Return list
	 */
	@SuppressWarnings("unchecked")
	public List<Student> list() {
	return session.getCurrentSession().createQuery("from Student").list();
		
	}
/**
 * Delete existing Student
 */
	public boolean delete(Student student) {
		try{
			session.getCurrentSession().delete(student);
		}catch(Exception ex){
			return false;	
		}
		return true;
	}

	/**
	 * Save new Student details
	 */
	public boolean saveOrUpdate(Student student) {
		session.getCurrentSession().saveOrUpdate(student);
		return true;
	}

}
