import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.buildkonfig)

    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(11)

    android()
    jvm("desktop")

    @Suppress("UnusedPrivateMember")
    sourceSets {
        all {
            languageSettings.apply {
                progressiveMode = true
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            }
        }

        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.materialIconsExtended)
                // Needed only for preview.
                implementation(compose.preview)

                implementation(libs.kamel)
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
                implementation(libs.decompose.core)
                implementation(libs.androidx.activity.compose)
            }
        }

        val desktopMain by getting {
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

    // This is needed due to https://issuetracker.google.com/issues/260059413
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

buildkonfig {
    packageName = "com.alapshin.multiplayground"
    defaultConfigs {
        buildConfigField(Type.STRING, "API_URL", "https://reqres.in/api/", const = true)
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
