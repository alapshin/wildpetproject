import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.example.multiplayground.android"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.example.multiplayground.android"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionName = project.version.toString()
        versionCode = Versioning.version(rootProject.version.toString()).code()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
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

dependencies {
    implementation(projects.shared)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.decompose.core)
}
