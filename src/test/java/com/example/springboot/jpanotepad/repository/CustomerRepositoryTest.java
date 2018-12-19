package com.example.springboot.jpanotepad.repository;

import com.example.springboot.jpanotepad.entity.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void crudTest(){
        String testLastName="curd-lastname-2";
        Customer customer = new Customer("name1",testLastName);
        customerRepository.save(customer);
        List<Customer> customerList = customerRepository.findByLastName(testLastName);
        Assert.assertEquals(1,customerList.size());

    }

}
