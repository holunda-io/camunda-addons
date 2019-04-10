import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val basePackage = "io.holunda.camunda.addons.dmn"

plugins {
  kotlin("jvm")
  `java-library`
}

dependencies {

  
  
  implementation("org.camunda.bpm:camunda-engine:${Versions.camunda}")
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