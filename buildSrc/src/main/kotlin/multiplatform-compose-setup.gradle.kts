plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}
kotlin{
    android()
    sourceSets{
        named("commonMain"){
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        named("androidMain"){
            dependencies {
                implementation(Dependencies.Android.kotlinStdlib)
                implementation(Dependencies.Android.Compose.runtime)
                implementation(Dependencies.Android.Compose.icons)
                implementation(Dependencies.Android.Compose.ui)
                implementation(Dependencies.Android.Compose.tooling)
                implementation(Dependencies.Android.Compose.toolingPreview)
                implementation(Dependencies.Android.Compose.foundation)
                implementation(Dependencies.Android.Compose.material)
                implementation(Dependencies.Android.Compose.materialThree)
                implementation(Dependencies.Android.Compose.materialWindow)
                implementation(Dependencies.Android.Compose.activityCompose)
                implementation(Dependencies.Android.Compose.iconsExtended)
                implementation(Dependencies.Android.Compose.navigation)
            }
        }

    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>{
        kotlinOptions.jvmTarget = "11"
    }
}