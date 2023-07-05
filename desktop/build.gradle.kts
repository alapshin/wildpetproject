plugins {
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm {
        withJava()
    }
    @Suppress("UnusedPrivateMember")
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.shared)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(libs.androidx.compose.compiler.get().toString())

    desktop {
        application {
            mainClass = "MainKt"
        }
    }
}
