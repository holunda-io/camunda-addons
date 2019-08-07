import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val basePackage = "io.holunda.camunda.addons.dmn"

plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    api("org.camunda.commons:camunda-commons-typed-values:${Versions.typedValues}")
    api(kotlin("stdlib-jdk8"))
    implementation("org.camunda.bpm:camunda-engine:${Versions.camunda}")

    implementation(kotlin("reflect"))

    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-core:2.10.0")

}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"

    // not an option since a kotlin app that uses this also has to set the compiler args
   //  freeCompilerArgs = listOf("-Xjvm-default=enable")
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}