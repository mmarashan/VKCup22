import io.volgadev.sampleapp.project.AndroidProject
import io.volgadev.sampleapp.project.Dependencies

plugins {
    id("com.android.library")
    kotlin("android")
}

android {

    compileSdk = AndroidProject.compileSdkVersion
    buildToolsVersion = AndroidProject.buildToolsVersion

    buildFeatures {
        compose = true
        buildConfig = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.composeCompilerVersion
    }

    defaultConfig {
        minSdk = AndroidProject.minSdkVersion
        targetSdk = AndroidProject.targetSdkVersion
    }
}

dependencies {
    api(Dependencies.AndroidX.Compose.ui)
    api(Dependencies.AndroidX.Compose.activity)
    api(Dependencies.AndroidX.Compose.compiler)
    api(Dependencies.AndroidX.Compose.runtime)
    api(Dependencies.AndroidX.Compose.themeAdapter)
    api(Dependencies.AndroidX.Compose.materialIcons)
    api(Dependencies.AndroidX.Compose.material)
    api(Dependencies.AndroidX.Compose.tooling)
    api(Dependencies.AndroidX.Accompanist.insets)
    api(Dependencies.AndroidX.Accompanist.accompanistPlaceholder)
}