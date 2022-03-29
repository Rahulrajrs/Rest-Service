package com.java.springboot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.dao.EmployeeDAO;
import com.java.springboot.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeDAO employeeDAO;
	@Autowired
	public EmployeeRestController(EmployeeDAO employeeDAO) {
		
		this.employeeDAO = employeeDAO;
	}
	
	//expose "/employees" and return list of employees
	
	@GetMapping("/employees")
	public List<Employee> finadAll(){
		
		
		return employeeDAO.findAll();
	}
	
	//add mapping for getemployee
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		
		Employee theEmployee=employeeDAO.findById(employeeId);
		
		if(theEmployee==null){
			throw new RuntimeException("Employee id not found");
		}
		return theEmployee;
	}
	
	//add mapping for POST/employees- add new employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee){
		
		//also jst in case they pass an id in JSON...set id 0
		// this is force to save of new items...instead of update
		theEmployee.setId(0);
		employeeDAO.save(theEmployee);
		return theEmployee;
		
		
	}
	
	//add mapping for PUT/employees-- update exsisting employees
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee){
		
		employeeDAO.save(theEmployee);
		return theEmployee;
	}
	
	//add mapping for delete/employees/{employee id} -delete employee id
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		
		Employee theEmployee=employeeDAO.findById(employeeId);
		// throw exception if null
		if(theEmployee==null){
			
			throw new RuntimeException("Employee id not found..."+employeeId);
		}
		employeeDAO.deleteById(employeeId);
		return "Delete employee id-"+employeeId;
	}
	
	
}
