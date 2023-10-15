plugins {
    id("android-setup")
    id("multiplatform-setup")
    id("app.cash.sqldelight") version "2.0.0"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {

                //(project(":common:auth:api"))


                api(Dependencies.Kotlin.Coroutines.core)

                implementation(Dependencies.Settings.core)
                implementation(Dependencies.Settings.noargs)

                api(Dependencies.Kodein.core)
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

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.nevrozq.chatar")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases/schema"))
            migrationOutputDirectory.set(file("src/commonMain/sqldelight/databases/migrations"))
        }
    }
}