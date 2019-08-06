plugins {
    `java-library`
    id("org.springframework.boot") version Versions.springBoot
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}"))
    implementation(platform("org.camunda.bpm:camunda-bom:${Versions.camunda}"))

    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:${Versions.camundaSpringBoot}")
    implementation("com.h2database:h2")

    implementation(project(":camunda-data"))
}
