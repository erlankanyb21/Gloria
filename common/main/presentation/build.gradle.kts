plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":common:main:api"))
                api(project(":common:core"))
                api(Dependencies.Other.ViewModel.core)
            }
        }
    }
}