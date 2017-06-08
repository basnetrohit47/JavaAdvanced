package fr.epita.rohit.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import fr.epita.rohit.model.Student;
import fr.epita.rohit.services.StudentServices;

/**
 * this is Controller class
 * @author Basnet
 *
 */

@Controller
public class StudentController {
	
	@Autowired
	StudentServices sv;
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return UserTable view if Username and Password is Correct otherwise Return into Welcome view
	 */
	@RequestMapping(value="/student",method=RequestMethod.POST)
	public String Login(@RequestParam("Username")String username,@RequestParam("Password")String password){
		
		
		
		String user=username;
		String pass=password;
		
		if(user.equals("root")&& pass.equals("admin")){
			return "UserTable";
		}
		else{
			
			return "index";
		}
	}
	
	


	
		
	
     
	/**
	 * Delete given student
	 * @param student
	 */
	 @RequestMapping(value = "/Delete", method = RequestMethod.POST)	
	 public  @ResponseBody void Delete( @RequestBody Student student )   {		
            
		 sv.delete(student);
	 	
	 }
	 
	
	 
	 
  /**
   * Delete Save Or Update Student
   * @param student
   */
	 @RequestMapping(value = "/save", method = RequestMethod.POST)	
	 public  @ResponseBody void saveCompany_JSON( @RequestBody Student student )   {		
            	 
		 sv.saveOrUpdate(student);
		 System.out.println(student.getFullname()+student.getEmail()+student.getUsername());
	 	
	 }
	 
	 
	/**
	 * 
	 * @param student
	 * @return all  Student
	*/
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object>getAll(Student student){
		Map<String,Object>map=new HashMap<String,Object>();
		List<Student>list=sv.list();
		if(list!=null){
			map.put("status", "200");
			map.put("message", "Data found");
			map.put("data", list);
			
		}
		else{
			map.put("status", "404");
			map.put("message", "data not found");
		}
		return map;
	}
	 
	
	
	
	
}
