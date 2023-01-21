import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.versions.check)
    alias(libs.plugins.versions.update)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.jetbrains.compose).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

versionCatalogUpdate {
    sortByKey.set(false)
}

tasks.withType<DependencyUpdatesTask>().configureEach {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}