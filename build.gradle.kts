import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.gitversioning)
    alias(libs.plugins.versions.check)
    alias(libs.plugins.versions.update)

    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.ktorfit).apply(false)
    alias(libs.plugins.buildkonfig).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.parcelize).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.jetbrains.compose).apply(false)
    alias(libs.plugins.molecule).apply(false)
}

tasks.register("detektAll") {
    allprojects {
        this@register.dependsOn(tasks.withType<Detekt>())
    }
}

allprojects {
    apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)

    detekt {
        autoCorrect = true
        buildUponDefaultConfig = true
    }

    dependencies {
        detektPlugins(rootProject.libs.detekt.formatting)
    }

    tasks.withType<Detekt>().configureEach {
        setSource(files(project.projectDir))
        exclude("**/build/**")
        exclude {
            it.file.relativeTo(projectDir).startsWith(project.buildDir.relativeTo(projectDir))
        }
    }
}

versionCatalogUpdate {
    sortByKey.set(false)
}

tasks.withType<DependencyUpdatesTask>().configureEach {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

gitVersioning.apply {
    // Optional fallback configuration in case of no matching ref configuration
    rev {
        version = "\${describe.tag.version}-\${describe.distance}-\${commit.short}\${dirty}"
    }
}
