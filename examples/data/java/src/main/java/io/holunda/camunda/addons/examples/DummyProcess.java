package io.holunda.camunda.addons.examples;

import io.holunda.camunda.addons.data.variable.Variable;
import io.holunda.camunda.addons.data.variable.VariableAdaptersKt;
import io.holunda.camunda.addons.data.variable.VariablesKt;
import io.holunda.camunda.addons.data.variable.adapter.VariableReadWriteAdapter;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DummyProcess {
    public static final String KEY = "dummy.bpmn";
    public static final BpmnModelInstance PROCESS = Bpmn.createProcess("dummy")
            .startEvent()
            .serviceTask("service").camundaDelegateExpression("${dummyDelegate}")
            .userTask("task")
            .endEvent()
            .done();

    public enum VARIABLES {
        ;

        public static final Variable<String> FOO = VariablesKt.stringVariable("foo");
        public static final Variable<Boolean> CHECKED = VariablesKt.booleanVariable("isChecked");
        public static final Variable<SomeEnum> SOME = VariablesKt.enumVariable("someEnum", SomeEnum.class);
        public static final Variable<Long> NUMBER = VariablesKt.longVariable("number");
    }

    private final RuntimeService runtimeService;

    public DummyProcess(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public Deployment deploy(PostDeployEvent event) {
        return event.getProcessEngine().getRepositoryService()
                .createDeployment().addModelInstance(KEY, PROCESS)
                .deploy();
    }

    public ProcessInstance start(
            String businessKey,
            String foo,
            boolean checked,
            long number,
            SomeEnum some) {
        VariableMap variables = Variables.createVariables();
        VariableReadWriteAdapter variableReadWriteAdapter = VariableAdaptersKt.readWriteAdapter(variables);

        variableReadWriteAdapter.write(VARIABLES.FOO, foo);
        variableReadWriteAdapter.write(VARIABLES.CHECKED, checked);
        variableReadWriteAdapter.write(VARIABLES.NUMBER, number);
        variableReadWriteAdapter.write(VARIABLES.SOME, some);

        return runtimeService.startProcessInstanceByKey(
                KEY,
                businessKey,
                variables
        );
    }

    @Bean
    public JavaDelegate dummyDelegate() {
        return execution -> {
            VariableReadWriteAdapter adapter = VariableAdaptersKt.readWriteAdapter(execution);

        };
    }

    public enum SomeEnum {
        HELLO, WORLD
    }
}
