package com.windy.webcrud.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.windy.webcrud.dao.EmployeeDAO;
import com.windy.webcrud.service.EmployeeService;
import com.windy.webcrud.vo.EmployeeVO;

@Controller
public class EmployeeController  {
	
	@Autowired
	EmployeeDAO employeeDAO;

	@Autowired
	EmployeeService employeeService;
	
	public EmployeeController() {
		System.out.println("Controller !!!!");
	}
	
	@RequestMapping(path="/emps",method=RequestMethod.GET)
	public String getEmployeeList(HashMap map){
		List  list=employeeService.getEmployeeList();
		map.put("employeeList",list);
		return "list";
	}
	
	@RequestMapping(path="/emp",method=RequestMethod.GET)
	public String getEmployeeVOOne(HashMap hashmap, EmployeeVO employeeVO,@RequestParam(name="eid",required=false) String eid){
		if(eid!=null){
			hashmap.put("employee",employeeVO);
		}else{
			hashmap.put("employee",new EmployeeVO());
		}
		hashmap.put("departments", employeeDAO.getDepartments().values())  ;
		//hashmap.put("departments" ,new HashMap())  ;
//
		hashmap.put("genders", GendersUtil());
		//hashmap.put("genders", new HashMap());		
		return "detail";
	}
	
	@RequestMapping(path="/emp/{eid}",method=RequestMethod.GET)
	public String getEmployeeVO(HashMap hashmap,EmployeeVO employeeVO,@PathVariable("eid") String eid){

		hashmap.put("employee", employeeDAO. getEmployeeVO(eid) );

		hashmap.put("departments", employeeDAO. getDepartments().values())  ;

		hashmap.put("genders", GendersUtil());
	
		return "edit";
	}
	
	
	
	
	//@ModelAttribute
	public void getModelVO( Map map,@RequestParam(name="eid" ,required=false )String eid){
		if(eid!=null){
			EmployeeVO employeeVO=employeeDAO. getEmployeeVO(eid);
			map.put("employeeVO", employeeVO);
		}
	}
	
	@RequestMapping(path="/emp",method=RequestMethod.PUT)
	public String updateEmployeeVO(EmployeeVO employeeVO,Map map){
		  employeeDAO .saveEmployee(employeeVO);  
		  return "redirect:/emps";
	}
	
 
	@RequestMapping(path="/emp",method=RequestMethod.POST)
	public String saveEmployeeVO(@Valid EmployeeVO employeeVO ,BindingResult binderResult,Map map){
		if(binderResult.hasFieldErrors()){
			  List<FieldError> fieldErrors = binderResult.getFieldErrors();
			 for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getCode()+"\t"+fieldError.getField()+"\t"+fieldError.getDefaultMessage());
			 }
			 map.put("employeeVO", employeeVO);
			 map.put("departments", employeeDAO.getDepartments().values())  ;
			 map.put("genders", GendersUtil());
			 return "detail";
		}
		  employeeDAO .saveEmployee(employeeVO);  
		  System.out.println(employeeVO.toString());
		  return "redirect:/emps";
	}
	
	@RequestMapping(path="/empConvert",method=RequestMethod.POST)
	public String saveFromString(@RequestParam("employee") EmployeeVO employeeVO,Map map){
		  employeeDAO .saveEmployee(employeeVO);  
		  return "redirect:/emps";
	}
	
	
	@RequestMapping(path="/emp",method=RequestMethod.DELETE)
	public String delete( @RequestParam(name="eid",required=false) String eid ){
		employeeDAO .deleteEmployee(eid);  
		return "redirect:/emps";
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder webDataBinder){
//		webDataBinder.setDisallowedFields("age");
//	}

	public Map GendersUtil(){
		HashMap map=new HashMap();
		map.put("0", "male");
		map.put("1", "female");
		return map;
		
	}
}
