
import io.volgadev.sampleapp.project.AndroidProject
import io.volgadev.sampleapp.project.Dependencies
import io.volgadev.sampleapp.project.Modules

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    kotlin("android")
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
    api(project(Modules.Core.network))
    implementation(project(Modules.Core.common))
    implementation(Dependencies.Kotlin.Serialization.json)
}

