import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktor)
    alias(libs.plugins.sqldelight)

    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
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

dependencies {
    implementation(libs.logback)

    implementation(libs.jjwt.api)
    runtimeOnly(libs.jjwt.impl)
    runtimeOnly(libs.jjwt.json)

    implementation(libs.sqldelight.driver.jvm)

    implementation(libs.bundles.ktor.server)

    ksp(libs.kotlininject.ksp)
    implementation(libs.kotlininject.runtime)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.ktor.server.test)
    testImplementation(libs.ktor.client.content)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
