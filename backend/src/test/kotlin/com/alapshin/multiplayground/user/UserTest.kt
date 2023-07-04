package com.alapshin.multiplayground.user

import com.alapshin.multiplayground.auth.presentation.Credentials
import com.alapshin.multiplayground.test.createDefaultClient
import com.alapshin.multiplayground.test.unitTestApplication
import com.alapshin.multiplayground.user.data.User
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UserTest {
    @Test
    fun testUser() = unitTestApplication {
        val client = createDefaultClient()
        val user1 = client.post("/auth/register/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }.body<User>()
        val loginResponse = client.post("/auth/login/") {
            setBody(Credentials(username = "john", password = "12345678"))
        }
        val token = loginResponse.headers[HttpHeaders.Authorization]
        val response = client.get("/users/${user1.userId}/") {
            header(HttpHeaders.Authorization, "Bearer $token")
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun verifyThatUserRequireAuth() = unitTestApplication {
        val client = createDefaultClient()
        val response = client.get("/users/1/")
        assertEquals(HttpStatusCode.Unauthorized, response.status)
    }
}
