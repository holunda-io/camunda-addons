package io.holunda.addons.camunda.junit5

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace

class CamundaExtension : BeforeAllCallback , BeforeEachCallback {
  override fun beforeEach(context: ExtensionContext?) {
    println("""


      ---- BEFORE EACH


    """.trimIndent())
  }

  companion object {
    val NAMESPACE : Namespace = Namespace.create(
        "io", "holunda", "addons", "camunda", "junit5", "CamundaExtension"
    )
  }

  override fun beforeAll(ctx: ExtensionContext) {
    println("""


      ---- BEFORE ALL


    """.trimIndent())
  }

}