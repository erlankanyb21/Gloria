plugins{
    id("com.android.library")
}
android{
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        compileSdk = 33
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
    dependencies {
        implementation(Dependencies.Android.Compose.runtime)

        implementation(Dependencies.SplashApi.core)
        implementation(Dependencies.Image.Coil.core)
        implementation(Dependencies.Image.Coil.compose)
        implementation(Dependencies.Android.Compose.dialogsCore)
        implementation(Dependencies.Android.Compose.calendarDialog)
    }
}