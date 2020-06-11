package wonjun.kim.justgo.navigation.router.handler

interface NavigationRepository {
    fun getDrivingDirection(departLat: Double, departLng: Double, arrivalLat: Double, arrivalLng: Double)
    fun getTransitDirection(departLat: Double, departLng: Double, arrivalLat: Double, arrivalLng: Double)
    fun getWalkingDirection(departLat: Double, departLng: Double, arrivalLat: Double, arrivalLng: Double)
}
