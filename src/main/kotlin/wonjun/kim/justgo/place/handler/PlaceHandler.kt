package wonjun.kim.justgo.place.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.kotlin.core.publisher.toMono
import wonjun.kim.justgo.place.router.request.SuggestionRequest
import wonjun.kim.justgo.place.service.PlaceService

@Component
class PlaceHandler(private val placeService: PlaceService) {

    fun handleSuggestion(serverRequest: ServerRequest) =
        SuggestionRequest.from(serverRequest.queryParams().toSingleValueMap()).toMono()
            .flatMap {
                placeService.findPlaceByPositionAndTag(it.departLat, it.departLng, it.maxDistance, it.tags)
            }
            .flatMap(ServerResponse.ok()::bodyValue)
            .onErrorResume { ServerResponse.status(500).build() }

}
