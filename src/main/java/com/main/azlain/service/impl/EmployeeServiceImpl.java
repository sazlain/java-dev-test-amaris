package com.main.azlain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.main.azlain.common.ErrorCodes;
import com.main.azlain.common.StatusResponse;
import com.main.azlain.common.SupportMessages;
import com.main.azlain.dao.EmployeeDao;
import com.main.azlain.exception.MainAzlainException;
import com.main.azlain.response.GeneralResponse;
import com.main.azlain.response.GetEmployeeResponse;
import com.main.azlain.response.GetEmployeesResponse;
import com.main.azlain.service.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	SupportMessages messages;
	
	@Override
	public GeneralResponse getById(int employeeId) {
		GetEmployeeResponse response = new GetEmployeeResponse();
		try {
			response.setData(employeeDao.getById(employeeId));
			response.setStatus(StatusResponse.SUCCESS.toString());
			response.setStatusCode(HttpStatus.OK);
			return response;
		} catch (MainAzlainException e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setStatus(StatusResponse.ERROR.toString());
			generalResponse.setMessage(e.getError().getMessage());
			generalResponse.setStatusCode(e.getError().getHttpStatus());
			return generalResponse;
		} catch (Exception e) {
			MainAzlainException cException = new MainAzlainException(ErrorCodes.ERR001_INTERNAL_ERROR, messages,HttpStatus.INTERNAL_SERVER_ERROR);
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setStatus(StatusResponse.ERROR.toString());
			generalResponse.setMessage(cException.getError().getMessage());
			generalResponse.setStatusCode(cException.getError().getHttpStatus());
			return generalResponse;
		}
	}

	@Override
	public GeneralResponse getAll() {
		GetEmployeesResponse response = new GetEmployeesResponse();
		try {
			response.setData(employeeDao.getAll());
			response.setStatus(StatusResponse.SUCCESS.toString());
			response.setStatusCode(HttpStatus.OK);
			return response;
		} catch (MainAzlainException e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setStatus(StatusResponse.ERROR.toString());
			generalResponse.setMessage(e.getError().getMessage());
			generalResponse.setStatusCode(e.getError().getHttpStatus());
			return generalResponse;
		} catch (Exception e) {
			MainAzlainException cException = new MainAzlainException(ErrorCodes.ERR001_INTERNAL_ERROR, messages,HttpStatus.INTERNAL_SERVER_ERROR);
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setStatus(StatusResponse.ERROR.toString());
			generalResponse.setMessage(cException.getError().getMessage());
			generalResponse.setStatusCode(cException.getError().getHttpStatus());
			return generalResponse;
		}
		
	}

}
