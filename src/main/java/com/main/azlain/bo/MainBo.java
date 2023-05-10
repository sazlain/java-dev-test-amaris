package com.main.azlain.bo;

import java.util.List;

import com.main.azlain.dto.Employee;

public class MainBo {

	public static Employee calculateAnualSalary(Employee employee) {
		if(employee != null && employee.getEmployeeSalary() != null) {
			employee.setAnualSalary(employee.getEmployeeSalary() * 12);
		}
		return employee;
	}
	
	public static List<Employee> calculateAnualSalary(List<Employee> employees) {
		for(Employee employee: employees) {
			if(employee != null && employee.getEmployeeSalary() != null) {
				employee.setAnualSalary(employee.getEmployeeSalary() * 12);
			}
		}
		return employees;
	}
}
