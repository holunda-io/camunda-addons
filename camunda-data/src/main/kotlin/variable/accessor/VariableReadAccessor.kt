package io.holunda.camunda.addons.data.variable.accessor

import io.holunda.camunda.addons.data.variable.Variable
import io.holunda.camunda.addons.data.variable.adapter.VariableReadAdapter
import io.holunda.camunda.addons.data.variable.exception.variableNotFound
import java.util.*

/**
 * Read typed value of given Variable using VariableReadAdapter.
 */
open class VariableReadAccessor<T>(
    open val variable: Variable<T>,
    open val adapter : VariableReadAdapter
) {

  fun read() : Optional<T> = adapter.read(variable)
  fun readRequired() : T = read().orElseThrow(variableNotFound(variable))

}