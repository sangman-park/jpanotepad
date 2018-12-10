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

        // ###
        Member member = new Member();
        member.setUsername("user1");
        // 1 : N 단방향에서 , N 에해당하는 객체는 영속상태여야
        // 그룹에해당하는 그룹을 참조하는 외래키 업데이트가가능
        em.persist(member);

        Member member2 = new Member();
        member2.setUsername("user2");
        em.persist(member2);
        // ###

        MemberGroup memberGroup = new MemberGroup();
        List<Member> members = new ArrayList<>();
        members.add(member);
        members.add(member2);
        memberGroup.setMembers(members);
        memberGroup.setName("group1");
        em.merge(memberGroup);

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
