package com.main.azlain.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.azlain.dto.Employee;
import com.main.azlain.exception.MainAzlainException;

@Service
public interface EmployeeDao {

	public Employee getById(int employeeId)  throws MainAzlainException;
	
	public List<Employee> getAll() throws MainAzlainException;
	
}
