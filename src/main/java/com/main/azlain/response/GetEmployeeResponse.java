package com.main.azlain.response;

import com.google.gson.Gson;
import com.main.azlain.dto.Employee;

public class GetEmployeeResponse extends GeneralResponse {

	private Employee data;

	public GetEmployeeResponse() {
		super();
	}

	public Employee getData() {
		return data;
	}

	public void setData(Employee data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
