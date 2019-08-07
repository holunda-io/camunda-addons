package io.holunda.camunda.addons.data.variable.exception

import io.holunda.camunda.addons.data.variable.Variable
import java.util.function.Supplier

/**
 * Creates a Supplier of VariableNotFoundException to use with Optional#orElseThrow.
 */
fun variableNotFound(variable: Variable<*>) : Supplier<VariableNotFoundException> = Supplier { VariableNotFoundException(variable) }

/**
 * Raise this if a variable can not be found for given variable.
 */
class VariableNotFoundException(variable: Variable<*>) : IllegalStateException("no value found for variable:${variable.key}")
