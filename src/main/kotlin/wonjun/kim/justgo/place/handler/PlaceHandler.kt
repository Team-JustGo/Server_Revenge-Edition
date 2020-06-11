package wonjun.kim.justgo.place.handler

import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.kotlin.core.publisher.toMono
import wonjun.kim.justgo.getDistanceByLatLon
import wonjun.kim.justgo.place.repository.PlaceRepository
import wonjun.kim.justgo.place.router.request.SuggestionRequest
import wonjun.kim.justgo.place.router.response.PlaceResponse

@Component
class PlaceHandler(val placeRepository: PlaceRepository) {

    fun handleSuggestion(serverRequest: ServerRequest) =
        SuggestionRequest.from(serverRequest.queryParams().toSingleValueMap()).toMono()
            .flatMap(::findLocation)
            .flatMap(ServerResponse.ok()::bodyValue)
            .onErrorResume { ServerResponse.status(500).build() }

    private fun findLocation(request: SuggestionRequest) =
        placeRepository.findByLocationNear(
            Point(request.departLng, request.departLat),
            Distance(request.maxDistance.toDouble() / 1000, Metrics.KILOMETERS)
        ).map {
            PlaceResponse(
                it.id, it.tags,
                getDistanceByLatLon(it.location.y, it.location.x, request.departLat, request.departLng)
            )
        }.collectList()

}
