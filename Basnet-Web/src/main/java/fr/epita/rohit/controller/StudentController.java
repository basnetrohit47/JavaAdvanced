package fr.epita.rohit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.epita.rohit.model.Student;
import fr.epita.rohit.services.StudentServices;

/**
 * this is Controller class
 * @author basnet
 *
 */




@Controller

public class StudentController {
	
	
	//Student Services class
	@Autowired
	StudentServices sv;
	
	//login authentication
	@RequestMapping(value="/student",method=RequestMethod.POST)
	public String Login(@RequestParam("Username")String username,@RequestParam("Password")String password){
		
		
		
		String user=username;
		String pass=password;
		
		if(user.equals("root")&& pass.equals("admin")){
			return "UserTable";
		}
		else{
			
			return "Welcome";
		}
	}
	
	
	//welcome page
	//Return Welcome page when user request
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView gepage(){
		
		ModelAndView view=new ModelAndView("Login");
		return view; 
	}
	
	// return login page
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public ModelAndView login(){
		
		ModelAndView view=new ModelAndView("test");
		return view; 
	}

	

	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getSaved(Student student) {
        Map<String, Object> map = new HashMap<String, Object>();
 
        if (sv.saveOrUpdate(student)) {
            map.put("status", "200");
            map.put("message", "Your record have been saved successfully");
        }
        else{
        	System.out.println("badluck");
        }
        return map;
    }
 
	
	
	//GET method to return  all the data from student database as a list
	 @ResponseBody
	@RequestMapping(value="/listall",method=RequestMethod.GET)
	public List<Student>getall(Student student){
		List<Student>list=sv.list();
		
		return list;
		
	}
	
	 //POST method to delete the data in studnet database
	 @RequestMapping(value = "/Delete", method = RequestMethod.POST)	
	 public  @ResponseBody void Delete( @RequestBody Student student )   {		
            
		 sv.delete(student);
	 	
	 }
	 
	 
	 //POST method to insert or update data 
	 //binding data got from the form with Student model & send that data to save
	 @RequestMapping(value = "/save", method = RequestMethod.POST)	
	 public  @ResponseBody void saveCompany_JSON( @RequestBody Student student )   {		
            	 
		 sv.saveOrUpdate(student);
		 System.out.println(student.getFullname()+student.getEmail()+student.getUsername());
	 	
	 }
	 
	 
	
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
