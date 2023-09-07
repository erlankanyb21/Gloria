plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("native.cocoapods")
}
version = "0.0.1"

kotlin{
    cocoapods{
        summary = "Gloria IOS SDK"
        homepage = "https://google.com"
        ios.deploymentTarget = "16.0"

        framework {
            transitiveExport = false
            baseName = "SharedSDK"
            export(project(":common:core"))
            export(project(":common:core-utils"))
            export(project(":common:umbrella-core"))
        }
    }

    sourceSets{
        commonMain{
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:core-utils"))
                implementation(project(":common:umbrella-core"))
            }
        }

        iosMain{
            dependencies {
                api(project(":common:core"))
                api(project(":common:core-utils"))
                api(project(":common:umbrella-core"))
            }
        }

        ios{
            compilations.all{
                kotlinOptions{
                    freeCompilerArgs = freeCompilerArgs + "-Xbundle-id=org.tbm.gloria.android"
                }
            }
        }
    }
}