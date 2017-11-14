package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.EmployeeDao;
import com.example.domain.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	public List<Employee> queryList() {
		return employeeDao.selectAll();
	}

	public void add(Employee employee) {
		employeeDao.insert(employee);
	}

	public void updateEmployee(Employee employee) {
		employeeDao.updateByPrimaryKey(employee);
	}

	public void deleteEmployee(Integer empno) {
		employeeDao.deleteByPrimaryKey(empno);
	}

	public Map<String, Object> queryScrollPage(Integer pageSize, Integer currentPage) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", (currentPage - 1) * pageSize);
		queryMap.put("rows", pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", employeeDao.queryTotalCount());
		map.put("rows", employeeDao.queryPageData(queryMap));
		return map;
	}
}
