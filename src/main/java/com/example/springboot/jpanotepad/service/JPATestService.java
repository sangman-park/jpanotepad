package com.example.springboot.jpanotepad.service;

import com.example.springboot.jpanotepad.entity.Member;
import com.example.springboot.jpanotepad.entity.MemberGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //JPQL
    @Transactional
    public List findAll(String tableName){
        List result = em.createQuery(String.format("SELECT t FROM %s t",tableName))
                .getResultList();

        result.forEach( entity->{
            logger.info(entity.toString());
        });

        return result;
    }

    //Criteria Query
    @Transactional
    public List findByNameFromMember(String username){
        // scenario : subquery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);
        Root<Member> m = query.from(Member.class);
        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"),username));
        List resultList = em.createQuery(cq).getResultList();

        return resultList;

    }

    //Query DSL
    public List QueryDsl1() throws Exception {
        /*
        JPAQuery query = new JPAQuery(em);
        QMember = member = Qmember.member;
        List<Member> members =
                query.from(member)
                        .where(member.username.eq("Kim"))
                        .list(member);
        */
        throw new Exception("preparing......");
    }


}
