package io.holunda.addons.camunda.data

import java.util.*

/**
 * A variable is identified by its key.
 *
 * Can be used if enum/string setup for variables is used.
 */
interface VariableKey {
    val key: String
}

interface Variable<T : Any> : VariableKey {
    override val key: String
    val type: Class<T>
}

interface VariableReader {
    fun <T : Any> read(variable: Variable<T>): Optional<T>

    fun <T : Any> readRequired(variable: Variable<T>): T = read(variable).orElseThrow { VariableNotFoundException(variable) }
}

/**
 * Exception raised when a variable key can not be found on VariableReader#readRequired.
 */
class VariableNotFoundException(variable: Variable<*>) : IllegalStateException("no value found for variable:${variable.key}")
