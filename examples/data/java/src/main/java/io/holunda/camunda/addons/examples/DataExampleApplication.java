package io.holunda.camunda.addons.examples;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.util.UUID;

@SpringBootApplication
@EnableProcessApplication
public class DataExampleApplication {

    private final DummyProcess process;

    public DataExampleApplication(DummyProcess process) {
        this.process = process;
    }

    @EventListener
    @Order(1000)
    public void startProcess(PostDeployEvent event) {
        process.deploy(event);

        ProcessInstance processInstance = process.start(
                UUID.randomUUID().toString(),
                "bar",
                false,
                1L,
                DummyProcess.SomeEnum.WORLD
        );

    }

    public static void main(String[] args) {
        SpringApplication.run(DataExampleApplication.class);
    }

}
