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

/**
 * Class for Store, Update ,Delete, List And search Action 
 * Implements Dao Interface
 * @author Basnet Rohit
 *
 */

@Repository
public class AddressDao implements Dao<Address> {

	
	private static final Logger LOGGER = LogManager.getLogger(AddressDao.class);
	
	
	@Inject
	SessionFactory sf;
	
	
	
	/**
	 * Insert Address Object 
	 * @param address
	 */
	public void write(Address address){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(address);
		transaction.commit();
		session.close();
		LOGGER.info("data inserted" ,address);
	}
	
	/**
	 * Delete Existing Address
	 * @param address
	 */
	public void delete(Address address){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(address);
		transaction.commit();
		session.close();
		LOGGER.info("data hase been deleted",address);
	}
	
	/**
	 * Update Existing Address 
	 * @param Address 
	 */
	
	public void update(Address address){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(address);
		transaction.commit();
		session.close();
		LOGGER.info("data hase been updated",address);

	}
	/**
	 * Get All Data from Address 
	 */
	@SuppressWarnings("unchecked")
	public List<Address>listall(){
		Session session=sf.openSession();
		List<Address>StList=session.createQuery("from Address").list();
		
		session.close();
		return StList;
	

	}
	/**
	 * Search Address by City 
	 * @param Address 
	 */
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
