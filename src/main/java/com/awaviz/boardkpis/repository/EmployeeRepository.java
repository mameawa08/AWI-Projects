package com.awaviz.boardkpis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.awaviz.boardkpis.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
