package com.example.springboot.jpanotepad.actor.persitent;

import akka.actor.AbstractActor;
import akka.persistence.AbstractPersistentActor;
import akka.persistence.SnapshotOffer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;



class Evt implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String data;

    public Evt(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

class ExampleState implements Serializable {
    private static final long serialVersionUID = 1L;
    private final ArrayList<String> events;

    public ExampleState() {
        this(new ArrayList<>());
    }

    public ExampleState(ArrayList<String> events) {
        this.events = events;
    }

    public ExampleState copy() {
        return new ExampleState(new ArrayList<>(events));
    }

    public void update(Evt evt) {
        events.add(evt.getData());
    }

    public int size() {
        return events.size();
    }

    @Override
    public String toString() {
        return events.toString();
    }
}

@Component
@Scope("prototype")
public class ExamplePersistentActor extends AbstractPersistentActor {

    private ExampleState state = new ExampleState();

    public int getNumEvents() {
        return state.size();
    }

    @Override
    public String persistenceId() { return "sample-id-1"; }

    @Override
    public AbstractActor.Receive createReceiveRecover() {
        return receiveBuilder()
                .match(Evt.class, e -> state.update(e))
                .match(SnapshotOffer.class, ss -> state = (ExampleState) ss.snapshot())
                .build();
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(Cmd.class, c -> {
                    final String data = c.getData();
                    final Evt evt = new Evt(data + "-" + getNumEvents());
                    persist(evt, (Evt event) -> {
                        state.update(event);
                        getContext().system().eventStream().publish(event);
                    });
                    sender().tell("save",null); // response for test
                })
                .matchEquals("snap", s -> saveSnapshot(state.copy()))
                .matchEquals("print", s -> System.out.println(state))
                .build();
    }

}