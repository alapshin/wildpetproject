plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)

    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    android()
    jvm("desktop")

    @Suppress("UnusedPrivateMember")
    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlinx.coroutines.ExperimentalComposeLibrary")
                optIn("org.jetbrains.compose.ExperimentalComposeLibrary")
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            }
        }

        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                // Needed only for preview.
                implementation(compose.preview)

                implementation(libs.decompose.core)
                implementation(libs.decompose.extensions)
                implementation(libs.essenty.parcelable)
                implementation(libs.bundles.mvikotlin)

                implementation(libs.kermit)
                implementation(libs.kotlin.inject.runtime)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)

                implementation(libs.ktorfit.lib)
                implementation(libs.bundles.ktor.client)
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
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

dependencies {
    add("kspDesktop", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspCommonMainMetadata", libs.ktorfit.ksp)

    add("kspDesktop", libs.kotlin.inject.ksp)
    add("kspAndroid", libs.kotlin.inject.ksp)
    add("kspCommonMainMetadata", libs.kotlin.inject.ksp)
}
