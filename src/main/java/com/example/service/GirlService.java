package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Girl;
import com.example.enums.ResultEnum;
import com.example.repertory.GirlRepertory;
import com.example.utils.GirlException;

@Service
public class GirlService {
	@Autowired
	GirlRepertory girlRepertory;

	@Transactional
	public void insertTwo() {
		Girl girlA = new Girl();
		girlA.setCupSize("A");
		girlA.setAge(18);
		girlRepertory.save(girlA);

		Girl girlB = new Girl();
		girlB.setCupSize("BBBBB");
		girlB.setAge(19);
		girlRepertory.save(girlB);
	}

	public void getAge(Integer id) {
		Girl girl = girlRepertory.findOne(id);
		Integer age = girl.getAge();
		if (age < 10) {
			throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
		} else if (age > 10 && age < 16) {
			throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
		}
	}
	
	public Girl findOne(Integer id) {
		return girlRepertory.findOne(id);
	}
}
