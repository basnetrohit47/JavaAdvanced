package fr.epita.basnet.Services;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fr.epita.basnet.model.Student;

@Repository
public class Hiberdao implements Dao<Student>{
	private static final Logger LOGGER = LogManager.getLogger(Hiberdao.class);
	
	
	@Inject
	SessionFactory sf;
	
	
	public void write(Student student){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(student);
		transaction.commit();
		session.close();
	}
	
	public void delete(Student student){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(student);
		transaction.commit();
		session.close();
	}
	
	public void update(Student student){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(student);
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Student>listall(){
		Session session=sf.openSession();
		List<Student>StList=session.createQuery("from Student").list();
		
		session.close();
		return StList;
	}
	@SuppressWarnings("unchecked")
	public List<Student>searchBy(String adrs){
		Session session=sf.openSession();
		Query qr=session.createQuery("From Address AS address Where address.City like:City");
		qr.setParameter("City","%" +adrs);
		List<Student>StList=qr.list();
		session.close();
		return StList;
		
		

	}
}
