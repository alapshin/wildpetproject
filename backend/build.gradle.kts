plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

application {
    mainClass.set("com.alapshin.multiplayground.ServerKt")
}

dependencies {
    implementation(libs.logback)
    implementation(libs.bundles.ktor.server)
}
