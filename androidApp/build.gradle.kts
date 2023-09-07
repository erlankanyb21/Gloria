plugins {
    id("com.android.application")
    id("org.jetbrains.compose")
    kotlin("android")
}

android {
    namespace = "org.tbm.gloria.android"
    compileSdk = 33
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "org.tbm.gloria.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            multiDexEnabled = false
        }
        getByName("debug") {
            multiDexEnabled = false
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":common:umbrella-compose"))
    implementation(project(":common:core"))
    implementation(project(":common:umbrella-core"))
    implementation(Dependencies.Android.kotlinStdlib)
    implementation(Dependencies.Android.Compose.runtime)
    implementation(Dependencies.Android.Compose.icons)
    implementation(Dependencies.Android.Compose.ui)
    implementation(Dependencies.Android.Compose.tooling)
    implementation(Dependencies.Android.Compose.toolingPreview)
    implementation(Dependencies.Android.Compose.foundation)
    implementation(Dependencies.Android.Compose.material)
    implementation(Dependencies.Android.Compose.activityCompose)
}