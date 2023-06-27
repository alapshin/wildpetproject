import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.ksp)
    alias(libs.plugins.ktor)
    alias(libs.plugins.sqldelight)
}

application {
    mainClass.set("com.alapshin.multiplayground.ApplicationKt")
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.alapshin.multiplayground.db")
        }
    }
}

@Suppress("UnusedPrivateProperty")
@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    // Enable the default target hierarchy:
    // https://kotlinlang.org/docs/whatsnew1820.html#new-approach-to-source-set-hierarchy
    targetHierarchy.default()

    jvm {
        withJava()
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(libs.logback)
                implementation(libs.sqldelight.driver.jvm)
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.ktor.server)
                implementation(libs.kotlininject.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.ktor.server.test)
                implementation(libs.ktor.client.content)
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    add("kspJvm", libs.kotlininject.ksp)
    add("kspCommonMainMetadata", libs.kotlininject.ksp)
}
