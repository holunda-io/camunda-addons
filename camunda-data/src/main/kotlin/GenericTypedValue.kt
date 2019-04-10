package io.holunda.camunda.addons.typedvalues

import org.camunda.bpm.engine.variable.type.ValueType
import org.camunda.bpm.engine.variable.value.TypedValue

data class GenericTypedValue(val name:String) : TypedValue {
  override fun getType(): ValueType {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getValue(): Any {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun isTransient(): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
