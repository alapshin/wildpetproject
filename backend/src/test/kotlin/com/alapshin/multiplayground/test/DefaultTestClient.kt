package com.alapshin.multiplayground.test

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.ApplicationTestBuilder

fun ApplicationTestBuilder.createDefaultClient(): HttpClient {
    return createClient {
        this.install(ContentNegotiation) {
            json()
        }
        this.install(DefaultRequest) {
            contentType(ContentType.Application.Json)
        }
    }
}
