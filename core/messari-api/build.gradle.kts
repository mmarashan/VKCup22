
import io.volgadev.sampleapp.project.AndroidProject
import io.volgadev.sampleapp.project.Dependencies
import io.volgadev.sampleapp.project.Modules
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(Modules.Core.network))
    implementation(project(Modules.Core.common))
    implementation(Dependencies.Kotlin.Serialization.json)
}

