buildscript {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }

    dependencies {
        classpath(dependencyNotation = io.volgadev.sampleapp.project.BuildPlugins.gradle)
        classpath(dependencyNotation = io.volgadev.sampleapp.project.BuildPlugins.Kotlin.core)
        classpath(dependencyNotation = io.volgadev.sampleapp.project.BuildPlugins.Kotlin.serialization)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
}

val clean by tasks.registering(Delete::class) { delete(rootProject.buildDir) }
