package com.example.springboot.jpanotepad.dddtest;

import com.example.springboot.jpanotepad.entity.event.DomainEvent;
import org.springframework.transaction.event.TransactionalEventListener;

public interface TestEventHandler {
    @TransactionalEventListener
    void handleEvent(DomainEvent event);
}
