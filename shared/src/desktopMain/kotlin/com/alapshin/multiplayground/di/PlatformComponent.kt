package com.alapshin.multiplayground.di

import io.kamel.core.config.KamelConfig
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
actual abstract class PlatformComponent {
    abstract val kamelConfig: KamelConfig

    @Provides
    fun kamelConfig(): KamelConfig {
        return KamelConfig {
            takeFrom(KamelConfig.Default)
        }
    }
}
