@file:Suppress("unused")

package io.holunda.camunda.addons.dmn

import org.camunda.bpm.engine.DecisionService
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.repository.DecisionDefinition
import org.camunda.bpm.engine.variable.VariableMap
import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.engine.variable.context.VariableContext
import org.camunda.bpm.model.dmn.DmnModelInstance
import org.camunda.bpm.model.dmn.impl.DmnModelConstants.DMN_ELEMENT_DECISION_TABLE
import org.camunda.bpm.model.dmn.instance.DecisionTable
import java.lang.IllegalStateException
import java.util.*

/**
 * Transform a VariableContext to VariableMap using VariableContext#resolve.
 */
fun VariableContext.variableMap(): VariableMap {
  val map = Variables.createVariables()

  this.keySet().forEach { map.putValueTyped(it, this.resolve(it)) }

  return map
}

/**
 * Evaluate decisionTable based on VariableContext.
 */
fun DecisionService.evaluateDecisionTableByKey(decisionTableKey: String, context: VariableContext) = this.evaluateDecisionTableByKey(decisionTableKey, context.variableMap())!!

fun RepositoryService.loadDecisionDefinition(decisionTableKey: String) = this
    .createDecisionDefinitionQuery()
    .decisionDefinitionKey(decisionTableKey)
    .latestVersion()
    .singleResult() ?: throw IllegalStateException()

fun RepositoryService.loadDecisionTable(decisionTableKey: String) = loadDecisionDefinition(decisionTableKey).id.let {
  this.getDmnModelInstance(it)
}.getDecisionTable()

fun DmnModelInstance.getDecisionTable(): DecisionTable = this.getModelElementById(DMN_ELEMENT_DECISION_TABLE)
