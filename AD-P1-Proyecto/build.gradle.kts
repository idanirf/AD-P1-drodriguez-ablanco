import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
    // Serializacion importante
    kotlin("plugin.serialization") version "1.7.10"
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
    

    //provando otro tipo de json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    // probando otro tipo de xml
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.84.3")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.84.3")

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
