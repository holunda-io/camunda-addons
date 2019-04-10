package io.holunda.addons.camunda.junit5

import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.ProcessEngineServices
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl
import org.camunda.bpm.engine.impl.test.TestHelper
import org.camunda.bpm.engine.impl.util.ClockUtil
import org.junit.jupiter.api.extension.*


class ProcessEngineExtension(
    private val processEngine: ProcessEngine
) : ProcessEngineServices by processEngine,
    BeforeEachCallback,
    AfterEachCallback,
BeforeAllCallback,
AfterAllCallback{

  override fun beforeAll(context: ExtensionContext?) {
    println(" -------------------     beforeall")
  }

  override fun afterAll(context: ExtensionContext?) {
    println(" -------------------     afterall")
  }

  companion object {
    val NAMESPACE: ExtensionContext.Namespace = nameSpace(this::class.java.simpleName)

    operator fun invoke(configuration: ProcessEngineConfigurationImpl): ProcessEngineExtension = ProcessEngineExtension(configuration.buildProcessEngine())
  }

  var deploymentId: String? = null
  var additionalDeployments = mutableListOf<String>()
  var ensureCleanAfterTest = false

  override fun beforeEach(context: ExtensionContext) {
    deploymentId = TestHelper.annotationDeploymentSetUp(
        processEngine,
        context.requiredTestClass,
        context.requiredTestMethod.name
    )
  }

  override fun afterEach(context: ExtensionContext) {
    identityService.clearAuthentication()
    processEngine.processEngineConfiguration.isTenantCheckEnabled = true

    TestHelper.annotationDeploymentTearDown(processEngine, deploymentId, context.requiredTestClass, context.requiredTestMethod.name)
    for (additionalDeployment in additionalDeployments) {
      TestHelper.deleteDeployment(processEngine, additionalDeployment)
    }

    if (ensureCleanAfterTest) {
      TestHelper.assertAndEnsureCleanDbAndCache(processEngine)
    }

    TestHelper.resetIdGenerator(processEngine.processEngineConfiguration as ProcessEngineConfigurationImpl)
    ClockUtil.reset()
  }

  fun manageDeployment(deployment: org.camunda.bpm.engine.repository.Deployment) {
    this.additionalDeployments.add(deployment.id)
  }

}