plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.nevrozq.chatar.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.nevrozq.chatar.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common:umbrella-compose"))
    implementation(project(":common:umbrella-core"))
    implementation(project(":common:core"))
    implementation(project(":common:utils"))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(Dependencies.Decompose.decompose)
    implementation(Dependencies.Android.runtime)
}