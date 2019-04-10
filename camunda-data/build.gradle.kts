import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val basePackage = "io.holunda.camunda.addons.dmn"

plugins {
  kotlin("jvm")
  `java-library`
}

dependencies {


  api("org.camunda.commons:camunda-commons-typed-values:${Versions.typedValues}")

  compile(kotlin("stdlib-jdk8"))

}
repositories {
  mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
  jvmTarget = "1.8"
}