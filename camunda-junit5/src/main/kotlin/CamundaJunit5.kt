package io.holunda.addons.camunda.junit5

import org.junit.jupiter.api.extension.ExtensionContext

// empty, marker to span up package namespace

fun nameSpace(extensionName: String) : ExtensionContext.Namespace = ExtensionContext.Namespace.create(
    "io", "holunda", "addons", "camunda", "junit5", extensionName
)
