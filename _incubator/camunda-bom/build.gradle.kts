plugins {
    `java-platform`
    `maven-publish`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.camunda.bpm:camunda-bom:${Versions.camunda}"))
}

publishing {
    publications {
        create<MavenPublication>("camunda-bom") {
            from(components["javaPlatform"])
        }
    }
}
