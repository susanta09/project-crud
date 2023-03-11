package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.model.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmloyees();

	public void saveEmployee(Employee employee);

	public Employee getEmployee(Integer id);

	public void deleteEmp(Integer id);
	
	Page<Employee> findPaginated(int pageNo,int pageSize);

}
