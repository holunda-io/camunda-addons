import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("java-library")
    }

    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
}

