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

                api(project(":common:main:api"))
//                api(project(":common:settings:api"))
                implementation(project(":common:utils"))

                api(project(":common:chat:presentation"))
                implementation(Dependencies.Decompose.decompose)
            }
        }
    }
}