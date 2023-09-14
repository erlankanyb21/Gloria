object Dependencies {
    object Kodein {
        const val core = "org.kodein.di:kodein-di:7.1.0"
    }

    object Settings {
        const val core = "com.russhwolf:multiplatform-settings:1.0.0-RC"
        const val noargs = "com.russhwolf:multiplatform-settings-no-arg:1.0.0-RC"
    }

    object SplashApi {
        private const val version = "1.0.1"
        const val core = "androidx.core:core-splashscreen:$version"
    }

    object Image {
        object Coil {
            const val version = "2.2.2"
            const val core = "io.coil-kt:coil:2.2.2"
            const val compose = "io.coil-kt:coil-compose:2.2.2"
        }
    }

    object Kotlin {
        private const val version = "1.7.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Serialization {
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:1.6.21"
            const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.2.2"
        }

        object Coroutines {
            private const val version = "1.6.4"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object Compose {
        private const val version = "1.4.0"
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$version"
    }

    object Ktor {
        private const val version = "2.1.0"
        const val core = "io.ktor:ktor-client-core:$version"
        const val json = "io.ktor:ktor-client-json:$version"
        const val ios = "io.ktor:ktor-client-ios:$version"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val kotlin_json = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val serialization = "io.ktor:ktor-client-serialization:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
        const val android = "io.ktor:ktor-client-android:$version"
        const val okhttp = "io.ktor:ktor-client-okhttp:$version"
    }

    object SqlDelight {
        private const val version = "1.5.3"

        const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$version"
        const val core = "com.squareup.sqldelight:runtime:$version"
        const val android = "com.squareup.sqldelight:android-driver:$version"
        const val desktop = "com.squareup.sqldelight:sqlite-driver:$version"
        const val ios = "com.squareup.sqldelight:native-driver:$version"
    }

    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:7.2.2"
        const val composeActivity = "androidx.activity:activity-compose:1.5.1"
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10"

        object Compose {
            private const val version = "1.4.3"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val icons = "androidx.compose.material:material-icons-core:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val material = "androidx.compose.material:material:$version"
            const val activityCompose = "androidx.activity:activity-compose:1.7.1"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val navigation = "androidx.navigation:navigation-compose:2.6.0"
        }
    }

    object Other {
        object ViewModel {
            private const val version = "0.7.1"
            const val core = "com.adeo:kviewmodel:$version"
            const val compose = "com.adeo:kviewmodel-compose:$version"
            const val odyssey = "com.adeo:kviewmodel-odyssey:$version"
        }

        object Navigation {
            private const val version = "1.0.0-beta15"
            const val core = "io.github.alexgladkov:odyssey-core:$version"
            const val compose = "io.github.alexgladkov:odyssey-compose:$version"
        }
    }
}