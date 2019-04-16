import org.gradle.kotlin.dsl.embeddedKotlinVersion

object Versions {
  val kotlin = embeddedKotlinVersion

  const val camunda = "7.10.0"
  const val camundaEE = "7.10.5-ee"

  const val junit5 = "5.4.1"

  const val typedValues = "1.6.1"
}

object Gav {
  val groupId = "io.holunda.camunda.addons"
}
