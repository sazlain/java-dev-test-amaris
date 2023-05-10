package com.main.azlain.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.main.azlain.bo.MainBo;
import com.main.azlain.common.ErrorCodes;
import com.main.azlain.common.SupportMessages;
import com.main.azlain.dao.EmployeeDao;
import com.main.azlain.dto.Employee;
import com.main.azlain.exception.MainAzlainException;
import com.main.azlain.response.GetEmployeeResponse;
import com.main.azlain.response.GetEmployeesResponse;

@Service
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.service.employee.url}")
	private String employeeByIdUrl;

	@Value("${api.service.employees.url}")
	private String employeesListUrl;

	@Autowired
	SupportMessages messages;

	@Override
	public Employee getById(int employeeId) throws MainAzlainException {
		try {

			ResponseEntity<GetEmployeeResponse> response = restTemplate.getForEntity(employeeByIdUrl + "/" + employeeId,
					GetEmployeeResponse.class);
			if(response.getBody() != null ) {
				return MainBo.calculateAnualSalary(response.getBody().getData());
			} else {
				return null;
			}
			

		} catch (HttpClientErrorException e) {
			logger.error("External access api error: {}", e.getMessage());
			throw new MainAzlainException(ErrorCodes.ERR002_EXTERNAL_CLIENT_ACCESS_ERROR, messages,
					HttpStatus.BAD_GATEWAY);
		} catch (Exception e) {
			logger.error("External access api error: {}", e.getMessage());
			throw new MainAzlainException(ErrorCodes.ERR001_INTERNAL_ERROR, messages, HttpStatus.BAD_GATEWAY);
		}

	}

	@Override
	public List<Employee> getAll() throws MainAzlainException {
		try {

			ResponseEntity<GetEmployeesResponse> response = restTemplate.getForEntity(employeesListUrl + "/",
					GetEmployeesResponse.class);
			if(response.getBody() != null ) {
				return MainBo.calculateAnualSalary(response.getBody().getData());
			} else {
				return new ArrayList<>();
			}

		} catch (HttpClientErrorException e) {
			logger.error("External access api error: {} ", e.getMessage());
			throw new MainAzlainException(ErrorCodes.ERR002_EXTERNAL_CLIENT_ACCESS_ERROR, messages,
					HttpStatus.BAD_GATEWAY);
		} catch (Exception e) {
			logger.error("External access api error: {}", e.getMessage());
			throw new MainAzlainException(ErrorCodes.ERR001_INTERNAL_ERROR, messages, HttpStatus.BAD_GATEWAY);
		}
	}

}
