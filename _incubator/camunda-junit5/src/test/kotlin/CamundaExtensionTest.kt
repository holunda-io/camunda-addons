package io.holunda.addons.camunda.junit5

import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration
import org.camunda.bpm.engine.impl.history.HistoryLevel
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.mock.MockExpressionManager
import org.junit.jupiter.api.extension.RegisterExtension
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_DROP_CREATE
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith


@Disabled
@ExtendWith(CamundaExtension::class)
@Deployment(resources = ["dummy.bpmn"])
class CamundaExtensionTest {

  @RegisterExtension
  val camunda = ProcessEngineExtension(StandaloneInMemProcessEngineConfiguration().apply {
    historyLevel = HistoryLevel.HISTORY_LEVEL_FULL
    expressionManager = MockExpressionManager()
    isJobExecutorActivate = false
    isMetricsEnabled = false
    isCmmnEnabled = false
    databaseSchemaUpdate = DB_SCHEMA_UPDATE_DROP_CREATE
  })

  @Test
  internal fun `process gets deployed`() {
    val definitions = camunda.repositoryService.createProcessDefinitionQuery().list()
    assertAll(
        {   !definitions.isEmpty() },
        {   definitions.map { it.id }.first() == "Process_1" }
    )
  }

  @Test
  internal fun `deploy and start`() {
    val processInstance = camunda.runtimeService.startProcessInstanceByKey("Process_1")


  }
}