package com.alapshin.multiplayground.auth

import com.alapshin.multiplayground.auth.presentation.Credentials
import com.alapshin.multiplayground.test.createDefaultClient
import com.alapshin.multiplayground.test.unitTestApplication
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AuthTest {
    @Test
    fun `Should allow to login with existing user`() = unitTestApplication {
        val client = createDefaultClient()
        client.post("/auth/register/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        val response = client.post("/auth/login/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertTrue(response.headers.contains(HttpHeaders.Authorization))
    }

    @Test
    fun `Should allow to register new user`() = unitTestApplication {
        val client = createDefaultClient()
        val response = client.post("/auth/register/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Should not allow to register user if username already exists`() = unitTestApplication {
        val client = createDefaultClient()
        client.post("/auth/register/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        val response = client.post("/auth/register/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        assertEquals(HttpStatusCode.Conflict, response.status)
    }
}
