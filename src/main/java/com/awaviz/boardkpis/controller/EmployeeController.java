package com.awaviz.boardkpis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awaviz.boardkpis.exception.ResourceNotFoundException;
import com.awaviz.boardkpis.model.Employee;
import com.awaviz.boardkpis.repository.EmployeeRepository;

@RestController
@RequestMapping("awapi/v1")
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("employees")
	public List<Employee> getAllEmployees(){
		return this.employeeRepository.findAll();
	}

	public EmployeeRepository getEmployeerepository() {
		return employeeRepository;
	}

	public void setEmployeerepository(EmployeeRepository employeerepository) {
		this.employeeRepository = employeerepository;
	}
	
	// Create rest API
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// Get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow (()-> new ResourceNotFoundException("Cette personne ne travaille pas chez nous" + id ));
		return ResponseEntity.ok(employee);
	}
	
	// Update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		System.out.println("Vision" + id);
		Employee employee = employeeRepository.findById(id)
				.orElseThrow (()-> new ResourceNotFoundException("Cette personne ne travaille pas chez nous" + id ));
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
		
	}
	
	
	//Delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow (()-> new ResourceNotFoundException("Cette personne ne travaille pas chez nous" + id ));
		
		employeeRepository.delete(employee);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
