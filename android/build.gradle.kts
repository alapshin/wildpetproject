import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
}

@Suppress("UnusedPrivateProperty")
kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(projects.shared)
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(libs.androidx.compose.compiler.get().toString())
}

android {
    namespace = "com.alapshin.multiplayground.android"
    compileSdk = Versions.compileSdk

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        applicationId = "com.alapshin.multiplayground.android"
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
