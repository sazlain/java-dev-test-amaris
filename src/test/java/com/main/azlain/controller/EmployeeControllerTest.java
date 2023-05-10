package com.main.azlain.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.gson.Gson;
import com.main.azlain.AzlainDevTestApplication;
import com.main.azlain.common.StatusResponse;
import com.main.azlain.common.SupportMessages;
import com.main.azlain.dto.Employee;
import com.main.azlain.response.GetEmployeeResponse;
import com.main.azlain.response.GetEmployeesResponse;
import com.main.azlain.service.EmployeeService;
import com.main.azlain.validator.EmployeeRequestValidator;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AzlainDevTestApplication.class)
class EmployeeControllerTest {

	@Mock
	EmployeeService employeeService;
	
	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	SupportMessages messages;
	
	@Mock
	EmployeeRequestValidator employeeRequestValidator;
	
	@Test
	void ifReceiveAnValidEmployeeId() {
		
		Employee data = new Employee();
		data.setId(24);
		data.setEmployeeName("Tiger Nixon");
		data.setEmployeeSalary((long) 320800);
		data.setEmployeeAge(61);
		data.setProfileImage("");
		
		GetEmployeeResponse employeeMockResponse = new GetEmployeeResponse();
		employeeMockResponse.setStatus(StatusResponse.SUCCESS.toString());
		employeeMockResponse.setData(data);
		employeeMockResponse.setStatusCode(HttpStatus.OK);
		when(employeeService.getById(anyInt())).thenReturn(employeeMockResponse);
		
		ResponseEntity<String> response = employeeController.getEmployee("24");
		GetEmployeeResponse employeeResponse = new Gson().fromJson(response.getBody(), GetEmployeeResponse.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employeeResponse.getData(), employeeResponse.getData());
		
	}
	
	@Test
	void ifReceiveAnInvalidEmployeeId() {
		
		GetEmployeeResponse employeeMockResponse = new GetEmployeeResponse();
		employeeMockResponse.setStatus(StatusResponse.SUCCESS.toString());
		employeeMockResponse.setData(null);
		employeeMockResponse.setStatusCode(HttpStatus.OK);
		when(employeeService.getById(anyInt())).thenReturn(employeeMockResponse);
		
		ResponseEntity<String> response = employeeController.getEmployee("2400000");
		GetEmployeeResponse employeeResponse = new Gson().fromJson(response.getBody(), GetEmployeeResponse.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNull(employeeResponse.getData());
		
	}
	
	
	@Test
	void searchEmployeeList() {
		
		Employee data = new Employee();
		data.setId(24);
		data.setEmployeeName("Tiger Nixon");
		data.setEmployeeSalary((long) 320800);
		data.setEmployeeAge(61);
		data.setProfileImage("");
		
		List<Employee> empList = new ArrayList<>();
		empList.add(data);
		
		GetEmployeesResponse employeesMockResponse = new GetEmployeesResponse();
		employeesMockResponse.setStatus(StatusResponse.SUCCESS.toString());
		employeesMockResponse.setData(empList);
		employeesMockResponse.setStatusCode(HttpStatus.OK);
		when(employeeService.getAll()).thenReturn(employeesMockResponse);
		
		ResponseEntity<String> response = employeeController.getAllEmployees();
		GetEmployeesResponse employeesResponse = new Gson().fromJson(response.getBody(), GetEmployeesResponse.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertFalse(employeesResponse.getData().isEmpty());
		
	}
	
	


}
