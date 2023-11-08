import org.gradle.internal.jvm.Jvm
import java.io.ByteArrayOutputStream
import java.nio.file.Paths

val ktorVersion = "2.3.5"
val vkSdk = "0.0.8"

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.3"
    application
}

group = "kiko"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.kotlinizer:injection:1.0.4")
    implementation("com.petersamokhin.vksdk:core:$vkSdk")
    implementation("com.petersamokhin.vksdk:http-client-jvm-okhttp:$vkSdk")
    implementation("io.ktor:ktor-server-core:2.3.6")
    implementation("io.ktor:ktor-server-netty:2.3.6")
    implementation("io.ktor:ktor-server-status-pages:2.3.6")
    implementation("io.ktor:ktor-server-default-headers:2.3.6")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "kiko.ApplicationKt"
    }
}

application {
    mainClass.set("kiko.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}