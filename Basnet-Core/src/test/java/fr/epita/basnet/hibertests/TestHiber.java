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



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})

public class TestHiber {
	
	private static final Logger LOGGER = LogManager.getLogger(TestHiber.class);
	
	
	@Inject
	Dao <Student>dao;
	@Inject
	Dao <Address>daoA;
	@Test
	public void testhiber(){
		
		
		
		
		
		Address address=new Address(101,"paris","droncy");
		
		Student student=new Student("rohit","gmail",123,address);
		
		
		//inserting in address table
		daoA.write(address);
		LOGGER.info("address inserted");
		//search
		List<Address>Listad=daoA.searchBy("paris");
		System.out.println(Listad.get(0).getStreet());
		Assert.assertTrue(Listad!=null && !Listad.isEmpty());
		
		
		
		
		//Insert testing
		dao.write(student);
		List<Student>l=dao.listall();
		
		//List<Address>lad=daoA.listall();
		Address ad=l.get(0).getAddress();
		System.out.println(ad.getId());
		Assert.assertTrue(ad!=null);
		Assert.assertTrue(!l.isEmpty());
		
		
		
		//update testing
	    student.setName("basnet");
	    dao.update(student);
	    List<Student>la=dao.listall();
	    String name=la.get(0).getName();
	   
	    assertEquals(name,"basnet");
		
		//delete testing
		dao.delete(student);
		
		List<Student>lao=dao.listall();
		Assert.assertTrue(lao.isEmpty());
		
		
	}
	
	
	@Test
	public void TestAddress(){
		Address address=new Address(102,"NICE","JANE");
		
		daoA.write(address);
		List<Address>All_Result=daoA.listall();
		Assert.assertTrue(!All_Result.isEmpty());
		
		address.setCity("LYON");
		daoA.update(address);
		List<Address>Result=daoA.searchBy("LYON");
		Assert.assertTrue(Result!=null && !Result.isEmpty());
	}
	
	
	
	
	
}
