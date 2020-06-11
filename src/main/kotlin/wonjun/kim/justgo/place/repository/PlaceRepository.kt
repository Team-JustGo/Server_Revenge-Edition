package wonjun.kim.justgo.place.repository

import org.springframework.data.geo.Distance
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import wonjun.kim.justgo.place.entity.Place

@Repository
interface PlaceRepository : ReactiveMongoRepository<Place, String> {

    fun findByLocationNear(point: Point, distance: Distance? = null): Flux<Place>

}