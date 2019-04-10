@file:Suppress("PackageDirectoryMismatch", "unused")
package _buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler


fun DependencyHandler.junit5(module: String): Any = "org.junit.jupiter:junit-jupiter-$module:${Versions.junit5}"


//
//compile("io.ktor:ktor-server-netty:${Versions.ktor}")
//compile("io.ktor:ktor-html-builder:${Versions.ktor}")
//
//
//testCompile("io.ktor:ktor-server-tests:${Versions.ktor}")
//compile("io.projectreactor:reactor-core:3.2.6.RELEASE")
//testCompile("io.projectreactor:reactor-test:3.2.6.RELEASE")
