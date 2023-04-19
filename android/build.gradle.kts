import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
}

@Suppress("UnusedPrivateProperty")
kotlin {
    jvmToolchain(19)

    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(projects.shared)
            }
        }
    }
}

android {
    kotlin {
        jvmToolchain(19)
    }

    namespace = "com.example.multiplayground.android"
    compileSdk = Versions.compileSdk

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.example.multiplayground.android"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionName = project.version.toString()
        versionCode = Versioning.version(rootProject.version.toString()).code()
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

    applicationVariants.all {
        outputs.filterIsInstance<BaseVariantOutputImpl>().forEach { output ->
            output.outputFileName = if (flavorName.isEmpty()) {
                "${rootProject.name}-${buildType.name}-$versionName.apk"
            } else {
                "${rootProject.name}-$flavorName-${buildType.name}-$versionName.apk"
            }
        }
    }
}
