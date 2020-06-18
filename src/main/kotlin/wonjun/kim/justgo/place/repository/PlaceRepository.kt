package wonjun.kim.justgo.place.repository

import org.springframework.data.geo.Distance
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import wonjun.kim.justgo.place.entity.Place

@Repository
interface PlaceRepository : ReactiveMongoRepository<Place, String> {

    fun findByLocationNearAndTagsIn(point: Point, distance: Distance, tags: List<String>?): Flux<Place>

}
