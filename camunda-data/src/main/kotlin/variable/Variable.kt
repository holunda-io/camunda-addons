package io.holunda.camunda.addons.data.variable

import io.holunda.camunda.addons.data.variable.accessor.VariableReadAccessor
import io.holunda.camunda.addons.data.variable.accessor.VariableReadWriteAccessor
import io.holunda.camunda.addons.data.variable.adapter.VariableReadAdapter
import io.holunda.camunda.addons.data.variable.adapter.VariableReadWriteAdapter
import java.util.function.Function

/**
 * Defines a variable by key and type.
 *
 * Provides converters for read and write.
 */
@Suppress("UNCHECKED_CAST")
class Variable<T>(
    override val key:String,
    val type: Class<T>,
    private val readConverter: Function<Any, T> = Function { it as T  },
    private val writeConverter: Function<T, Any> = Function { it as Any }
  ) : VariableKey {

  fun convertForWrite(value : T) : Any = writeConverter.apply(value)
  fun convertForRead(value : Any) : T = readConverter.apply(value)

  fun adapter(adapter: VariableReadAdapter) = VariableReadAccessor(this, adapter)
  fun adapter(adapter: VariableReadWriteAdapter) = VariableReadWriteAccessor(this, adapter)
}
