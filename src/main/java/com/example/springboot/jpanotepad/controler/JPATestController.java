package com.example.springboot.jpanotepad.controler;

import com.example.springboot.jpanotepad.entity.Customer;
import com.example.springboot.jpanotepad.repository.CustomerRepository;
import com.example.springboot.jpanotepad.service.JPATestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JPATestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JPATestService jpaTestService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getProducts() {
        List<Customer> list = new ArrayList<>();
        customerRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String createProduct() {
        Customer customer = new Customer("test1","test2");
        customerRepository.save(customer);
        return "Product is saved successfully";
    }


    @RequestMapping(value = "/onetomany", method = RequestMethod.POST)
    public String createMemberAndGroup() {
        jpaTestService.jpaUpdateTest();
        return "Saved successfully, you can try SQL - http://localhost:8080/h2";
    }

}
