plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:chat:api"))
                implementation(Dependencies.Decompose.decompose)
            }
        }
    }
}