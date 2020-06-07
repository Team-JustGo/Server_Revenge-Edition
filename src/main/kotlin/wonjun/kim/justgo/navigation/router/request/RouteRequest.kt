package wonjun.kim.justgo.navigation.router.request

data class RouteRequest(
    val departLat: Double,
    val departLng: Double,
    val arrivalPlaceId: String
)