package wonjun.kim.justgo.place.entity

import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.mapping.Document

@Document("places")
data class Place(
    val id: String,
    val name: String,
    val tags: List<String> = emptyList(),
    val location: GeoJsonPoint
)
