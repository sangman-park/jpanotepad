package com.example.springboot.jpanotepad.entity.event;


import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Aggregate {
    @Transient
    private final Collection<DomainEvent> domainEvents;
    @Id
    @GeneratedValue
    private long id;

    public Aggregate() {
        domainEvents = new ArrayList<>();
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        domainEvents.clear();
    }

    public void domainOperation() {
        // some domain operation
        domainEvents.add(new DomainEvent());
    }

    @DomainEvents
    public Collection<DomainEvent> events() {
        return domainEvents;
    }

    public Collection<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
