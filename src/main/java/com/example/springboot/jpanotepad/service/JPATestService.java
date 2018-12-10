package com.example.springboot.jpanotepad.service;

import com.example.springboot.jpanotepad.entity.Member;
import com.example.springboot.jpanotepad.entity.MemberGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class JPATestService {

    Logger logger = LoggerFactory.getLogger(JPATestService.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void jpaUpdateTest(){
        Member member = new Member();
        member.setUsername("user1");
        em.persist(member);
        //em.merge(member);

        MemberGroup memberGroup = new MemberGroup();
        List<Member> members = new ArrayList<>();
        members.add(member);
        memberGroup.setMembers(members);
        memberGroup.setName("group1");
        em.persist(memberGroup);
    }

    @Transactional
    public List findAll(String tableName){
        List result = em.createQuery(String.format("SELECT t FROM %s t",tableName))
                .getResultList();

        result.forEach( entity->{
            logger.info(entity.toString());
        });

        return result;
    }

}
