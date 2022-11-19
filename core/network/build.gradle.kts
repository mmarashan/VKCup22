
import io.volgadev.sampleapp.project.AndroidProject
import io.volgadev.sampleapp.project.Dependencies
import io.volgadev.sampleapp.project.Modules

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    kotlin(module = "android")
}

android {

    compileSdk = AndroidProject.compileSdkVersion
    buildToolsVersion = AndroidProject.buildToolsVersion

    defaultConfig {
        minSdk = AndroidProject.minSdkVersion
        targetSdk = AndroidProject.targetSdkVersion
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Dependencies.Logger.core)
    implementation(Dependencies.Kotlin.Serialization.json)
    implementation(Dependencies.Kotlin.Serialization.ktor)
    api(Dependencies.Api.Ktor.core)
    api(Dependencies.Api.Ktor.logging)
    implementation(project(Modules.Core.common))
}