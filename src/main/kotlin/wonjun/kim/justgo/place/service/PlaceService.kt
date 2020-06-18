package wonjun.kim.justgo.place.service

import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point
import org.springframework.stereotype.Component
import wonjun.kim.justgo.getDistanceByLatLon
import wonjun.kim.justgo.place.repository.PlaceRepository
import wonjun.kim.justgo.place.router.response.PlaceResponse

@Component
class PlaceService(private val placeRepository: PlaceRepository) {

    fun findPlaceByPositionAndTag(departLat: Double, departLng: Double, maxDistance: Int, tags: List<String>?) =
        placeRepository
            .findByLocationNearAndTagsIn(
                Point(departLng, departLat), Distance(maxDistance.toDouble() / 1000, Metrics.KILOMETERS), tags
            )
            .map { PlaceResponse(it.id, it.tags, getDistanceByLatLon(it.location.y, it.location.x, departLat, departLng)) }
            .collectList()
}
