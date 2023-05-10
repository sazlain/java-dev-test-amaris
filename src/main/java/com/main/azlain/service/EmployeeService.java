package com.main.azlain.service;

import org.springframework.stereotype.Service;

import com.main.azlain.response.GeneralResponse;

@Service
public interface EmployeeService {

	public GeneralResponse getById(int employeeId);
	
	public GeneralResponse getAll();
}
