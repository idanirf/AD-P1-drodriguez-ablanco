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

    //grandle
    //implementation 'com.google.code.gson:gson:2.8.6'

    //logger
    //implementation 'com.orhanobut:logger:2.2.0'

    //probando libreria de xml
    // https://mvnrepository.com/artifact/xml-apis/xml-apis
    //implementation 'xml-apis:xml-apis:1.4.01'




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
