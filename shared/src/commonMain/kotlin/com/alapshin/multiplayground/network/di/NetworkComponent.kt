package com.alapshin.multiplayground.network.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
interface NetworkComponent {
    @Provides
    fun httpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}