plugins {
    id("android-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))

                implementation(project(":common:utils-compose"))
                implementation(project(":common:utils"))

                implementation(project(":common:umbrella-core"))

                implementation(project(":common:settings:api"))
                implementation(project(":common:main:api"))
                implementation(project(":common:main:compose"))

                implementation(Dependencies.Decompose.decompose)
                implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

            }
        }

        androidMain {
            dependencies {
                implementation(Dependencies.Android.composeActivity)

                implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
            }
        }
    }
}