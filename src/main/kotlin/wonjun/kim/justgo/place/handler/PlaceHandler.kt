package wonjun.kim.justgo.place.handler

import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import wonjun.kim.justgo.getDistanceByLatLon
import wonjun.kim.justgo.place.repository.PlaceRepository
import wonjun.kim.justgo.place.router.request.SuggestionRequest
import wonjun.kim.justgo.place.router.response.PlaceResponse

@Component
class PlaceHandler(val placeRepository: PlaceRepository) {
    fun handleSuggestion(serverRequest: ServerRequest): Mono<ServerResponse> {
        val suggestionRequest = SuggestionRequest.from(serverRequest.queryParams().toSingleValueMap())

        return suggestionRequest.toMono()
            .flatMapMany { request ->
                placeRepository.findByLocationNear(
                    Point(request.departLng, request.departLat),
                    Distance(request.maxDistance?.toDouble() ?: 5000.0 / 1000, Metrics.KILOMETERS)
                )
            }.map {
                val distance = getDistanceByLatLon(
                    it.location.x,
                    it.location.y,
                    suggestionRequest.departLat,
                    suggestionRequest.departLng
                )

                return@map PlaceResponse(it.id, it.tags, distance)
            }.collectList()
            .flatMap { ServerResponse.ok().bodyValue(it) }
    }
}
