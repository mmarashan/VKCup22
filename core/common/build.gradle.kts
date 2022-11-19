
import io.volgadev.sampleapp.project.AndroidProject
import io.volgadev.sampleapp.project.Dependencies

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
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Core.coreKtx)
    implementation(Dependencies.Navigation.navigationCompose)
}