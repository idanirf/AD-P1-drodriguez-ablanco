import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "es.danizados"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()


}

dependencies {
    testImplementation(kotlin("test"))

//grandle
   // implementation 'com.google.code.gson:gson:2.8.6'




}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}