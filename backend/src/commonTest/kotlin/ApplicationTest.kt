import com.alapshin.multiplayground.auth.model.Credentials
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testLogin() = testApplication {
        val client = createClient {
            this.install(DefaultRequest) {
                contentType(ContentType.Application.Json)
            }
            this.install(ContentNegotiation) {
                json()
            }
        }
        val response = client.post("/login") {
            setBody(Credentials(username = "john", password = "foobar"))
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }
}
