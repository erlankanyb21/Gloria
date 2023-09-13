plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin{
    sourceSets{
        commonMain{
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:core-compose"))
                implementation(project(":common:core-utils"))
                implementation(project(":common:main:compose"))
                implementation(project(":common:auth:compose"))

                implementation(Dependencies.Other.ViewModel.core)
                implementation(Dependencies.Other.ViewModel.compose)
                implementation(Dependencies.Other.ViewModel.odyssey)
                implementation(Dependencies.Other.Navigation.compose)
                implementation(Dependencies.Other.Navigation.core)
            }
        }

        androidMain{
            dependencies {
                implementation(project(":common:core-compose"))

                implementation(Dependencies.Android.composeActivity)

                implementation(Dependencies.SplashApi.core)
                implementation(Dependencies.Image.Coil.core)
                implementation(Dependencies.Image.Coil.compose)
            }
        }
    }
}