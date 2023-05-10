package com.main.azlain.dao.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.main.azlain.AzlainDevTestApplication;
import com.main.azlain.common.ErrorCodes;
import com.main.azlain.common.SupportMessages;
import com.main.azlain.dto.Employee;
import com.main.azlain.exception.MainAzlainException;
import com.main.azlain.response.GeneralResponse;
import com.main.azlain.response.GetEmployeeResponse;
import com.main.azlain.response.GetEmployeesResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AzlainDevTestApplication.class)
class EmployeeDaoImplTest {

	@Mock
	RestTemplate restTemplate;
	
	@Mock
	SupportMessages messages;
	
	@InjectMocks
	@Spy
	EmployeeDaoImpl employeeDao;
	
	@Value("${api.service.employees.url}")
	private String employeesListUrl;
	
	@Test
	void ifReceiveEmployeeValidIdShouldItReturnAnEmployee() throws MainAzlainException {

		Employee data = new Employee();
		data.setId(24);
		data.setEmployeeName("Tiger Nixon");
		data.setEmployeeSalary((long) 320800);
		data.setEmployeeAge(61);
		data.setProfileImage("");
		
		GetEmployeeResponse employeeMockResponse = new GetEmployeeResponse();
		employeeMockResponse.setStatus("success");
		employeeMockResponse.setMessage("Successfully! Record has been fetched.");
		employeeMockResponse.setData(data);
		
		when(restTemplate.getForEntity(anyString(), any()))
		.thenReturn(new ResponseEntity<>(employeeMockResponse, HttpStatus.OK));
		
		Employee employee = employeeDao.getById(24);
		assertEquals(employeeMockResponse.getData(), employee);
	}
	
	@Test
	void ifReceiveEmployeeInvalidIdShouldItReturnAnNullEmployee() throws MainAzlainException {

		GetEmployeeResponse employeeMockResponse = new GetEmployeeResponse();
		employeeMockResponse.setStatus("success");
		employeeMockResponse.setMessage("Successfully! Record has been fetched.");
		employeeMockResponse.setData(null);
		
		when(restTemplate.getForEntity(anyString(), any()))
		.thenReturn(new ResponseEntity<>(employeeMockResponse, HttpStatus.OK));
		
		Employee employee = employeeDao.getById(240000);
		assertEquals(employeeMockResponse.getData(), employee);
	}
	
	@Test
	void throwMainAzlainExceptionWhenGetEmployeeById() {
		
		try {
			when(restTemplate.getForEntity(anyString(), any()))
			.thenThrow(HttpClientErrorException.MethodNotAllowed.class);

			employeeDao.getById(24);
		} catch (MainAzlainException e) {
			assertEquals(HttpStatus.BAD_GATEWAY, e.getError().getHttpStatus());
		}
		
	}
	
	@Test
	void throwExceptionWhenGetEmployeeById() {
		
		try {
			when(restTemplate.getForEntity(anyString(), any()))
			.thenThrow(NullPointerException.class);

			employeeDao.getById(24);
		} catch (MainAzlainException e) {
			assertEquals(HttpStatus.BAD_GATEWAY, e.getError().getHttpStatus());
		}
		
	}
	
	@Test
	void ifNotReceiveEmployeeIdShouldItReturnAnEmployeeList() throws MainAzlainException {
		
		GetEmployeesResponse employeesMockResponse = new GetEmployeesResponse();
        employeesMockResponse.setStatus("success");
        employeesMockResponse.setMessage("Successfully! All records have been fetched.");

        List<Employee> empList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setEmployeeName("John Doe");
        empList.add(employee1);

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setEmployeeName("Jane Smith");
        empList.add(employee2);

        employeesMockResponse.setData(empList);

        when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(new ResponseEntity<>(employeesMockResponse, HttpStatus.OK));

        List<Employee> employees = employeeDao.getAll();
        assertEquals(empList, employees);
	}
	
	@Test
	void throwMainAzlainExceptionWhenGetEmployeeList() {
		
		try {
			when(restTemplate.getForEntity(anyString(), any()))
			.thenThrow(HttpClientErrorException.MethodNotAllowed.class);

			employeeDao.getAll();
		} catch (MainAzlainException e) {
			assertEquals(HttpStatus.BAD_GATEWAY, e.getError().getHttpStatus());
		}
		
	}
	
	@Test
	void throwExceptionWhenGetEmployeeList() {
		
		try {
			when(restTemplate.getForEntity(anyString(), any()))
			.thenThrow(NullPointerException.class);

			employeeDao.getAll();
		} catch (MainAzlainException e) {
			assertEquals(HttpStatus.BAD_GATEWAY, e.getError().getHttpStatus());
		}
	}
}
