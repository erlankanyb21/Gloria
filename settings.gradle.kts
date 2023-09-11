pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Gloria"
include(":androidApp")
include(":common:core")
include(":common:umbrella-ios")
include(":common:umbrella-compose")
include(":common:umbrella-core")
include(":common:core-compose")
include(":common:core-utils")
include(":common:main:compose")
include(":common:auth:api")
include(":common:auth:compose")
include(":common:auth:data")
include(":common:auth:presentation")



