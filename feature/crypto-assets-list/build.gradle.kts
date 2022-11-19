
import io.volgadev.sampleapp.project.AndroidProject
import io.volgadev.sampleapp.project.Dependencies
import io.volgadev.sampleapp.project.Modules

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    kotlin(module = "android")
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
    implementation(Dependencies.Logger.core)
    implementation(Dependencies.Kotlin.Serialization.json)
    implementation(Dependencies.Koin.compose)
    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Navigation.navigationCompose)

    implementation(Dependencies.AndroidX.Compose.compiler)
    implementation(Dependencies.AndroidX.Compose.runtime)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.tooling)
    implementation(Dependencies.AndroidX.Compose.toolingPreview)
    implementation(Dependencies.AndroidX.Compose.activity)

    implementation(project(Modules.Core.messariApi))
    implementation(project(Modules.Core.common))
    implementation(project(Modules.Core.featureApi))
}
