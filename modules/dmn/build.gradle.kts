val basePackage = "io.holunda.camunda.addons.dmn"

plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {

    implementation("org.camunda.bpm:camunda-engine:${Versions.camunda}")

}