package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;
@Service
public class EmployeeServicImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empR;
	@Override
	public List<Employee> getAllEmloyees() {
		
		return empR.findAll();
	}
	@Override
	public void saveEmployee(Employee employee) {
		empR.save(employee);
		
	}
	@Override
	public Employee getEmployee(Integer id) {
		Employee e=empR.getById(id);
		return e;
	}
	@Override
	public void deleteEmp(Integer id) {
		empR.deleteById(id);
	}
	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return this.empR.findAll(pageable);
	}

	

}
