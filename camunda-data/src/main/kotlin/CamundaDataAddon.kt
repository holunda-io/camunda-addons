package io.holunda.addons.camunda.data

import java.util.*

/**
 * The key of a variable used to look it up in data stores and maps.
 *
 * Can be used if enum/string setup for variables is used.
 */
interface VariableKey {
    val key: String
}

/**
 * A variable ist defined by a name and a type.
 */
interface Variable<T : Any> : VariableKey {
    override val key: String
    val type: Class<T>
}

/**
 * Returns the value (if present) of given type stored under given key.
 */
interface VariableReader {
    fun <T : Any> read(variable: Variable<T>): Optional<T>

    fun <T : Any> readRequired(variable: Variable<T>): T = read(variable).orElseThrow { VariableNotFoundException(variable) }
}

/**
 * Stores a value of given type under the VariableKey of the given variable.
 *
 * For fluent usage (multiple writes), returns self.
 */
interface VariableWriter {
    fun <T : Any> write(variable:Variable<T>, value: T) : VariableWriter
}

/**
 * Exception raised when a variable key can not be found on VariableReader#readRequired.
 */
class VariableNotFoundException(variable: Variable<*>) : IllegalStateException("no value found for variable:${variable.key}")
