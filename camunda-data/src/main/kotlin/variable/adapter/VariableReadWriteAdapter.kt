package io.holunda.addons.camunda.data.variable.adapter

import io.holunda.addons.camunda.data.variable.Variable

/**
 * Allows read and write access to given variable
 */
interface VariableReadWriteAdapter : VariableReadAdapter {
  fun <T> write(variable: Variable<T>, value: T): T
}