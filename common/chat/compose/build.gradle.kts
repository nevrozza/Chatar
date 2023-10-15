plugins {
    id("android-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {

                implementation(project(":common:chat:presentation"))
//                implementation(project(":common:settings:compose"))
                ////implementation(":common:settings:presentation")
                implementation(project(":common:core"))

                //implementation(project(":common:utils-compose"))

                //implementation(Dependencies.Moko.Resources.compose)
                implementation(project(":common:utils"))
                implementation(project(":common:utils-compose"))

                implementation(Dependencies.Decompose.decompose)
            }
        }


    }
}