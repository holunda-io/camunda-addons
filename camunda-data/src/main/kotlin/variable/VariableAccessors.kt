package io.holunda.addons.camunda.data.variable

import io.holunda.addons.camunda.data.variable.readWriteAdapter
import org.camunda.bpm.engine.CaseService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.delegate.VariableScope

fun <T> Variable<T>.adapter(runtimeService: RuntimeService, processInstanceId: String) = this.adapter(runtimeService.readWriteAdapter(processInstanceId))
fun <T> Variable<T>.adapter(variables: MutableMap<String, Any>) = this.adapter(variables.readWriteAdapter())
fun <T> Variable<T>.adapter(variableScope: VariableScope) = this.adapter(variableScope.readWriteAdapter())
fun <T> Variable<T>.adapter(caseService: CaseService, caseInstanceId: String) = this.adapter(caseService.readWriteAdapter(caseInstanceId))
