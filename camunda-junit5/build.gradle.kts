import _buildsrc.junit5

plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(junit5("api"))

    implementation("org.camunda.bpm:camunda-engine:${Versions.camunda}")

    testRuntimeOnly(junit5("engine"))
    testImplementation("com.h2database:h2:1.4.197")
    testImplementation("ch.qos.logback:logback-classic:1.2.3")
}


tasks {

    withType<Test> {
        useJUnitPlatform{}
    }
}
