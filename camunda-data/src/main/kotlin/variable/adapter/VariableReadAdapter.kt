package io.holunda.addons.camunda.data.variable.adapter

import io.holunda.addons.camunda.data.variable.Variable
import io.holunda.addons.camunda.data.variable.exception.VariableNotFoundException
import io.holunda.addons.camunda.data.variable.exception.variableNotFound
import java.util.*


/**
 * Allows read access to given variable. Uses variable#convert to modify the saved value if needed.
 */
interface VariableReadAdapter {

  /**
   * Tries to read value of given variable. Optional is empty if non found.
   *
   * @param the variable (key/type)
   * @return optional value
   */
  fun <T> read(variable: Variable<T>): Optional<T>

  /**
   * Reads the value of given variable. Error if value can not be found.
   *
   * @paramthe variable /key, type)
   * @return value
   * @throws VariableNotFoundException if no value exists
   */
  @Throws(VariableNotFoundException::class)
  fun <T> readRequired(variable: Variable<T>): T = read(variable).orElseThrow(variableNotFound(variable))
}