package com.main.azlain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.azlain.common.StatusResponse;
import com.main.azlain.exception.MainAzlainException;
import com.main.azlain.response.GeneralResponse;
import com.main.azlain.service.EmployeeService;
import com.main.azlain.validator.EmployeeRequestValidator;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRequestValidator employeeRequestValidator;
	
	@GetMapping(value="/employee", produces = "application/json")
	 public ResponseEntity<String> getEmployee(@RequestParam(required=false) String id){
		
		try {
			employeeRequestValidator.validate(id);
			GeneralResponse response = employeeService.getById(Integer.parseInt(id));
			return new ResponseEntity<>(response.toString(), response.getStatusCode());
		} catch (MainAzlainException e) {
			GeneralResponse response = new GeneralResponse();
			response.setStatus(StatusResponse.ERROR.toString());
			response.setMessage(e.getError().getMessage());
			
			return new ResponseEntity<>(response.toString(), e.getError().getHttpStatus());
		}
		
		
		  
	 }
	
	@GetMapping(value="/employees", produces = "application/json")
	 public ResponseEntity<String> getAllEmployees(){
		GeneralResponse response = employeeService.getAll();
		return new ResponseEntity<>(response.toString(), response.getStatusCode());
		  
	 }
	
}
