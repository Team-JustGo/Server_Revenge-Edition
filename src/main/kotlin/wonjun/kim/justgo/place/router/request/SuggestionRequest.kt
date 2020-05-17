package wonjun.kim.justgo.place.router.request

import wonjun.kim.justgo.common.exception.BadRequestException

data class SuggestionRequest(
    val departLat: Double,
    val departLng: Double,
    val tags: List<String>?,
    val maxDistance: Int
) {
    companion object {
        fun from(map: Map<String, String>): SuggestionRequest  {
            try {
                val departLat = map.getValue("departLat").toDouble()
                val departLng = map.getValue("departLng").toDouble()
                val tags: List<String>? = map["tags"]?.split(',')
                val maxDistance = map["maxDistance"]?.toInt()

                require(departLat in 0.0..180.0)
                require(departLng in 0.0..180.0)

                return SuggestionRequest(departLat, departLng, tags, maxDistance ?: 5000)
            } catch (e: Exception) {
                throw BadRequestException()
            }
        }
    }
}

/*
    * "departLat": 36.111111,
    * "departLng": 36.111111,
    * "tags": ["something", "something"],
    * "maxDistance" : 5000 // meter
    * */
