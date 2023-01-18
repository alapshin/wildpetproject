rootProject.name = "MultiPlayground"

include(":shared")
include(":androidApp")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("dependencies") {
            version("agp", "7.4.0")
            version("kotlin", "1.8.0")
            version("detekt", "1.22.0")
            plugin("detekt", "io.gitlab.arturbosch.detekt").versionRef("detekt")
            plugin("library", "com.android.library").versionRef("agp")
            plugin("application", "com.android.application").versionRef("agp")
            plugin("kotlin", "org.jetbrains.kotlin.android").versionRef("kotlin")
            plugin("multiplatform", "org.jetbrains.kotlin.multiplatform").versionRef("kotlin")
        }
    }
}
