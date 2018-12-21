package com.example.springboot.jpanotepad.config;

import akka.actor.ActorSystem;
import com.example.springboot.jpanotepad.extension.SpringExtension;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@Configuration
@Lazy
@ComponentScan(basePackages = { "com.example.springboot.jpanotepad.service",
        "com.example.springboot.jpanotepad.actor", "com.example.springboot.jpanotepad.extension" })
public class ApplicationConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;


    @Autowired
    private Environment env;

    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem
                .create("AkkaTestApp", akkaConfiguration());
        springExtension.initialize(applicationContext);
        return system;
    }

    @Bean
    public Config akkaConfiguration() {

        String akkaip = env.getProperty("akka.remote.netty.tcp.ip");
        String port = env.getProperty("akka.remote.netty.tcp.port");

        /*
        return ConfigFactory.load().withValue("akka.remote.netty.tcp.ip", ConfigValueFactory.fromAnyRef(akkaip))
                .withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(port));*/
        return ConfigFactory.load();

    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
    }

}
