package io.holunda.camunda.addons.data.variable.adapter

import io.holunda.camunda.addons.data.variable.Variable

/**
 * Allows read and write access to given variable
 */
interface VariableReadWriteAdapter : VariableReadAdapter {
  fun <T> write(variable: Variable<T>, value: T): T
}