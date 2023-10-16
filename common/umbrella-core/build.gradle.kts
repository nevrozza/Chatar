plugins {
    id("android-setup")
    id("multiplatform-setup")
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                //implementation(project(":common:main:api"))
                implementation(project(":common:main:presentation"))
                implementation(project(":common:settings:data"))
                implementation(project(":common:chat:data"))
                implementation(Dependencies.Decompose.decompose)

                implementation(Dependencies.Kodein.core)
            }
        }

    }
}