plugins {
    id("android-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:utils"))
                implementation(project(":common:core"))
                //implementation(Dependencies.Moko.Resources.compose)
                implementation(Dependencies.Decompose.decompose)
            }
        }
    }
}