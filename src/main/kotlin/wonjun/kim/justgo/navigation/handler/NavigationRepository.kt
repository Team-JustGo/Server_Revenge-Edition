package wonjun.kim.justgo.navigation.handler

import java.util.*

interface NavigationRepository {
    fun getDrivingDirection(departLat: Double, departLng: Double, arrivalLat: Double, arrivalLng: Double, lang: Locale = Locale.KOREA)
    fun getTransitDirection(departLat: Double, departLng: Double, arrivalLat: Double, arrivalLng: Double, lang: Locale = Locale.KOREA)
    fun getWalkingDirection(departLat: Double, departLng: Double, arrivalLat: Double, arrivalLng: Double, lang: Locale = Locale.KOREA)
}
