import Versions


plugins {
  base
  idea

  kotlin("jvm") version Versions.kotlin
}


// set gav for project and repos
allprojects {
  group = "io.holunda.camunda.addons"
  version = "0.0.1-SNAPSHOT"

  repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
  }

}

dependencies {
  // Make the root project archives configuration depend on every sub-project
  subprojects.forEach {
    archives(it)
  }
}
