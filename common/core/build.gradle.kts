plugins {
    id("multiplatform-setup")
    id("android-setup")
}
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(Dependencies.Kotlin.Serialization.serialization)
                api(Dependencies.Kotlin.Coroutines.core)

                api(Dependencies.Ktor.core)
                implementation(Dependencies.Ktor.json)
                implementation(Dependencies.Ktor.serialization)
                implementation(Dependencies.Ktor.negotiation)
                implementation(Dependencies.Ktor.kotlin_json)
                implementation(Dependencies.Ktor.logging)
                implementation(Dependencies.Ktor.auth)

                implementation(Dependencies.Settings.core)
                implementation(Dependencies.Settings.noargs)

                api(Dependencies.Kodein.core)
//                api(Dependencies.SqlDelight.core)
            }
        }

        androidMain {
            dependencies {
                implementation(Dependencies.Ktor.android)
                implementation(Dependencies.Kotlin.Coroutines.android)
//                implementation(Dependencies.SqlDelight.android)
                implementation(Dependencies.SplashApi.core)
                implementation(Dependencies.Image.Coil.core)
                implementation(Dependencies.Image.Coil.compose)
            }
        }

        iosMain {
            dependencies {
                implementation(Dependencies.Ktor.ios)
//                implementation(Dependencies.SqlDelight.ios)
            }
        }
    }
}