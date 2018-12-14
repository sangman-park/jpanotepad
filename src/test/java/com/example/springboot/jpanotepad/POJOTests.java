package com.example.springboot.jpanotepad;

import com.example.springboot.jpanotepad.entity.Customer;
import com.example.springboot.jpanotepad.entity.Customer2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class POJOTests {

    // Test for Pure Old Java Object

    @Test
    public void toStringTest(){
        // Customer-ToString 을 Override 를함
        Customer strOverider = new Customer("a","b");


        // Customer2-ToString 을 Override 안함
        Customer2 noOverider = new Customer2("a","b");

        String test1 = String.format("%s",strOverider);
        String test2 = String.format("%s",noOverider);

        // TEST 1: to String 오버라이드를 하던 안하든 잘찍힘
        System.out.println(test1);
        System.out.println(test2);

        String castString = String.format("%s",( Customer)noOverider );

        // 캐스팅 잘됨 - overid 안해도
        System.out.println(castString);


        // TEST 2: null 객체를 null로 표시하여 잘짝힘 - tostring 오버라이드와 상관없음
        noOverider=null;
        String test3 = String.format("%s",noOverider);
        System.out.println(test3);

        // TEST 3: null 객체의 속성을 접근하면 ..java.lang.NullPointerException 발생함
        //String test4 = String.format("%s",noOverider.getFirstName());
        //System.out.println(test3);

    }
}
