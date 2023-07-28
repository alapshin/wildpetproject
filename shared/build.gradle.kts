import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    alias(libs.plugins.ksp)

    alias(libs.plugins.ktorfit)
    alias(libs.plugins.molecule)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.mokoresources)

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
                implementation(compose.uiTooling)

                api(libs.mokoresources)
                api(libs.mokoresources.compose)

                implementation(libs.kamel)
                implementation(libs.decompose.core)
                implementation(libs.decompose.extensions)
                implementation(libs.essenty.parcelable)
                implementation(libs.markdown)
                implementation(libs.molecule)

                implementation(libs.kermit)
                implementation(libs.kotlininject.runtime)
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
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}

android {
    namespace = "com.alapshin.multiplayground"
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

buildkonfig {
    packageName = "com.alapshin.multiplayground"
    defaultConfigs {
        buildConfigField(Type.STRING, "API_URL", "http://192.168.2.107:8080/", const = true)
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.alapshin.multiplayground"
}

dependencies {
    add("kspDesktop", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspCommonMainMetadata", libs.ktorfit.ksp)

    add("kspDesktop", libs.kotlininject.ksp)
    add("kspAndroid", libs.kotlininject.ksp)
    add("kspCommonMainMetadata", libs.kotlininject.ksp)
}
