plugins {
    alias(dependencies.plugins.detekt)
    alias(dependencies.plugins.library).apply(false)
    alias(dependencies.plugins.application).apply(false)
    alias(dependencies.plugins.kotlin).apply(false)
    alias(dependencies.plugins.multiplatform).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
