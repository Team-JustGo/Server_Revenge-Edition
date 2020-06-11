package wonjun.kim.justgo

import kotlin.math.*

fun getDistanceByLatLon(lat1: Double, lon1: Double, lat2: Double,
                        lon2: Double): Double {
    val R = 6371 // Radius of the earth
    val latDistance = Math.toRadians(lat2 - lat1)
    val lonDistance = Math.toRadians(lon2 - lon1)
    val a = (sin(latDistance / 2) * sin(latDistance / 2)
        + (cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2))
        * sin(lonDistance / 2) * sin(lonDistance / 2)))
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return R * c * 1000
}