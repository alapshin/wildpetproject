package com.alapshin.multiplayground.auth

import com.alapshin.multiplayground.auth.model.Credentials
import com.alapshin.multiplayground.test.createDefaultClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

class AuthTest {
    @Test
    fun testLogin() = testApplication {
        val client = createDefaultClient()
        client.post("/auth/register/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        val response = client.post("/auth/login/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testRegister() = testApplication {
        val client = createDefaultClient()
        val response = client.post("/auth/register/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }
}
