plugins {
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.shared)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}