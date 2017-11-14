package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Girl;
import com.example.domain.Result;
import com.example.repertory.GirlRepertory;
import com.example.service.GirlService;
import com.example.utils.ResultUtil;

@RestController
public class GirlController {

	private static final Logger logger = LoggerFactory.getLogger(GirlController.class);

	@Autowired
	private GirlRepertory girlRepertory;

	@Autowired
	private GirlService GirlService;

	// 获取女生列表
	@GetMapping(value = "/girls")
	public List<Girl> girlList() {
		logger.info("girlList");
		return girlRepertory.findAll();
	}

	// 添加一个女生
	@PostMapping(value = "/girls")
	public Result girlAdd(@Valid Girl girl, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return null;
			//return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
		}
		return ResultUtil.success(girlRepertory.save(girl));
	}

	// 查询一个女生
	@GetMapping(value = "girls/{id}")
	public Girl girlFindOne(@PathVariable(value = "id") Integer id) {
		Girl girl = girlRepertory.findOne(id);
		return girl;
	}

	// 查新一个女生
	@PutMapping(value = "/girls/{id}")
	public Girl girlUpdate(@PathVariable(value = "id") Integer id, @RequestParam(value = "cupSize") String cupSize,
			@RequestParam(value = "age") Integer age) {
		Girl girl = new Girl();
		girl.setId(id);
		girl.setCupSize(cupSize);
		girl.setAge(age);

		girlRepertory.save(girl);
		return girl;
	}

	// 删除一个女生
	@DeleteMapping(value = "/girls/{id}")
	public void girlDelete(@PathVariable(value = "id") Integer id) {
		girlRepertory.delete(id);
	}

	// 通过年龄查询女生列表
	@GetMapping(value = "/girls/age/{id}")
	public List<Girl> girlListByAge(@PathVariable(value = "id") Integer age) {
		return girlRepertory.findByAge(age);
	}

	@PostMapping(value = "/girls/two")
	public void girlTwo() {
		GirlService.insertTwo();
	}

	@GetMapping(value = "/girls/getAge/{id}")
	public void getAge(@PathVariable(value = "id") Integer id) throws Exception {
		System.out.println("YES");
		GirlService.getAge(id);
	}
}
