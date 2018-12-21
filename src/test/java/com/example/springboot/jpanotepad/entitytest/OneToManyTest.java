package com.example.springboot.jpanotepad.entitytest;

import com.example.springboot.jpanotepad.entity.Member;
import com.example.springboot.jpanotepad.entity.MemberGroup;
import com.example.springboot.jpanotepad.repository.CustomerRepository;
import com.example.springboot.jpanotepad.service.JPATestService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class OneToManyTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    JPATestService jpaTestService;

    @BeforeClass
    public static void beforeClassFunction(){
        System.out.println("======= Before Class #1");
    }

    @Before
    public void beforeFunction(){
        System.out.println("======= Before Function #2");
        memberLoad();

        System.out.println("======= Test Function -Start #3");
    }

    @After
    public void afterFunction(){
        System.out.println("======= Test Function -End #4");
        System.out.println("After Function #5");
    }

    @AfterClass
    public static void afterClassFunction(){
        System.out.println("After Class #6");
    }


    protected void memberLoad(){
        // ###
        Member member = new Member();
        member.setUsername("user1");
        // 1 -> N : Unidirectional Test
        em.persist(member);

        Member member2 = new Member();
        member2.setUsername("user2");
        em.persist(member2);
        // ###

        MemberGroup memberGroup = new MemberGroup();
        List<Member> members = new ArrayList<>();

        em.persist(memberGroup);


        Set<Long> operatorIds = new HashSet<Long>();
        operatorIds.add(1L);
        operatorIds.add(3L);
        memberGroup.setOperatorIds(operatorIds);


        members.add(member);
        members.add(member2);
        memberGroup.setMembers(members);
        memberGroup.setName("group1");

        em.merge(memberGroup);

    }


    @Test
    public void memberCheckByService(){
        //Check
        Assert.assertTrue(jpaTestService.findAll("Member").size() > 0);
        Assert.assertTrue(jpaTestService.findAll("MemberGroup").size() > 0);
        Assert.assertTrue(jpaTestService.findByNameFromMember("user1").size() >0);
    }

    @Test
    public void memeberCheckByQuery(){
        List result = em.createQuery(String.format("SELECT t FROM Member t"))
                .getResultList();

        Assert.assertTrue(result.size()>0);
    }


}
