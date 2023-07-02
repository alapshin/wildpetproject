package com.alapshin.multiplayground.user

import com.alapshin.multiplayground.auth.model.Credentials
import com.alapshin.multiplayground.test.createDefaultClient
import com.alapshin.multiplayground.test.unitTestApplication
import com.alapshin.multiplayground.user.model.User
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
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
        val response = client.get("/users/${user1.userId}/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(user1, response.body())
    }
}