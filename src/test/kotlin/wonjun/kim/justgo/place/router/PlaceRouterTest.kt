package wonjun.kim.justgo.place.router

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.*
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
            .consumeWith<WebTestClient.ListBodySpec<PlaceResponse>>(
                document(
                    "placeRouter/200",
                    preprocessResponse(prettyPrint()),
                    relaxedRequestParameters(
                        parameterWithName("departLat").description("출발지의 위도"),
                        parameterWithName("departLng").description("출발지의 경도"),
                        parameterWithName("maxDistance").description("최대 거리 (미터)"),
                        parameterWithName("tags").description("태그 ( , 로 구분)").optional()
                    ),
                    responseFields(
                        fieldWithPath("[].id").type(JsonFieldType.STRING).description("장소 유니크 아이디"),
                        fieldWithPath("[].tags").type(JsonFieldType.ARRAY).description("장소 태그"),
                        fieldWithPath("[].distance").type(JsonFieldType.NUMBER).description("출발점으로 부터 장소까지의 거리")
                    )
                )
            )
    }
}
