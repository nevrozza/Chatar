plugins {
    //id("dev.icerock.mobile.multiplatform-resources").version("0.23.0")
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    mavenLocal()
    google()
    gradlePluginPortal()

    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://jitpack.io")
}


dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.10-1.0.13")
    implementation(Dependencies.Kotlin.gradlePlugin)
    implementation(Dependencies.Compose.gradlePlugin)
    implementation(Dependencies.Android.gradlePlugin)
//    implementation(Dependencies.Kotlin.Serialization.gradlePlugin)
    //implementation(Dependencies.Moko.Resources.gradlePlugin)
}
kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}


//multiplatformResources {
//    multiplatformResourcesPackage = "com.nevrozq.chatar"
//}