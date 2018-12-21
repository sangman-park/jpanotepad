package com.example.springboot.jpanotepad.dddtest;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.example.springboot.jpanotepad.actor.persitent.Cmd;
import com.example.springboot.jpanotepad.extension.SpringExtension;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ActorEventTest {


    @Autowired
    ApplicationContext applicationContext;

    ActorSystem actorSystem;

    SpringExtension ext;

    @Before
    public void beforeFunction(){
        System.out.println("======= Before Function #2");

        //memberLoad();
        actorSystem = applicationContext.getBean(ActorSystem.class);
        ext = applicationContext.getBean(SpringExtension.class);

        System.out.println("======= Test Function -Start #3");
    }


    @Test
    public void actorMsgTest() {
        new TestKit(actorSystem) {{
            final ActorRef persistentActor = actorSystem.actorOf( ext.props("examplePersistentActor"),"exActor1");
            final TestKit probe = new TestKit(actorSystem);  //추가 테스트 객체생성..
            within(java.time.Duration.ofSeconds(3), () -> {
                persistentActor.tell(new Cmd("foo"), probe.getRef());
                persistentActor.tell(new Cmd("baz"), null);
                persistentActor.tell(new Cmd("bar"), null);
                persistentActor.tell("snap", null);
                persistentActor.tell(new Cmd("buzz"), null);
                persistentActor.tell("print", null);
                //wait for message
                awaitCond(probe::msgAvailable);
                //check message queue
                probe.expectMsgAnyOf(java.time.Duration.ofSeconds(0), "save");
                return null;
            });
        }};
    }

}
