package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.properties.GirlProperties;

@RestController
public class HelloController {

	@Autowired
	private GirlProperties girlProperties;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello() {
		return girlProperties.getCupSize() + "," + girlProperties.getAge();
	}
}
