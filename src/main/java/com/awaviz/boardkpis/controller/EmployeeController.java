package com.awaviz.boardkpis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
