package wonjun.kim.justgo.navigation.router

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class NavigationRouter {

    @Bean
    fun getRouter() = router {
        "/navigation".nest {
            GET("/route")
        }
    }

}