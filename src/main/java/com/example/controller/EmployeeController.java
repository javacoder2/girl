package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Employee;
import com.example.domain.Result;
import com.example.service.EmployeeService;
import com.example.utils.ResultUtil;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/list")
	public Result list(@RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer currentPage) {
		return ResultUtil.success(employeeService.queryScrollPage(pageSize, currentPage));
	}

	@PostMapping(value = "add")
	public Result add(Employee employee) {
		employeeService.add(employee);
		return ResultUtil.success();
	}

	@PostMapping("/update")
	public Result update(Employee employee) {
		employeeService.updateEmployee(employee);
		return ResultUtil.success();
	}

	@GetMapping("/delete")
	public Result delete(@RequestParam Integer empno) {
		employeeService.deleteEmployee(empno);
		return ResultUtil.success();
	}
}
