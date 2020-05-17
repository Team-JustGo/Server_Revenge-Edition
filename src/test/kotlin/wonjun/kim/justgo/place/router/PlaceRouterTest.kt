package wonjun.kim.justgo.place.router

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document
import org.springframework.test.web.reactive.server.WebTestClient
import wonjun.kim.justgo.place.router.response.PlaceResponse

@SpringBootTest
@ExtendWith(RestDocumentationExtension::class)
class PlaceRouterTest {
    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp(applicationContext: ApplicationContext, restDocumentation: RestDocumentationContextProvider) {
        this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
            .configureClient()
            .filter(WebTestClientRestDocumentation.documentationConfiguration(restDocumentation))
            .build()
    }

    @Test
    fun `should return bad request by unexpected params`() {
        webTestClient.get()
            .uri("/place/suggestion")
            .exchange()
            .expectStatus().isBadRequest
            .expectBody()
            .consumeWith(document("placeRouter/400"))
    }

    @Test
    fun `should return places by lat,lng,distance`() {
        webTestClient.get()
            .uri { builder ->
                builder.path("/place/suggestion")
                    .queryParam("departLat", 33.4)
                    .queryParam("departLng", 126.8)
                    .queryParam("maxDistance", 20000)
                    .build()
            }
            .exchange()
            .expectStatus().isOk
            .expectBodyList(PlaceResponse::class.java)
            .hasSize(2)
            .consumeWith<WebTestClient.ListBodySpec<PlaceResponse>>(document("placeRouter/200"))
    }
}
