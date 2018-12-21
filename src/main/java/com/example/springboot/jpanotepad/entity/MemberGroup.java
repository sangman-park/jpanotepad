package com.example.springboot.jpanotepad.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
public class MemberGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name="TEAM_ID")
    private List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @ElementCollection(fetch = EAGER)
    private Set<Long> operatorIds = new HashSet<Long>(); // > 0 for private tournaments

    public Set<Long> getOperatorIds() {
        return operatorIds;
    }

    public void setOperatorIds(Set<Long> operatorIds) {
        this.operatorIds = operatorIds;
    }

    @Override
    public String toString() {
        return String.format(
                "MemberGroup[id=%d, name='%s', memlen=%d]",
                id, name,members.size());
    }

}
