package io.holunda.camunda.addons.data.variable

import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.variable.VariableMap
import org.camunda.bpm.engine.variable.Variables
import org.mockito.Mockito

class RuntimeServiceFake(val variables: VariableMap, val runtimeService: RuntimeService = Mockito.spy(RuntimeService::class.java)) : RuntimeService by runtimeService{

  init {
    Mockito.`when`(runtimeService.getVariable(Mockito.eq(VariableAdaptersTest.instanceId), Mockito.anyString())).thenAnswer { i -> variables[i.getArgument(1)] }
    Mockito.`when`(runtimeService.setVariable(Mockito.eq(VariableAdaptersTest.instanceId), Mockito.anyString(), Mockito.any())).thenAnswer { i -> variables.putValue(i.getArgument(1), i.getArgument(2)) }

  }

}