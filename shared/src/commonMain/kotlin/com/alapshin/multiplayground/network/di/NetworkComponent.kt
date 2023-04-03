package com.alapshin.multiplayground.network.di

import com.alapshin.multiplayground.BuildKonfig
import com.alapshin.multiplayground.network.KermitKtorLogger
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Component
@NetworkScope
interface NetworkComponent {
    @Provides
    @NetworkScope
    fun json(): Json {
        return Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }

    @Provides
    fun logger(): Logger = KermitKtorLogger

    @Provides
    @NetworkScope
    fun httpClient(json: Json, logger: Logger): HttpClient {
        return HttpClient(CIO) {
            expectSuccess = true
            developmentMode = true
            install(Logging) {
                this.level = LogLevel.ALL
                this.logger = logger
            }
            install(ContentNegotiation) {
                json(json)
            }
            install(DefaultRequest) {
                url(BuildKonfig.API_URL)
                contentType(ContentType.Application.Json)
            }
        }
    }

    @Provides
    @NetworkScope
    fun ktorfit(httpClient: HttpClient): Ktorfit {
        return Ktorfit.Builder()
            .httpClient(httpClient)
            .baseUrl(BuildKonfig.API_URL)
            .build()
    }
}

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
private annotation class NetworkScope
