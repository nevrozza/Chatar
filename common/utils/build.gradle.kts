plugins {
    id("android-setup")
    id("multiplatform-setup")
    //id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {

                //api(Dependencies.Moko.Resources.res)
            }

        }
    }
}
