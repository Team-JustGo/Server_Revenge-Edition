package wonjun.kim.justgo.place.router.response

data class PlaceResponse(
    val id: String,
    val tags: List<String>,
    val distance: Double
)
