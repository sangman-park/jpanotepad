package com.example.springboot.jpanotepad.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MemberGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    @Override
    public String toString() {
        return String.format(
                "MemberGroup[id=%d, name='%s', memlen=%d]",
                id, name,members.size());
    }

}
