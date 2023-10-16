plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("app.cash.sqldelight") version "2.0.0"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:chat:api"))
                implementation(project(":common:core"))
                implementation(project(":common:utils"))

                implementation(Dependencies.Kodein.core)
                implementation(Dependencies.SqlDelight.core)
            }
        }
        androidMain {
            dependencies {
                implementation(Dependencies.SqlDelight.android)
            }
        }

        iosMain {
            dependencies {
                implementation(Dependencies.SqlDelight.ios)
            }
        }

        desktopMain {
            dependencies {
                implementation(Dependencies.SqlDelight.desktop)
            }
        }
    }
}
//
//sqldelight {
//    database("Database") {
//        packageName = "com.nevrozq.chatar.chat"
//        dependency(project(":common:core"))
//    }
//}
