plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

kotlin {
    androidTarget()
    jvm("desktop")
    ios()

    sourceSets {

        commonMain {
            dependencies {
                //api(Dependencies.Moko.Resources.res)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}
