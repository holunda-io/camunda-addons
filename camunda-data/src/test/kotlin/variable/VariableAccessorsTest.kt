package io.holunda.camunda.addons.data.variable

import org.assertj.core.api.Assertions.*
import org.camunda.bpm.engine.variable.Variables
import org.junit.Test

class VariableAccessorsTest {


  private val enumVariable = enumVariable("dummy", DummyEnum::class.java)

  @Test
  fun `access on runtimeService`() {
    val variables = Variables.createVariables()
    val runtimeService = RuntimeServiceFake(variables)

    val accessor = enumVariable.adapter(runtimeService, "1")

    accessor.write(DummyEnum.WORLD)

    assertThat(accessor.readRequired()).isEqualTo(DummyEnum.WORLD)

  }
}