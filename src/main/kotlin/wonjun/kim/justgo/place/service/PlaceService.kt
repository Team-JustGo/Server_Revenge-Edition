package wonjun.kim.justgo.place.service

import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point
import org.springframework.stereotype.Component
import wonjun.kim.justgo.getDistanceByLatLon
import wonjun.kim.justgo.place.repository.PlaceRepository
import wonjun.kim.justgo.place.router.request.SuggestionRequest
import wonjun.kim.justgo.place.router.response.PlaceResponse

@Component
class PlaceService(val placeRepository: PlaceRepository) {

    fun findLocation(request: SuggestionRequest) =
        placeRepository.findByLocationNear(
            Point(request.departLng, request.departLat),
            Distance(request.maxDistance.toDouble() / 1000, Metrics.KILOMETERS)
        )

}
