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


import fr.epita.basnet.model.Address;



@Repository
public class AddressDao implements Dao<Address> {

	
	private static final Logger LOGGER = LogManager.getLogger(AddressDao.class);
	
	
	@Inject
	SessionFactory sf;
	
	
	
	
	public void write(Address address){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(address);
		transaction.commit();
		session.close();
		LOGGER.info("data inserted" ,address);
	}
	
	public void delete(Address address){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(address);
		transaction.commit();
		session.close();
	}
	
	public void update(Address address){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(address);
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Address>listall(){
		Session session=sf.openSession();
		List<Address>StList=session.createQuery("from Address").list();
		
		session.close();
		return StList;
	}
	@SuppressWarnings("unchecked")
	public List<Address>searchBy(String adrs){
		Session session=sf.openSession();
		Query qr=session.createQuery("From Address AS address Where address.City like:City");
		qr.setParameter("City","%" +adrs);
		List<Address>StList=qr.list();
		session.close();
		return StList;
		
		

	}
	
}
