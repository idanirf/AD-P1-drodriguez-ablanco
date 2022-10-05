import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "es.danizados"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://mvnrepository.com/artifact/xml-apis/xml-apis/1.4.01")



}

dependencies {
    testImplementation(kotlin("test"))

    //  Jackson  para Json
    //  https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.3")


    //logger
    implementation ("com.orhanobut:logger:2.2.0")

    //mockito para test
    // https://mvnrepository.com/artifact/org.mockito/mockito-core
    testImplementation ("org.mockito:mockito-core:4.6.1")

// https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.4")



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
