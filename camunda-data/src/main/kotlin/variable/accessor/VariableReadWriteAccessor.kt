package io.holunda.camunda.addons.data.variable.accessor

import io.holunda.camunda.addons.data.variable.Variable
import io.holunda.camunda.addons.data.variable.adapter.VariableReadWriteAdapter

/**
 * Read/Write typed value from/to given Variable using VariableReadWriteAdapter.
 */
class VariableReadWriteAccessor<T>(
    override val variable: Variable<T>,
    override val adapter: VariableReadWriteAdapter
) : VariableReadAccessor<T>(variable, adapter) {
  fun write(value: T) = adapter.write(variable, value)
}