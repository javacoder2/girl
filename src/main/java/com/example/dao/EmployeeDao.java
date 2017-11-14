package com.example.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.example.common.BaseDao;
import com.example.domain.Employee;

public interface EmployeeDao extends BaseDao<Employee> {

	@Select("select * from employee")
	public List<Employee> selectByState(Integer state);

	@Select("select count(1) from employee")
	public int queryTotalCount();

	@Select("select * from employee limit #{offset}, #{rows}")
	public List<Employee> queryPageData(Map<String, Object> queryMap);
}