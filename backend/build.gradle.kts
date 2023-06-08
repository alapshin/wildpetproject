plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

java {
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("com.alapshin.multiplayground.ApplicationKt")
}

dependencies {
    implementation(libs.logback)
    implementation(libs.bundles.ktor.server)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.ktor.server.test)
    testImplementation(libs.ktor.client.content)
}
