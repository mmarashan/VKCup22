
import io.volgadev.sampleapp.project.AndroidProject
import io.volgadev.sampleapp.project.Dependencies
import io.volgadev.sampleapp.project.Modules

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    kotlin("android")
}

android {
    compileSdk = AndroidProject.compileSdkVersion
    defaultConfig {
        minSdk = AndroidProject.minSdkVersion
        targetSdk = AndroidProject.targetSdkVersion
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.composeCompilerVersion
    }
}

dependencies {
    implementation(Dependencies.Kotlin.Serialization.json)
    implementation(Dependencies.Koin.compose)
    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Navigation.navigationCompose)

    implementation(project(Modules.Core.common))
    implementation(project(Modules.Core.featureApi))

    implementation(project(Modules.Core.uiKit))
}
