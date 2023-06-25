import com.alapshin.multiplayground.test.createDefaultClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        val client = createDefaultClient()
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}
