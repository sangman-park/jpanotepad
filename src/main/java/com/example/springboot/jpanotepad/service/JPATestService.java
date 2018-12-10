package com.example.springboot.jpanotepad.service;

import com.example.springboot.jpanotepad.entity.Member;
import com.example.springboot.jpanotepad.entity.MemberGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class JPATestService {
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

}
