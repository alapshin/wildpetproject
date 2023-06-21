plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

application {
   mainClass.set("com.alapshin.multiplayground.ApplicationKt")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(libs.logback)
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