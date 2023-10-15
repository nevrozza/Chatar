pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "chatar"

include(":common:chat:api")
include(":common:chat:compose")
include(":common:chat:data")
include(":common:chat:presentation")

include(":common:main:api")
include(":common:main:compose")
//include(":common:launch:data")
include(":common:main:presentation")

include(":common:settings:api")
//include(":common:main:compose")
include(":common:settings:data")
//include(":common:main:presentation")


include(":androidApp")
include(":desktopApp")
include(":common:core")
include(":common:umbrella-compose")
include(":common:umbrella-core")
include(":common:umbrella-ios")
include(":common:utils")
include(":common:utils-compose")