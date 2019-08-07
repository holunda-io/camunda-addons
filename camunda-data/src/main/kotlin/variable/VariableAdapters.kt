package io.holunda.addons.camunda.data.variable

import io.holunda.addons.camunda.data.variable.adapter.VariableReadAdapter
import io.holunda.addons.camunda.data.variable.adapter.VariableReadWriteAdapter
import org.camunda.bpm.engine.CaseService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.delegate.VariableScope
import java.util.*


fun RuntimeService.readAdapter(processInstanceId: String): VariableReadAdapter = object : VariableReadAdapter {
  override fun <T> read(variable: Variable<T>): Optional<T> = read(variable) { this@readAdapter.getVariable(processInstanceId, variable.key) }
}
fun RuntimeService.readWriteAdapter(processInstanceId: String): VariableReadWriteAdapter = object : VariableReadWriteAdapter {
  override fun <T> write(variable: Variable<T>, value: T): T = write(variable, value) { v -> this@readWriteAdapter.setVariable(processInstanceId, variable.key, v) }.let { value }
  override fun <T> read(variable: Variable<T>): Optional<T> = this@readWriteAdapter.readAdapter(processInstanceId).read(variable)
}


fun Map<String, Any>.readAdapter() = object : VariableReadAdapter {
  override fun <T> read(variable: Variable<T>): Optional<T> = read(variable) { this@readAdapter[variable.key] }
}
fun MutableMap<String, Any>.readWriteAdapter() = object : VariableReadWriteAdapter {
  override fun <T> write(variable: Variable<T>, value: T): T = write(variable, value) { v -> this@readWriteAdapter[variable.key] = v }.let { value }
  override fun <T> read(variable: Variable<T>): Optional<T> = this@readWriteAdapter.readAdapter().read(variable)
}

fun VariableScope.readAdapter() = object : VariableReadAdapter {
  override fun <T> read(variable: Variable<T>): Optional<T> = read(variable) { this@readAdapter.getVariable(variable.key) }
}
fun VariableScope.readWriteAdapter() = object : VariableReadWriteAdapter {
  override fun <T> write(variable: Variable<T>, value: T): T = write(variable, value) { v -> this@readWriteAdapter.setVariable(variable.key, v) }.let { value }
  override fun <T> read(variable: Variable<T>): Optional<T> = this@readWriteAdapter.readAdapter().read(variable)
}


fun CaseService.readAdapter(caseInstanceId: String): VariableReadAdapter = object : VariableReadAdapter {
  override fun <T> read(variable: Variable<T>): Optional<T> = read(variable) { this@readAdapter.getVariable(caseInstanceId, variable.key) }
}
fun CaseService.readWriteAdapter(caseInstanceId: String): VariableReadAdapter = object : VariableReadWriteAdapter {
  override fun <T> read(variable: Variable<T>): Optional<T> = this@readWriteAdapter.readAdapter(caseInstanceId).read(variable)
  override fun <T> write(variable: Variable<T>, value: T): T = write(variable, value) { v -> this@readWriteAdapter.setVariable(caseInstanceId, variable.key, v) }.let { value }
}


private fun <T> read(variable: Variable<T>, getVariable: () -> Any?) = Optional.ofNullable(getVariable()).map { variable.convertForRead(it) }
private fun <T> write(variable: Variable<T>, value: T, setVariable: (Any) -> Unit) = setVariable(variable.convertForWrite(value))
