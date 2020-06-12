package wonjun.kim.justgo.navigation.router.request

import wonjun.kim.justgo.common.exception.BadRequestException

data class RouteRequest(
    val departLat: Double,
    val departLng: Double,
    val arrivalPlaceId: String
) {
    companion object {

        fun from(requestMap: Map<String, String>): RouteRequest {
            try {
                val departLat = requestMap.getValue("departLat").toDouble()
                val departLng = requestMap.getValue("departLng").toDouble()
                val arrivalPlaceId = requestMap.getValue("arrivalPlaceId")

                require(departLat in 0.0..180.0)
                require(departLng in 0.0..180.0)

                return RouteRequest(departLat, departLng, arrivalPlaceId)
            } catch (e: Exception) {
                throw BadRequestException()
            }
        }

    }
}
