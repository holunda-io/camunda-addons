import _buildsrc.junit5

plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    api("org.camunda.bpm:camunda-engine:${Versions.camunda}")
}

