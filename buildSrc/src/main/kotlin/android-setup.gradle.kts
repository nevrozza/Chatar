plugins {
    id("com.android.library")

}

android {
    namespace = "com.nevrozq.chatar.android"
    compileSdk = 34

    defaultConfig {
        targetSdk = 34
        minSdk = 21
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    sourceSets {
        named("main") {
            dependencies {
                implementation("androidx.compose.runtime:runtime:1.5.1")
            }
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }


}