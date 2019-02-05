package io.holunda.camunda.addons.dmn.evaluation

import io.holunda.camunda.addons.dmn.loadDecisionTable
import org.camunda.bpm.engine.RepositoryService

class DetermineDecisionTableInputParameters(private val repositoryService: RepositoryService) : java.util.function.Function<String, Set<String>> {

  private val cache = mutableMapOf<String,Set<String>>()

  override fun apply(decisionTableKey: String): Set<String> = cache.computeIfAbsent(decisionTableKey) {
      repositoryService.loadDecisionTable(it)
          .inputs
          .map { input ->  input.textContent }
          .toSet()
  }
}

