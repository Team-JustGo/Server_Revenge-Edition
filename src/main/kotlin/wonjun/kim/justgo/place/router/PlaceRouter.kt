package wonjun.kim.justgo.place.router

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router
import wonjun.kim.justgo.place.handler.PlaceHandler

@Component
class PlaceRouter(private val placeHandler: PlaceHandler) {
    @Bean
    fun getRouter() = router {
        "/place".nest {
            GET("/suggestion", placeHandler::handleSuggestion)
        }
    }
}
