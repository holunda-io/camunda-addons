dependencies {
    implementation(project(":camunda-data"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.camunda.bpm:camunda-engine:${Versions.camunda}")

    implementation(kotlin("reflect"))

    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-core:2.10.0")
}