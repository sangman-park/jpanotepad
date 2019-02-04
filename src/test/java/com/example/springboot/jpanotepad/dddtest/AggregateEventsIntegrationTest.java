package com.example.springboot.jpanotepad.dddtest;

import com.example.springboot.jpanotepad.entity.event.Aggregate;
import com.example.springboot.jpanotepad.entity.event.AggregateRepository;
import com.example.springboot.jpanotepad.entity.event.DomainEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;


// link - https://github.com/eugenp/tutorials/tree/master/persistence-modules/spring-data-jpa

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AggregateEventsIntegrationTest {

    @MockBean
    private TestEventHandler eventHandler;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    AggregateRepository repository;

    @Before
    public void beforeEach() {
        repository.deleteAll();
    }

    @Test
    public void aggregateEventsTest() {
        Aggregate existingDomainEntity = new Aggregate();
        repository.save(existingDomainEntity);

        // when
        repository.findById(existingDomainEntity.getId())
                .get()
                .domainOperation();

        // then
        verifyZeroInteractions(eventHandler);
    }

    @Test
    public void domainEvents() {

        // given
        Aggregate aggregate = new Aggregate();

        // when
        aggregate.domainOperation();
        repository.save(aggregate);


        // then
        verifyZeroInteractions(eventHandler);

        // then :
        //verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));

    }


}
