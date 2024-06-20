import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.gitversioning)
    alias(libs.plugins.versions.check)
    alias(libs.plugins.versions.update)

    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.ktorfit).apply(false)
    alias(libs.plugins.buildkonfig).apply(false)

    alias(libs.plugins.gradle.cacheFix).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.compose).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.parcelize).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.jetbrains.compose).apply(false)
}

allprojects {
    // Set JVM target for compile tasks
    tasks.withType<JavaCompile> {
        targetCompatibility = JavaVersion.VERSION_11.toString()
    }
    // Using task option instead of jvmToolchain property because it allows to use already
    // installed JDK even if JDK version is greater than target version
    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)
    detekt {
        autoCorrect = true
        buildUponDefaultConfig = true
    }

    tasks.register("detektAll") {
        allprojects {
            this@register.dependsOn(tasks.withType<Detekt>())
        }
    }
    tasks.withType<Detekt>().configureEach {
        setSource(files(project.projectDir))
        exclude("**/build/**")
        exclude {
            it.file.relativeTo(projectDir).startsWith(project.buildDir.relativeTo(projectDir))
        }
    }

    apply(plugin = rootProject.libs.plugins.ktlint.get().pluginId)
    ktlint {
        version.set(rootProject.libs.versions.ktlint.get())
        filter {
            exclude {
                it.file.relativeTo(projectDir)
                    .startsWith(project.buildDir.relativeTo(projectDir))
            }
        }
        reporters {
            reporter(ReporterType.HTML)
            reporter(ReporterType.PLAIN)
        }
    }
}

versionCatalogUpdate {
    sortByKey.set(false)
    keep {
        keepUnusedVersions.set(true)
    }
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
