package com.alapshin.multiplayground.config

import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
@Suppress("UnnecessaryAbstractClass")
abstract class ConfigComponent(@get:Provides val config: ApplicationConfig)
