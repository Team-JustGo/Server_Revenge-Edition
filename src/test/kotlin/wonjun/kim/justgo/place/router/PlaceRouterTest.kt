package wonjun.kim.justgo.place.router

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.web.reactive.server.WebTestClient
import wonjun.kim.justgo.place.router.response.PlaceResponse

@SpringBootTest
class PlaceRouterTest {
    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp(applicationContext: ApplicationContext) {
        this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
            .configureClient()
            .build()
    }

    @Test
    fun `should get places by lat,lng,distance`() {
        webTestClient.get()
            .uri { builder ->
                builder.path("/place/suggestion")
                    .queryParam("departLat", 33.4)
                    .queryParam("departLng", 126.8)
                    .queryParam("maxDistance", 10000)
                    .build()
            }
            .exchange()
            .expectStatus().isOk
            .expectBodyList(PlaceResponse::class.java)
            .hasSize(2)
    }
}
