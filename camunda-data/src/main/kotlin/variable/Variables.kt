package io.holunda.camunda.addons.data.variable

import java.util.function.Function
import kotlin.reflect.KClass

fun stringVariable(key: String) = typedVariable(key, String::class.java)
fun stringVariable(variableKey: VariableKey) = stringVariable(variableKey.key)

fun longVariable(key: String) = typedVariable(key, Long::class.java)
fun longVariable(variableKey: VariableKey) = longVariable(variableKey.key)

fun booleanVariable(key: String) = typedVariable(key, Boolean::class.java)
fun booleanVariable(variableKey: VariableKey) = booleanVariable(variableKey.key)

fun <T : Enum<T>>enumVariable(key:String, type: Class<T>) = Variable(key, type,
    Function { value -> type.enumConstants.find { it.name == value }
          ?: error("no enum found for name=$value (type=$type)")
    },
    Function { it.name }
)
fun <T : Enum<T>>enumVariable(variableKey: VariableKey, type: Class<T>) = enumVariable(variableKey.key, type)

fun <T> typedVariable(key:String, type: Class<T>) = Variable(key, type)
fun <T> typedVariable(variableKey: VariableKey, type: Class<T>) = typedVariable(variableKey.key, type)

