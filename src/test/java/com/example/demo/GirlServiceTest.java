package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Girl;
import com.example.service.GirlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

	@Autowired
	private GirlService girlService;

	@Test
	public void findOneTest() {
		Girl girl = girlService.findOne(1);
		Assert.assertEquals(new Integer(15), girl.getAge());
	}
}
