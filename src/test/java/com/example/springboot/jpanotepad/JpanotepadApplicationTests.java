package com.example.springboot.jpanotepad;

import com.example.springboot.jpanotepad.entity.Customer;
import com.example.springboot.jpanotepad.entity.Customer2;
import com.example.springboot.jpanotepad.service.JPATestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpanotepadApplicationTests {

	@Autowired
	JPATestService jpaTestService;

	@Test
	public void contextLoads() {
		jpaTestService.jpaUpdateTest();
		//Check
		Assert.assertTrue(jpaTestService.findAll("Member").size() > 0);
		Assert.assertTrue(jpaTestService.findAll("MemberGroup").size() > 0);
        Assert.assertTrue(jpaTestService.findByNameFromMember("user1").size() >0);

	}



}
