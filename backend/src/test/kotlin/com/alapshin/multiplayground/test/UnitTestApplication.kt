package com.alapshin.multiplayground.test

import io.ktor.server.config.ApplicationConfig
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.routing
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication

fun unitTestApplication(block: suspend ApplicationTestBuilder.() -> Unit) {
    testApplication {
        environment {
            config = ApplicationConfig("application-test.yaml")
        }
//        externalServices {
//            hosts("http:/0.0.0.0:8080/.well-known/") {
//                routing {
//                    staticResources("/.well-known/", "well-known")
//                }
//            }
//        }
        block()
    }
}
