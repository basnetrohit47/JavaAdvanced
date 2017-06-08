package fr.epita.basnet.hibertests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import fr.epita.basnet.Services.Dao;

import fr.epita.basnet.model.Address;
import fr.epita.basnet.model.Student;

/**
 * Test class to test DAOs 
 * @author Basnet ROhit
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})

public class TestHiber {
	
	private static final Logger LOGGER = LogManager.getLogger(TestHiber.class);
	
	
	@Inject
	Dao <Student>dao;
	@Inject
	Dao <Address>daoA;
	
	/**
	 * Testing Student Dao Methods
	 */
	@Test
	public void testStudent(){
	    
		Address address=new Address(101,"paris","droncy");	
		Student student=new Student("rohit","gmail",123,address);
		
		

		daoA.write(address);
		List<Address>Listad=daoA.searchBy("paris");
		Assert.assertTrue(Listad!=null && !Listad.isEmpty());
		LOGGER.info("Tested Write Method & searchBy from AddressDao",address);
		
		
		
		
		dao.write(student);
		List<Student>l=dao.listall();
		//Address ad=l.get(0).getAddress();
		//Assert.assertTrue(ad!=null);
		Assert.assertTrue(!l.isEmpty());
		LOGGER.info("Tested Listall & Write Methods",student);
		
		
		
	    student.setName("basnet");
	    dao.update(student);
	    List<Student>la=dao.listall();
	    String name=la.get(0).getName();
	    assertEquals(name,"basnet");
		LOGGER.info("Tested Update Method",student);
		
		//delete testing
		dao.delete(student);
		List<Student>lao=dao.listall();
		Assert.assertTrue(lao.isEmpty());
		LOGGER.info("Tested Delete Method",student);
		
		
	}
	
	/**
	 * To test AddressDao 
	 */
	@Test
	public void TestAddress(){
		Address address=new Address(102,"NICE","JANE");
		
		daoA.write(address);
		List<Address>All_Result=daoA.listall();
		Assert.assertTrue(!All_Result.isEmpty());
		LOGGER.info("Tested Write and ListAll Methods",address);
		
		address.setCity("LYON");
		daoA.update(address);
		List<Address>Result=daoA.searchBy("LYON");
		Assert.assertTrue(Result!=null && !Result.isEmpty());
		LOGGER.info("Tested Update and SearchBy Methods",address);
		
		daoA.delete(address);
		List<Address>All_Resulta=daoA.listall();
		Assert.assertTrue(All_Resulta.isEmpty());
		LOGGER.info("Tested Delete Method",address);
		
	}
	
	
	
	
	
}
