package com.example.springboot.jpanotepad;

import com.example.springboot.jpanotepad.entity.Customer;
import com.example.springboot.jpanotepad.entity.Customer2;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class POJOTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    // Test for Pure Old Java Object

    @Test
    public void toStringTest(){

        // Override VS None

        Customer strOverider = new Customer("a","b");
        Customer2 noOverider = new Customer2("a","b");

        String test1 = String.format("%s",strOverider);
        String test2 = String.format("%s",noOverider);

        System.out.println(test1);
        System.out.println(test2);

        // Casting Tests
        String castString = String.format("%s",( Customer)noOverider );
        System.out.println(castString);


        // Null Tests
        noOverider=null;
        String test3 = String.format("%s",noOverider);
        System.out.println(test3);

        // Access Properti Test : Expected : java.lang.NullPointerException
        exception.expect(NullPointerException.class);
        String test4 = String.format("%s",noOverider.getFirstName());


    }
}
