import io.volgadev.sampleapp.project.AndroidProject
import io.volgadev.sampleapp.project.Dependencies
import io.volgadev.sampleapp.project.Modules

plugins {
    id("com.android.library")
    kotlin("android")
}

android {

    compileSdk = AndroidProject.compileSdkVersion
    buildToolsVersion = AndroidProject.buildToolsVersion

    defaultConfig {
        minSdk = AndroidProject.minSdkVersion
        targetSdk = AndroidProject.targetSdkVersion
    }

    buildFeatures {
        buildConfig = false
    }
}

dependencies {
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Core.coreKtx)
    implementation(Dependencies.Navigation.navigationCompose)

    implementation(project(Modules.Core.common))
}