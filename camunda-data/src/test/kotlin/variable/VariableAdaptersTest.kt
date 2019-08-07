package io.holunda.camunda.addons.data.variable

import io.holunda.addons.camunda.data.variable.readWriteAdapter
import io.holunda.addons.camunda.data.variable.stringVariable
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.variable.Variables
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class VariableAdaptersTest {
  companion object {
    const val instanceId = "1"
  }


  private val stringVar = stringVariable("foo")


  @Test
  fun `adapter for runtimeService`() {
    val variables = Variables.createVariables()
    val runtimeService = RuntimeServiceFake(variables)
    val adapter = runtimeService.readWriteAdapter(instanceId)

    assertThat(adapter.read(stringVar)).isEmpty

    adapter.write(stringVar, "hello")

    assertThat(adapter.read(stringVar)).hasValue("hello")
  }
}