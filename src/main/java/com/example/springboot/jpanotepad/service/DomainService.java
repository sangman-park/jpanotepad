package com.example.springboot.jpanotepad.service;


import com.example.springboot.jpanotepad.entity.event.AggregateRepository;
import com.example.springboot.jpanotepad.entity.event.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomainService {

    private final ApplicationEventPublisher eventPublisher;
    private final AggregateRepository repository;

    public DomainService(AggregateRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void serviceDomainOperation(long entityId) {
        repository.findById(entityId)
                .ifPresent(entity -> {
                    entity.domainOperation();
                    repository.save(entity);
                    eventPublisher.publishEvent(new DomainEvent());
                });
    }

}
