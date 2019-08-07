import org.gradle.kotlin.dsl.embeddedKotlinVersion

object Versions {
  val kotlin = embeddedKotlinVersion
  val java = "1.8"

  val springBoot = "2.1.6.RELEASE"

  const val camunda = "7.11.0"
  const val camundaEE = "7.11.2-ee"
  const val camundaSpringBoot = "3.3.2"

  const val junit5 = "5.4.1"

  const val typedValues = "1.7.1"
}

object Gav {
  val groupId = "io.holunda.camunda.addons"
}
