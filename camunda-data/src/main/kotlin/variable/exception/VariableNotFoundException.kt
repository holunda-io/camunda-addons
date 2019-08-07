package io.holunda.addons.camunda.data.variable.exception

import io.holunda.addons.camunda.data.variable.Variable
import java.util.function.Supplier


fun variableNotFound(variable: Variable<*>) : Supplier<VariableNotFoundException> = Supplier { VariableNotFoundException(variable) }

class VariableNotFoundException(variable: Variable<*>) : IllegalStateException("no value found for variable:${variable.key}")
