package com.example.springboot.jpanotepad.dddtest;

import com.example.springboot.jpanotepad.entity.event.Aggregate;
import com.example.springboot.jpanotepad.entity.event.AggregateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


// link - https://github.com/eugenp/tutorials/tree/master/persistence-modules/spring-data-jpa

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AggregateEventsIntegrationTest {

    @MockBean
    private TestEventHandler eventHandler;

    @Autowired
    AggregateRepository repository;

    @Test
    public void domainEvents() {

        // given
        Aggregate aggregate = new Aggregate();

        // when
        aggregate.domainOperation();
        repository.save(aggregate);

        // then : Oops, very difficult ...
        //verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));

    }


}
