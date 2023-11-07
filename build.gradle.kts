import org.gradle.internal.jvm.Jvm
import java.io.ByteArrayOutputStream
import java.nio.file.Paths

val ktorVersion="2.3.5"
val vkSdk="0.0.8"

plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "kiko"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.petersamokhin.vksdk:core:$vkSdk")
    implementation("com.petersamokhin.vksdk:http-client-jvm-okhttp:$vkSdk")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}