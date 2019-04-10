import org.gradle.kotlin.dsl.embeddedKotlinVersion

object Versions {
  val kotlin = embeddedKotlinVersion

  const val camunda = "7.10.0"
  const val camundaEE = "7.10.2-ee"

  const val junit5 = "5.3.2"

  const val typedValues = "1.6.1"
}

object Gav {
  val groupId = "io.holunda.camunda.addons"
}
