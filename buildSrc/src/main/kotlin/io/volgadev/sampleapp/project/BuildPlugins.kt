package io.volgadev.sampleapp.project

object BuildPlugins {

    const val gradle = "com.android.tools.build:gradle:7.1.3"

    object Kotlin {
        const val core = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
    }
}