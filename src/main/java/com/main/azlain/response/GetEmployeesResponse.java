package com.main.azlain.response;

import java.util.List;

import com.google.gson.Gson;
import com.main.azlain.dto.Employee;

public class GetEmployeesResponse extends GeneralResponse {
	
	private List<Employee> data;

	public GetEmployeesResponse() {
		super();
	}
	
	public List<Employee> getData() {
		return data;
	}

	public void setData(List<Employee> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}

