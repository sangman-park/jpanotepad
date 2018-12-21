package com.example.springboot.jpanotepad.extension;

import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Extension to tell Akka how to create beans via Spring.
 */
@Component
public class SpringExtension implements Extension {

    private ApplicationContext applicationContext;

    /**
     * Used to initialize the Spring application context for the extension.
     */
    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Create a Props for the specified actorBeanName using the
     * SpringActorProducer class.
     */
    public Props props(String actorBeanName, Object... args) {
        return (args != null && args.length > 0) ?
                Props.create(SpringActorProducer.class,
                        applicationContext,
                        actorBeanName, args) :
                Props.create(SpringActorProducer.class,
                        applicationContext,
                        actorBeanName);
    }
}