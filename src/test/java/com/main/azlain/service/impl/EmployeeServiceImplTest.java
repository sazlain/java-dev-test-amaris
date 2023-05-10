package com.main.azlain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import com.main.azlain.AzlainDevTestApplication;
import com.main.azlain.common.ErrorCodes;
import com.main.azlain.common.StatusResponse;
import com.main.azlain.common.SupportMessages;
import com.main.azlain.dao.EmployeeDao;
import com.main.azlain.dto.Employee;
import com.main.azlain.exception.MainAzlainException;
import com.main.azlain.response.GeneralResponse;
import com.main.azlain.response.GetEmployeeResponse;
import com.main.azlain.response.GetEmployeesResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AzlainDevTestApplication.class)
class EmployeeServiceImplTest {

	@Mock
	EmployeeDao employeeDao;

	@Mock
	SupportMessages messages;

	@InjectMocks
	@Spy
	EmployeeServiceImpl employeeService;

	@Test
	void ifReceiveEmployeeValidIdShouldItReturnAnEmployee() throws MainAzlainException {

		Employee data = new Employee();
		data.setId(24);
		data.setEmployeeName("Tiger Nixon");
		data.setEmployeeSalary((long) 320800);
		data.setEmployeeAge(61);
		data.setProfileImage("");

		when(employeeDao.getById(24)).thenReturn(data);

		GetEmployeeResponse response = (GetEmployeeResponse) employeeService.getById(24);
		assertEquals(data, response.getData());
		assertEquals(StatusResponse.SUCCESS.toString(), response.getStatus());

	}

	@Test
	void ifReceiveEmployeeInvalidIdShouldItReturnAnEmployee() throws MainAzlainException {
		
		when(employeeDao.getById(240000)).thenReturn(null);
		
		GetEmployeeResponse response = (GetEmployeeResponse) employeeService.getById(240000);
		assertEquals(null, response.getData());
		assertEquals(StatusResponse.SUCCESS.toString(), response.getStatus());
	}

	@Test
	void throwMainAzlainExceptionWhenGetEmployeeById() throws MainAzlainException {
		doThrow(new MainAzlainException(ErrorCodes.ERR001_INTERNAL_ERROR, messages, HttpStatus.BAD_GATEWAY))
				.when(employeeDao).getById(anyInt());

		GeneralResponse response = employeeService.getById(24);
		assertEquals(StatusResponse.ERROR.toString(), response.getStatus());
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
	}

	@Test
	void throwExceptionWhenGetEmployeeById() throws Exception {

		doThrow(NullPointerException.class).when(employeeDao).getById(anyInt());

		GeneralResponse response = employeeService.getById(24);
		assertEquals(StatusResponse.ERROR.toString(), response.getStatus());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

	}

	@Test
	void ifReceiveEmployeeValidIdShouldItReturnAnEmployeeList() throws MainAzlainException {

		Employee data = new Employee();
		data.setId(24);
		data.setEmployeeName("Tiger Nixon");
		data.setEmployeeSalary((long) 320800);
		data.setEmployeeAge(61);
		data.setProfileImage("");

		List<Employee> empList = new ArrayList<>();
		empList.add(data);

		when(employeeDao.getAll()).thenReturn(empList);

		GetEmployeesResponse response = (GetEmployeesResponse) employeeService.getAll();
		assertEquals(empList, response.getData());
		assertEquals(StatusResponse.SUCCESS.toString(), response.getStatus());

	}

	@Test
	void ifReceiveEmployeeInvalidIdShouldItReturnAnEmployeeEmptyList() throws MainAzlainException {

		List<Employee> empList = new ArrayList<>();
		when(employeeDao.getAll()).thenReturn(empList);

		GetEmployeesResponse response = (GetEmployeesResponse) employeeService.getAll();
		assertTrue(response.getData().isEmpty());
		assertEquals(StatusResponse.SUCCESS.toString(), response.getStatus());
	}

	@Test
	void throwMainAzlainExceptionWhenGetEmployeeList() throws MainAzlainException {
		doThrow(new MainAzlainException(ErrorCodes.ERR001_INTERNAL_ERROR, messages, HttpStatus.BAD_GATEWAY))
				.when(employeeDao).getAll();

		GeneralResponse response = employeeService.getAll();
		assertEquals(StatusResponse.ERROR.toString(), response.getStatus());
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
	}

	@Test
	void throwExceptionWhenGetEmployeeList() throws Exception {

		doThrow(NullPointerException.class).when(employeeDao).getAll();

		GeneralResponse response = employeeService.getAll();
		assertEquals(StatusResponse.ERROR.toString(), response.getStatus());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

	}

}
