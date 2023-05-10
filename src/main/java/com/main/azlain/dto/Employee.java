package com.main.azlain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonProperty("employee_name")
	private String employeeName;
	
	@JsonProperty("employee_salary")
	private Long employeeSalary;
	
	@JsonProperty("employee_age")
	private Integer employeeAge;
	
	@JsonProperty("profile_image")
	private String profileImage;
	
	private long anualSalary;	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public Long getEmployeeSalary() {
		return employeeSalary;
	}
	
	public void setEmployeeSalary(Long employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	public Integer getEmployeeAge() {
		return employeeAge;
	}
	
	public void setEmployeeAge(Integer employeeAge) {
		this.employeeAge = employeeAge;
	}
	
	public String getProfileImage() {
		return profileImage;
	}
	
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public long getAnualSalary() {
		return anualSalary;
	}

	public void setAnualSalary(long anualSalary) {
		this.anualSalary = anualSalary;
	}

}
