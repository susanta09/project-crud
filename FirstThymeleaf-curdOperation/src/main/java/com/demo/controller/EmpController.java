package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;

@Controller
public class EmpController {
	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/index")
	public String viewHomePage(Model m)
	{
		return findPaginated(1, m);
//		m.addAttribute("listemp",employeeService.getAllEmloyees());
//		return "abc";
	}
	@GetMapping("/showNewEmpForm")
	public String showNewEmpForm(Model m)
	{
		Employee emp=new Employee();
		m.addAttribute("employee", emp);
		return "new_emp";
	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee)
	{
		employeeService.saveEmployee(employee);
		return "redirect:/index";
	}
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable(value="id") Integer id,Model m)
	{
		Employee e=employeeService.getEmployee(id);
		m.addAttribute("employee", e);
		return "updateEmployee";
	}
	@GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value="id") Integer id)
    {
		employeeService.deleteEmp(id);
    	return "redirect:/index";
    }

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,Model m)
	{
		int pageSize=4;
		Page<Employee> page=employeeService.findPaginated(pageNo, pageSize);
		List<Employee> list=page.getContent();
		
		m.addAttribute("currentPage", pageNo);
		m.addAttribute("totalPages", page.getTotalPages());
		m.addAttribute("totalItems", page.getTotalElements());
		m.addAttribute("list", list);
		return "index";
	}
}
