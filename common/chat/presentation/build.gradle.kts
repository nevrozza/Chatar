plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))

                api(project(":common:chat:api"))
//                api(project(":common:settings:api"))
                implementation(project(":common:utils"))

//                api(project(":common:settings:presentation"))
                implementation(Dependencies.Kotlin.DateTime.dateTime)
                implementation(Dependencies.Decompose.decompose)
            }
        }
    }
}