plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                // Needed only for preview.
                implementation(compose.preview)
            }
        }
        val androidMain by getting
    }
}

android {
    namespace = "com.example.multiplayground"
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}