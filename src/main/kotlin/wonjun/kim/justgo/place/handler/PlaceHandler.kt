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

    /*private fun findLocation(request: SuggestionRequest) =
        placeRepository.findByLocationNear(
            Point(request.departLng, request.departLat),
            Distance(request.maxDistance.toDouble() / 1000, Metrics.KILOMETERS)
        ).map {
            PlaceResponse(
                it.id, it.tags,
                getDistanceByLatLon(it.location.y, it.location.x, request.departLat, request.departLng)
            )
        }.collectList()

    private fun findPlaceByPositionAndTag(departLat: Double, departLng: Double, maxDistance: Int, tags: List<String>) =
        placeRepository
            .findByLocationNear(Point(departLng, departLat), Distance(maxDistance.toDouble() / 1000, Metrics.KILOMETERS))
            .filter { it.tags.any { tags.contains(it) } }
            .map { PlaceResponse(it.id, it.tags, getDistanceByLatLon(it.location.y, it.location.x, departLat, departLng)) }
            .collectList()*/

}
