package io.holunda.camunda.addons.data.variable

import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.variable.Variables
import org.junit.Test


class VariableTest {


  @Test
  fun `long variable`() {
    val fooVariable = longVariable("foo")

    assertThat(fooVariable.key).isEqualTo("foo")
    assertThat(fooVariable.type).isEqualTo(Long::class.java)

    val variables = Variables.createVariables()

    fooVariable.adapter(variables.readWriteAdapter()).write(5L)

    assertThat(variables["foo"]).isEqualTo(5L)

    assertThat(variables.readAdapter().read(fooVariable)).hasValue(5L)
    assertThat(variables.readAdapter().read(longVariable("bar"))).isEmpty
  }

  @Test
  fun `enum variable`() {
    val enumVariable = enumVariable("foo", DummyEnum::class.java)
    val variables = Variables.createVariables()

    val adapter = enumVariable.adapter(variables.readWriteAdapter())

    adapter.write(DummyEnum.HELLO)

    assertThat(variables["foo"]).isEqualTo(DummyEnum.HELLO.name)

    assertThat(adapter.readRequired()).isEqualTo(DummyEnum.HELLO)
  }

}